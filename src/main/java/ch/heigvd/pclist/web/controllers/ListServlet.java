package ch.heigvd.pclist.web.controllers;

import ch.heigvd.pclist.services.FactoryServiceLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
public class ListServlet extends HttpServlet {

    @EJB
    private FactoryServiceLocal factoryService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pcList", factoryService.getAllPc());
        request.setAttribute("cpuList", factoryService.getAllCpu());
        request.setAttribute("ramList", factoryService.getAllRam());
        request.setAttribute("gpuList", factoryService.getAllGpu());

        request.getRequestDispatcher("WEB-INF/pages/list.jsp").forward(request, response);
    }
}
