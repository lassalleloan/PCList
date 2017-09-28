package ch.heigvd.pclist.util;

/**
 * This utility class is used to generate random values.
 *
 * @author Loan Lassalle (loan.lassalle@heig-vd.ch)
 * @author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 */
public class Chance {

    private static final String[] pcBrands = {"Apple", "Dell", "Asus", "Lenovo", "MSI", "HP", "Acer", "Samsung", "Toshiba", "Medion", "Packard Bell"};
    private static final String[] cpuBrands = {"AMD", "Intel"};
    private static final String[] cpuNbCores = {"1", "2", "4", "6", "8", "10", "12"};
    private static final String[] ramBrands = {"G.Skill", "Corsair", "Crucial", "Kingston", "Samsung", "HP", "HyperFx"};
    private static final String[] gpuBrands = {"NVIDIA", "AMD", "Asus", "ATI", "MSI", "Gigabyte", "XFX", "EVGA"};

    /**
     * Basic random generator for
     *
     * @return
     */
    public static String randomPcBrand() {
        return pickRandom(pcBrands);
    }

    /**
     * Basic random generator for
     *
     * @return
     */
    public static String randomCpuBrand() {
        return pickRandom(cpuBrands);
    }

    /**
     * Basic random generator for
     *
     * @return
     */
    public static String randomCpuNbCores() {
        return pickRandom(cpuNbCores);
    }

    /**
     * Basic random generator for
     *
     * @return
     */
    public static double randomCpuFrequency() {
        return 2 + (4 - 2) * Math.random();
    }

    /**
     * Basic random generator for
     *
     * @return
     */
    public static String randomRamBrands() {
        return pickRandom(ramBrands);
    }

    /**
     * Basic random generator for
     *
     * @return
     */
    public static String randomGpuBrands() {
        return pickRandom(gpuBrands);
    }

    /**
     * Select a random element within an array of elements
     *
     * @param elements the array in which to select a random element
     * @return one element of the array, selected randomly
     */
    public static String pickRandom(String[] elements) {
        return elements[(int) (Math.random() * elements.length)];
    }

}
