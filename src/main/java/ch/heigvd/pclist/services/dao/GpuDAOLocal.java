package ch.heigvd.pclist.services.dao;

import ch.heigvd.pclist.models.Gpu;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
@Local
public interface GpuDAOLocal {

    Gpu getOne(long id);

    List<Gpu> getAll();
}
