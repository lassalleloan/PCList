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
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Objects for ram
 *
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 * @since 13.09.2017
 */
@Singleton
public class RamDAO implements RamDAOLocal {

    @Resource(lookup = "java:/jdbc/pclist")
    private DataSource dataSource;

    public Ram get(long id) {
        return get(Collections.singletonList(id)).get(0);
    }

    @Override
    public List<Ram> get(List<Long> idList) {
        List<Ram> ramList = new ArrayList<>();

        StringBuilder sqlQuery = new StringBuilder()
                .append("SELECT * ")
                .append("FROM ram ")
                .append("WHERE idRam= ?;");

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery.toString());

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
    public List<Ram> get(String like, String orderBy, long pageSize, long pageIndex) {
        List<Ram> ramList = new ArrayList<>();

        StringBuilder sqlQuery = new StringBuilder()
                .append("SELECT * ")
                .append("FROM ram ")
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

        StringBuilder sqlQuery = new StringBuilder()
                .append("SELECT DISTINCT brand ")
                .append("FROM ram;");

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery.toString());
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

        StringBuilder sqlQuery = new StringBuilder()
                .append("SELECT COUNT(*) ")
                .append("FROM ram;");

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery.toString());
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
    public long set(Ram ram) {
        return set(Collections.singletonList(ram));
    }

    @Override
    public long set(List<Ram> ramList) {
        int rowsAffected = 0;

        StringBuilder sqlQuery = new StringBuilder()
                .append("INSERT INTO ram ")
                .append("(`idRam`, `brand`, `size`) VALUES ")
                .append("DEFAULT, ?, ?);");

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery.toString());

            for (Ram ram : ramList) {
                preparedStatement.setString(1, ram.getBrand());
                preparedStatement.setInt(2, ram.getSize());
                preparedStatement.addBatch();
            }

            rowsAffected = preparedStatement.executeBatch().length != ramList.size() ? 0 : ramList.size();

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(RamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }

    @Override
    public long update(Ram ram) {
        return update(Collections.singletonList(ram));
    }

    @Override
    public long update(List<Ram> ramList) {
        int rowsAffected = 0;

        StringBuilder sqlQuery = new StringBuilder()
                .append("UPDATE ram ")
                .append("SET brand=?, ")
                .append("size=? ")
                .append("WHERE idRam=?;");

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery.toString());

            for (Ram ram : ramList) {
                preparedStatement.setString(1, ram.getBrand());
                preparedStatement.setInt(2, ram.getSize());
                preparedStatement.setLong(3, ram.getIdRam());
                preparedStatement.addBatch();
            }

            rowsAffected = preparedStatement.executeBatch().length != ramList.size() ? 0 : ramList.size();

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(RamDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                .append("DELETE FROM ram ")
                .append("WHERE idRam=?;");

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
            Logger.getLogger(RamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }

    @Override
    public long delete() {
        int rowsAffected = 0;

        StringBuilder sqlQuery = new StringBuilder()
                .append("DELETE FROM ram;");

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery.toString());

            rowsAffected = preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(RamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }
}
