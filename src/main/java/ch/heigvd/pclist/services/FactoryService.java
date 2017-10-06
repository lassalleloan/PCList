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
    public Pc getOnePc(long id) {
        return pcStore.getOne(id);
    }

    @Override
    public List<Pc> getAllPc() {
        return pcStore.getAll();
    }

    @Override
    public Cpu getOneCpu(long id) {
        return cpuStore.getOne(id);
    }

    @Override
    public List<Cpu> getAllCpu() {
        return cpuStore.getAll();
    }

    @Override
    public boolean setOneCpu(Cpu cpu) {
        return cpuStore.setOne(cpu);
    }

    @Override
    public boolean setAllCpu(List<Cpu> cpuList) {
        return cpuStore.setAll(cpuList);
    }

    @Override
    public Ram getOneRam(long id) {
        return ramStore.getOne(id);
    }

    @Override
    public List<Ram> getAllRam() {
        return ramStore.getAll();
    }

    @Override
    public Gpu getOneGpu(long id) {
        return gpuStore.getOne(id);
    }

    @Override
    public List<Gpu> getAllGpu() {
        return gpuStore.getAll();
    }
}
