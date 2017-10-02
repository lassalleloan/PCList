package ch.heigvd.pclist.models;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
public class Cpu {

    private final String brand;
    private final int nbCores;
    private final double frequency;

    public Cpu(String brand, int nbCores, double frequency) {
        this.brand = brand;
        this.nbCores = nbCores;
        this.frequency = frequency;
    }

    public String getBrand() {
        return brand;
    }

    public int getNbCores() {
        return nbCores;
    }

    public double getFrequency() {
        return frequency;
    }

    @Override
    public String toString() {
        return "Cpu{" +
                "brand='" + brand + '\'' +
                ", nbCores=" + nbCores +
                ", frequency=" + frequency +
                '}';
    }
}
