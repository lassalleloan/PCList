package ch.heigvd.pclist.models;

import java.util.Collections;
import java.util.List;

/**
 * Models a graphic
 *
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 * @since 13.09.2017
 */
public class Gpu {

    /**
     * All fields of gpu
     */
    public static List<String> FIELD_LIST = Collections.singletonList("brand");

    private long idGpu;
    private String brand;

    public Gpu(long idGpu, String brand) {
        this.idGpu = idGpu;
        this.brand = brand;
    }

    /**
     * Gets ID of gpu
     *
     * @return ID of gpu
     */
    public long getIdGpu() {
        return idGpu;
    }

    /**
     * Sets ID of gpu
     *
     * @param idGpu ID of gpu
     */
    public void setIdGpu(long idGpu) {
        this.idGpu = idGpu;
    }

    /**
     * Gets brand of gpu
     *
     * @return brand of gpu
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets brand of gpu
     *
     * @param brand brand of gpu
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Gets representation json of gpu
     *
     * @return representation json of gpu
     */
    @Override
    public String toString() {
        return "Gpu{" +
                "idGpu=" + idGpu +
                ", brand='" + brand + '\'' +
                '}';
    }
}
