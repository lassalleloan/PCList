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
                    "WHERE idRam=" + id);
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

    public List<Ram> get() {
        List<Ram> ramList = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * " +
                    "FROM ram");
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
}
