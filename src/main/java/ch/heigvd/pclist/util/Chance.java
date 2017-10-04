package ch.heigvd.pclist.util;

import ch.heigvd.pclist.models.Cpu;
import ch.heigvd.pclist.models.Gpu;
import ch.heigvd.pclist.models.Ram;

import java.util.Arrays;
import java.util.List;

/**
 * This utility class is used to generate random values.
 *
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
public class Chance {

    private static final List<String> pcBrands = Arrays.asList("Acer", "Apple", "Asus", "Dell", "HP", "Lenovo", "Medion", "MSI", "Packard Bell", "Samsung", "Toshiba");
    private static final List<String> cpuBrands = Arrays.asList("AMD", "Intel");
    private static final List<Integer> cpuNbCores = Arrays.asList(1, 2, 4, 6, 8, 10, 12);
    private static final List<String> ramBrands = Arrays.asList("Corsair", "Crucial", "G.Skill", "HP", "HyperFx", "Kingston", "Samsung");
    private static final List<Integer> ramSize = Arrays.asList(2, 4, 8, 16, 32, 64);
    private static final List<String> gpuBrands = Arrays.asList("AMD", "Asus", "ATI", "EVGA", "Gigabyte", "MSI", "NVIDIA", "XFX");

    public static List<String> getPcBrands() {
        return pcBrands;
    }

    public static List<String> getCpuBrands() {
        return cpuBrands;
    }

    public static List<Integer> getCpuNbCores() {
        return cpuNbCores;
    }

    public static List<String> getRamBrands() {
        return ramBrands;
    }

    public static List<Integer> getRamSize() {
        return ramSize;
    }

    public static List<String> getGpuBrands() {
        return gpuBrands;
    }

    /**
     * Basic random generator for
     *
     * @return a random pc brand
     */
    public static String randomPcBrand() {
        return pickRandom(pcBrands);
    }

    /**
     * Basic random generator for
     *
     * @return a random pc price
     */
    public static double randomPcPrice() {
        return Math.floor((Math.random() * (5000 - 1000 + 1) + 1000) * 100) / 100;
    }

    /**
     * Basic random generator for
     *
     * @return a random cpu
     */
    public static Cpu randomCpu() {
        return new Cpu(randomCpuBrand(), randomCpuNbCores(), randomCpuFrequency());
    }

    /**
     * Basic random generator for
     *
     * @return a random cpu brand
     */
    private static String randomCpuBrand() {
        return pickRandom(cpuBrands);
    }

    /**
     * Basic random generator for
     *
     * @return a random cpu number cores
     */
    private static int randomCpuNbCores() {
        return pickRandom(cpuNbCores);
    }

    /**
     * Basic random generator for
     *
     * @return a random cpu frequency
     */
    private static double randomCpuFrequency() {
        return Math.floor((Math.random() * (4 - 2 + 1) + 2) * 100) / 100;
    }

    /**
     * Basic random generator for
     *
     * @return a random ram
     */
    public static Ram randomRam() {
        return new Ram(randomRamBrands(), randomRamSize());
    }

    /**
     * Basic random generator for
     *
     * @return a random ram brand
     */
    private static String randomRamBrands() {
        return pickRandom(ramBrands);
    }

    /**
     * Basic random generator for
     *
     * @return a random ram size
     */
    private static int randomRamSize() {
        return pickRandom(ramSize);
    }

    /**
     * Basic random generator for
     *
     * @return a random gpu
     */
    public static Gpu randomGpu() {
        return new Gpu(randomGpuBrands());
    }

    /**
     * Basic random generator for
     *
     * @return a random gpu brands
     */
    private static String randomGpuBrands() {
        return pickRandom(gpuBrands);
    }

    /**
     * Select a random element within an array of elements
     *
     * @param elements the array in which to select a random element
     * @return one element of the array, selected randomly
     */
    private static <T> T pickRandom(List<T> elements) {
        return elements.get((int) (Math.random() * elements.size()));
    }
}