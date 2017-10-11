package ch.heigvd.pclist.services.dao;

import ch.heigvd.pclist.models.Pc;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
@Local
public interface PcDAOLocal {

    Pc get(long id);

    List<Pc> get(List<Long> idList);

    List<Pc> get(long pageSize, long pageIndex);

    List<Pc> get();

    long count();
}
