package ch.heigvd.pclist.web.controllers;

import ch.heigvd.pclist.models.Cpu;
import ch.heigvd.pclist.models.Gpu;
import ch.heigvd.pclist.models.Ram;
import ch.heigvd.pclist.services.business.FactoryServiceLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
public class CreateServlet extends HttpServlet {

    @EJB
    private FactoryServiceLocal factoryService;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String product = req.getParameter("product");

        product = product != null && Arrays.asList("pc", "cpu", "ram", "gpu").contains(product) ? product : null;

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
                        Cpu cpu = factoryService.getCpu(Long.valueOf(idCpu));
                        Ram ram = factoryService.getRam(Long.valueOf(idRam));
                        Gpu gpu = factoryService.getGpu(Long.valueOf(idGpu));

                        if (cpu != null && ram != null && gpu != null) {
                            // TODO: 07.10.2017 create action for pc
//                          url += "?product=" + product +
//                                  "&action=created" +
//                                  "&rowsAffected=" +
//                                  factoryService.setPc(new Pc(0, pcBrand, Double.valueOf(pcPrice), cpu, ram, gpu));
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
                                factoryService.setCpu(new Cpu(0, cpuBrand, Integer.valueOf(cpuCores), Double.valueOf(cpuFrequency)));
                    }
                    break;

                case "ram":
                    String ramBrand = req.getParameter("ramBrand");
                    String ramSize = req.getParameter("ramSize");

                    if (ramBrand != null && ramSize != null) {
                        // TODO: 07.10.2017 create action for ram
//                    url += "?product=" + product +
//                            "&action=created" +
//                            "&rowsAffected=" +
//                            factoryService.setRam(new Ram(0, ramBrand, Integer.valueOf(ramSize)));
                    }
                    break;

                case "gpu":
                    String gpuBrand = req.getParameter("gpuBrand");

                    if (gpuBrand != null) {
                        // TODO: 07.10.2017 create action for gpu
//                    url += "?product=" + product +
//                            "&action=created" +
//                            "&rowsAffected=" +
//                            factoryService.setGpu(new Gpu(0, gpuBrand));
                    }
                    break;
            }
        }

        resp.sendRedirect(url);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String product = req.getParameter("product");

        product = product != null && Arrays.asList("pc", "cpu", "ram", "gpu").contains(product) ? product : null;

        String url = "/pclist/list";
        String pageTitle = "";

        if (product == null) {
            resp.sendRedirect(url);
        } else {
            switch (product) {
                case "pc":
                    // TODO: 07.10.2017 create action for pc
//                    pageTitle = "PC";
//                    req.setAttribute("pcBrandList", factoryService.getPcBrand());
//                    req.setAttribute("cpuList", factoryService.getCpu());
//                    req.setAttribute("ramList", factoryService.getRam());
//                    req.setAttribute("gpuList", factoryService.getGpu());
                    break;

                case "cpu":
                    pageTitle = "Processor";
                    req.setAttribute("cpuBrandList", factoryService.getCpuBrand());
                    break;

                case "ram":
                    // TODO: 07.10.2017 create action for ram
//                    pageTitle = "Memory";
//                    req.setAttribute("ramBrandList", factoryService.getRamBrand());
                    break;

                case "gpu":
                    // TODO: 07.10.2017 create action for gpu
//                    pageTitle = "Graphic";
//                    req.setAttribute("gpuBrandList", factoryService.getGpuBrand());
                    break;
            }

            req.setAttribute("pageTitle", pageTitle);
            req.setAttribute("product", product);

            req.getRequestDispatcher("WEB-INF/pages/create.jsp").forward(req, resp);
        }
    }
}
