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
public class CreateServlet extends HttpServlet {

    @EJB
    private FactoryServiceLocal factoryService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String product = request.getParameter("product");

        product = product != null && Arrays.asList("pc", "cpu", "ram", "gpu").contains(product) ? product : null;

        String url = "/pclist/list";

        if (product != null) {
            switch (product) {
                case "pc":
                    String pcBrand = request.getParameter("pcBrand");
                    String pcPrice = request.getParameter("pcPrice");
                    String idCpu = request.getParameter("idCpu");
                    String idRam = request.getParameter("idRam");
                    String idGpu = request.getParameter("idGpu");

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
                    String cpuBrand = request.getParameter("cpuBrand");
                    String cpuCores = request.getParameter("cpuCores");
                    String cpuFrequency = request.getParameter("cpuFrequency");

                    if (cpuBrand != null && cpuCores != null && cpuFrequency != null) {
                        url += "?product=" + product +
                                "&action=created" +
                                "&rowsAffected=" +
                                factoryService.setCpu(new Cpu(0, cpuBrand, Integer.valueOf(cpuCores), Double.valueOf(cpuFrequency)));
                    }
                    break;

                case "ram":
                    String ramBrand = request.getParameter("ramBrand");
                    String ramSize = request.getParameter("ramSize");

                    if (ramBrand != null && ramSize != null) {
                        // TODO: 07.10.2017 create action for ram
//                    url += "?product=" + product +
//                            "&action=created" +
//                            "&rowsAffected=" +
//                            factoryService.setRam(new Ram(0, ramBrand, Integer.valueOf(ramSize)));
                    }
                    break;

                case "gpu":
                    String gpuBrand = request.getParameter("gpuBrand");

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

        response.sendRedirect(url);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String product = request.getParameter("product");

        product = product != null && Arrays.asList("pc", "cpu", "ram", "gpu").contains(product) ? product : null;

        String url = "/pclist/list";
        String titlePage = "";

        if (product == null) {
            response.sendRedirect(url);
        } else {
            switch (product) {
                case "pc":
                    // TODO: 07.10.2017 create action for pc
//                    titlePage = "PC";
//                    request.setAttribute("pcBrandList", factoryService.getPcBrand());
//                    request.setAttribute("cpuList", factoryService.getCpu());
//                    request.setAttribute("ramList", factoryService.getRam());
//                    request.setAttribute("gpuList", factoryService.getGpu());
                    break;

                case "cpu":
                    titlePage = "Processor";
                    request.setAttribute("cpuBrandList", factoryService.getCpuBrand());
                    break;

                case "ram":
                    // TODO: 07.10.2017 create action for ram
//                    titlePage = "Memory";
//                    request.setAttribute("ramBrandList", factoryService.getRamBrand());
                    break;

                case "gpu":
                    // TODO: 07.10.2017 create action for gpu
//                    titlePage = "Processor";
//                    request.setAttribute("gpuBrandList", factoryService.getGpuBrand());
                    break;
            }

            request.setAttribute("titlePage", titlePage);
            request.setAttribute("product", product);

            request.getRequestDispatcher("WEB-INF/pages/create.jsp").forward(request, response);
        }
    }
}
