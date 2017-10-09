package ch.heigvd.pclist.models;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
public class Ram {

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
