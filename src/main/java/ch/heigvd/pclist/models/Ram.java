package ch.heigvd.pclist.models;

public class Ram {

    private final String brand;
    private final int size;

    public Ram(String brand, int size) {
        this.brand = brand;
        this.size = size;
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
                "brand='" + brand + '\'' +
                ", size=" + size +
                '}';
    }
}
