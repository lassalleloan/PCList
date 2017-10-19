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
        long productGenerated = jspService.getUnsignedLong(req, "productGenerated");
        return productGenerated <= 0 || productGenerated > 123456789;
    }

    @Override
    public boolean isCreateError(HttpServletRequest req) {
        final int lengthMax = 45;
        boolean isError = false;

        String product = jspService.getProduct(req);

        switch (product) {
            case "pc":
                String pcBrand = req.getParameter("pcBrand");
                double pcPrice = jspService.getUnsignedDouble(req, "pcPrice");
                long idCpu = jspService.getUnsignedLong(req, "idCpu");
                long idRam = jspService.getUnsignedLong(req, "idRam");
                long idGpu = jspService.getUnsignedLong(req, "idGpu");

                isError = pcBrand.isEmpty() || pcBrand.charAt(0) == ' ' || pcBrand.length() > lengthMax || pcPrice <= 0
                        || idCpu <= 0 || idRam <= 0 || idGpu <= 0;
                break;

            case "cpu":
                String cpuBrand = req.getParameter("cpuBrand");
                int cpuCores = jspService.getUnsignedInteger(req, "cpuCores");
                double cpuFrequency = jspService.getUnsignedDouble(req, "cpuFrequency");

                isError = cpuBrand.isEmpty() || cpuBrand.charAt(0) == ' ' || cpuBrand.length() > lengthMax
                        || cpuCores <= 0 || cpuFrequency <= 0;
                break;

            case "ram":
                String ramBrand = req.getParameter("ramBrand");
                int ramSize = jspService.getUnsignedInteger(req, "ramSize");

                isError = ramBrand.isEmpty() || ramBrand.charAt(0) == ' ' || ramBrand.length() > lengthMax
                        || ramSize <= 0;
                break;

            case "gpu":
                String gpuBrand = req.getParameter("gpuBrand");

                isError = gpuBrand.isEmpty() || gpuBrand.charAt(0) == ' ' || gpuBrand.length() > lengthMax;
                break;
        }

        return isError;
    }
}
