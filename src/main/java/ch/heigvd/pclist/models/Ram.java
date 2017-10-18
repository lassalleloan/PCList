package ch.heigvd.pclist.models;

import java.util.Arrays;
import java.util.List;

/**
 * Models a memory
 *
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 * @since 13.09.2017
 */
public class Ram {

    /**
     * All fields of ram
     */
    public static List<String> FIELD_LIST = Arrays.asList("brand", "size");

    private long idRam;
    private String brand;
    private int size;

    public Ram(long idRam, String brand, int size) {
        this.idRam = idRam;
        this.brand = brand;
        this.size = size;
    }

    /**
     * Gets ID of ram
     *
     * @return ID of ram
     */
    public long getIdRam() {
        return idRam;
    }

    /**
     * Sets ID of ram
     *
     * @param idRam ID of ram
     */
    public void setIdRam(long idRam) {
        this.idRam = idRam;
    }

    /**
     * Gets brand of ram
     *
     * @return brand of ram
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets brand of ram
     *
     * @param brand brand of ram
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Gets size of ram
     *
     * @return size of ram
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets size of ram
     *
     * @param size size of ram
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Gets representation json of ram
     *
     * @return representation json of ram
     */
    @Override
    public String toString() {
        return "Ram{" +
                "idRam=" + idRam +
                ", brand='" + brand + '\'' +
                ", size=" + size +
                '}';
    }
}
