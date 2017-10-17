package ch.heigvd.pclist.services.business;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
@Singleton
public class JspService implements JspServiceLocal {

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
        return getString(req, "product", productService.PRODUCT_LIST);
    }

    @Override
    public long getPageSize(HttpServletRequest req) {
        long pageSize = getUnsignedLong(req, "pageSize");

        if (pageSize <= 0) {
            pageSize = getProduct(req).isEmpty() ? PAGE_SIZE_ALL_LIST : PAGE_SIZE_PRODUCT_LIST;
        }

        return pageSize;
    }

    @Override
    public long getNumberPages(String product, long pageSize, long pageIndex) {
        return (productService.count(product) + pageSize - 1) / pageSize;
    }

    @Override
    public void setPageTitle(HttpServletRequest req) {
        req.setAttribute("pageTitle", TITLE_STRING_MAP.get(req.getServletPath()) + NAME_PRODUCT_STRING_MAP.get(getProduct(req)));
    }

    @Override
    public void setHeaderTitle(HttpServletRequest req) {
        req.setAttribute("headerTitle", "Number of " + NAME_PRODUCT_STRING_MAP.get(getProduct(req)));
    }

    @Override
    public void setList(HttpServletRequest req) {
        String product = getProduct(req);
        Map<String, Object> objectMap = new HashMap<>();

        long pageSize = getPageSize(req);
        long pageIndex = getUnsignedLong(req, "pageIndex");

        boolean isAllList = product.isEmpty();

        if (isAllList || product.equals("pc")) {
            String col = getString(req, "col", Arrays.asList("brand", "price", "cpuBrand", "cpuCores", "cpuFrequency", "ramBrand", "ramSize", "gpuBrand"));
            String order = getString(req, "order", Arrays.asList("ASC", "DESC"));
            objectMap.putAll(productService.get("pc", col, order, pageSize, pageIndex));
        }

        if (isAllList || product.equals("cpu")) {
            //String like = getString(req, "like", Arrays.asList("brand LIKE 'Int%'", "cores", "frequency"));
            String col = getString(req, "col", Arrays.asList("brand", "cores", "frequency"));
            String order = getString(req, "order", Arrays.asList("ASC", "DESC"));
            objectMap.putAll(productService.get("cpu", col, order, pageSize, pageIndex));
        }

        if (isAllList || product.equals("ram")) {
            String col = getString(req, "col", Arrays.asList("brand", "size"));
            String order = getString(req, "order", Arrays.asList("ASC", "DESC"));
            objectMap.putAll(productService.get("ram", col, order, pageSize, pageIndex));
        }

        if (isAllList || product.equals("gpu")) {
            String col = getString(req, "col", Arrays.asList("brand"));
            String order = getString(req, "order", Arrays.asList("ASC", "DESC"));
            objectMap.putAll(productService.get("gpu", col, order, pageSize, pageIndex));
        }

        req.setAttribute("allList", isAllList);

        for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
            req.setAttribute(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void setOrder(HttpServletRequest req) {
        String order = req.getParameter("order");
        if (order == null) {
            order = "DESC";
        }
        req.setAttribute("order", order);
    }

    @Override
    public void setProductList(HttpServletRequest req) {
        setAttribute(req, productService.getComponent(getProduct(req)));
    }

    @Override
    public void setProductComponent(HttpServletRequest req) {
        if (!"/create".equals(req.getServletPath())) {
            setAttribute(req, productService.get(getProduct(req), getUnsignedLong(req, "id")));
        }
    }

    @Override
    public void setProductBrandList(HttpServletRequest req) {
        setAttribute(req, productService.getBrand(getProduct(req)));
    }

    @Override
    public void setInformationMessage(HttpServletRequest req, String informationMessage) {
        req.setAttribute("informationMessage", informationMessage);
    }

    @Override
    public void setPageLinks(HttpServletRequest req) {
        String product = getProduct(req);

        long pageSize = getPageSize(req);
        long pageIndex = getUnsignedLong(req, "pageIndex");
        long numberOfPages = getNumberPages(product, pageSize, pageIndex);

        numberOfPages = numberOfPages <= 0 ? 1 : numberOfPages;

        req.setAttribute("firstPageLink", "/list?product=" + product + "&pageSize=" + pageSize + "&pageIndex=0");
        req.setAttribute("previousPageLink", "/list?product=" + product + "&pageSize=" + pageSize + "&pageIndex=" + Math.max(0, pageIndex - 1));
        req.setAttribute("nextPageLink", "/list?product=" + product + "&pageSize=" + pageSize + "&pageIndex=" + Math.min(pageIndex + 1, numberOfPages - 1));
        req.setAttribute("lastPageLink", "/list?product=" + product + "&pageSize=" + pageSize + "&pageIndex=" + (numberOfPages - 1));
        req.setAttribute("pageCount", numberOfPages);
    }

    @Override
    public void setAttribute(HttpServletRequest req, Map<String, Object> objectMap) {
        for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
            req.setAttribute(entry.getKey(), entry.getValue());
        }
    }
}
