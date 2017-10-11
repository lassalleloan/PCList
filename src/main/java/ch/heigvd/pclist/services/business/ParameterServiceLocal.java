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
    List<String> ACTION_LIST = Arrays.asList("created", "edited", "deleted");

    long PAGE_SIZE_IS_ALL_LIST = 2;
    long PAGE_SIZE_IS_PRODUCT_LIST = 10;

    String getString(HttpServletRequest req, String s, List<String> stringList);

    String getProduct(HttpServletRequest req);

    long getUnsignedLong(HttpServletRequest req, String parameter);

    String getPageTitle(String product);

    long getPageSize(HttpServletRequest req, String product);

    long getNumberPages(String product, long pageSize, long pageIndex);

    void setPageTitle(HttpServletRequest req, String product);

    void setProductList(HttpServletRequest req, String product, long pageSize, long pageIndex);

    void setInformationsMessage(HttpServletRequest req, String product);

    void setPageLinks(HttpServletRequest req, String product, long pageSize, long pageIndex);
}
