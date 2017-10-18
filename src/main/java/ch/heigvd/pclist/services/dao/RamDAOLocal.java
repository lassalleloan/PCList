package ch.heigvd.pclist.services.dao;

import ch.heigvd.pclist.models.Ram;

import javax.ejb.Local;
import java.util.List;

/**
 * Data Access Objects for ram
 *
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 * @since 13.09.2017
 */
@Local
public interface RamDAOLocal {

    /**
     * Checks if ID of ram exists
     *
     * @param id ID of ram
     * @return true if ID of ram exists, false otherwise
     */
    boolean isExist(long id);

    /**
     * Gets ram which corresponds to ID
     *
     * @param id ID of ram
     * @return ram which corresponds to ID
     */
    Ram get(long id);

    /**
     * Gets list of ram which corresponds to list of ID of ram
     *
     * @param idList list of ID of ram
     * @return list of ram which corresponds to list of ID of ram
     */
    List<Ram> get(List<Long> idList);

    /**
     * Gets list of ram which corresponds to like conditions, order conditions, number of ram and offset for ID of ram
     *
     * @param like      like conditions
     * @param orderBy   order conditions
     * @param pageSize  number of ram
     * @param pageIndex offset for ID of ram
     * @return list of ram which corresponds to like conditions, order conditions, number of ram and offset for ID of ram
     */
    List<Ram> get(String like, String orderBy, long pageSize, long pageIndex);

    /**
     * Gets list of brand of ram
     *
     * @return list of brand of ram
     */
    List<String> getBrand();

    /**
     * Gets number of ram
     *
     * @return number of ram
     */
    long count();

    /**
     * Sets a ram
     *
     * @param ram a ram
     * @return number of rows affected
     */
    long set(Ram ram);

    /**
     * Sets a list of ram
     *
     * @param ramList list of ram
     * @return number of rows affected
     */
    long set(List<Ram> ramList);

    /**
     * Updates a of ram
     *
     * @param ram a ram
     * @return number of rows affected
     */
    long update(Ram ram);

    /**
     * Updates a list of ram
     *
     * @param ramList list of ram
     * @return number of rows affected
     */
    long update(List<Ram> ramList);

    /**
     * Deletes a of ram which corresponds to ID of ram
     *
     * @param id ID of ram
     * @return number of rows affected
     */
    long delete(long id);

    /**
     * Deletes a list of ram which corresponds to ID list of ram
     *
     * @param idList list of ID of ram
     * @return number of rows affected
     */
    long delete(List<Long> idList);

    /**
     * Deletes all ram
     *
     * @return number of rows affected
     */
    long delete();
}
