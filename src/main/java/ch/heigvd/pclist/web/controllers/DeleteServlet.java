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
public class DeleteServlet extends HttpServlet {

    @EJB
    private FactoryServiceLocal factoryService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String what = request.getParameter("what");
        String idString = request.getParameter("id");

        what = what != null && Arrays.asList("pc", "cpu", "ram", "gpu").contains(what) ? what : null;
        long id = idString != null ? Long.valueOf(idString) : 0;
        String url = "/pclist/list";

        if (what != null && id > 0) {
            url += "?what=" + what;

            if (what.equals("cpu")) {
                url += "&rowsAffected=" + factoryService.deleteCpu(id);
            }
        }

        response.sendRedirect(url);
    }
}
