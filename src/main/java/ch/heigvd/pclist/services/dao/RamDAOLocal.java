package ch.heigvd.pclist.services.dao;

import ch.heigvd.pclist.models.Ram;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
@Local
public interface RamDAOLocal {

    Ram get(long id);

    List<Ram> get(List<Long> idList);

    List<Ram> get(int pageSize, int pageIndex);

    List<Ram> get();

    List<String> getBrand();

    long count();

    int set(Ram cpu);

    int set(List<Ram> cpuList);

    int update(Ram cpu);

    int update(List<Ram> cpuList);

    int delete(long id);

    int delete(List<Long> idList);

    int delete();
}
