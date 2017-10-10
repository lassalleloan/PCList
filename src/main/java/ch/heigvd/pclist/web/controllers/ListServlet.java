package ch.heigvd.pclist.web.controllers;

import ch.heigvd.pclist.services.dao.CpuDAOLocal;
import ch.heigvd.pclist.services.dao.GpuDAOLocal;
import ch.heigvd.pclist.services.dao.PcDAOLocal;
import ch.heigvd.pclist.services.dao.RamDAOLocal;

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
 * Handles requests coming from /list
 *
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 * @since 13.09.2017
 */
public class ListServlet extends HttpServlet {

    @EJB
    private PcDAOLocal pcDAO;

    @EJB
    private CpuDAOLocal cpuDAO;

    @EJB
    private RamDAOLocal ramDAO;

    @EJB
    private GpuDAOLocal gpuDAO;

    /**
     * Processes requests for both HTTP <code>GET</code> method
     *
     * @param req  servlet request
     * @param resp servlet response
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String product = req.getParameter("product");
        String action = req.getParameter("action");
        String rowsAffectedString = req.getParameter("rowsAffected");

        product = product != null && Arrays.asList("pc", "cpu", "ram", "gpu").contains(product) ? product : null;
        action = action != null && Arrays.asList("created", "edited", "deleted").contains(action) ? action : null;
        long rowsAffected = rowsAffectedString != null ? Long.valueOf(rowsAffectedString) : 0;

        boolean isAllList = product == null;
        String pageTitle = "";
        Map<String, Object> objectMap = new HashMap<>();

        int pageSize;
        try {
            pageSize = Integer.parseInt(req.getParameter("pageSize"));
        } catch (NumberFormatException e) {
            pageSize = isAllList ? 2 : 10;
        }

        int pageIndex;
        try {
            pageIndex = Integer.parseInt(req.getParameter("pageIndex"));
        } catch (NumberFormatException e) {
            pageIndex = 0;
        }

        long numberOfPages = 0;

        if (isAllList || product.equals("pc")) {
            pageTitle = "PC";
            objectMap.put("pcList", pcDAO.get());
        }

        if (isAllList || product.equals("cpu")) {
            pageTitle = "Processor";
            objectMap.put("cpuList", cpuDAO.get(pageSize, pageIndex));
            numberOfPages = (cpuDAO.count() + pageSize - 1) / pageSize;
        }

        if (isAllList || product.equals("ram")) {
            pageTitle = "Memory";
            objectMap.put("ramList", ramDAO.get());
        }

        if (isAllList || product.equals("gpu")) {
            pageTitle = "Graphic";
            objectMap.put("gpuList", gpuDAO.get());
        }

        if (isAllList) {
            pageTitle = "All";
        }

        objectMap.put("pageTitle", pageTitle);
        objectMap.put("allList", isAllList);

        if (rowsAffected > 0) {
            objectMap.put("product", pageTitle);
            objectMap.put("action", action);
            objectMap.put("rowsAffected", rowsAffected);
        }

        objectMap.put("firstPageLink", "/list?product=" + product + "&pageSize=" + pageSize + "&pageIndex=0");
        objectMap.put("previousPageLink", "/list?product=" + product + "&pageSize=" + pageSize + "&pageIndex=" + Math.max(0, pageIndex - 1));
        objectMap.put("nextPageLink", "/list?product=" + product + "&pageSize=" + pageSize + "&pageIndex=" + Math.min(pageIndex + 1, numberOfPages - 1));
        objectMap.put("lastPageLink", "/list?product=" + product + "&pageSize=" + pageSize + "&pageIndex=" + (numberOfPages - 1));
        objectMap.put("pageCount", numberOfPages);

        for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
            req.setAttribute(entry.getKey(), entry.getValue());
        }

        req.getRequestDispatcher("WEB-INF/pages/list.jsp").forward(req, resp);
    }

    /**
     * Handles the HTTP <code>GET</code> method
     *
     * @param req  servlet request
     * @param resp servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
