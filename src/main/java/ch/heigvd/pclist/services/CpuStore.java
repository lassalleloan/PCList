package ch.heigvd.pclist.services;

import ch.heigvd.pclist.models.Cpu;

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
public class CpuStore implements CpuStoreLocal {

    @Resource(lookup = "java:/jdbc/pclist")
    private DataSource dataSource;

    public List<Cpu> getAll() {
        List<Cpu> cpuList = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM cpu");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String brand = resultSet.getString("brand");
                int nbCores = resultSet.getInt("nbCores");
                double frequency = resultSet.getDouble("frequency");

                cpuList.add(new Cpu(brand, nbCores, frequency));
            }

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(FactoryService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cpuList;
    }
}
