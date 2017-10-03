package ch.heigvd.pclist.services;

import ch.heigvd.pclist.models.Cpu;
import ch.heigvd.pclist.models.Gpu;
import ch.heigvd.pclist.models.Pc;
import ch.heigvd.pclist.models.Ram;
import ch.heigvd.pclist.util.Chance;

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
    public List<Pc> getAllPc() {
        return inMemoryDataStore.findAllPcs();
    }

    @Override
    public List<String> getAllPcBrand() {
        return Chance.getPcBrands();
    }

    @Override
    public List<String> getAllCpuBrand() {
        return Chance.getCpuBrands();
    }

    @Override
    public List<Integer> getAllCpuNbCore() {
        return Chance.getCpuNbCores();
    }

    @Override
    public List<String> getAllRamBrand() {
        return Chance.getRamBrands();
    }

    @Override
    public List<Integer> getAllRamSize() {
        return Chance.getRamSize();
    }

    @Override
    public List<String> getAllGpuBrand() {
        return Chance.getGpuBrands();
    }
}
