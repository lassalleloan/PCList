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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
@Singleton
public class GpuDAO implements GpuDAOLocal {

    @Resource(lookup = "java:/jdbc/pclist")
    private DataSource dataSource;

    public Gpu get(long id) {
        Gpu gpu = null;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * " +
                    "FROM gpu " +
                    "WHERE idGpu=?");

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            String brand = resultSet.getString("brand");

            gpu = new Gpu(id, brand);

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(GpuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return gpu;
    }

    public List<Gpu> get(List<Long> idList) {
        List<Gpu> gpuList = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * " +
                    "FROM gpu " +
                    "WHERE idGpu= ?;");

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

    public List<Gpu> get(long pageSize, long pageIndex) {
        List<Gpu> gpuList = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * " +
                    "FROM gpu " +
                    "LIMIT ? OFFSET ?;");

            preparedStatement.setLong(1, pageSize);
            preparedStatement.setLong(2, pageSize * pageIndex);
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

    public List<Gpu> get() {
        List<Gpu> gpuList = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * " +
                    "FROM gpu");
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

    public List<String> getBrand() {
        List<String> brandList = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT DISTINCT brand " +
                    "FROM gpu;");
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

    public long count() {
        long numberGpu = 0;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) " +
                    "FROM gpu;");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            numberGpu = resultSet.getLong("COUNT(*)");

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(GpuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return numberGpu;
    }

    public int set(Gpu gpu) {
        int rowsAffected = 0;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO gpu " +
                    "(`idGpu`, `brand`) VALUES (DEFAULT, ?);");

            preparedStatement.setString(1, gpu.getBrand());

            rowsAffected = preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(GpuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }

    public int set(List<Gpu> gpuList) {
        int rowsAffected = 0;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO gpu " +
                    "(`idGpu`, `brand`) VALUES (DEFAULT, ?);");

            for (Gpu gpu : gpuList) {
                preparedStatement.setString(1, gpu.getBrand());

                rowsAffected += preparedStatement.executeUpdate();
            }

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(GpuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }

    public int update(Gpu gpu) {
        int rowsAffected = 0;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE gpu " +
                    "SET brand=? " +
                    "WHERE idGpu=?;");

            preparedStatement.setString(1, gpu.getBrand());
            preparedStatement.setLong(2, gpu.getIdGpu());

            rowsAffected = preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(GpuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }

    public int update(List<Gpu> gpuList) {
        int rowsAffected = 0;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE gpu " +
                    "SET brand=? " +
                    "WHERE idGpu=?;");

            for (Gpu gpu : gpuList) {
                preparedStatement.setString(1, gpu.getBrand());
                preparedStatement.setLong(2, gpu.getIdGpu());

                rowsAffected += preparedStatement.executeUpdate();
            }

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(GpuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }

    public int delete(long id) {
        int rowsAffected = 0;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM gpu " +
                    "WHERE idGpu=?;");

            preparedStatement.setLong(1, id);

            rowsAffected = preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(GpuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }

    public int delete(List<Long> idList) {
        int rowsAffected = 0;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM gpu " +
                    "WHERE idGpu=?;");

            for (Long id : idList) {
                preparedStatement.setLong(1, id);

                rowsAffected += preparedStatement.executeUpdate();
            }

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(GpuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }

    public int delete() {
        int rowsAffected = 0;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM gpu;");

            rowsAffected = preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(GpuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }



}
