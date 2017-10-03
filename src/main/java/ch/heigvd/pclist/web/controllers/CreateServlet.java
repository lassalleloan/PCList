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
public class CreateServlet extends HttpServlet {

    @EJB
    private FactoryServiceLocal factoryService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pcBrandList", factoryService.getAllPcBrand());
        request.setAttribute("cpuBrandList", factoryService.getAllCpuBrand());
        request.setAttribute("cpuNbCoreList", factoryService.getAllCpuNbCore());
        request.setAttribute("ramBrandList", factoryService.getAllRamBrand());
        request.setAttribute("ramSizeList", factoryService.getAllRamSize());
        request.setAttribute("gpuBrandList", factoryService.getAllGpuBrand());
        request.getRequestDispatcher("/WEB-INF/pages/create.jsp").forward(request, response);
    }
}
