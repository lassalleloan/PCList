package ch.heigvd.pclist.services.dao;

import ch.heigvd.pclist.models.Gpu;
import ch.heigvd.pclist.services.business.FactoryService;

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
                    "WHERE idGpu=" + id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            String brand = resultSet.getString("brand");

            gpu = new Gpu(id, brand);

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(FactoryService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return gpu;
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
            Logger.getLogger(FactoryService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return gpuList;
    }
}
