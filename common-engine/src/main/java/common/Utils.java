package common;

public class Utils {

    public static boolean isTheyTheLookingFixtures(int dataA, int dataB,Object dataAA, Object dataBB) {
        if (dataAA == null || dataBB == null) {
            throw new IllegalStateException("Object data cannot find on contact");
        }

        if (dataA == (int) dataAA && dataB == (int) dataBB) {
            return true;
        } else if (dataB == (int) dataAA && dataA == (int) dataBB) {
            return true;
        } else {
            return false;
        }
    }

}
