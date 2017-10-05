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
public class EditServlet extends HttpServlet {

    @EJB
    private FactoryServiceLocal factoryService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String what = request.getParameter("what");
        long id = Long.valueOf(request.getParameter("id"));

        request.setAttribute("titlePage", what.toUpperCase());

        switch (what) {
            case "pc":
                request.setAttribute("pc", factoryService.getOnePc(id));
                break;

            case "cpu":
                request.setAttribute("cpu", factoryService.getOneCpu(id));
                break;

            case "ram":
                request.setAttribute("ram", factoryService.getOneRam(id));
                break;

            case "gpu":
                request.setAttribute("gpu", factoryService.getOneGpu(id));
                break;
        }

        request.getRequestDispatcher("WEB-INF/pages/edit.jsp").forward(request, response);
    }
}
