package ch.heigvd.pclist.web.controllers;

import ch.heigvd.pclist.services.FactoryServiceLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
public class ListServlet extends HttpServlet {

    @EJB
    private FactoryServiceLocal factoryService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String product = request.getParameter("product");
        String action = request.getParameter("action");
        String rowsAffectedString = request.getParameter("rowsAffected");

        product = product != null && Arrays.asList("pc", "cpu", "ram", "gpu").contains(product) ? product : null;
        action = action != null && Arrays.asList("created", "edited", "deleted").contains(action) ? action : null;
        long rowsAffected = rowsAffectedString != null ? Long.valueOf(rowsAffectedString) : 0;

        boolean isAllList = product == null;
        String titlePage = "";
        Map<String, Object> objectMap = new HashMap<>();

        if (isAllList || product.equals("pc")) {
            titlePage = "PC";
            objectMap.put("pcList", factoryService.getPc());
        }

        if (isAllList || product.equals("cpu")) {
            titlePage = "Processor";
            objectMap.put("cpuList", factoryService.getCpu());
        }

        if (isAllList || product.equals("ram")) {
            titlePage = "Memory";
            objectMap.put("ramList", factoryService.getRam());
        }

        if (isAllList || product.equals("gpu")) {
            titlePage = "Graphic";
            objectMap.put("gpuList", factoryService.getGpu());
        }

        if (isAllList) {
            titlePage = "All";
        }

        request.setAttribute("titlePage", titlePage);
        request.setAttribute("allList", isAllList);

        if (rowsAffected > 0) {
            request.setAttribute("product", titlePage);
            request.setAttribute("action", action);
            request.setAttribute("rowsAffected", rowsAffected);
        }

        for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }

        request.getRequestDispatcher("WEB-INF/pages/list.jsp").forward(request, response);
    }
}
