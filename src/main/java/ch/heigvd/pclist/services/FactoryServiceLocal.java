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

    Pc getPc(long id);

    List<Pc> getPc();

    Cpu getCpu(long id);

    List<Cpu> getCpu(List<Long> idList);

    List<Cpu> getCpu();

    int setCpu(Cpu cpu);

    int setCpu(List<Cpu> cpuList);

    int updateCpu(Cpu cpu);

    int updateCpu(List<Cpu> cpuList);

    int deleteCpu(long id);

    int deleteCpu(List<Long> idList);

    int deleteCpu();

    Ram getRam(long id);

    List<Ram> getRam();

    Gpu getGpu(long id);

    List<Gpu> getGpu();
}
