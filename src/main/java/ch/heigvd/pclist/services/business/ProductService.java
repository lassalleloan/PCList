package ch.heigvd.pclist.services.business;

import ch.heigvd.pclist.models.Cpu;
import ch.heigvd.pclist.models.Gpu;
import ch.heigvd.pclist.models.Pc;
import ch.heigvd.pclist.models.Ram;
import ch.heigvd.pclist.services.dao.CpuDAOLocal;
import ch.heigvd.pclist.services.dao.GpuDAOLocal;
import ch.heigvd.pclist.services.dao.PcDAOLocal;
import ch.heigvd.pclist.services.dao.RamDAOLocal;
import ch.heigvd.pclist.util.Chance;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles actions for all products
 *
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 * @since 13.09.2017
 */
@Singleton
public class ProductService implements ProductServiceLocal {

    @EJB
    private JspServiceLocal jspService;

    @EJB
    private FormServiceLocal formService;

    @EJB
    private PcDAOLocal pcDAO;

    @EJB
    private CpuDAOLocal cpuDAO;

    @EJB
    private RamDAOLocal ramDAO;

    @EJB
    private GpuDAOLocal gpuDAO;

    @Override
    public void generate(HttpServletRequest req) {
        String informationMessage = "Incoming information are incorrect";

        if (!formService.isConfigurationError(req)) {
            String product = jspService.getProduct(req);
            String nameProduct = jspService.NAME_PRODUCT_STRING_MAP.get(product);
            long productGenerated = jspService.getUnsignedLong(req, "productGenerated");

            long rowsAffected = 0;

            switch (product) {
                case "pc":
                    rowsAffected = pcDAO.set(Chance.randomPc(productGenerated));
                    break;

                case "cpu":
                    rowsAffected = cpuDAO.set(Chance.randomCpu(productGenerated));
                    break;

                case "ram":
                    rowsAffected = ramDAO.set(Chance.randomRam(productGenerated));
                    break;

                case "gpu":
                    rowsAffected = gpuDAO.set(Chance.randomGpu(productGenerated));
                    break;
            }

            if (rowsAffected <= 0) {
                informationMessage = "Internal error for " + nameProduct;
            } else {
                informationMessage = rowsAffected + " " + nameProduct + (rowsAffected <= 1 ? " was " : " were ") + "created";
            }
        }

        jspService.setInformationMessage(req, informationMessage);
    }

    @Override
    public void create(HttpServletRequest req) {
        String informationMessage = "Incoming information are incorrect";

        if (!formService.isCreateError(req)) {
            String product = jspService.getProduct(req);
            String nameProduct = jspService.NAME_PRODUCT_STRING_MAP.get(product);

            long rowsAffected = 0;

            switch (product) {
                case "pc":
                    String pcBrand = req.getParameter("pcBrand");
                    double pcPrice = jspService.getUnsignedDouble(req, "pcPrice");
                    Cpu cpu = cpuDAO.get(jspService.getUnsignedLong(req, "idCpu"));
                    Ram ram = ramDAO.get(jspService.getUnsignedLong(req, "idRam"));
                    Gpu gpu = gpuDAO.get(jspService.getUnsignedLong(req, "idGpu"));

                    if (cpu != null && ram != null && gpu != null) {
                        rowsAffected = pcDAO.set(new Pc(0, pcBrand, pcPrice, cpu, ram, gpu));
                    }
                    break;

                case "cpu":
                    String cpuBrand = req.getParameter("cpuBrand");
                    int cpuCores = jspService.getUnsignedInteger(req, "cpuCores");
                    double cpuFrequency = jspService.getUnsignedDouble(req, "cpuFrequency");

                    rowsAffected = cpuDAO.set(new Cpu(0, cpuBrand, cpuCores, cpuFrequency));
                    break;

                case "ram":
                    String ramBrand = req.getParameter("ramBrand");
                    int ramSize = jspService.getUnsignedInteger(req, "ramSize");

                    rowsAffected = ramDAO.set(new Ram(0, ramBrand, ramSize));
                    break;

                case "gpu":
                    String gpuBrand = req.getParameter("gpuBrand");

                    rowsAffected = gpuDAO.set(new Gpu(0, gpuBrand));
                    break;
            }

            if (rowsAffected <= 0) {
                informationMessage = "Internal error for " + nameProduct;
            } else {
                informationMessage = rowsAffected + " " + nameProduct + (rowsAffected <= 1 ? " was " : " were ") + "created";
            }
        }

        jspService.setInformationMessage(req, informationMessage);
    }

    @Override
    public void update(HttpServletRequest req) {
        long id = jspService.getUnsignedLong(req, "id");
        String informationMessage = "Incoming information are incorrect";

        if (id > 0 && !formService.isCreateError(req)) {
            String product = jspService.getProduct(req);
            String nameProduct = jspService.NAME_PRODUCT_STRING_MAP.get(product);

            long rowsAffected = 0;

            switch (product) {
                case "pc":
                    String pcBrand = req.getParameter("pcBrand");
                    double pcPrice = jspService.getUnsignedDouble(req, "pcPrice");
                    Cpu cpu = cpuDAO.get(jspService.getUnsignedLong(req, "idCpu"));
                    Ram ram = ramDAO.get(jspService.getUnsignedLong(req, "idRam"));
                    Gpu gpu = gpuDAO.get(jspService.getUnsignedLong(req, "idGpu"));

                    if (cpu != null && ram != null && gpu != null) {
                        rowsAffected = pcDAO.update(new Pc(id, pcBrand, pcPrice, cpu, ram, gpu));
                    }
                    break;

                case "cpu":
                    String cpuBrand = req.getParameter("cpuBrand");
                    int cpuCores = jspService.getUnsignedInteger(req, "cpuCores");
                    double cpuFrequency = jspService.getUnsignedDouble(req, "cpuFrequency");

                    rowsAffected = cpuDAO.update(new Cpu(id, cpuBrand, cpuCores, cpuFrequency));
                    break;

                case "ram":
                    String ramBrand = req.getParameter("ramBrand");
                    int ramSize = jspService.getUnsignedInteger(req, "ramSize");

                    rowsAffected = ramDAO.update(new Ram(id, ramBrand, ramSize));
                    break;

                case "gpu":
                    String gpuBrand = req.getParameter("gpuBrand");

                    rowsAffected = gpuDAO.update(new Gpu(id, gpuBrand));
                    break;
            }

            if (rowsAffected <= 0) {
                informationMessage = "Internal error for " + nameProduct;
            } else {
                informationMessage = rowsAffected + " " + nameProduct + (rowsAffected <= 1 ? " was " : " were ") + "updated";
            }
        }

        jspService.setInformationMessage(req, informationMessage);
    }

    @Override
    public void delete(HttpServletRequest req) {
        long id = jspService.getUnsignedLong(req, "id");
        String informationMessage = "Incoming information are incorrect";

        if (id > 0) {
            String product = jspService.getProduct(req);
            String nameProduct = jspService.NAME_PRODUCT_STRING_MAP.get(product);

            long rowsAffected = 0;

            switch (product) {
                case "pc":
                    rowsAffected = pcDAO.delete(id);
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

            if (rowsAffected <= 0) {
                informationMessage = "Internal error for " + nameProduct;
            } else {
                informationMessage = rowsAffected + " " + nameProduct + (rowsAffected <= 1 ? " was " : " were ") + "deleted";
            }
        }

        jspService.setInformationMessage(req, informationMessage);
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
    public Map<String, Object> get(String product, String col, String order, long pageSize, long pageIndex) {

        Map<String, Object> objectMap = new HashMap<>();

        switch (product) {
            case "pc":
                objectMap.put("pcList", pcDAO.get(col, order, pageSize, pageIndex));
                break;

            case "cpu":
                objectMap.put("cpuList", cpuDAO.get(col, order, pageSize, pageIndex));
                break;

            case "ram":
                objectMap.put("ramList", ramDAO.get(col, order, pageSize, pageIndex));
                break;

            case "gpu":
                objectMap.put("gpuList", gpuDAO.get(col, order, pageSize, pageIndex));
                break;
        }

        return objectMap;
    }

    @Override
    public Map<String, Object> getBrand(String product) {

        Map<String, Object> objectMap = new HashMap<>();

        switch (product) {
            case "pc":
                objectMap.put("pcBrandList", pcDAO.getBrand());
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
                objectMap.put("cpuList", cpuDAO.get("", "brand, cores, frequency", 0, 0));
                objectMap.put("ramList", ramDAO.get("", "brand, size", 0, 0));
                objectMap.put("gpuList", gpuDAO.get("", "brand", 0, 0));
                break;
        }

        return objectMap;
    }

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
}
