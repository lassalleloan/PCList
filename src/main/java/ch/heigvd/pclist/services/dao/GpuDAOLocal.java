package ch.heigvd.pclist.services.dao;

import ch.heigvd.pclist.models.Gpu;

import javax.ejb.Local;
import java.util.List;

/**
 * Data Access Objects for gpu
 *
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 * @since 13.09.2017
 */
@Local
public interface GpuDAOLocal {

    /**
     * Checks if ID of gpu exists
     *
     * @param id ID of gpu
     * @return true if ID of cpu exists, false otherwise
     */
    boolean isExist(long id);

    /**
     * Gets gpu which corresponds to ID
     *
     * @param id ID of gpu
     * @return gpu which corresponds to ID
     */
    Gpu get(long id);

    /**
     * Gets list of gpu which corresponds to list of ID of gpu
     *
     * @param idList list of ID of gpu
     * @return list of gpu which corresponds to list of ID of gpu
     */
    List<Gpu> get(List<Long> idList);

    /**
     * Gets list of gpu which corresponds to like conditions, order conditions, number of gpu and offset for ID of gpu
     *
     * @param like      like conditions
     * @param orderBy   order conditions
     * @param pageSize  number of gpu
     * @param pageIndex offset for ID of gpu
     * @return list of gpu which corresponds to like conditions, order conditions, number of gpu and offset for ID of gpu
     */
    List<Gpu> get(String like, String orderBy, long pageSize, long pageIndex);

    /**
     * Gets list of brand of gpu
     *
     * @return list of brand of gpu
     */
    List<String> getBrand();

    /**
     * Gets number of gpu
     *
     * @return number of gpu
     */
    long count();

    /**
     * Sets a gpu
     *
     * @param gpu a gpu
     * @return number of rows affected
     */
    long set(Gpu gpu);

    /**
     * Sets a list of gpu
     *
     * @param gpuList list of gpu
     * @return number of rows affected
     */
    long set(List<Gpu> gpuList);

    /**
     * Updates a of gpu
     *
     * @param gpu a gpu
     * @return number of rows affected
     */
    long update(Gpu gpu);

    /**
     * Updates a list of gpu
     *
     * @param gpuList list of gpu
     * @return number of rows affected
     */
    long update(List<Gpu> gpuList);

    /**
     * Deletes a of gpu which corresponds to ID of gpu
     *
     * @param id ID of gpu
     * @return number of rows affected
     */
    long delete(long id);

    /**
     * Deletes a list of gpu which corresponds to ID list of gpu
     *
     * @param idList list of ID of gpu
     * @return number of rows affected
     */
    long delete(List<Long> idList);

    /**
     * Deletes all gpu
     *
     * @return number of rows affected
     */
    long delete();
}
