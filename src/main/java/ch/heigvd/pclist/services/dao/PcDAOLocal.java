package ch.heigvd.pclist.services.dao;

import ch.heigvd.pclist.models.Pc;

import javax.ejb.Local;
import java.util.List;

/**
 * Data Access Objects for pc
 *
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 * @since 13.09.2017
 */
@Local
public interface PcDAOLocal {

    /**
     * Checks if ID of pc exists
     *
     * @param id ID of pc
     * @return true if ID of cpu exists, false otherwise
     */
    boolean isExist(long id);

    /**
     * Gets pc which corresponds to ID
     *
     * @param id ID of pc
     * @return pc which corresponds to ID
     */
    Pc get(long id);

    /**
     * Gets list of pc which corresponds to list of ID of pc
     *
     * @param idList list of ID of pc
     * @return list of pc which corresponds to list of ID of pc
     */
    List<Pc> get(List<Long> idList);

    /**
     * Gets list of pc which corresponds to like conditions, order conditions, number of pc and offset for ID of pc
     *
     * @param like      like conditions
     * @param orderBy   order conditions
     * @param pageSize  number of pc
     * @param pageIndex offset for ID of pc
     * @return list of pc which corresponds to like conditions, order conditions, number of pc and offset for ID of pc
     */
    List<Pc> get(String like, String orderBy, long pageSize, long pageIndex);

    /**
     * Gets list of brand of pc
     *
     * @return list of brand of pc
     */
    List<String> getBrand();

    /**
     * Gets number of pc
     *
     * @return number of pc
     */
    long count();

    /**
     * Sets a pc
     *
     * @param pc a pc
     * @return number of rows affected
     */
    long set(Pc pc);

    /**
     * Sets a list of pc
     *
     * @param pcList list of pc
     * @return number of rows affected
     */
    long set(List<Pc> pcList);

    /**
     * Updates a of pc
     *
     * @param pc a pc
     * @return number of rows affected
     */
    long update(Pc pc);

    /**
     * Updates a list of pc
     *
     * @param pcList list of pc
     * @return number of rows affected
     */
    long update(List<Pc> pcList);

    /**
     * Deletes a of pc which corresponds to ID of pc
     *
     * @param id ID of pc
     * @return number of rows affected
     */
    long delete(long id);

    /**
     * Deletes a list of pc which corresponds to ID list of pc
     *
     * @param idList list of ID of pc
     * @return number of rows affected
     */
    long delete(List<Long> idList);

    /**
     * Deletes all pc
     *
     * @return number of rows affected
     */
    long delete();
}
