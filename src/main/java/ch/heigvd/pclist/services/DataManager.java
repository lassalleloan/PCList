package ch.heigvd.pclist.services;

import ch.heigvd.pclist.services.dao.CpuDAOLocal;
import ch.heigvd.pclist.services.dao.GpuDAOLocal;
import ch.heigvd.pclist.services.dao.PcDAOLocal;
import ch.heigvd.pclist.services.dao.RamDAOLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
@Stateless
public class DataManager implements DataManagerLocal {

    @EJB
    PcDAOLocal pcDAO;

    @EJB
    CpuDAOLocal cpuDAO;

    @EJB
    RamDAOLocal ramDAO;

    @EJB
    GpuDAOLocal gpuDAO;

    @Override
    public void generateData() {

    }
}
