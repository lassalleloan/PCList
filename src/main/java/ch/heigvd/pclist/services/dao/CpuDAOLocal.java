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

    boolean isExist(long id);

    Cpu get(long id);

    List<Cpu> get(List<Long> idList);

    List<Cpu> get(String col, String order, long pageSize, long pageIndex);

    List<String> getBrand();

    long count();

    long set(Cpu cpu);

    long set(List<Cpu> cpuList);

    long update(Cpu cpu);

    long update(List<Cpu> cpuList);

    long delete(long id);

    long delete(List<Long> idList);

    long delete();
}
