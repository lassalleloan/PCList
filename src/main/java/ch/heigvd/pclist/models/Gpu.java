package ch.heigvd.pclist.models;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
public class Gpu {

    private final long idGpu;
    private final String brand;

    public Gpu(long idGpu, String brand) {
        this.idGpu = idGpu;
        this.brand = brand;
    }

    public long getIdGpu() {
        return idGpu;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return "Gpu{" +
                "idGpu=" + idGpu +
                ", brand='" + brand + '\'' +
                '}';
    }
}
