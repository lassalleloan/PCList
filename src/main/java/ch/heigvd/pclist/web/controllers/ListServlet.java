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
     * Processes requests for both HTTP <code>GET</code> method
     *
     * @param req  servlet request
     * @param resp servlet response
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Gets type of product
        String product = parameterService.getProduct(req);

        // Gets type of action and number of rows affected
        parameterService.setInformationsMessage(req, product);

        // Gets page size, page index for pagination and number of pages
        long pageSize = parameterService.getPageSize(req, product);
        long pageIndex = parameterService.getUnsignedLong(req, "pageIndex");

        // Sets list of product and page links
        parameterService.setProductList(req, product, pageSize, pageIndex);
        parameterService.setPageLinks(req, product, pageSize, pageIndex);

        req.getRequestDispatcher("WEB-INF/pages/list.jsp").forward(req, resp);
    }

    /**
     * Handles the HTTP <code>GET</code> method
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
