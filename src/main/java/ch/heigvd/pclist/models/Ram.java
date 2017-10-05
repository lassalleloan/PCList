package ch.heigvd.pclist.models;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
public class Ram {

    private final long idRam;
    private final String brand;
    private final int size;

    public Ram(long idRam, String brand, int size) {
        this.idRam = idRam;
        this.brand = brand;
        this.size = size;
    }

    public long getIdRam() {
        return idRam;
    }

    public String getBrand() {
        return brand;
    }

    public int getSize() {
        return size;
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
