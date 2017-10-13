package ch.heigvd.pclist.services.business;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
@Local
public interface EditFormServiceLocal {

    boolean isErrorInput(HttpServletRequest req);
}
