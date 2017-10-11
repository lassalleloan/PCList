package ch.heigvd.pclist.web.controllers;

import ch.heigvd.pclist.services.dao.CpuDAOLocal;
import ch.heigvd.pclist.services.dao.GpuDAOLocal;
import ch.heigvd.pclist.services.dao.PcDAOLocal;
import ch.heigvd.pclist.services.dao.RamDAOLocal;
import ch.heigvd.pclist.util.Chance;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Handles requests coming from /configuration
 *
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 * @since 13.09.2017
 */
public class ConfigurationServlet extends HttpServlet {

    @EJB
    private PcDAOLocal pcDAO;

    @EJB
    private CpuDAOLocal cpuDAO;

    @EJB
    private RamDAOLocal ramDAO;

    @EJB
    private GpuDAOLocal gpuDAO;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods
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

        // Get number of product to generate
        long numberGenerate;
        try {
            numberGenerate = Integer.parseInt(req.getParameter("numberGenerate"));
        } catch (NumberFormatException e) {
            numberGenerate = 0;
        }

        String pageTitle;

        switch (product) {
            case "pc":
                // TODO: 07.10.2017 configuration action for pc
                pageTitle = "PC";

                for (long i = 0; i < numberGenerate; ++i) {
//                    pcDAO.set(Chance.randomPc());
                }
                break;

            case "cpu":
                pageTitle = "Processor";

                for (long i = 0; i < numberGenerate; ++i) {
                    cpuDAO.set(Chance.randomCpu());
                }
                break;

            case "ram":
                pageTitle = "Memory";

                for (long i = 0; i < numberGenerate; ++i) {
                    ramDAO.set(Chance.randomRam());
                }
                break;

            case "gpu":
                pageTitle = "Graphic";

                for (long i = 0; i < numberGenerate; ++i) {
                    gpuDAO.set(Chance.randomGpu());
                }
                break;

            default:
                resp.sendRedirect("/pclist/configuration?product=pc");
                return;
        }

        req.setAttribute("pageTitle", pageTitle);
        req.getRequestDispatcher("WEB-INF/pages/configuration.jsp").forward(req, resp);
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

    /**
     * Handles the HTTP <code>POST</code> method
     *
     * @param req  servlet request
     * @param resp servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
