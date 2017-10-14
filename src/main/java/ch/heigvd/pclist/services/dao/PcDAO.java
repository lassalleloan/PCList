package ch.heigvd.pclist.services.dao;

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
public class PcDAO implements PcDAOLocal {

    @Resource(lookup = "java:/jdbc/pclist")
    private DataSource dataSource;

    public Pc get(long id) {
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
                    "WHERE idPc=?");
            preparedStatement.setLong(1, id);

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
            Logger.getLogger(PcDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pc;
    }

    public List<Pc> get(List<Long> idList) {
        List<Pc> pcList = new ArrayList<>();

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
                    "WHERE idPc=?");

            for (long id : idList) {
                preparedStatement.setLong(1, id);

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

                pcList.add(new Pc(id, brand, price, cpu, ram, gpu));
            }

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CpuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pcList;
    }

    public List<Pc> get(long pageSize, long pageIndex) {
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
                    "INNER JOIN gpu AS g ON p.idGpu = g.idGpu " +
                    "LIMIT ? OFFSET ?;");

            preparedStatement.setLong(1, pageSize);
            preparedStatement.setLong(2, pageSize * pageIndex);
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
            Logger.getLogger(CpuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pcList;
    }

    public List<Pc> get() {
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
            Logger.getLogger(PcDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pcList;
    }

    public List<String> getBrand() {
        List<String> brandList = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT DISTINCT brand " +
                    "FROM pc;");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                brandList.add(resultSet.getString("brand"));
            }

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(PcDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return brandList;
    }

    public long count() {
        long numberPc = 0;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) " +
                    "FROM pc;");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            numberPc = resultSet.getLong("COUNT(*)");

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CpuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return numberPc;
    }
}
