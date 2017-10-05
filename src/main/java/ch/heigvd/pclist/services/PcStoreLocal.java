package ch.heigvd.pclist.services;

import ch.heigvd.pclist.models.Pc;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
@Local
public interface PcStoreLocal {

    Pc getOne(long id);

    List<Pc> getAll();
}
