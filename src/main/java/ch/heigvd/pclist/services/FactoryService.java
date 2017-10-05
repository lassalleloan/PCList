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

    @EJB(beanName = "ramStore")
    private RamStoreLocal ramStore;

    @EJB(beanName = "GpuStore")
    private GpuStoreLocal gpuStore;

    @Override
    public List<Pc> getAllPc() {
        return pcStore.getAll();
    }

    @Override
    public List<Cpu> getAllCpu() {
        return cpuStore.getAll();
    }

    @Override
    public List<Ram> getAllRam() {
        return ramStore.getAll();
    }

    @Override
    public List<Gpu> getAllGpu() {
        return gpuStore.getAll();
    }
}
