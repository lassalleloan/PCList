package ch.heigvd.pclist.services.business;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
@Singleton
public class EditFormService implements EditFormServiceLocal {

    @EJB
    private ParameterServiceLocal parameterService;

    @Override
    public boolean isErrorInput(HttpServletRequest req) {
        boolean isError = false;

        // Gets type of product
        String product = parameterService.getProduct(req);

        switch (product) {
            case "pc":
                String pcBrand = req.getParameter("pcBrand");
                double pcPrice = parameterService.getUnsignedDouble(req, "pcPrice");
                long idCpu = parameterService.getUnsignedLong(req, "idCpu");
                long idRam = parameterService.getUnsignedLong(req, "idRam");
                long idGpu = parameterService.getUnsignedLong(req, "idGpu");

                isError = pcBrand.isEmpty() || pcBrand.charAt(0) == ' ' || pcPrice <= 0
                        || idCpu <= 0 || idRam <= 0 || idGpu <= 0;
                break;

            case "cpu":
                String cpuBrand = req.getParameter("cpuBrand");
                int cpuCores = parameterService.getUnsignedInteger(req, "cpuCores");
                double cpuFrequency = parameterService.getUnsignedDouble(req, "cpuFrequency");

                isError = cpuBrand.isEmpty() || cpuBrand.charAt(0) == ' ' || cpuCores <= 0 || cpuFrequency <= 0;
                break;

            case "ram":
                String ramBrand = req.getParameter("ramBrand");
                int ramSize = parameterService.getUnsignedInteger(req, "ramSize");

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
