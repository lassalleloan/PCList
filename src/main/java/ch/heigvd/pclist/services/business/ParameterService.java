package ch.heigvd.pclist.services.business;

import ch.heigvd.pclist.services.dao.CpuDAOLocal;
import ch.heigvd.pclist.services.dao.GpuDAOLocal;
import ch.heigvd.pclist.services.dao.PcDAOLocal;
import ch.heigvd.pclist.services.dao.RamDAOLocal;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
@Singleton
public class ParameterService implements ParameterServiceLocal {

    @EJB
    private PcDAOLocal pcDAO;

    @EJB
    private CpuDAOLocal cpuDAO;

    @EJB
    private RamDAOLocal ramDAO;

    @EJB
    private GpuDAOLocal gpuDAO;

    public String getString(HttpServletRequest req, String s, List<String> stringList) {
        String string = req.getParameter(s);
        return string == null || !stringList.contains(string) ? "" : string;
    }

    public long getUnsignedLong(HttpServletRequest req, String parameter) {
        long value;

        try {
            value = Long.valueOf(req.getParameter(parameter));
        } catch (NumberFormatException e) {
            value = 0;
        }

        return value <= -1 ? 0 : value;
    }

    public String getProduct(HttpServletRequest req) {
        return getString(req, "product", PRODUCT_LIST);
    }

    public String getPageTitle(String product) {
        String pageTitle = "All";

        switch (product) {
            case "pc":
                pageTitle = "PC";
                break;

            case "cpu":
                pageTitle = "Processor";
                break;

            case "ram":
                pageTitle = "Memory";
                break;

            case "gpu":
                pageTitle = "Graphic";
                break;
        }

        return pageTitle;
    }

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

    public long getNumberPages(String product, long pageSize, long pageIndex) {
        long numberProduct = 0;

        switch (product) {
            case "pc":
                numberProduct = pcDAO.count();
                break;

            case "cpu":
                numberProduct = cpuDAO.count();
                break;

            case "ram":
                numberProduct = ramDAO.count();
                break;

            case "gpu":
                numberProduct = gpuDAO.count();
                break;
        }

        return numberProduct <= 0 ? 0 : (numberProduct + pageSize - 1) / pageSize;
    }

    public void setPageTitle(HttpServletRequest req) {
        req.setAttribute("pageTitle", getPageTitle(getProduct(req)));
    }

    public void setProductList(HttpServletRequest req) {

        // Gets type of product
        String product = getProduct(req);

        // Gets page size, page index for pagination and number of pages
        long pageSize = getPageSize(req);
        long pageIndex = getUnsignedLong(req, "pageIndex");

        boolean isAllList = product.isEmpty();

        if (isAllList || product.equals("pc")) {
            req.setAttribute("pcList", pcDAO.get());
        }

        if (isAllList || product.equals("cpu")) {
            req.setAttribute("cpuList", cpuDAO.get(pageSize, pageIndex));
        }

        if (isAllList || product.equals("ram")) {
            req.setAttribute("ramList", ramDAO.get(pageSize, pageIndex));
        }

        if (isAllList || product.equals("gpu")) {
            req.setAttribute("gpuList", gpuDAO.get(pageSize, pageIndex));
        }

        req.setAttribute("allList", isAllList);
    }

    public void setProductBrandList(HttpServletRequest req) {

        switch (getProduct(req)) {
            case "pc":
                // TODO: 07.10.2017 create action for pc
//                    req.setAttribute("pcBrandList", pcDAO.getBrand());
//                    req.setAttribute("cpuList", cpuDAO.get());
//                    req.setAttribute("ramList", ramDAO.get());
//                    req.setAttribute("gpuList", gpuDAO.get());
                break;

            case "cpu":
                req.setAttribute("cpuBrandList", cpuDAO.getBrand());
                break;

            case "ram":
                req.setAttribute("ramBrandList", ramDAO.getBrand());
                break;

            case "gpu":
                req.setAttribute("gpuBrandList", gpuDAO.getBrand());
                break;
        }
    }

    public void setInformationsMessage(HttpServletRequest req) {

        // Gets type of product and product ID
        String product = getProduct(req);
        long id = getUnsignedLong(req, "id");

        long rowsAffected = 0;
        String pageTitle = getPageTitle(product);

        if (id > 0) {
            switch (product) {
                case "pc":
                    // TODO: 07.10.2017 delete action for pc
//                        rowsAffected = pcDAO.delete(id);
                    break;

                case "cpu":
                    rowsAffected = cpuDAO.delete(id);
                    break;

                case "ram":
                    rowsAffected = ramDAO.delete(id);
                    break;

                case "gpu":
                    rowsAffected = gpuDAO.delete(id);
                    break;
            }
        }

        if (rowsAffected <= 0) {
            req.setAttribute("informationsMessage", "Incorrect " + pageTitle + " ID");
        } else {
            req.setAttribute("informationsMessage", rowsAffected + " " + pageTitle + " was deleted");
        }
    }

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
