package ch.heigvd.pclist.services.business;

import ch.heigvd.pclist.models.Cpu;
import ch.heigvd.pclist.models.Gpu;
import ch.heigvd.pclist.models.Ram;
import ch.heigvd.pclist.services.dao.CpuDAOLocal;
import ch.heigvd.pclist.services.dao.GpuDAOLocal;
import ch.heigvd.pclist.services.dao.PcDAOLocal;
import ch.heigvd.pclist.services.dao.RamDAOLocal;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
@Singleton
public class ProductService implements ProductServiceLocal {

    @EJB
    private ParameterServiceLocal parameterService;

    @EJB
    private EditFormServiceLocal editFormService;

    @EJB
    private PcDAOLocal pcDAO;

    @EJB
    private CpuDAOLocal cpuDAO;

    @EJB
    private RamDAOLocal ramDAO;

    @EJB
    private GpuDAOLocal gpuDAO;

    @Override
    public long count(String product) {
        long numberProduct = 0;

        switch (product) {
            case "pc":
                numberProduct = pcDAO.count();
                break;

            case "cpu":
                numberProduct = cpuDAO.count();
                break;

            case "ram":
                numberProduct = ramDAO.count();
                break;

            case "gpu":
                numberProduct = gpuDAO.count();
                break;
        }

        return numberProduct <= 0 ? 0 : numberProduct;
    }

    @Override
    public Map<String, Object> get(String product, long id) {

        Map<String, Object> objectMap = new HashMap<>();

        switch (product) {
            case "pc":
                objectMap.put("pc", pcDAO.get(id));
                break;

            case "cpu":
                objectMap.put("cpu", cpuDAO.get(id));
                break;

            case "ram":
                objectMap.put("ram", ramDAO.get(id));
                break;

            case "gpu":
                objectMap.put("gpu", gpuDAO.get(id));
                break;
        }

        return objectMap;
    }

    @Override
    public Map<String, Object> get(String product, long pageSize, long pageIndex) {

        Map<String, Object> objectMap = new HashMap<>();

        switch (product) {
            case "pc":
                objectMap.put("pcList", pcDAO.get(pageSize, pageIndex));
                break;

            case "cpu":
                objectMap.put("cpuList", cpuDAO.get(pageSize, pageIndex));
                break;

            case "ram":
                objectMap.put("ramList", ramDAO.get(pageSize, pageIndex));
                break;

            case "gpu":
                objectMap.put("gpuList", gpuDAO.get(pageSize, pageIndex));
                break;
        }

        return objectMap;
    }

    @Override
    public Map<String, Object> getBrand(String product) {

        Map<String, Object> objectMap = new HashMap<>();

        switch (product) {
            case "pc":
                // TODO: 13.10.2017 getBrand action for pc
//                objectMap.put("pcBrandList", pcDAO.getBrand());
                break;

            case "cpu":
                objectMap.put("cpuBrandList", cpuDAO.getBrand());
                break;

            case "ram":
                objectMap.put("ramBrandList", ramDAO.getBrand());
                break;

            case "gpu":
                objectMap.put("gpuBrandList", gpuDAO.getBrand());
                break;
        }

        return objectMap;
    }

    @Override
    public Map<String, Object> getComponent(String product) {
        Map<String, Object> objectMap = new HashMap<>();

        switch (product) {
            case "pc":
                objectMap.put("cpuList", cpuDAO.get());
                objectMap.put("ramList", ramDAO.get());
                objectMap.put("gpuList", gpuDAO.get());
                break;
        }

        return objectMap;
    }

    @Override
    public void create(HttpServletRequest req) {
        String product = parameterService.getProduct(req);
        String pageTitle = parameterService.getPageTitle(req.getServletPath(), product);

        long rowsAffected = 0;
        String informationMessage = "Incoming information is incorrect";

        if (!editFormService.isErrorInput(req)) {
            switch (product) {
                case "pc":
                    // TODO: 13.10.2017 create action for pc
                    String pcBrand = req.getParameter("pcBrand");
                    double pcPrice = parameterService.getUnsignedDouble(req, "pcPrice");
                    Cpu cpu = cpuDAO.get(parameterService.getUnsignedLong(req, "idCpu"));
                    Ram ram = ramDAO.get(parameterService.getUnsignedLong(req, "idRam"));
                    Gpu gpu = gpuDAO.get(parameterService.getUnsignedLong(req, "idGpu"));

                    if (cpu != null && ram != null && gpu != null) {
//                    rowsAffected = pcDAO.setPc(new Pc(0, pcBrand, pcPrice, cpu, ram, gpu));
                    }
                    break;

                case "cpu":
                    String cpuBrand = req.getParameter("cpuBrand");
                    int cpuCores = parameterService.getUnsignedInteger(req, "cpuCores");
                    double cpuFrequency = parameterService.getUnsignedDouble(req, "cpuFrequency");

                    rowsAffected = cpuDAO.set(new Cpu(0, cpuBrand, cpuCores, cpuFrequency));
                    break;

                case "ram":
                    String ramBrand = req.getParameter("ramBrand");
                    int ramSize = parameterService.getUnsignedInteger(req, "ramSize");

                    rowsAffected = ramDAO.set(new Ram(0, ramBrand, ramSize));
                    break;

                case "gpu":
                    String gpuBrand = req.getParameter("gpuBrand");

                    rowsAffected = gpuDAO.set(new Gpu(0, gpuBrand));
                    break;
            }

            if (rowsAffected <= 0) {
                informationMessage = "Incorrect " + pageTitle + " ID";
            } else {
                informationMessage = rowsAffected + " " + pageTitle + " was created";
            }
        }

        parameterService.setInformationMessage(req, informationMessage);
    }

    @Override
    public void update(HttpServletRequest req) {
        String product = parameterService.getProduct(req);
        String pageTitle = parameterService.getPageTitle(req.getServletPath(), product);
        long id = parameterService.getUnsignedLong(req, "id");

        long rowsAffected = 0;
        String informationMessage = "Incoming information is incorrect";

        if (id > 0 && !editFormService.isErrorInput(req)) {
            switch (product) {
                case "pc":
                    // TODO: 07.10.2017 update action for pc
                    String pcBrand = req.getParameter("pcBrand");
                    double pcPrice = parameterService.getUnsignedDouble(req, "pcPrice");
                    Cpu cpu = cpuDAO.get(parameterService.getUnsignedLong(req, "idCpu"));
                    Ram ram = ramDAO.get(parameterService.getUnsignedLong(req, "idRam"));
                    Gpu gpu = gpuDAO.get(parameterService.getUnsignedLong(req, "idGpu"));

                    if (cpu != null && ram != null && gpu != null) {
//                    rowsAffected = pcDAO.update(new Pc(id, pcBrand, pcPrice, cpu, ram, gpu));
                    }
                    break;

                case "cpu":
                    String cpuBrand = req.getParameter("cpuBrand");
                    int cpuCores = parameterService.getUnsignedInteger(req, "cpuCores");
                    double cpuFrequency = parameterService.getUnsignedDouble(req, "cpuFrequency");

                    rowsAffected = cpuDAO.update(new Cpu(id, cpuBrand, cpuCores, cpuFrequency));
                    break;

                case "ram":
                    String ramBrand = req.getParameter("ramBrand");
                    int ramSize = parameterService.getUnsignedInteger(req, "ramSize");

                    rowsAffected = ramDAO.update(new Ram(id, ramBrand, ramSize));
                    break;

                case "gpu":
                    String gpuBrand = req.getParameter("gpuBrand");

                    rowsAffected = gpuDAO.update(new Gpu(id, gpuBrand));
                    break;
            }

            if (rowsAffected <= 0) {
                informationMessage = "Incorrect " + pageTitle + " ID";
            } else {
                informationMessage = rowsAffected + " " + pageTitle + " was updated";
            }
        }

        parameterService.setInformationMessage(req, informationMessage);
    }

    @Override
    public void delete(HttpServletRequest req) {
        String product = parameterService.getProduct(req);
        String pageTitle = parameterService.getPageTitle(req.getServletPath(), product);
        long id = parameterService.getUnsignedLong(req, "id");

        long rowsAffected = 0;
        String informationMessage;

        if (id > 0) {
            switch (product) {
                case "pc":
                    // TODO: 07.10.2017 delete action for pc
//                    rowsAffected = pcDAO.delete(id);
                    break;

                case "cpu":
                    rowsAffected = cpuDAO.delete(id);
                    break;

                case "ram":
                    rowsAffected = ramDAO.delete(id);
                    break;

                case "gpu":
                    rowsAffected = gpuDAO.delete(id);
                    break;
            }
        }

        if (rowsAffected <= 0) {
            informationMessage = "Incorrect " + pageTitle + " ID";
        } else {
            informationMessage = rowsAffected + " " + pageTitle + " was deleted";
        }

        parameterService.setInformationMessage(req, informationMessage);
    }
}
