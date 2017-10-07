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

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String product = req.getParameter("product");
        String idString = req.getParameter("id");

        product = product != null && Arrays.asList("pc", "cpu", "ram", "gpu").contains(product) ? product : null;
        long id = idString != null ? Long.valueOf(idString) : 0;

        String url = "/pclist/list";

        if (product != null && id > 0) {
            url += "?product=" + product + "&action=deleted";

            switch (product) {
                case "pc":
                    // TODO: 07.10.2017 delete action for pc
//                    url += "&rowsAffected=" + factoryService.deletePc(id);
                    break;

                case "cpu":
                    url += "&rowsAffected=" + factoryService.deleteCpu(id);
                    break;

                case "ram":
                    // TODO: 07.10.2017 delete action for ram
//                    url += "&rowsAffected=" + factoryService.deleteRam(id);
                    break;

                case "gpu":
                    // TODO: 07.10.2017 delete action for gpu
//                    url += "&rowsAffected=" + factoryService.deleteGpu(id);
                    break;
            }
        }

        resp.sendRedirect(url);
    }
}
