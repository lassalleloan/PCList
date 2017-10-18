package ch.heigvd.pclist.services.business;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
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
@Local
public interface JspServiceLocal {

    /**
     * Name of product
     */
    Map<String, String> NAME_PRODUCT_STRING_MAP = new HashMap<String, String>() {{
        put("", "All");
        put("cpu", "Processor");
        put("gpu", "Graphic");
        put("ram", "Memory");
        put("pc", "PC");
    }};

    /**
     * Title of page
     */
    Map<String, String> TITLE_STRING_MAP = new HashMap<String, String>() {{
        put("/configuration", "Configuration of ");
        put("/create", "Create a ");
        put("/edit", "Edit a ");
        put("/list", "List of ");
    }};

    /**
     * Direction for list
     */
    List<String> DIRECTION_LIST = Arrays.asList("ASC", "DESC");

    /**
     * Default page size
     */
    long PAGE_SIZE_ALL_LIST = 2;
    long PAGE_SIZE_PRODUCT_LIST = 10;

    /**
     * Gets value of integer parameter
     *
     * @param req       servlet request
     * @param parameter name of parameter
     * @return value of integer parameter
     */
    int getUnsignedInteger(HttpServletRequest req, String parameter);

    /**
     * Gets value of long parameter
     *
     * @param req servlet request
     * @param parameter name of parameter
     * @return value of long parameter
     */
    long getUnsignedLong(HttpServletRequest req, String parameter);

    /**
     * Gets value of double parameter
     *
     * @param req servlet request
     * @param parameter name of parameter
     * @return value of double parameter
     */
    double getUnsignedDouble(HttpServletRequest req, String parameter);

    /**
     * Gets value of product parameter
     *
     * @param req servlet request
     * @return value of product parameter
     */
    String getProduct(HttpServletRequest req);

    /**
     * Sets value for page title parameter
     *
     * @param req servlet request
     */
    void setPageTitle(HttpServletRequest req);

    /**
     * Sets value for information message parameter
     *
     * @param req     servlet request
     * @param message message to display
     */
    void setInformationMessage(HttpServletRequest req, String message);

    /**
     * Sets value for header title parameter
     *
     * @param req servlet request
     */
    void setHeaderTitle(HttpServletRequest req);

    /**
     * Sets value for product list
     *
     * @param req servlet request
     */
    void setList(HttpServletRequest req);

    /**
     * Sets value for product list
     *
     * @param req servlet request
     */
    void setProductList(HttpServletRequest req);

    /**
     * Sets value for component product
     *
     * @param req servlet request
     */
    void setProductComponent(HttpServletRequest req);

    /**
     * Sets value for brand product list
     *
     * @param req servlet request
     */
    void setProductBrandList(HttpServletRequest req);

    /**
     * Sets value for links of pages
     *
     * @param req servlet request
     */
    void setLinks(HttpServletRequest req);
}
