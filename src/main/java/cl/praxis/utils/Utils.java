package cl.praxis.utils;

public class Utils {

    public static String getOS() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            osName = "Windows";
        } else {
            osName = "Unix";
        }
        return osName;
    }

    public static void clearScreenFalse() {
        for(int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

}
