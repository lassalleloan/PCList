package ch.heigvd.pclist.services;

import ch.heigvd.pclist.models.Cpu;
import ch.heigvd.pclist.models.Gpu;
import ch.heigvd.pclist.models.Pc;
import ch.heigvd.pclist.models.Ram;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
@Local
public interface FactoryServiceLocal {

    Pc getOnePc(long id);

    List<Pc> getAllPc();

    Cpu getOneCpu(long id);

    List<Cpu> getAllCpu();

    boolean setOneCpu(Cpu cpu);

    boolean setAllCpu(List<Cpu> cpuList);

    Ram getOneRam(long id);

    List<Ram> getAllRam();

    Gpu getOneGpu(long id);

    List<Gpu> getAllGpu();
}
