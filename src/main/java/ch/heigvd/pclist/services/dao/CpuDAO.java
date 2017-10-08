package ch.heigvd.pclist.services.dao;

import ch.heigvd.pclist.models.Cpu;
import ch.heigvd.pclist.services.factory.FactoryService;

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
public class CpuDAO implements CpuDAOLocal {

    @Resource(lookup = "java:/jdbc/pclist")
    private DataSource dataSource;

    public Cpu get(long id) {
        Cpu cpu = null;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * " +
                    "FROM cpu " +
                    "WHERE idCpu=?;");

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            String brand = resultSet.getString("brand");
            int cores = resultSet.getInt("cores");
            double frequency = resultSet.getDouble("frequency");

            cpu = new Cpu(id, brand, cores, frequency);

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(FactoryService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cpu;
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
            Logger.getLogger(FactoryService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cpuList;
    }

    public List<Cpu> get() {
        List<Cpu> cpuList = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * " +
                    "FROM cpu;");
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
            Logger.getLogger(FactoryService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cpuList;
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
            Logger.getLogger(FactoryService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return brandList;
    }

    public int set(Cpu cpu) {
        int rowsAffected = 0;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO cpu " +
                    "(`idCpu`, `brand`, `cores`, `frequency`) VALUES (DEFAULT, ?, ?, ?);");

            preparedStatement.setString(1, cpu.getBrand());
            preparedStatement.setInt(2, cpu.getCores());
            preparedStatement.setDouble(3, cpu.getFrequency());

            rowsAffected = preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(FactoryService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }

    public int set(List<Cpu> cpuList) {
        int rowsAffected = 0;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO cpu " +
                    "(`idCpu`, `brand`, `cores`, `frequency`) VALUES (DEFAULT, ?, ?, ?);");

            for (Cpu cpu : cpuList) {
                preparedStatement.setString(1, cpu.getBrand());
                preparedStatement.setInt(2, cpu.getCores());
                preparedStatement.setDouble(3, cpu.getFrequency());

                rowsAffected += preparedStatement.executeUpdate();
            }

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(FactoryService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }

    public int update(Cpu cpu) {
        int rowsAffected = 0;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE cpu " +
                    "SET brand=?, " +
                    "cores=?, " +
                    "frequency=? " +
                    "WHERE idCpu=?;");

            preparedStatement.setString(1, cpu.getBrand());
            preparedStatement.setInt(2, cpu.getCores());
            preparedStatement.setDouble(3, cpu.getFrequency());
            preparedStatement.setLong(4, cpu.getIdCpu());

            rowsAffected = preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(FactoryService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }

    public int update(List<Cpu> cpuList) {
        int rowsAffected = 0;

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

                rowsAffected += preparedStatement.executeUpdate();
            }

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(FactoryService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }

    public int delete(long id) {
        int rowsAffected = 0;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM cpu " +
                    "WHERE idCpu=?;");

            preparedStatement.setLong(1, id);

            rowsAffected = preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(FactoryService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }

    public int delete(List<Long> idList) {
        int rowsAffected = 0;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM cpu " +
                    "WHERE idCpu=?;");

            for (Long id : idList) {
                preparedStatement.setLong(1, id);

                rowsAffected += preparedStatement.executeUpdate();
            }

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(FactoryService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }

    public int delete() {
        int rowsAffected = 0;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM cpu;");

            rowsAffected = preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(FactoryService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }
}
