package ch.heigvd.pclist.models;

public class Gpu {

    private final String brand;

    public Gpu(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return "Gpu{" +
                "brand='" + brand + '\'' +
                '}';
    }
}
