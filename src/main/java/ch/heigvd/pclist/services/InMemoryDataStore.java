package ch.heigvd.pclist.services;

import ch.heigvd.pclist.models.Cpu;
import ch.heigvd.pclist.models.Gpu;
import ch.heigvd.pclist.models.Pc;
import ch.heigvd.pclist.models.Ram;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class InMemoryDataStore implements InMemoryDataStoreLocal {

    private final Map<Long, Pc> pcMap = new HashMap<>();
    private final Map<Long, Ram> ramMap = new HashMap<>();
    private final Map<Long, Cpu> cpuMap = new HashMap<>();
    private final Map<Long, Gpu> gpuMap = new HashMap<>();
    private long pcIdCounter = 0;
    private long ramIdCounter = 0;
    private long cpuIdCounter = 0;
    private long gpuIdCounter = 0;

    public long savePc(Pc pc) {
        pcIdCounter++;
        pcMap.put(pcIdCounter, pc);
        return pcIdCounter;
    }

    public Pc loadPc(long id) {
        return pcMap.get(id);
    }

    public long saveCpu(Cpu cpu) {
        cpuIdCounter++;
        cpuMap.put(cpuIdCounter, cpu);
        return cpuIdCounter;
    }

    public Cpu loadCpu(long id) {
        return cpuMap.get(id);
    }

    public long saveRam(Ram ram) {
        ramIdCounter++;
        ramMap.put(ramIdCounter, ram);
        return ramIdCounter;
    }

    public Ram loadRam(long id) {
        return ramMap.get(id);
    }

    public long saveGpu(Gpu gpu) {
        gpuIdCounter++;
        gpuMap.put(gpuIdCounter, gpu);
        return gpuIdCounter;
    }

    public Gpu loadGpu(long id) {
        return gpuMap.get(id);
    }

    public List<Pc> findAllPcs() {
        List<Pc> pcList = new ArrayList<>();

        for (int i = 1; i <= pcIdCounter; ++i) {
            pcList.add(loadPc(i));
        }

        return pcList;
    }
}
