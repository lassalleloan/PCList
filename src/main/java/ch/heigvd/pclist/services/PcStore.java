package ch.heigvd.pclist.services;

import ch.heigvd.pclist.models.Cpu;
import ch.heigvd.pclist.models.Gpu;
import ch.heigvd.pclist.models.Pc;
import ch.heigvd.pclist.models.Ram;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
@Singleton
public class PcStore implements PcStoreLocal {

    @Resource(lookup = "java:/jdbc/pclist")
    private DataSource dataSource;

    public Pc getOne(long id) {
        Pc pc = null;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT p.brand, " +
                    "p.price, " +
                    "c.idCpu, " +
                    "c.brand AS cpuBrand, " +
                    "c.cores AS cpuCores, " +
                    "c.frequency AS cpuFrequency, " +
                    "r.idRam, " +
                    "r.brand AS ramBrand, " +
                    "r.size AS ramSize, " +
                    "g.idGpu, " +
                    "g.brand AS gpuBrand " +
                    "FROM pc AS p " +
                    "INNER JOIN cpu AS c ON p.idCpu = c.idCpu " +
                    "INNER JOIN ram AS r ON p.idRam = r.idRam " +
                    "INNER JOIN gpu AS g ON p.idGpu = g.idGpu " +
                    "WHERE idPc=" + id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            String brand = resultSet.getString("brand");
            double price = resultSet.getDouble("price");

            long idCpu = resultSet.getLong("idCpu");
            String cpuBrand = resultSet.getString("cpuBrand");
            int cpuCores = resultSet.getInt("cpuCores");
            double cpuFrequency = resultSet.getDouble("cpuFrequency");

            long idRam = resultSet.getLong("idRam");
            String ramBrand = resultSet.getString("ramBrand");
            int ramSize = resultSet.getInt("ramSize");

            long idGpu = resultSet.getLong("idGpu");
            String gpuBrand = resultSet.getString("gpuBrand");

            Cpu cpu = new Cpu(idCpu, cpuBrand, cpuCores, cpuFrequency);
            Ram ram = new Ram(idRam, ramBrand, ramSize);
            Gpu gpu = new Gpu(idGpu, gpuBrand);

            pc = new Pc(id, brand, price, cpu, ram, gpu);

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(FactoryService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pc;
    }

    public List<Pc> getAll() {
        List<Pc> pcList = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT p.idPc, " +
                    "p.brand, " +
                    "p.price, " +
                    "c.idCpu, " +
                    "c.brand AS cpuBrand, " +
                    "c.cores AS cpuCores, " +
                    "c.frequency AS cpuFrequency, " +
                    "r.idRam, " +
                    "r.brand AS ramBrand, " +
                    "r.size AS ramSize, " +
                    "g.idGpu, " +
                    "g.brand AS gpuBrand " +
                    "FROM pc AS p " +
                    "INNER JOIN cpu AS c ON p.idCpu = c.idCpu " +
                    "INNER JOIN ram AS r ON p.idRam = r.idRam " +
                    "INNER JOIN gpu AS g ON p.idGpu = g.idGpu");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long idPc = resultSet.getLong("idPc");
                String brand = resultSet.getString("brand");
                double price = resultSet.getDouble("price");

                long idCpu = resultSet.getLong("idCpu");
                String cpuBrand = resultSet.getString("cpuBrand");
                int cpuCores = resultSet.getInt("cpuCores");
                double cpuFrequency = resultSet.getDouble("cpuFrequency");

                long idRam = resultSet.getLong("idRam");
                String ramBrand = resultSet.getString("ramBrand");
                int ramSize = resultSet.getInt("ramSize");

                long idGpu = resultSet.getLong("idGpu");
                String gpuBrand = resultSet.getString("gpuBrand");

                Cpu cpu = new Cpu(idCpu, cpuBrand, cpuCores, cpuFrequency);
                Ram ram = new Ram(idRam, ramBrand, ramSize);
                Gpu gpu = new Gpu(idGpu, gpuBrand);

                pcList.add(new Pc(idPc, brand, price, cpu, ram, gpu));
            }

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(FactoryService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pcList;
    }
}
