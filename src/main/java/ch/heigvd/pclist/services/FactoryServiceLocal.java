package ch.heigvd.pclist.services;

import ch.heigvd.pclist.models.Pc;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
@Local
public interface FactoryServiceLocal {

    long buildPc(String brand, double price, long cpuId, long ramId, long gpuId);

    List<Pc> getAllPcs();
}
