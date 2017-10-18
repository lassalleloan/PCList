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

    /**
     * All fields of cpu
     */
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

    /**
     * Gets ID of cpu
     *
     * @return ID of cpu
     */
    public long getIdCpu() {
        return idCpu;
    }

    /**
     * Sets ID of cpu
     *
     * @param idCpu ID of cpu
     */
    public void setIdCpu(long idCpu) {
        this.idCpu = idCpu;
    }

    /**
     * Gets brand of cpu
     *
     * @return brand of cpu
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets brand of cpu
     *
     * @param brand brand of cpu
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Gets number of cores of cpu
     *
     * @return number of cores of cpu
     */
    public int getCores() {
        return cores;
    }

    /**
     * Sets number of cores of cpu
     *
     * @param cores number of cores of cpu
     */
    public void setCores(int cores) {
        this.cores = cores;
    }

    /**
     * Gets frequency of cpu
     *
     * @return frequency of cpu
     */
    public double getFrequency() {
        return frequency;
    }

    /**
     * Sets frequency of cpu
     *
     * @param frequency frequency of cpu
     */
    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    /**
     * Gets representation json of cpu
     *
     * @return representation json of cpu
     */
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
