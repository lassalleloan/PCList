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
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Objects for pc
 *
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 * @since 13.09.2017
 */
@Singleton
public class PcDAO implements PcDAOLocal {

    @Resource(lookup = "java:/jdbc/pclist")
    private DataSource dataSource;

    @Override
    public Pc get(long id) {
        return get(Collections.singletonList(id)).get(0);
    }

    @Override
    public List<Pc> get(List<Long> idList) {
        List<Pc> pcList = new ArrayList<>();

        StringBuilder sqlQuery = new StringBuilder()
                .append("SELECT p.brand, ")
                .append("p.price, ").append("c.idCpu, ")
                .append("c.brand AS cpuBrand, ")
                .append("c.cores AS cpuCores, ")
                .append("c.frequency AS cpuFrequency, ")
                .append("r.idRam, ")
                .append("r.brand AS ramBrand, ")
                .append("r.size AS ramSize, ")
                .append("g.idGpu, ")
                .append("g.brand AS gpuBrand ")
                .append("FROM pc AS p ")
                .append("INNER JOIN cpu AS c ON p.idCpu = c.idCpu ")
                .append("INNER JOIN ram AS r ON p.idRam = r.idRam ")
                .append("INNER JOIN gpu AS g ON p.idGpu = g.idGpu ")
                .append("WHERE idPc=?");

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery.toString());

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
            Logger.getLogger(PcDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pcList;
    }

    @Override
    public List<Pc> get(String like, String orderBy, long pageSize, long pageIndex) {
        List<Pc> pcList = new ArrayList<>();

        StringBuilder sqlQuery = new StringBuilder()
                .append("SELECT p.idPc, ")
                .append("p.brand, ")
                .append("p.price, ")
                .append("c.idCpu, ")
                .append("c.brand AS cpuBrand, ")
                .append("c.cores AS cpuCores, ")
                .append("c.frequency AS cpuFrequency, ")
                .append("r.idRam, ")
                .append("r.brand AS ramBrand, ")
                .append("r.size AS ramSize, ")
                .append("g.idGpu, ")
                .append("g.brand AS gpuBrand ")
                .append("FROM pc AS p ")
                .append("INNER JOIN cpu AS c ON p.idCpu = c.idCpu ")
                .append("INNER JOIN ram AS r ON p.idRam = r.idRam ")
                .append("INNER JOIN gpu AS g ON p.idGpu = g.idGpu;")
                .append(like.isEmpty() ? "" : "WHERE " + like + " ")
                .append(orderBy.isEmpty() ? "" : "ORDER BY " + orderBy + " ")
                .append(pageSize <= 0 ? "" : "LIMIT ? OFFSET ?;");

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery.toString());

            if (sqlQuery.toString().contains("?")) {
                preparedStatement.setLong(1, pageSize);
                preparedStatement.setLong(2, pageSize * pageIndex);
            }

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

    @Override
    public List<String> getBrand() {
        List<String> brandList = new ArrayList<>();

        StringBuilder sqlQuery = new StringBuilder()
                .append("SELECT DISTINCT brand ")
                .append("FROM pc;");

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery.toString());
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

    @Override
    public long count() {
        long numberPc = 0;

        StringBuilder sqlQuery = new StringBuilder()
                .append("SELECT COUNT(*) ")
                .append("FROM pc;");

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            numberPc = resultSet.getLong("COUNT(*)");

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(PcDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return numberPc;
    }
}
