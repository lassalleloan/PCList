package ch.heigvd.pclist.web.controllers;

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String what = request.getParameter("what");
        long id = Long.valueOf(request.getParameter("id"));

        what = what != null && Arrays.asList("pc", "cpu", "ram", "gpu").contains(what) ? what : null;

        String titlePage = "All";
        Object o;

        switch (what) {
            case "pc":
                titlePage = "Pc";
                o = factoryService.getPc(id);
                break;

            case "cpu":
                titlePage = "Processor";
                o = factoryService.getCpu(id);
                break;

            case "ram":
                titlePage = "Memory";
                o = factoryService.getRam(id);
                break;

            case "gpu":
                titlePage = "Graphic";
                o = factoryService.getGpu(id);
                break;

            default:
                request.setAttribute("titlePage", titlePage);
                request.getRequestDispatcher("WEB-INF/pages/home.jsp").forward(request, response);
                return;
        }

        request.setAttribute("titlePage", titlePage);
        request.setAttribute(what, o);
        request.getRequestDispatcher("WEB-INF/pages/edit.jsp").forward(request, response);
    }
}
