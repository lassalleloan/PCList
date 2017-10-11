package ch.heigvd.pclist.web.controllers;

import ch.heigvd.pclist.models.Cpu;
import ch.heigvd.pclist.models.Gpu;
import ch.heigvd.pclist.models.Ram;
import ch.heigvd.pclist.services.business.ParameterServiceLocal;
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

        // Get type of product
        String product = parameterService.getProduct(req);

        String url = "/pclist/list";

        if (product != null) {
            switch (product) {
                case "pc":
                    String pcBrand = req.getParameter("pcBrand");
                    String pcPrice = req.getParameter("pcPrice");
                    String idCpu = req.getParameter("idCpu");
                    String idRam = req.getParameter("idRam");
                    String idGpu = req.getParameter("idGpu");

                    if (pcBrand != null && pcPrice != null && idCpu != null && idRam != null && idGpu != null) {
                        Cpu cpu = cpuDAO.get(Long.valueOf(idCpu));
                        Ram ram = ramDAO.get(Long.valueOf(idRam));
                        Gpu gpu = gpuDAO.get(Long.valueOf(idGpu));

                        if (cpu != null && ram != null && gpu != null) {
                            // TODO: 07.10.2017 create action for pc
//                          url += "?product=" + product +
//                                  "&action=created" +
//                                  "&rowsAffected=" +
//                                  pcDAO.setPc(new Pc(0, pcBrand, Double.valueOf(pcPrice), cpu, ram, gpu));
                        }
                    }
                    break;

                case "cpu":
                    String cpuBrand = req.getParameter("cpuBrand");
                    String cpuCores = req.getParameter("cpuCores");
                    String cpuFrequency = req.getParameter("cpuFrequency");

                    if (cpuBrand != null && cpuCores != null && cpuFrequency != null) {
                        url += "?product=" + product +
                                "&action=created" +
                                "&rowsAffected=" +
                                cpuDAO.set(new Cpu(0, cpuBrand, Integer.valueOf(cpuCores), Double.valueOf(cpuFrequency)));
                    }
                    break;

                case "ram":
                    String ramBrand = req.getParameter("ramBrand");
                    String ramSize = req.getParameter("ramSize");

                    if (ramBrand != null && ramSize != null) {
                        url += "?product=" + product +
                                "&action=created" +
                                "&rowsAffected=" +
                                ramDAO.set(new Ram(0, ramBrand, Integer.valueOf(ramSize)));
                    }
                    break;

                case "gpu":
                    String gpuBrand = req.getParameter("gpuBrand");

                    if (gpuBrand != null) {
                        url += "?product=" + product +
                                "&action=created" +
                                "&rowsAffected=" +
                                gpuDAO.set(new Gpu(0, gpuBrand));
                    }
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

        // Get type of product
        String product = parameterService.getProduct(req);

        String url = "/pclist/list";

        if (product == null) {
            resp.sendRedirect(url);
        } else {
            switch (product) {
                case "pc":
                    // TODO: 07.10.2017 create action for pc
//                    req.setAttribute("pcBrandList", pcDAO.getBrand());
//                    req.setAttribute("cpuList", cpuDAO.get());
//                    req.setAttribute("ramList", ramDAO.get());
//                    req.setAttribute("gpuList", gpuDAO.get());
                    break;

                case "cpu":
                    req.setAttribute("cpuBrandList", cpuDAO.getBrand());
                    break;

                case "ram":
                    req.setAttribute("ramBrandList", ramDAO.getBrand());
                    break;

                case "gpu":
                    req.setAttribute("gpuBrandList", gpuDAO.getBrand());
                    break;
            }

            parameterService.setPageTitle(req, product);

            req.getRequestDispatcher("WEB-INF/pages/create.jsp").forward(req, resp);
        }
    }
}
