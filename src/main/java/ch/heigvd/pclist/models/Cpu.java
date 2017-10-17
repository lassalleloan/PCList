package ch.heigvd.pclist.models;

import java.util.Arrays;
import java.util.List;

/**
 * Models a processor
 *
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 * @since 13.09.2017
 */
public class Cpu {

    public static List<String> FIELD_LIST = Arrays.asList("brand", "cores", "frequency");

    private long idCpu;
    private String brand;
    private int cores;
    private double frequency;

    public Cpu(long idCpu, String brand, int cores, double frequency) {
        this.idCpu = idCpu;
        this.brand = brand;
        this.cores = cores;
        this.frequency = frequency;
    }

    public long getIdCpu() {
        return idCpu;
    }

    public void setIdCpu(long idCpu) {
        this.idCpu = idCpu;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getCores() {
        return cores;
    }

    public void setCores(int cores) {
        this.cores = cores;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "Cpu{" +
                "idCpu=" + idCpu +
                ", brand='" + brand + '\'' +
                ", cores=" + cores +
                ", frequency=" + frequency +
                '}';
    }
}
