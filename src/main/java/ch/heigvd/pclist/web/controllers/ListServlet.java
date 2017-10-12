package ch.heigvd.pclist.web.controllers;

import ch.heigvd.pclist.services.business.ParameterServiceLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Handles requests coming from /list
 *
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 * @since 13.09.2017
 */
public class ListServlet extends HttpServlet {

    @EJB
    private ParameterServiceLocal parameterService;

    /**
     * Processes requests for both HTTP GET method
     *
     * @param req  servlet request
     * @param resp servlet response
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();

        // Sets informations message
        if ("/delete".equals(servletPath)) {
            parameterService.setInformationsMessage(req);
        }

        // Sets page title, list of product and page links
        parameterService.setPageTitle(req);
        parameterService.setProductList(req);
        parameterService.setPageLinks(req);

        req.getRequestDispatcher("WEB-INF/pages/list.jsp").forward(req, resp);
    }

    /**
     * Handles the HTTP GET method
     *
     * @param req  servlet request
     * @param resp servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
