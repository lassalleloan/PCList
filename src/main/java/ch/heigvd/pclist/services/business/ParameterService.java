package ch.heigvd.pclist.services.business;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
@Singleton
public class ParameterService implements ParameterServiceLocal {

    @EJB
    private ProductServiceLocal productService;

    @Override
    public String getString(HttpServletRequest req, String s, List<String> stringList) {
        String string = req.getParameter(s);
        return string == null || !stringList.contains(string) ? "" : string;
    }

    @Override
    public int getUnsignedInteger(HttpServletRequest req, String parameter) {
        int value;

        try {
            value = Integer.valueOf(req.getParameter(parameter));
        } catch (NumberFormatException e) {
            value = 0;
        }

        return value <= -1 ? 0 : value;
    }

    @Override
    public long getUnsignedLong(HttpServletRequest req, String parameter) {
        long value;

        try {
            value = Long.valueOf(req.getParameter(parameter));
        } catch (NumberFormatException e) {
            value = 0;
        }

        return value <= -1 ? 0 : value;
    }

    @Override
    public double getUnsignedDouble(HttpServletRequest req, String parameter) {
        double value;

        try {
            value = Double.valueOf(req.getParameter(parameter));
        } catch (NumberFormatException e) {
            value = 0;
        }

        return value <= -1 ? 0 : value;
    }

    @Override
    public String getProduct(HttpServletRequest req) {
        return getString(req, "product", PRODUCT_LIST);
    }

    @Override
    public String getPageTitle(String action, String product) {
        String pageTitle = "";

        switch (action) {
            case "/list":
                pageTitle = "List of ";
                break;

            case "/create":
                pageTitle = "Create a ";
                break;

            case "/edit":
                pageTitle = "Edit a ";
                break;

            case "/configuration":
                pageTitle = "Configuration of ";
                break;
        }

        switch (product) {
            case "pc":
                pageTitle += "PC";
                break;

            case "cpu":
                pageTitle += "Processor";
                break;

            case "ram":
                pageTitle += "Memory";
                break;

            case "gpu":
                pageTitle += "Graphic";
                break;

            default:
                pageTitle += "All";
                break;
        }

        return pageTitle;
    }

    @Override
    public long getPageSize(HttpServletRequest req) {

        // Gets type of product
        String product = getProduct(req);

        long pageSize;

        try {
            pageSize = Long.parseLong(req.getParameter("pageSize"));
        } catch (NumberFormatException e) {
            pageSize = product.isEmpty() ? PAGE_SIZE_IS_ALL_LIST : PAGE_SIZE_IS_PRODUCT_LIST;
        }

        return pageSize <= 0 ? 1 : pageSize;
    }

    @Override
    public long getNumberPages(String product, long pageSize, long pageIndex) {
        return (productService.count(product) + pageSize - 1) / pageSize;
    }

    @Override
    public void setPageTitle(HttpServletRequest req) {
        req.setAttribute("pageTitle", getPageTitle(req.getServletPath(), getProduct(req)));
    }

    public void setProduct(HttpServletRequest req) {
        String product = getProduct(req);
        long id = getUnsignedLong(req, "id");
        Map<String, Object> objectMap = productService.get(product, id);

        for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
            req.setAttribute(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void setProductList(HttpServletRequest req) {

        // Gets type of product
        String product = getProduct(req);
        Map<String, Object> objectMap = new HashMap<>();

        // Gets page size, page index for pagination and number of pages
        long pageSize = getPageSize(req);
        long pageIndex = getUnsignedLong(req, "pageIndex");

        boolean isAllList = product.isEmpty();

        if (isAllList || product.equals("pc")) {
            objectMap.putAll(productService.get("pc", pageSize, pageIndex));
        }

        if (isAllList || product.equals("cpu")) {
            objectMap.putAll(productService.get("cpu", pageSize, pageIndex));
        }

        if (isAllList || product.equals("ram")) {
            objectMap.putAll(productService.get("ram", pageSize, pageIndex));
        }

        if (isAllList || product.equals("gpu")) {
            objectMap.putAll(productService.get("gpu", pageSize, pageIndex));
        }

        req.setAttribute("allList", isAllList);

        for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
            req.setAttribute(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void setProductBrandList(HttpServletRequest req) {

        // Gets product brand list
        String product = getProduct(req);

        Map<String, Object> objectMap = productService.getBrand(product);
        objectMap.putAll(productService.getComponent(product));

        for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
            req.setAttribute(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void setComponentList(HttpServletRequest req) {
        String product = getProduct(req);

        Map<String, Object> objectMap = productService.getComponent(product);

        for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
            req.setAttribute(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void setInformationMessage(HttpServletRequest req, String informationMessage) {
        req.setAttribute("informationMessage", informationMessage);
    }

    @Override
    public void setPageLinks(HttpServletRequest req) {

        // Gets type of product
        String product = getProduct(req);

        // Gets page size, page index for pagination and number of pages
        long pageSize = getPageSize(req);
        long pageIndex = getUnsignedLong(req, "pageIndex");
        long numberOfPages = getNumberPages(product, pageSize, pageIndex);

        req.setAttribute("firstPageLink", "/list?product=" + product + "&pageSize=" + pageSize + "&pageIndex=0");
        req.setAttribute("previousPageLink", "/list?product=" + product + "&pageSize=" + pageSize + "&pageIndex=" + Math.max(0, pageIndex - 1));
        req.setAttribute("nextPageLink", "/list?product=" + product + "&pageSize=" + pageSize + "&pageIndex=" + Math.min(pageIndex + 1, numberOfPages - 1));
        req.setAttribute("lastPageLink", "/list?product=" + product + "&pageSize=" + pageSize + "&pageIndex=" + (numberOfPages - 1));
        req.setAttribute("pageCount", numberOfPages);
    }
}
