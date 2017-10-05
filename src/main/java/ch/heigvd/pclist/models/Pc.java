package ch.heigvd.pclist.models;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
public class Pc {

    private final long idPc;
    private final String brand;
    private final Cpu cpu;
    private final Ram ram;
    private final Gpu gpu;
    private final double price;

    public Pc(long idPc, String brand, Cpu cpu, Ram ram, Gpu gpu, double price) {
        this.idPc = idPc;
        this.brand = brand;
        this.cpu = cpu;
        this.ram = ram;
        this.gpu = gpu;
        this.price = price;
    }

    public long getIdPc() {
        return idPc;
    }

    public String getBrand() {
        return brand;
    }

    public Cpu getCpu() {
        return cpu;
    }

    public Ram getRam() {
        return ram;
    }

    public Gpu getGpu() {
        return gpu;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Pc{" +
                "idPc=" + idPc +
                ", brand='" + brand + '\'' +
                ", cpu=" + cpu +
                ", ram=" + ram +
                ", gpu=" + gpu +
                ", price=" + price +
                '}';
    }
}
