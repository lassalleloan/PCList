package ch.heigvd.pclist.services;

import javax.ejb.Local;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
@Local
public interface TestDataServiceLocal {

    void generateTestData();
}
