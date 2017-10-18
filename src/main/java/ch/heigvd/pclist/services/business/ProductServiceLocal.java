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

    /**
     * All product
     */
    List<String> PRODUCT_LIST = Arrays.asList("pc", "cpu", "ram", "gpu");

    /**
     * Generates products
     *
     * @param req servlet request
     */
    void generate(HttpServletRequest req);

    /**
     * Creates products
     *
     * @param req servlet request
     */
    void create(HttpServletRequest req);

    /**
     * Updates products
     *
     * @param req servlet request
     */
    void update(HttpServletRequest req);

    /**
     * Deletes products
     *
     * @param req servlet request
     */
    void delete(HttpServletRequest req);

    /**
     * Gets a map of attributes and values of product which corresponds to ID and type of product
     *
     * @param product type of product
     * @param id      ID of product
     * @return a map of attributes and values of product which corresponds to ID and type of product
     */
    Map<String, Object> get(String product, long id);

    /**
     * Gets a map of attributes and values of product which corresponds to type of product, like conditions,
     * order conditions, number of product and offset for ID of product
     *
     * @param product   type of product
     * @param like      like conditions
     * @param orderBy   order conditions
     * @param pageSize  number of cpu
     * @param pageIndex offset for ID of cpu
     * @return Gets a map of attributes and values of product which corresponds to type of product, like conditions,
     * order conditions, number of product and offset for ID of product
     */
    Map<String, Object> get(String product, String like, String orderBy, long pageSize, long pageIndex);

    /**
     * Gets a map of attributes and values of brand of type of product
     *
     * @param product type of product
     * @return a map of attributes and values of brand of type of product
     */
    Map<String, Object> getBrand(String product);

    /**
     * Gets a map of attributes and values of component of type of product
     *
     * @param product type of product
     * @return a map of attributes and values of component of type of product
     */
    Map<String, Object> getComponent(String product);

    /**
     * Gets number of type of product
     *
     * @param product type of product
     * @return number of type of product
     */
    long count(String product);
}
