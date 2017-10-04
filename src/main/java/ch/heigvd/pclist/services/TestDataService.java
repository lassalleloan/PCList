package ch.heigvd.pclist.services;

import ch.heigvd.pclist.util.Chance;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
@Stateless
public class TestDataService implements TestDataServiceLocal {

    @EJB(beanName = "FactoryService")
    private FactoryServiceLocal factoryService;

    @EJB(beanName = "InMemoryDataStore")
    private InMemoryDataStoreLocal inMemoryDataStore;

    @Override
    public void generateTestData() {
        int nbPc = 10;

        for (int i = 0; i < nbPc; ++i) {
            long cpuId = inMemoryDataStore.saveCpu(Chance.randomCpu());
            long ramId = inMemoryDataStore.saveRam(Chance.randomRam());
            long gpuId = inMemoryDataStore.saveGpu(Chance.randomGpu());

            factoryService.buildPc(Chance.randomPcBrand(), Chance.randomPcPrice(), cpuId, ramId, gpuId);
        }
    }
}
