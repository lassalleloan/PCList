package ch.heigvd.pclist.services.business;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Handles actions for all products
 *
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 * @since 13.09.2017
 */
@Local
public interface ProductServiceLocal {

    List<String> PRODUCT_LIST = Arrays.asList("pc", "cpu", "ram", "gpu");

    void generate(HttpServletRequest req);

    void create(HttpServletRequest req);

    void update(HttpServletRequest req);

    void delete(HttpServletRequest req);

    Map<String, Object> get(String product, long id);

    Map<String, Object> get(String product, String like, String orderBy, long pageSize, long pageIndex);

    Map<String, Object> getBrand(String product);

    Map<String, Object> getComponent(String product);

    long count(String product);
}
