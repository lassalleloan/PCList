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

    public String getProduct(HttpServletRequest req) {
        return getString(req, "product", PRODUCT_LIST);
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

    public long getPageSize(HttpServletRequest req, String product) {
        long pageSize;

        try {
            pageSize = Long.parseLong(req.getParameter("pageSize"));
        } catch (NumberFormatException e) {
            pageSize = product.isEmpty() ? PAGE_SIZE_IS_ALL_LIST : PAGE_SIZE_IS_PRODUCT_LIST;
        }

        return pageSize <= 0 ? 1 : pageSize;
    }

    public long getNumberPages(String product, long pageSize, long pageIndex) {
        long numberPages = 0;

        switch (product) {
            case "pc":
                numberPages = (pcDAO.count() + pageSize - 1) / pageSize;
                break;

            case "cpu":
                numberPages = (cpuDAO.count() + pageSize - 1) / pageSize;
                break;

            case "ram":
                numberPages = (ramDAO.count() + pageSize - 1) / pageSize;
                break;

            case "gpu":
                numberPages = (gpuDAO.count() + pageSize - 1) / pageSize;
                break;
        }

        return numberPages;
    }

    public void setPageTitle(HttpServletRequest req, String product) {
        req.setAttribute("pageTitle", getPageTitle(product));
    }

    public void setProductList(HttpServletRequest req, String product, long pageSize, long pageIndex) {

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

        setPageTitle(req, product);
        req.setAttribute("allList", isAllList);
    }

    public void setInformationsMessage(HttpServletRequest req, String product) {

        // Gets type of action and number of rows affected
        String action = getString(req, "action", ACTION_LIST);
        long rowsAffected = getUnsignedLong(req, "rowsAffected");

        if (!product.isEmpty() && !action.isEmpty() && rowsAffected > 0) {
            setPageTitle(req, product);
            req.setAttribute("action", action);
            req.setAttribute("rowsAffected", rowsAffected);
        }
    }

    public void setPageLinks(HttpServletRequest req, String product, long pageSize, long pageIndex) {
        long numberOfPages = getNumberPages(product, pageSize, pageIndex);

        req.setAttribute("firstPageLink", "/list?product=" + product + "&pageSize=" + pageSize + "&pageIndex=0");
        req.setAttribute("previousPageLink", "/list?product=" + product + "&pageSize=" + pageSize + "&pageIndex=" + Math.max(0, pageIndex - 1));
        req.setAttribute("nextPageLink", "/list?product=" + product + "&pageSize=" + pageSize + "&pageIndex=" + Math.min(pageIndex + 1, numberOfPages - 1));
        req.setAttribute("lastPageLink", "/list?product=" + product + "&pageSize=" + pageSize + "&pageIndex=" + (numberOfPages - 1));
        req.setAttribute("pageCount", numberOfPages);
    }
}
