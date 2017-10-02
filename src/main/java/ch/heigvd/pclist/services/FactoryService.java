package ch.heigvd.pclist.services;

import ch.heigvd.pclist.models.Cpu;
import ch.heigvd.pclist.models.Gpu;
import ch.heigvd.pclist.models.Pc;
import ch.heigvd.pclist.models.Ram;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
@Stateless
public class FactoryService implements FactoryServiceLocal {

    @EJB
    private InMemoryDataStoreLocal inMemoryDataStore;

    @Override
    public long buildPc(String brand, double price, long cpuId, long ramId, long gpuId) {
        Cpu cpu = inMemoryDataStore.loadCpu(cpuId);
        Ram ram = inMemoryDataStore.loadRam(ramId);
        Gpu gpu = inMemoryDataStore.loadGpu(gpuId);

        Pc pc = new Pc(brand, price, cpu, ram, gpu);
        long pcId = inMemoryDataStore.savePc(pc);
        return pcId;
    }

    @Override
    public List<Pc> getAllPcs() {
        return inMemoryDataStore.findAllPcs();
    }
}
