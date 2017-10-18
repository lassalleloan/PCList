package ch.heigvd.pclist.services.business;

import ch.heigvd.pclist.models.Cpu;
import ch.heigvd.pclist.models.Gpu;
import ch.heigvd.pclist.models.Pc;
import ch.heigvd.pclist.models.Ram;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handles parameters of jsp pages
 *
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 * @since 13.09.2017
 */
@Singleton
public class JspService implements JspServiceLocal {

    @EJB
    private ProductServiceLocal productService;

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

        boolean isAllList = product.isEmpty();
        objectMap.put("allList", isAllList);

        long pageSize = getPageSize(req);
        long pageIndex = getUnsignedLong(req, "pageIndex");

        if (isAllList || product.equals("pc")) {
            objectMap.putAll(productService.get("pc", "", getOrderBy(req, Pc.FIELD_LIST), pageSize, pageIndex));
        }

        if (isAllList || product.equals("cpu")) {
            objectMap.putAll(productService.get("cpu", "", getOrderBy(req, Cpu.FIELD_LIST), pageSize, pageIndex));
        }

        if (isAllList || product.equals("ram")) {
            objectMap.putAll(productService.get("ram", "", getOrderBy(req, Ram.FIELD_LIST), pageSize, pageIndex));
        }

        if (isAllList || product.equals("gpu")) {
            objectMap.putAll(productService.get("gpu", "", getOrderBy(req, Gpu.FIELD_LIST), pageSize, pageIndex));
        }

        setAttribute(req, objectMap);
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
    public void setInformationMessage(HttpServletRequest req, String message) {
        req.setAttribute("informationMessage", message);
    }

    @Override
    public void setLinks(HttpServletRequest req) {
        setProduct(req);
        setPageSize(req);
        setPageIndex(req);
        setPageCount(req);
        setOrderBy(req);
        setDirection(req);
    }

    /**
     * Gets string parameter which corresponds to list of values allowed
     *
     * @param req        servlet request
     * @param s          string parameter
     * @param stringList list of values allowed
     * @return string parameter which corresponds to list of values allowed
     */
    private String getString(HttpServletRequest req, String s, List<String> stringList) {
        String string = req.getParameter(s);
        return string == null || !stringList.contains(string) ? "" : string;
    }

    /**
     * Gets value of page size parameter
     *
     * @param req servlet request
     * @return value of page size parameter
     */
    private long getPageSize(HttpServletRequest req) {
        long pageSize = getUnsignedLong(req, "pageSize");

        if (pageSize <= 0) {
            pageSize = getProduct(req).isEmpty() ? PAGE_SIZE_ALL_LIST : PAGE_SIZE_PRODUCT_LIST;
        }

        return pageSize;
    }

    /**
     * Gets order by parameter
     *
     * @param req        servlet request
     * @param stringList list of values allowed
     * @return value of page size parameter
     */
    private String getOrderBy(HttpServletRequest req, List<String> stringList) {
        String orderBy = getString(req, "orderBy", stringList);
        String direction = getString(req, "direction", DIRECTION_LIST);
        return orderBy.isEmpty() ? "" : orderBy + " " + (direction.isEmpty() ? "ASC" : direction);
    }

    /**
     * Sets product for links
     *
     * @param req servlet request
     */
    private void setProduct(HttpServletRequest req) {
        req.setAttribute("product", getProduct(req));
    }

    /**
     * Sets page size for links
     *
     * @param req servlet request
     */
    private void setPageSize(HttpServletRequest req) {
        req.setAttribute("pageSize", getPageSize(req));
    }

    /**
     * Sets page index for links
     *
     * @param req servlet request
     */
    private void setPageIndex(HttpServletRequest req) {
        req.setAttribute("pageIndex", getUnsignedLong(req, "pageIndex"));
    }

    /**
     * Sets page count for links
     *
     * @param req servlet request
     */
    private void setPageCount(HttpServletRequest req) {
        long pageSize = getPageSize(req);
        long pageCount = (productService.count(getProduct(req)) + pageSize - 1) / pageSize;

        pageCount = pageCount <= 0 ? 1 : pageCount;
        req.setAttribute("pageCount", pageCount);
    }

    /**
     * Sets order by for links
     *
     * @param req servlet request
     */
    private void setOrderBy(HttpServletRequest req) {
        req.setAttribute("orderBy", req.getParameter("orderBy"));
    }

    /**
     * Sets direction parameter for links
     *
     * @param req servlet request
     */
    private void setDirection(HttpServletRequest req) {
        String direction = req.getParameter("direction");
        req.setAttribute("direction", direction == null ? "DESC" : direction);
    }

    /**
     * Sets attributes with a map of attributes and values
     *
     * @param req       servlet request
     * @param objectMap map of attributes and values
     */
    private void setAttribute(HttpServletRequest req, Map<String, Object> objectMap) {
        for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
            req.setAttribute(entry.getKey(), entry.getValue());
        }
    }
}
