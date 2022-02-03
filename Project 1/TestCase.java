import java.io.FileNotFoundException;

public class TestCase {
    public static void main(String[] args) {
        try {
            GridMonitor gm = new GridMonitor("oneByOne.txt");
            System.out.println(arrayToString(gm.getBaseGrid()));
            System.out.println(arrayToString(gm.getSurroundingSumGrid()));
            System.out.println(arrayToString(gm.getSurroundingAvgGrid()));
            System.out.println(arrayToString(gm.getDeltaGrid()));
            System.out.println(arrayToString(gm.getDangerGrid()));
            System.out.println(gm.toString());
        } catch (FileNotFoundException e) {
            System.out.println("Exception");
        }
    }

    public static String arrayToString(double[][] array) {
        String toReturn = "";
        for (int i = 0; i < array.length; ++i) {
            toReturn += "[";
            for (int j = 0; j < array[0].length; ++j) {
                toReturn += array[i][j] + " ";
            }
            toReturn += "]\n";
        }
        return toReturn;
    }

    public static String arrayToString(boolean[][] array) {
        String toReturn = "";
        for (int i = 0; i < array.length; ++i) {
            toReturn += "[";
            for (int j = 0; j < array[0].length; ++j) {
                toReturn += array[i][j] + " ";
            }
            toReturn += "]\n";
        }
        return toReturn;
    }
}