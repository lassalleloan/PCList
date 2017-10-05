package ch.heigvd.pclist.services;

import ch.heigvd.pclist.models.Cpu;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
@Stateless
public class FactoryService implements FactoryServiceLocal {

    @EJB(beanName = "CpuStore")
    private CpuStoreLocal cpuStore;

    @Override
    public List<Cpu> getAllCpu() {
        return cpuStore.getAll();
    }
}
