package ch.heigvd.pclist.web.controllers;

import ch.heigvd.pclist.models.Cpu;
import ch.heigvd.pclist.models.Gpu;
import ch.heigvd.pclist.models.Ram;
import ch.heigvd.pclist.services.FactoryServiceLocal;

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
public class EditServlet extends HttpServlet {

    @EJB
    private FactoryServiceLocal factoryService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String product = req.getParameter("product");
        String idString = req.getParameter("id");

        product = product != null && Arrays.asList("pc", "cpu", "ram", "gpu").contains(product) ? product : null;
        long id = idString != null ? Long.valueOf(idString) : 0;

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
                            // TODO: 07.10.2017 edit action for pc
//                          url += "?product=" + product +
//                                  "&action=edited" +
//                                  "&rowsAffected=" +
//                                  factoryService.updatePc(new Pc(id, pcBrand, Double.valueOf(pcPrice), cpu, ram, gpu));
                        }
                    }
                    break;

                case "cpu":
                    String cpuBrand = req.getParameter("cpuBrand");
                    String cpuCores = req.getParameter("cpuCores");
                    String cpuFrequency = req.getParameter("cpuFrequency");

                    if (cpuBrand != null && cpuCores != null && cpuFrequency != null) {
                        url += "?product=" + product +
                                "&action=edited" +
                                "&rowsAffected=" +
                                factoryService.updateCpu(new Cpu(id, cpuBrand, Integer.valueOf(cpuCores), Double.valueOf(cpuFrequency)));
                    }
                    break;

                case "ram":
                    String ramBrand = req.getParameter("ramBrand");
                    String ramSize = req.getParameter("ramSize");

                    if (ramBrand != null && ramSize != null) {
                        // TODO: 07.10.2017 edit action for ram
//                    url += "?product=" + product +
//                            "&action=edited" +
//                            "&rowsAffected=" +
//                            factoryService.updateRam(new Ram(id, ramBrand, Integer.valueOf(ramSize)));
                    }
                    break;

                case "gpu":
                    String gpuBrand = req.getParameter("gpuBrand");

                    if (gpuBrand != null) {
                        // TODO: 07.10.2017 edit action for gpu
//                    url += "?product=" + product +
//                            "&action=edited" +
//                            "&rowsAffected=" +
//                            factoryService.updateGpu(new Gpu(id, gpuBrand));
                    }
                    break;
            }
        }

        resp.sendRedirect(url);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String product = req.getParameter("product");
        String idString = req.getParameter("id");

        product = product != null && Arrays.asList("pc", "cpu", "ram", "gpu").contains(product) ? product : null;
        long id = idString != null ? Long.valueOf(idString) : 0;

        String url = "/pclist/list";
        String titlePage = "";

        if (product == null || id <= 0) {
            resp.sendRedirect(url);
        } else {
            switch (product) {
                case "pc":
                    // TODO: 07.10.2017 edit action for pc
//                    titlePage = "PC";
//                    req.setAttribute("pc", factoryService.getPC(id));
//                    req.setAttribute("pcBrandList", factoryService.getPcBrand());
//                    req.setAttribute("cpuList", factoryService.getCpu());
//                    req.setAttribute("ramList", factoryService.getRam());
//                    req.setAttribute("gpuList", factoryService.getGpu());
                    break;

                case "cpu":
                    titlePage = "Processor";
                    req.setAttribute("cpu", factoryService.getCpu(id));
                    req.setAttribute("cpuBrandList", factoryService.getCpuBrand());
                    break;

                case "ram":
                    // TODO: 07.10.2017 edit action for ram
//                    titlePage = "Memory";
//                    req.setAttribute("ram", factoryService.getRam(id));
//                    req.setAttribute("ramBrandList", factoryService.getRamBrand());
                    break;

                case "gpu":
                    // TODO: 07.10.2017 edit action for gpu
//                    titlePage = "Graphic";
//                    req.setAttribute("gpu", factoryService.getGpu(id));
//                    req.setAttribute("gpuBrandList", factoryService.getGpuBrand());
                    break;
            }

            req.setAttribute("titlePage", titlePage);
            req.setAttribute("product", product);

            req.getRequestDispatcher("WEB-INF/pages/edit.jsp").forward(req, resp);
        }
    }
}
