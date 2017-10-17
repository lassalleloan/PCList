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

    boolean isExist(long id);

    Gpu get(long id);

    List<Gpu> get(List<Long> idList);

    List<Gpu> get(String like, String orderBy, long pageSize, long pageIndex);

    List<String> getBrand();

    long count();

    long set(Gpu gpu);

    long set(List<Gpu> gpuList);

    long update(Gpu gpu);

    long update(List<Gpu> gpuList);

    long delete(long id);

    long delete(List<Long> idList);

    long delete();
}
