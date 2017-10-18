package ch.heigvd.pclist.util;

import ch.heigvd.pclist.models.Cpu;
import ch.heigvd.pclist.models.Gpu;
import ch.heigvd.pclist.models.Pc;
import ch.heigvd.pclist.models.Ram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Generates random values
 *
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 * @since 13.09.2017
 */
public class Chance {

    /**
     * Values used to generate random things
     */
    private static final List<String> cpuBrands = Arrays.asList("AMD", "Intel");
    private static final List<Integer> cpuNbCores = Arrays.asList(1, 2, 4, 6, 8, 10, 12);
    private static final List<String> gpuBrands = Arrays.asList("AMD", "Asus", "ATI", "EVGA", "Gigabyte", "MSI", "NVIDIA", "XFX");
    private static final List<String> pcBrands = Arrays.asList("Acer", "Apple", "Asus", "Dell", "HP", "Lenovo", "Medion", "MSI", "Packard Bell", "Samsung", "Toshiba");
    private static final List<String> ramBrands = Arrays.asList("Corsair", "Crucial", "G.Skill", "HP", "HyperFx", "Kingston", "Samsung");
    private static final List<Integer> ramSize = Arrays.asList(2, 4, 8, 16, 32, 64);

    /**
     * Generates a list of random cpu
     *
     * @return a random list of random cpu
     */
    public static List<Cpu> randomCpu(long maximum) {
        List<Cpu> cpuList = new ArrayList<>();

        for (int i = 0; i < maximum; ++i) {
            cpuList.add(randomCpu());
        }

        return cpuList;
    }

    /**
     * Generates a random cpu
     *
     * @return a random cpu
     */
    private static Cpu randomCpu() {
        return new Cpu(0, randomCpuBrand(), randomCpuNbCores(), randomCpuFrequency());
    }

    /**
     * Generates a random cpu brand
     *
     * @return a random cpu brand
     */
    private static String randomCpuBrand() {
        return pickRandom(cpuBrands);
    }

    /**
     * Generates a random cpu number cores
     *
     * @return a random cpu number cores
     */
    private static int randomCpuNbCores() {
        return pickRandom(cpuNbCores);
    }

    /**
     * Generates a random cpu frequency
     *
     * @return a random cpu frequency
     */
    private static double randomCpuFrequency() {
        return Math.floor((Math.random() * (4 - 2 + 1) + 2) * 100) / 100;
    }

    /**
     * Generates a random list of random gpu
     *
     * @return a random list of random gpu
     */
    public static List<Gpu> randomGpu(long maximum) {
        List<Gpu> gpuList = new ArrayList<>();

        for (int i = 0; i < maximum; ++i) {
            gpuList.add(randomGpu());
        }

        return gpuList;
    }

    /**
     * Generates a random gpu
     *
     * @return a random gpu
     */
    private static Gpu randomGpu() {
        return new Gpu(0, randomGpuBrands());
    }

    /**
     * Generates a random gpu brands
     *
     * @return a random gpu brands
     */
    private static String randomGpuBrands() {
        return pickRandom(gpuBrands);
    }

    /**
     * Generates a list of random pc
     *
     * @return a random list of random pc
     */
    public static List<Pc> randomPc(long maximum) {
        List<Pc> pcList = new ArrayList<>();

        for (int i = 0; i < maximum; ++i) {
            pcList.add(randomPc());
        }

        return pcList;
    }

    /**
     * Generates a random pc
     *
     * @return a random pc
     */
    private static Pc randomPc() {
        return new Pc(0, randomPcBrand(), randomPcPrice(), randomCpu(), randomRam(), randomGpu());
    }

    /**
     * Generates a random pc brand
     *
     * @return a random pc brand
     */
    private static String randomPcBrand() {
        return pickRandom(pcBrands);
    }

    /**
     * Generates a random pc size
     *
     * @return a random pc price
     */
    private static double randomPcPrice() {
        return Math.floor((Math.random() * (5000 - 1000 + 1) + 1000) * 100) / 100;
    }

    /**
     * Generates a list of random ram
     *
     * @return a random list of random ram
     */
    public static List<Ram> randomRam(long maximum) {
        List<Ram> ramList = new ArrayList<>();

        for (int i = 0; i < maximum; ++i) {
            ramList.add(randomRam());
        }

        return ramList;
    }

    /**
     * Generates a random ram
     *
     * @return a random ram
     */
    private static Ram randomRam() {
        return new Ram(0, randomRamBrands(), randomRamSize());
    }

    /**
     * Generates a random ram brand
     *
     * @return a random ram brand
     */
    private static String randomRamBrands() {
        return pickRandom(ramBrands);
    }

    /**
     * Generates a random ram size
     *
     * @return a random ram size
     */
    private static int randomRamSize() {
        return pickRandom(ramSize);
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
