package ch.heigvd.pclist.services.dao;

import ch.heigvd.pclist.models.Pc;

import javax.ejb.Local;
import java.util.List;

/**
 * Data Access Objects for pc
 *
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 * @since 13.09.2017
 */
@Local
public interface PcDAOLocal {

    boolean isExist(long id);

    Pc get(long id);

    List<Pc> get(List<Long> idList);

    List<Pc> get(String like, String orderBy, long pageSize, long pageIndex);

    List<String> getBrand();

    long count();

    long set(Pc pc);

    long set(List<Pc> pcList);
}
