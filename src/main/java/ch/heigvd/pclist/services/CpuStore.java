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

    public Cpu getOne(long id) {
        Cpu cpu = null;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * " +
                    "FROM cpu " +
                    "WHERE idCpu=" + id);
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

    public List<Cpu> getAll() {
        List<Cpu> cpuList = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * " +
                    "FROM cpu");
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

    public boolean setOne(Cpu cpu) {
        boolean isSet = false;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO cpu " +
                    "(`idCpu`, `brand`, `cores`, `frequency`) VALUES (DEFAULT, ?, ?, ?)");

            preparedStatement.setString(1, cpu.getBrand());
            preparedStatement.setInt(2, cpu.getCores());
            preparedStatement.setDouble(3, cpu.getFrequency());

            isSet = preparedStatement.executeUpdate() == 1;

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(FactoryService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isSet;
    }

    public boolean setAll(List<Cpu> cpuList) {
        boolean isSet = true;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO cpu " +
                    "(`idCpu`, `brand`, `cores`, `frequency`) VALUES (DEFAULT, ?, ?, ?)");

            for (Cpu cpu : cpuList) {
                preparedStatement.setString(1, cpu.getBrand());
                preparedStatement.setInt(2, cpu.getCores());
                preparedStatement.setDouble(3, cpu.getFrequency());

                isSet = preparedStatement.executeUpdate() == 1 && isSet;
            }

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(FactoryService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isSet;
    }
}
