package ch.heigvd.pclist.services.business;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
@Local
public interface ParameterServiceLocal {

    List<String> PRODUCT_LIST = Arrays.asList("pc", "cpu", "ram", "gpu");

    long PAGE_SIZE_IS_ALL_LIST = 2;
    long PAGE_SIZE_IS_PRODUCT_LIST = 10;

    String getString(HttpServletRequest req, String s, List<String> stringList);

    int getUnsignedInteger(HttpServletRequest req, String parameter);

    long getUnsignedLong(HttpServletRequest req, String parameter);

    double getUnsignedDouble(HttpServletRequest req, String parameter);

    String getProduct(HttpServletRequest req);

    String getPageTitle(String product);

    long getPageSize(HttpServletRequest req);

    long getNumberPages(String product, long pageSize, long pageIndex);

    void setPageTitle(HttpServletRequest req);

    void setProductList(HttpServletRequest req);

    void setProductBrandList(HttpServletRequest req);

    void setComponentList(HttpServletRequest req);

    void setInformationMessage(HttpServletRequest req, String informationMessage);

    void setPageLinks(HttpServletRequest req);
}
