package ch.heigvd.pclist.services.business;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
@Local
public interface ProductServiceLocal {

    long count(String product);

    Map<String, Object> get(String product, long pageSize, long pageIndex);

    Map<String, Object> getBrand(String product);

    Map<String, Object> getComponent(String product);

    void create(HttpServletRequest req);

    void update(HttpServletRequest req);

    void delete(HttpServletRequest req);
}
