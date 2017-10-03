package ch.heigvd.pclist.web.controllers;

import ch.heigvd.pclist.services.FactoryServiceLocal;
import ch.heigvd.pclist.services.TestDataServiceLocal;

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
    private TestDataServiceLocal testDataService;

    @EJB
    private FactoryServiceLocal factoryService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        testDataService.generateTestData();
        Object model = factoryService.getAllPcs();
        request.setAttribute("pcList", model);
        request.getRequestDispatcher("/WEB-INF/pages/list.jsp").forward(request, response);
    }
}
