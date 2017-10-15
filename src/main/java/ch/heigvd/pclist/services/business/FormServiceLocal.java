package ch.heigvd.pclist.services.business;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

/**
 * Handles input from form
 *
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
@Local
public interface FormServiceLocal {

    boolean isConfigurationError(HttpServletRequest req);

    boolean isCreateError(HttpServletRequest req);
}
