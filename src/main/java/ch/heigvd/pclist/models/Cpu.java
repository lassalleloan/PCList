package ch.heigvd.pclist.models;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
public class Cpu {

    private final String brand;
    private final int cores;
    private final double frequency;

    public Cpu(String brand, int cores, double frequency) {
        this.brand = brand;
        this.cores = cores;
        this.frequency = frequency;
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
                "brand='" + brand + '\'' +
                ", cores=" + cores +
                ", frequency=" + frequency +
                '}';
    }
}
