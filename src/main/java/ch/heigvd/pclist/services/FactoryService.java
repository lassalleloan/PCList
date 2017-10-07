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

    @EJB(beanName = "PcStore")
    private PcStoreLocal pcStore;

    @EJB(beanName = "CpuStore")
    private CpuStoreLocal cpuStore;

    @EJB(beanName = "RamStore")
    private RamStoreLocal ramStore;

    @EJB(beanName = "GpuStore")
    private GpuStoreLocal gpuStore;

    @Override
    public Pc getPc(long id) {
        return pcStore.getOne(id);
    }

    @Override
    public List<Pc> getPc() {
        return pcStore.getAll();
    }

    @Override
    public List<String> getCpuBrand() {
        return cpuStore.getBrand();
    }

    @Override
    public Cpu getCpu(long id) {
        return cpuStore.get(id);
    }

    @Override
    public List<Cpu> getCpu(List<Long> idList) {
        return cpuStore.get(idList);
    }

    @Override
    public List<Cpu> getCpu() {
        return cpuStore.get();
    }

    @Override
    public int setCpu(Cpu cpu) {
        return cpuStore.set(cpu);
    }

    @Override
    public int setCpu(List<Cpu> cpuList) {
        return cpuStore.set(cpuList);
    }

    @Override
    public int updateCpu(Cpu cpu) {
        return cpuStore.update(cpu);
    }

    @Override
    public int updateCpu(List<Cpu> cpuList) {
        return cpuStore.update(cpuList);
    }

    @Override
    public int deleteCpu(long id) {
        return cpuStore.delete(id);
    }

    @Override
    public int deleteCpu(List<Long> idList) {
        return cpuStore.delete(idList);
    }

    @Override
    public int deleteCpu() {
        return cpuStore.delete();
    }

    @Override
    public Ram getRam(long id) {
        return ramStore.getOne(id);
    }

    @Override
    public List<Ram> getRam() {
        return ramStore.getAll();
    }

    @Override
    public Gpu getGpu(long id) {
        return gpuStore.getOne(id);
    }

    @Override
    public List<Gpu> getGpu() {
        return gpuStore.getAll();
    }
}
