package ch.heigvd.pclist.services.dao;

import ch.heigvd.pclist.models.Cpu;

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
 * Data Access Objects for cpu
 *
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
@Singleton
public class CpuDAO implements CpuDAOLocal {

    @Resource(lookup = "java:/jdbc/pclist")
    private DataSource dataSource;

    public Cpu get(long id) {
        return get(Collections.singletonList(id)).get(0);
    }

    public List<Cpu> get(List<Long> idList) {
        List<Cpu> cpuList = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * " +
                    "FROM cpu " +
                    "WHERE idCpu= ?;");

            for (long id : idList) {
                preparedStatement.setLong(1, id);

                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();

                String brand = resultSet.getString("brand");
                int cores = resultSet.getInt("cores");
                double frequency = resultSet.getDouble("frequency");

                cpuList.add(new Cpu(id, brand, cores, frequency));
            }

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CpuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cpuList;
    }

    public List<Cpu> get(long pageSize, long pageIndex) {
        List<Cpu> cpuList = new ArrayList<>();
        String limit = pageSize <= 0 ? "" : "LIMIT ? OFFSET ?;";

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * " +
                    "FROM cpu " +
                    limit);

            if (!limit.isEmpty()) {
                preparedStatement.setLong(1, pageSize);
                preparedStatement.setLong(2, pageSize * pageIndex);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("idCpu");
                String brand = resultSet.getString("brand");
                int cores = resultSet.getInt("cores");
                double frequency = resultSet.getDouble("frequency");

                cpuList.add(new Cpu(id, brand, cores, frequency));
            }

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CpuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cpuList;
    }

    public List<Cpu> get() {
        return get(0, 0);
    }

    public List<String> getBrand() {
        List<String> brandList = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT DISTINCT brand " +
                    "FROM cpu;");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                brandList.add(resultSet.getString("brand"));
            }

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CpuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return brandList;
    }

    public long count() {
        long numberRows = 0;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) " +
                    "FROM cpu;");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            numberRows = resultSet.getLong("COUNT(*)");

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CpuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return numberRows;
    }

    public long set(Cpu cpu) {
        return set(Collections.singletonList(cpu));
    }

    public long set(List<Cpu> cpuList) {
        long rowsAffected = 0;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO cpu " +
                    "(`idCpu`, `brand`, `cores`, `frequency`) VALUES (DEFAULT, ?, ?, ?);");

            for (Cpu cpu : cpuList) {
                preparedStatement.setString(1, cpu.getBrand());
                preparedStatement.setInt(2, cpu.getCores());
                preparedStatement.setDouble(3, cpu.getFrequency());
                preparedStatement.addBatch();
            }

            rowsAffected = preparedStatement.executeBatch().length != cpuList.size() ? 0 : cpuList.size();

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CpuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }

    public long update(Cpu cpu) {
        return update(Collections.singletonList(cpu));
    }

    public long update(List<Cpu> cpuList) {
        long rowsAffected = 0;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE cpu " +
                    "SET brand=?, " +
                    "cores=?, " +
                    "frequency=? " +
                    "WHERE idCpu=?;");

            for (Cpu cpu : cpuList) {
                preparedStatement.setString(1, cpu.getBrand());
                preparedStatement.setInt(2, cpu.getCores());
                preparedStatement.setDouble(3, cpu.getFrequency());
                preparedStatement.setLong(4, cpu.getIdCpu());
                preparedStatement.addBatch();
            }

            rowsAffected = preparedStatement.executeBatch().length != cpuList.size() ? 0 : cpuList.size();

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CpuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }

    public long delete(long id) {
        return delete(Collections.singletonList(id));
    }

    public long delete(List<Long> idList) {
        long rowsAffected = 0;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM cpu " +
                    "WHERE idCpu=?;");

            for (Long id : idList) {
                preparedStatement.setLong(1, id);
                preparedStatement.addBatch();
            }

            rowsAffected = preparedStatement.executeBatch().length != idList.size() ? 0 : idList.size();

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CpuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }

    public long delete() {
        long rowsAffected = 0;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM cpu;");

            rowsAffected = preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CpuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }
}
