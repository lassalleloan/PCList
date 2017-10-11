package ch.heigvd.pclist.services.dao;

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
public class RamDAO implements RamDAOLocal {

    @Resource(lookup = "java:/jdbc/pclist")
    private DataSource dataSource;

    public Ram get(long id) {
        Ram ram = null;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * " +
                    "FROM ram " +
                    "WHERE idRam=?;");

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            String brand = resultSet.getString("brand");
            int size = resultSet.getInt("size");

            ram = new Ram(id, brand, size);

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(RamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ram;
    }

    @Override
    public List<Ram> get(List<Long> idList) {
        List<Ram> ramList = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * " +
                    "FROM ram " +
                    "WHERE idRam= ?;");

            for (long id : idList) {
                preparedStatement.setLong(1, id);

                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();

                String brand = resultSet.getString("brand");
                int size = resultSet.getInt("size");

                ramList.add(new Ram(id, brand, size));
            }

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(RamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ramList;
    }

    @Override
    public List<Ram> get(int pageSize, int pageIndex) {
        List<Ram> ramList = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * " +
                    "FROM ram " +
                    "LIMIT ? OFFSET ?;");

            preparedStatement.setInt(1, pageSize);
            preparedStatement.setInt(2, pageSize * pageIndex);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("idRam");
                String brand = resultSet.getString("brand");
                int size = resultSet.getInt("size");

                ramList.add(new Ram(id, brand, size));
            }

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(RamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ramList;
    }

    public List<Ram> get() {
        List<Ram> ramList = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * " +
                    "FROM ram;");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("idRam");
                String brand = resultSet.getString("brand");
                int size = resultSet.getInt("size");

                ramList.add(new Ram(id, brand, size));
            }

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(RamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ramList;
    }

    @Override
    public List<String> getBrand() {
        List<String> brandList = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT DISTINCT brand " +
                    "FROM ram;");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                brandList.add(resultSet.getString("brand"));
            }

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(RamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return brandList;
    }

    @Override
    public long count() {
        long numberRam = 0;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) " +
                    "FROM ram;");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            numberRam = resultSet.getLong("COUNT(*)");

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(RamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return numberRam;
    }

    @Override
    public int set(Ram ram) {
        int rowsAffected = 0;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ram " +
                    "(`idRam`, `brand`, `size`) VALUES (DEFAULT, ?, ?);");

            preparedStatement.setString(1, ram.getBrand());
            preparedStatement.setInt(2, ram.getSize());

            rowsAffected = preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(RamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }

    @Override
    public int set(List<Ram> ramList) {
        int rowsAffected = 0;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ram " +
                    "(`idRam`, `brand`, `size`) VALUES (DEFAULT, ?, ?);");

            for (Ram ram : ramList) {
                preparedStatement.setString(1, ram.getBrand());
                preparedStatement.setInt(2, ram.getSize());

                rowsAffected += preparedStatement.executeUpdate();
            }

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(RamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }

    @Override
    public int update(Ram ram) {
        int rowsAffected = 0;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE ram " +
                    "SET brand=?, " +
                    "size=? " +
                    "WHERE idRam=?;");

            preparedStatement.setString(1, ram.getBrand());
            preparedStatement.setInt(2, ram.getSize());

            rowsAffected = preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(RamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }

    @Override
    public int update(List<Ram> ramList) {
        int rowsAffected = 0;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE cpu " +
                    "SET brand=?, " +
                    "size=? " +
                    "WHERE idRam=?;");

            for (Ram ram : ramList) {
                preparedStatement.setString(1, ram.getBrand());
                preparedStatement.setInt(2, ram.getSize());
                preparedStatement.setLong(3, ram.getIdRam());

                rowsAffected += preparedStatement.executeUpdate();
            }

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(RamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }

    @Override
    public int delete(long id) {
        int rowsAffected = 0;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ram " +
                    "WHERE idRam=?;");

            preparedStatement.setLong(1, id);

            rowsAffected = preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(RamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }

    @Override
    public int delete(List<Long> idList) {
        int rowsAffected = 0;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ram " +
                    "WHERE idRam=?;");

            for (Long id : idList) {
                preparedStatement.setLong(1, id);

                rowsAffected += preparedStatement.executeUpdate();
            }

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(RamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }

    @Override
    public int delete() {
        int rowsAffected = 0;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ram;");

            rowsAffected = preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(RamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }
}
