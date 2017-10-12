package ch.heigvd.pclist.web.controllers;

import ch.heigvd.pclist.services.business.ParameterServiceLocal;
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
    private ParameterServiceLocal parameterService;

    @EJB
    private PcDAOLocal pcDAO;

    @EJB
    private CpuDAOLocal cpuDAO;

    @EJB
    private RamDAOLocal ramDAO;

    @EJB
    private GpuDAOLocal gpuDAO;

    /**
     * Processes requests for both HTTP GET and POST methods
     *
     * @param req  servlet request
     * @param resp servlet response
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Gets type of product and number of product to generate
        String product = parameterService.getProduct(req);
        long productGenerated = parameterService.getUnsignedLong(req, "productGenerated");

        switch (product) {
            case "pc":
                // TODO: 07.10.2017 configuration action for pc
                for (long i = 0; i < productGenerated; ++i) {
//                    pcDAO.set(Chance.randomPc());
                }
                break;

            case "cpu":
                for (long i = 0; i < productGenerated; ++i) {
                    cpuDAO.set(Chance.randomCpu());
                }
                break;

            case "ram":
                for (long i = 0; i < productGenerated; ++i) {
                    ramDAO.set(Chance.randomRam());
                }
                break;

            case "gpu":
                for (long i = 0; i < productGenerated; ++i) {
                    gpuDAO.set(Chance.randomGpu());
                }
                break;

            default:
                resp.sendRedirect("/pclist/configuration?product=pc");
                return;
        }

        parameterService.setPageTitle(req);
        req.getRequestDispatcher("WEB-INF/pages/configuration.jsp").forward(req, resp);
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
        processRequest(req, resp);
    }
}
