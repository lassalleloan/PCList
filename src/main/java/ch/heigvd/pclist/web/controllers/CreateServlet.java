package ch.heigvd.pclist.web.controllers;

import ch.heigvd.pclist.services.business.JspServiceLocal;
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
    private JspServiceLocal jspService;

    @EJB
    private ProductServiceLocal productService;

    /**
     * Processes requests for both HTTP GET and POST methods
     *
     * @param req  servlet request
     * @param resp servlet response
     * @throws ServletException throws when it encounters difficulty
     * @throws IOException      throws when I/O operations failed or interrupted
     */
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isCreatePath = "/create".equals(req.getServletPath());

        jspService.setPageTitle(req);
        req.setAttribute("isCreatePath", isCreatePath);
        jspService.setProductBrandList(req);
        jspService.setProductList(req);

        if (!isCreatePath) {
            jspService.setProductDetails(req);
        }

        req.getRequestDispatcher("WEB-INF/pages/create.jsp").forward(req, resp);
    }

    /**
     * Handles the HTTP GET method
     *
     * @param req  servlet request
     * @param resp servlet response
     * @throws ServletException throws when it encounters difficulty
     * @throws IOException      throws when I/O operations failed or interrupted
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    /**
     * Handles the HTTP POST method
     *
     * @param req  servlet request
     * @param resp servlet response
     * @throws ServletException throws when it encounters difficulty
     * @throws IOException      throws when I/O operations failed or interrupted
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("/create".equals(req.getServletPath())) {
            productService.create(req);
        } else {
            productService.update(req);
        }

        processRequest(req, resp);
    }
}
