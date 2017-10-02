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
public interface InMemoryDataStoreLocal {

    long savePc(Pc pc);

    Pc loadPc(long id);

    long saveCpu(Cpu cpu);

    Cpu loadCpu(long id);

    long saveRam(Ram ram);

    Ram loadRam(long id);

    long saveGpu(Gpu gpu);

    Gpu loadGpu(long id);

    List<Pc> findAllPcs();
}
