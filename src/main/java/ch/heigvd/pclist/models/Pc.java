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

    public long getIdPc() {
        return idPc;
    }

    public void setIdPc(long idPc) {
        this.idPc = idPc;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Cpu getCpu() {
        return cpu;
    }

    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
    }

    public Ram getRam() {
        return ram;
    }

    public void setRam(Ram ram) {
        this.ram = ram;
    }

    public Gpu getGpu() {
        return gpu;
    }

    public void setGpu(Gpu gpu) {
        this.gpu = gpu;
    }

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
