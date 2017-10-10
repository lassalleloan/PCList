package ch.heigvd.pclist.services.dao;

import ch.heigvd.pclist.models.Cpu;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
@Local
public interface CpuDAOLocal {

    Cpu get(long id);

    List<Cpu> get(List<Long> idList);

    List<Cpu> get(int pageSize, int pageIndex);

    List<Cpu> get();

    List<String> getBrand();

    long count();

    int set(Cpu cpu);

    int set(List<Cpu> cpuList);

    int update(Cpu cpu);

    int update(List<Cpu> cpuList);

    int delete(long id);

    int delete(List<Long> idList);

    int delete();
}
