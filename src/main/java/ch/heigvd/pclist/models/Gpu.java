package ch.heigvd.pclist.models;

import java.util.Arrays;
import java.util.List;

/**
 * Models a graphic
 *
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 * @since 13.09.2017
 */
public class Gpu {

    public static List<String> FIELD_LIST = Arrays.asList("brand");

    private long idGpu;
    private String brand;

    public Gpu(long idGpu, String brand) {
        this.idGpu = idGpu;
        this.brand = brand;
    }

    public long getIdGpu() {
        return idGpu;
    }

    public void setIdGpu(long idGpu) {
        this.idGpu = idGpu;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Gpu{" +
                "idGpu=" + idGpu +
                ", brand='" + brand + '\'' +
                '}';
    }
}
