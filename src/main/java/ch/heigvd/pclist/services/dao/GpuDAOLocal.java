package ch.heigvd.pclist.services.dao;

import ch.heigvd.pclist.models.Gpu;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
@Local
public interface GpuDAOLocal {

    Gpu get(long id);

    List<Gpu> get(List<Long> idList);

    List<Gpu> get(long pageSize, long pageIndex);

    List<Gpu> get();

    List<String> getBrand();

    long count();

    int set(Gpu gpu);

    int set(List<Gpu> gpuList);

    int update(Gpu gpu);

    int update(List<Gpu> gpuList);

    int delete(long id);

    int delete(List<Long> idList);

    int delete();
}
