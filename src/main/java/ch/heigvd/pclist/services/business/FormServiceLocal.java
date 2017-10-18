package ch.heigvd.pclist.services.business;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

/**
 * Handles input from form
 *
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 * @since 13.09.2017
 */
@Local
public interface FormServiceLocal {

    /**
     * Checks if configuration form has an error of user input
     *
     * @param req servlet request
     * @return true if there is an error, false otherwise
     */
    boolean isConfigurationError(HttpServletRequest req);

    /**
     * Checks if create form has an error of user input
     *
     * @param req servlet request
     * @return true if there is an error, false otherwise
     */
    boolean isCreateError(HttpServletRequest req);
}
