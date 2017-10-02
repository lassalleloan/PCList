package ch.heigvd.pclist.models;

/**
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
public class Pc {
    private final String brand;
    private final double price;
    private final Cpu cpu;
    private final Ram ram;
    private final Gpu gpu;

    public Pc(String brand, double price, Cpu cpu, Ram ram, Gpu gpu) {
        this.brand = brand;
        this.price = price;
        this.cpu = cpu;
        this.ram = ram;
        this.gpu = gpu;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
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

    @Override
    public String toString() {
        return "Pc{" +
                "brand='" + brand + '\'' +
                ", price=" + price +
                ", cpu=" + cpu +
                ", ram=" + ram +
                ", gpu=" + gpu +
                '}';
    }
}
