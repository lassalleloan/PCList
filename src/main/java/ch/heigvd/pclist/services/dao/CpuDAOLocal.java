package ch.heigvd.pclist.services.dao;

import ch.heigvd.pclist.models.Cpu;

import javax.ejb.Local;
import java.util.List;

/**
 * Data Access Objects for cpu
 *
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 * @since 13.09.2017
 */
@Local
public interface CpuDAOLocal {

    /**
     * Checks if ID of cpu exists
     *
     * @param id ID of cpu
     * @return true if ID of cpu exists, false otherwise
     */
    boolean isExist(long id);

    /**
     * Gets cpu which corresponds to ID
     *
     * @param id ID of cpu
     * @return cpu which corresponds to ID
     */
    Cpu get(long id);

    /**
     * Gets list of cpu which corresponds to list of ID of cpu
     *
     * @param idList list of ID of cpu
     * @return list of cpu which corresponds to list of ID of cpu
     */
    List<Cpu> get(List<Long> idList);

    /**
     * Gets list of cpu which corresponds to like conditions, order conditions, number of cpu and offset for ID of cpu
     *
     * @param like      like conditions
     * @param orderBy   order conditions
     * @param pageSize  number of cpu
     * @param pageIndex offset for ID of cpu
     * @return list of cpu which corresponds to like conditions, order conditions, number of cpu and offset for ID of cpu
     */
    List<Cpu> get(String like, String orderBy, long pageSize, long pageIndex);

    /**
     * Gets list of brand of cpu
     *
     * @return list of brand of cpu
     */
    List<String> getBrand();

    /**
     * Gets number of cpu
     *
     * @return number of cpu
     */
    long count();

    /**
     * Sets a cpu
     *
     * @param cpu a cpu
     * @return number of rows affected
     */
    long set(Cpu cpu);

    /**
     * Sets a list of cpu
     *
     * @param cpuList list of cpu
     * @return number of rows affected
     */
    long set(List<Cpu> cpuList);

    /**
     * Updates a of cpu
     *
     * @param cpu a cpu
     * @return number of rows affected
     */
    long update(Cpu cpu);

    /**
     * Updates a list of cpu
     *
     * @param cpuList list of cpu
     * @return number of rows affected
     */
    long update(List<Cpu> cpuList);

    /**
     * Deletes a of cpu which corresponds to ID of cpu
     *
     * @param id ID of cpu
     * @return number of rows affected
     */
    long delete(long id);

    /**
     * Deletes a list of cpu which corresponds to ID list of cpu
     *
     * @param idList list of ID of cpu
     * @return number of rows affected
     */
    long delete(List<Long> idList);

    /**
     * Deletes all cpu
     *
     * @return number of rows affected
     */
    long delete();
}
