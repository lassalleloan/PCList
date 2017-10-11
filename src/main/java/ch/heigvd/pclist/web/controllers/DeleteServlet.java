package ch.heigvd.pclist.web.controllers;

import ch.heigvd.pclist.services.dao.CpuDAOLocal;
import ch.heigvd.pclist.services.dao.GpuDAOLocal;
import ch.heigvd.pclist.services.dao.PcDAOLocal;
import ch.heigvd.pclist.services.dao.RamDAOLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Handles requests coming from /delete
 *
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 * @since 13.09.2017
 */
public class DeleteServlet extends HttpServlet {

    // TODO: 10.10.2017 Know when a cpu, ram, gpu are bound to a pc and display a information message

    @EJB
    private PcDAOLocal pcDAO;

    @EJB
    private CpuDAOLocal cpuDAO;

    @EJB
    private RamDAOLocal ramDAO;

    @EJB
    private GpuDAOLocal gpuDAO;

    /**
     * Processes requests for both HTTP <code>GET</code> method
     *
     * @param req  servlet request
     * @param resp servlet response
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Get type of product
        String product = req.getParameter("product");
        product = product == null ? "" : product;

        // Get product ID
        long id;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0;
        }

        // Redirect URL
        String url = "/pclist/list";

        // Check if ID is correct
        if (id > 0) {
            switch (product) {
                case "pc":
                    // TODO: 07.10.2017 delete action for pc
//                    url += "?product=" + product + "&action=deleted&rowsAffected=" + pcDAO.delete(id);
                    break;

                case "cpu":
                    url += "?product=" + product + "&action=deleted&rowsAffected=" + cpuDAO.delete(id);
                    break;

                case "ram":
                    url += "?product=" + product + "&action=deleted&rowsAffected=" + ramDAO.delete(id);
                    break;

                case "gpu":
                    url += "?product=" + product + "&action=deleted&rowsAffected=" + gpuDAO.delete(id);
                    break;
            }
        }

        resp.sendRedirect(url);
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
