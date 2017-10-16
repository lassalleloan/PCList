package ch.heigvd.pclist.services.dao;

import ch.heigvd.pclist.models.Ram;

import javax.ejb.Local;
import java.util.List;

/**
 * Data Access Objects for ram
 *
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 * @since 13.09.2017
 */
@Local
public interface RamDAOLocal {

    boolean isExist(long id);

    Ram get(long id);

    List<Ram> get(List<Long> idList);

    List<Ram> get(String like, String orderBy, long pageSize, long pageIndex);

    List<String> getBrand();

    long count();

    long set(Ram cpu);

    long set(List<Ram> cpuList);

    long update(Ram cpu);

    long update(List<Ram> cpuList);

    long delete(long id);

    long delete(List<Long> idList);

    long delete();
}
