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
import java.util.HashMap;
import java.util.Map;

/**
 * Handles requests coming from /edit
 *
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 * @since 13.09.2017
 */
public class EditServlet extends HttpServlet {

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

        // Gets type of product and product ID
        String product = parameterService.getProduct(req);
        long id = parameterService.getUnsignedLong(req, "id");

        // Gets variables of form for pc
        String pcBrand = req.getParameter("pcBrand");
        pcBrand = pcBrand == null ? "" : pcBrand;
        Double pcPrice;
        Long idCpu;
        Long idRam;
        Long idGpu;
        try {
            pcPrice = Double.parseDouble(req.getParameter("pcPrice"));
            idCpu = Long.parseLong(req.getParameter("idCpu"));
            idRam = Long.parseLong(req.getParameter("idRam"));
            idGpu = Long.parseLong(req.getParameter("idGpu"));
        } catch (Exception e) {
            pcPrice = 0d;
            idCpu = 0L;
            idRam = 0L;
            idGpu = 0L;
        }

        // Gets variables of form for cpu
        String cpuBrand = req.getParameter("cpuBrand");
        cpuBrand = cpuBrand == null ? "" : cpuBrand;
        Integer cpuCores;
        Double cpuFrequency;
        try {
            cpuCores = Integer.parseInt(req.getParameter("cpuCores"));
            cpuFrequency = Double.parseDouble(req.getParameter("cpuFrequency"));
        } catch (Exception e) {
            cpuCores = 0;
            cpuFrequency = 0d;
        }

        // Gets variables of form for ram
        String ramBrand = req.getParameter("ramBrand");
        ramBrand = ramBrand == null ? "" : ramBrand;
        Integer ramSize;
        try {
            ramSize = Integer.parseInt(req.getParameter("ramSize"));
        } catch (NumberFormatException e) {
            ramSize = 0;
        }

        // Gets variables of form for gpu
        String gpuBrand = req.getParameter("gpuBrand");
        gpuBrand = gpuBrand == null ? "" : gpuBrand;

        String url = "/pclist/list";
        Map<String, Object> objectMap = new HashMap<>();

        // Checks if ID is correct
        if (id <= 0) {
            resp.sendRedirect(url);
        } else {
            switch (product) {
                case "pc":
                    // TODO: 07.10.2017 edit action for pc
//                    if (!pcBrand.isEmpty() && pcPrice > 0 && idCpu > 0 && idRam > 0 && idGpu > 0) {
//
//                        // Updates pc, if it exists
//                        Cpu cpu = cpuDAO.get(idCpu);
//                        Ram ram = ramDAO.get(idRam);
//                        Gpu gpu = gpuDAO.get(idGpu);
//
//                        if (cpu != null && ram != null && gpu != null) {
//                          url += "?product=" + product +
//                                  "&action=edited" +
//                                  "&rowsAffected=" +
//                                  pcDAO.update(new Pc(id, pcBrand, pcPrice, cpu, ram, gpu));
//                        }
//                    } else {
//
//                        // Fills page for editing on pc
//                        objectMap.put("pc", pcDAO.get(id));
//                        objectMap.put("pcBrandList", pcDAO.getBrand());
//                        objectMap.put("cpuList", cpuDAO.get());
//                        objectMap.put("ramList", ramDAO.get());
//                        objectMap.put("gpuList", gpuDAO.get());
//                    }
                    break;

                case "cpu":
                    if (!cpuBrand.isEmpty() && cpuCores > 0 && cpuFrequency > 0) {

                        // Updates cpu, if it exists
                        url += "?product=" + product +
                                "&action=edited" +
                                "&rowsAffected=" +
                                cpuDAO.update(new Cpu(id, cpuBrand, cpuCores, cpuFrequency));
                    } else {

                        // Fills page for editing on cpu
                        objectMap.put("cpu", cpuDAO.get(id));
                        objectMap.put("cpuBrandList", cpuDAO.get());
                    }
                    break;

                case "ram":
                    if (!ramBrand.isEmpty() && ramSize > 0) {

                        // Updates ram, if it exists
                        url += "?product=" + product +
                                "&action=edited" +
                                "&rowsAffected=" +
                                ramDAO.update(new Ram(id, ramBrand, ramSize));
                    } else {

                        // Fills page for editing on ram
                        objectMap.put("ram", ramDAO.get(id));
                        objectMap.put("ramBrandList", ramDAO.getBrand());
                    }
                    break;

                case "gpu":
                    if (!gpuBrand.isEmpty()) {

                        // Updates gpu, if it exists
                        url += "?product=" + product +
                                "&action=edited" +
                                "&rowsAffected=" +
                                gpuDAO.update(new Gpu(id, gpuBrand));
                    } else {

                        // Fills page for editing on gpu
                        objectMap.put("gpu", gpuDAO.get(id));
                        objectMap.put("gpuBrandList", gpuDAO.getBrand());
                    }
                    break;

                default:
                    resp.sendRedirect("/pclist/list");
                    return;
            }

            if (!pcBrand.isEmpty() || !cpuBrand.isEmpty() || !ramBrand.isEmpty() || !gpuBrand.isEmpty()) {
                resp.sendRedirect(url);
            } else {
                parameterService.setPageTitle(req, product);

                for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
                    req.setAttribute(entry.getKey(), entry.getValue());
                }

                req.getRequestDispatcher("WEB-INF/pages/edit.jsp").forward(req, resp);
            }
        }
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
