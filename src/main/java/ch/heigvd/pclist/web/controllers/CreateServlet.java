package ch.heigvd.pclist.web.controllers;

import ch.heigvd.pclist.services.business.ParameterServiceLocal;
import ch.heigvd.pclist.services.business.ProductServiceLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Handles requests coming from /create
 *
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 * @since 13.09.2017
 */
public class CreateServlet extends HttpServlet {

    @EJB
    private ParameterServiceLocal parameterService;

    @EJB
    private ProductServiceLocal productService;

    /**
     * Processes requests for both HTTP GET and POST methods
     *
     * @param req  servlet request
     * @param resp servlet response
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        boolean isCreatePath = "/create".equals(servletPath);

        parameterService.setPageTitle(req);
        req.setAttribute("isCreatePath", isCreatePath);
        parameterService.setProductBrandList(req);
        parameterService.setProductList(req);

        if (isCreatePath) {
            req.getRequestDispatcher("WEB-INF/pages/create.jsp").forward(req, resp);
        } else {
            parameterService.setProduct(req);
            req.getRequestDispatcher("WEB-INF/pages/create.jsp").forward(req, resp);
        }
    }

    /**
     * Handles the HTTP POST method
     *
     * @param req  servlet request
     * @param resp servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();

        if ("/create".equals(servletPath)) {
            productService.create(req);
        } else {
            productService.update(req);
        }

        processRequest(req, resp);
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
