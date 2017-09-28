package ch.heigvd.pclist.services;

import ch.heigvd.pclist.models.Cpu;
import ch.heigvd.pclist.models.Gpu;
import ch.heigvd.pclist.models.Pc;
import ch.heigvd.pclist.models.Ram;

import javax.ejb.Local;
import java.util.List;

@Local
public interface InMemoryDataStoreLocal {

    long savePc(Pc pc);

    Pc loadPc(long id);

    long saveCpu(Cpu pc);

    Cpu loadCpu(long id);

    long saveRam(Ram pc);

    Ram loadRam(long id);

    long saveGpu(Gpu pc);

    Gpu loadGpu(long id);

    List<Pc> findAllPcs();
}
