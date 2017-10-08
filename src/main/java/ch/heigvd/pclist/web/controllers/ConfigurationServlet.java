package ch.heigvd.pclist.web.controllers;

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
public class ConfigurationServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String product = req.getParameter("product");

        product = product != null && Arrays.asList("pc", "cpu", "ram", "gpu").contains(product) ? product : null;

        String url = "/pclist/configuration?product=pc";
        String pageTitle = "";

        if (product == null) {
            resp.sendRedirect(url);
        } else {
            switch (product) {
                case "pc":
                    // TODO: 07.10.2017 configuration action for pc
                    pageTitle = "PC";
                    break;

                case "cpu":
                    // TODO: 07.10.2017 configuration action for cpu
                    pageTitle = "Processor";
                    break;

                case "ram":
                    // TODO: 07.10.2017 configuration action for ram
                    pageTitle = "Memory";
                    break;

                case "gpu":
                    // TODO: 07.10.2017 configuration action for gpu
                    pageTitle = "Graphic";
                    break;
            }

            req.setAttribute("pageTitle", pageTitle);
            req.setAttribute("product", product);

            req.getRequestDispatcher("WEB-INF/pages/configuration.jsp").forward(req, resp);
        }
    }
}
