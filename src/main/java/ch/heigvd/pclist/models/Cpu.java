package ch.heigvd.pclist.models;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
public class Cpu {

    private final long idCpu;
    private final String brand;
    private final int cores;
    private final double frequency;

    public Cpu(long idCpu, String brand, int cores, double frequency) {
        this.idCpu = idCpu;
        this.brand = brand;
        this.cores = cores;
        this.frequency = frequency;
    }

    public long getIdCpu() {
        return idCpu;
    }

    public String getBrand() {
        return brand;
    }

    public int getCores() {
        return cores;
    }

    public double getFrequency() {
        return frequency;
    }

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
