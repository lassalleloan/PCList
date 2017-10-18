package ch.heigvd.pclist.services.business;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.servlet.http.HttpServletRequest;

/**
 * Handles input from form
 *
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 * @since 13.09.2017
 */
@Singleton
public class FormService implements FormServiceLocal {

    @EJB
    private JspServiceLocal jspService;

    @Override
    public boolean isConfigurationError(HttpServletRequest req) {
        return jspService.getUnsignedLong(req, "productGenerated") <= 0;
    }

    @Override
    public boolean isCreateError(HttpServletRequest req) {
        boolean isError = false;

        String product = jspService.getProduct(req);

        switch (product) {
            case "pc":
                String pcBrand = req.getParameter("pcBrand");
                double pcPrice = jspService.getUnsignedDouble(req, "pcPrice");
                long idCpu = jspService.getUnsignedLong(req, "idCpu");
                long idRam = jspService.getUnsignedLong(req, "idRam");
                long idGpu = jspService.getUnsignedLong(req, "idGpu");

                isError = pcBrand.isEmpty() || pcBrand.charAt(0) == ' ' || pcPrice <= 0
                        || idCpu <= 0 || idRam <= 0 || idGpu <= 0;
                break;

            case "cpu":
                String cpuBrand = req.getParameter("cpuBrand");
                int cpuCores = jspService.getUnsignedInteger(req, "cpuCores");
                double cpuFrequency = jspService.getUnsignedDouble(req, "cpuFrequency");

                isError = cpuBrand.isEmpty() || cpuBrand.charAt(0) == ' ' || cpuCores <= 0 || cpuFrequency <= 0;
                break;

            case "ram":
                String ramBrand = req.getParameter("ramBrand");
                int ramSize = jspService.getUnsignedInteger(req, "ramSize");

                isError = ramBrand.isEmpty() || ramBrand.charAt(0) == ' ' || ramSize <= 0;
                break;

            case "gpu":
                String gpuBrand = req.getParameter("gpuBrand");

                isError = gpuBrand.isEmpty() || gpuBrand.charAt(0) == ' ';
                break;
        }

        return isError;
    }
}
