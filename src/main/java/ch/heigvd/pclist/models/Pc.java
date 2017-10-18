package ch.heigvd.pclist.models;

import java.util.Arrays;
import java.util.List;

/**
 * Models a computer
 *
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 * @since 13.09.2017
 */
public class Pc {

    /**
     * All fields of pc
     */
    public static List<String> FIELD_LIST = Arrays.asList("brand", "price", "cpuBrand", "cpuCores", "cpuFrequency", "ramBrand", "ramSize", "gpuBrand");

    private long idPc;
    private String brand;
    private double price;
    private Cpu cpu;
    private Ram ram;
    private Gpu gpu;

    public Pc(long idPc, String brand, double price, Cpu cpu, Ram ram, Gpu gpu) {
        this.idPc = idPc;
        this.brand = brand;
        this.price = price;
        this.cpu = cpu;
        this.ram = ram;
        this.gpu = gpu;
    }

    /**
     * Gets ID of pc
     *
     * @return ID of pc
     */
    public long getIdPc() {
        return idPc;
    }

    /**
     * Sets ID of pc
     *
     * @param idPc ID of pc
     */
    public void setIdPc(long idPc) {
        this.idPc = idPc;
    }

    /**
     * Gets brand of pc
     *
     * @return brand of pc
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets brand of pc
     *
     * @param brand brand of pc
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Gets price of pc
     *
     * @return price of pc
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets price of pc
     *
     * @param price price of pc
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets cpu of pc
     *
     * @return cpu of pc
     */
    public Cpu getCpu() {
        return cpu;
    }

    /**
     * Sets cpu of pc
     *
     * @param cpu cpu of pc
     */
    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
    }

    /**
     * Gets ram of pc
     *
     * @return ram of pc
     */
    public Ram getRam() {
        return ram;
    }

    /**
     * Sets ram of pc
     *
     * @param ram cpu of pc
     */
    public void setRam(Ram ram) {
        this.ram = ram;
    }

    /**
     * Gets gpu of pc
     *
     * @return gpu of pc
     */
    public Gpu getGpu() {
        return gpu;
    }

    /**
     * Sets gpu of pc
     *
     * @param gpu gpu of pc
     */
    public void setGpu(Gpu gpu) {
        this.gpu = gpu;
    }

    /**
     * Gets representation json of pc
     *
     * @return representation json of pc
     */
    @Override
    public String toString() {
        return "Pc{" +
                "idPc=" + idPc +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", cpu=" + cpu +
                ", ram=" + ram +
                ", gpu=" + gpu +
                '}';
    }
}
