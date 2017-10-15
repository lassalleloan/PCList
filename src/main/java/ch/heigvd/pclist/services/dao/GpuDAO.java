package ch.heigvd.pclist.services.dao;

import ch.heigvd.pclist.models.Gpu;

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
 * Data Access Objects for gpu
 *
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 * @since 13.09.2017
 */
@Singleton
public class GpuDAO implements GpuDAOLocal {

    @Resource(lookup = "java:/jdbc/pclist")
    private DataSource dataSource;

    @Override
    public Gpu get(long id) {
        return get(Collections.singletonList(id)).get(0);
    }

    @Override
    public List<Gpu> get(List<Long> idList) {
        List<Gpu> gpuList = new ArrayList<>();

        StringBuilder sqlQuery = new StringBuilder()
                .append("SELECT * ")
                .append("FROM gpu ")
                .append("WHERE idGpu= ?;");

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery.toString());

            for (long id : idList) {
                preparedStatement.setLong(1, id);

                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();

                String brand = resultSet.getString("brand");

                gpuList.add(new Gpu(id, brand));
            }

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(GpuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return gpuList;
    }

    @Override
    public List<Gpu> get(String like, String orderBy, long pageSize, long pageIndex) {
        List<Gpu> gpuList = new ArrayList<>();

        StringBuilder sqlQuery = new StringBuilder()
                .append("SELECT * ")
                .append("FROM gpu ")
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
                long id = resultSet.getLong("idGpu");
                String brand = resultSet.getString("brand");

                gpuList.add(new Gpu(id, brand));
            }

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(GpuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return gpuList;
    }

    @Override
    public List<String> getBrand() {
        List<String> brandList = new ArrayList<>();

        StringBuilder sqlQuery = new StringBuilder()
                .append("SELECT DISTINCT brand ")
                .append("FROM gpu;");

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery.toString());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                brandList.add(resultSet.getString("brand"));
            }

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(GpuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return brandList;
    }

    @Override
    public long count() {
        long numberGpu = 0;

        StringBuilder sqlQuery = new StringBuilder()
                .append("SELECT COUNT(*) ")
                .append("FROM gpu;");

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            numberGpu = resultSet.getLong("COUNT(*)");

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(GpuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return numberGpu;
    }

    @Override
    public long set(Gpu gpu) {
        return set(Collections.singletonList(gpu));
    }

    @Override
    public long set(List<Gpu> gpuList) {
        int rowsAffected = 0;

        StringBuilder sqlQuery = new StringBuilder()
                .append("INSERT INTO gpu ")
                .append("(`idGpu`, `brand`) VALUES ")
                .append("DEFAULT, ?);");

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery.toString());

            for (Gpu gpu : gpuList) {
                preparedStatement.setString(1, gpu.getBrand());
                preparedStatement.addBatch();
            }

            rowsAffected = preparedStatement.executeBatch().length != gpuList.size() ? 0 : gpuList.size();

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(GpuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }

    @Override
    public long update(Gpu gpu) {
        return update(Collections.singletonList(gpu));
    }

    @Override
    public long update(List<Gpu> gpuList) {
        int rowsAffected = 0;

        StringBuilder sqlQuery = new StringBuilder()
                .append("UPDATE gpu ")
                .append("SET brand=? ")
                .append("WHERE idGpu=?;");

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery.toString());

            for (Gpu gpu : gpuList) {
                preparedStatement.setString(1, gpu.getBrand());
                preparedStatement.setLong(2, gpu.getIdGpu());
                preparedStatement.addBatch();
            }

            rowsAffected = preparedStatement.executeBatch().length != gpuList.size() ? 0 : gpuList.size();

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(GpuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }

    @Override
    public long delete(long id) {
        return delete(Collections.singletonList(id));
    }

    @Override
    public long delete(List<Long> idList) {
        int rowsAffected = 0;

        StringBuilder sqlQuery = new StringBuilder()
                .append("DELETE FROM gpu ")
                .append("WHERE idGpu=?;");

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
            Logger.getLogger(GpuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }

    @Override
    public long delete() {
        int rowsAffected = 0;

        StringBuilder sqlQuery = new StringBuilder()
                .append("DELETE FROM gpu;");

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery.toString());

            rowsAffected = preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(GpuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }


}
