package ch.heigvd.pclist.services.business;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
@Local
public interface JspServiceLocal {

    Map<String, String> NAME_PRODUCT_STRING_MAP = new HashMap<String, String>() {{
        put("", "All");
        put("cpu", "Processor");
        put("gpu", "Graphic");
        put("ram", "Memory");
        put("pc", "PC");
    }};

    Map<String, String> TITLE_STRING_MAP = new HashMap<String, String>() {{
        put("/configuration", "Configuration of ");
        put("/create", "Create a ");
        put("/edit", "Edit a ");
        put("/list", "List of ");
    }};

    long PAGE_SIZE_ALL_LIST = 2;
    long PAGE_SIZE_PRODUCT_LIST = 10;

    String getString(HttpServletRequest req, String s, List<String> stringList);

    int getUnsignedInteger(HttpServletRequest req, String parameter);

    long getUnsignedLong(HttpServletRequest req, String parameter);

    double getUnsignedDouble(HttpServletRequest req, String parameter);

    String getProduct(HttpServletRequest req);

    long getPageSize(HttpServletRequest req);

    long getNumberPages(String product, long pageSize, long pageIndex);

    void setPageTitle(HttpServletRequest req);

    void setHeaderTitle(HttpServletRequest req);

    void setProductList(HttpServletRequest req);

    void setProductComponent(HttpServletRequest req);

    void setProductBrandList(HttpServletRequest req);

    void setList(HttpServletRequest req);

    void setInformationMessage(HttpServletRequest req, String informationMessage);

    void setPageLinks(HttpServletRequest req);

    void setAttribute(HttpServletRequest req, Map<String, Object> objectMap);
}
