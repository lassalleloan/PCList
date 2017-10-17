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

    public static List<String> FIELD_LIST = Arrays.asList("brand", "size");

    private long idRam;
    private String brand;
    private int size;

    public Ram(long idRam, String brand, int size) {
        this.idRam = idRam;
        this.brand = brand;
        this.size = size;
    }

    public long getIdRam() {
        return idRam;
    }

    public void setIdRam(long idRam) {
        this.idRam = idRam;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Ram{" +
                "idRam=" + idRam +
                ", brand='" + brand + '\'' +
                ", size=" + size +
                '}';
    }
}
