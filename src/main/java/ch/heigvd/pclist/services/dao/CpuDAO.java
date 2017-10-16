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
 * @since 13.09.2017
 */
@Singleton
public class CpuDAO implements CpuDAOLocal {

    @Resource(lookup = "java:/jdbc/pclist")
    private DataSource dataSource;

    @Override
    public boolean isExist(long id) {
        return !get(Collections.singletonList(id)).isEmpty();
    }

    @Override
    public Cpu get(long id) {
        return get(Collections.singletonList(id)).get(0);
    }

    @Override
    public List<Cpu> get(List<Long> idList) {
        List<Cpu> cpuList = new ArrayList<>();

        StringBuilder sqlQuery = new StringBuilder()
                .append("SELECT * ")
                .append("FROM cpu ")
                .append("WHERE idCpu= ?;");

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery.toString());

            for (long id : idList) {
                preparedStatement.setLong(1, id);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    String brand = resultSet.getString("brand");
                    int cores = resultSet.getInt("cores");
                    double frequency = resultSet.getDouble("frequency");

                    cpuList.add(new Cpu(id, brand, cores, frequency));
                }
            }

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CpuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cpuList;
    }

    @Override
    public List<Cpu> get(String like, String orderBy, long pageSize, long pageIndex) {
        List<Cpu> cpuList = new ArrayList<>();

        StringBuilder sqlQuery = new StringBuilder()
                .append("SELECT * ")
                .append("FROM cpu ")
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

    @Override
    public List<String> getBrand() {
        List<String> brandList = new ArrayList<>();

        StringBuilder sqlQuery = new StringBuilder()
                .append("SELECT DISTINCT brand ")
                .append("FROM cpu ")
                .append("ORDER BY brand;");

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery.toString());
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

    @Override
    public long count() {
        long numberRows = 0;

        StringBuilder sqlQuery = new StringBuilder()
                .append("SELECT COUNT(*) ")
                .append("FROM cpu;");

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery.toString());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                numberRows = resultSet.getLong("COUNT(*)");
            }

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CpuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return numberRows;
    }

    @Override
    public long set(Cpu cpu) {
        return set(Collections.singletonList(cpu));
    }

    @Override
    public long set(List<Cpu> cpuList) {
        long rowsAffected = 0;

        StringBuilder sqlQuery = new StringBuilder()
                .append("INSERT INTO cpu ")
                .append("(`idCpu`, `brand`, `cores`, `frequency`) VALUES ")
                .append("(DEFAULT, ?, ?, ?);");

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery.toString());

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

    @Override
    public long update(Cpu cpu) {
        return update(Collections.singletonList(cpu));
    }

    @Override
    public long update(List<Cpu> cpuList) {
        long rowsAffected = 0;

        StringBuilder sqlQuery = new StringBuilder()
                .append("UPDATE cpu ")
                .append("SET brand=?, ")
                .append("cores=?, ")
                .append("frequency=? ")
                .append("WHERE idCpu=?;");

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery.toString());

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

    @Override
    public long delete(long id) {
        return delete(Collections.singletonList(id));
    }

    @Override
    public long delete(List<Long> idList) {
        long rowsAffected = 0;

        StringBuilder sqlQuery = new StringBuilder()
                .append("DELETE FROM cpu ")
                .append("WHERE idCpu=?;");

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery.toString());

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

    @Override
    public long delete() {
        long rowsAffected = 0;

        StringBuilder sqlQuery = new StringBuilder()
                .append("DELETE FROM cpu;");

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery.toString());

            rowsAffected = preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CpuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }
}
