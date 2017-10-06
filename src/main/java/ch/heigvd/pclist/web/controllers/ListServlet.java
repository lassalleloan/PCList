package ch.heigvd.pclist.web.controllers;

import ch.heigvd.pclist.services.FactoryServiceLocal;
import ch.heigvd.pclist.util.Chance;

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
        String what = request.getParameter("what");
        what = what != null && Arrays.asList("pc", "cpu", "ram", "gpu").contains(what) ? what : null;

        boolean isAllList = what == null;
        String titlePage = "";
        Map<String, Object> objectMap = new HashMap<>();

        if (isAllList || what.equals("pc")) {
            titlePage = "PC";
            objectMap.put("pcList", factoryService.getAllPc());
        }

        if (isAllList || what.equals("cpu")) {
            factoryService.setOneCpu(Chance.randomCpu());

            titlePage = "Processor";
            objectMap.put("cpuList", factoryService.getAllCpu());
        }

        if (isAllList || what.equals("ram")) {
            titlePage = "Memory";
            objectMap.put("ramList", factoryService.getAllRam());
        }

        if (isAllList || what.equals("gpu")) {
            titlePage = "Graphic";
            objectMap.put("gpuList", factoryService.getAllGpu());
        }

        if (isAllList) {
            titlePage = "All";
        }

        request.setAttribute("titlePage", titlePage);
        request.setAttribute("allList", isAllList);

        for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }

        request.getRequestDispatcher("WEB-INF/pages/list.jsp").forward(request, response);
    }
}
