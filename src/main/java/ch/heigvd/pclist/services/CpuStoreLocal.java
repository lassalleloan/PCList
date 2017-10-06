package ch.heigvd.pclist.services;

import ch.heigvd.pclist.models.Cpu;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
@Local
public interface CpuStoreLocal {

    Cpu getOne(long id);

    List<Cpu> getAll();

    boolean setOne(Cpu cpu);

    boolean setAll(List<Cpu> cpuList);
}
