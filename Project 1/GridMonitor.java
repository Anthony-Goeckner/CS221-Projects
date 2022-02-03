import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author anthonygoeckner
 */
public class GridMonitor implements GridMonitorInterface {
    private double[][] baseArray;

    /**
     * Constructor for GridMonitor class, reads in file passed in and parses data
     * accordingly
     * 
     * @param filename Name of the file from which to parse data
     * @throws FileNotFoundException if file name passed in does not exist, throws
     *                               exception
     */
    public GridMonitor(String filename) throws FileNotFoundException {
        File inputData = new File(filename);
        if (inputData.exists()) {
            Scanner data = new Scanner(inputData);

            // when properly formatted, first two integers are the rows and columns in the
            // grid, respectively
            int rows = Integer.parseInt(data.next());
            int cols = Integer.parseInt(data.next());
            baseArray = new double[rows][cols];

            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < cols; ++j) {
                    baseArray[i][j] = data.nextDouble();
                }
            }
            data.close();
        } else {
            throw new FileNotFoundException();
        }
    }

    @Override
    public double[][] getBaseGrid() {
        double[][] arrayToReturn = new double[baseArray.length][baseArray[0].length];
        for (int i = 0; i < baseArray.length; ++i) {
            for (int j = 0; j < baseArray[0].length; ++j) {
                arrayToReturn[i][j] = baseArray[i][j];
            }
        }
        return arrayToReturn;
    }

    @Override
    public double[][] getSurroundingSumGrid() {
        double[][] sumGrid = new double[baseArray.length][baseArray[0].length];

        // loop through each value in the array and sum the surrounding cells
        for (int i = 0; i < baseArray.length; ++i) {
            for (int j = 0; j < baseArray[0].length; ++j) {
                double totalSum = 0.0;
                // left of cell
                if (j != 0) {
                    totalSum += baseArray[i][j - 1];
                } else {
                    totalSum += baseArray[i][j];
                }
                // right of cell
                if (j != (baseArray[0].length - 1)) {
                    totalSum += baseArray[i][j + 1];
                } else {
                    totalSum += baseArray[i][j];
                }
                // above cell
                if (i != 0) {
                    totalSum += baseArray[i - 1][j];
                } else {
                    totalSum += baseArray[i][j];
                }
                // below cell
                if (i != (baseArray.length - 1)) {
                    totalSum += baseArray[i + 1][j];
                } else {
                    totalSum += baseArray[i][j];
                }

                sumGrid[i][j] = totalSum;
            }
        }

        return sumGrid;
    }

    @Override
    public double[][] getSurroundingAvgGrid() {
        double[][] sumGrid = this.getSurroundingSumGrid();
        double[][] avgGrid = new double[baseArray.length][baseArray[0].length];
        for (int i = 0; i < sumGrid.length; ++i) {
            for (int j = 0; j < sumGrid[0].length; ++j) {
                avgGrid[i][j] = (sumGrid[i][j] / 4);
            }
        }
        return avgGrid;
    }

    @Override
    public double[][] getDeltaGrid() {
        double[][] avgGrid = this.getSurroundingAvgGrid();
        double[][] deltaGrid = new double[baseArray.length][baseArray[0].length];
        for (int i = 0; i < avgGrid.length; ++i) {
            for (int j = 0; j < avgGrid[0].length; ++j) {
                deltaGrid[i][j] = Math.abs(avgGrid[i][j] / 2);
            }
        }
        return deltaGrid;
    }

    @Override
    public boolean[][] getDangerGrid() {
        double[][] deltaGrid = this.getDeltaGrid();
        double[][] avgGrid = this.getSurroundingAvgGrid();
        boolean[][] dangerGrid = new boolean[baseArray.length][baseArray[0].length];
        for (int i = 0; i < deltaGrid.length; ++i) {
            for (int j = 0; j < deltaGrid[0].length; ++j) {
                double currentTemp = baseArray[i][j];
                double safeMin = avgGrid[i][j] - deltaGrid[i][j];
                double safeMax = avgGrid[i][j] + deltaGrid[i][j];

                // currently broken for negative numbers
                if (currentTemp < safeMin || currentTemp > safeMax) {
                    dangerGrid[i][j] = true;
                } else {
                    dangerGrid[i][j] = false;
                }
            }
        }
        return dangerGrid;
    }

    @Override
    public String toString() {
        String information = "Current Temperatures:\n";

        // loop through the baseGrid to display temperatures in a good format
        for (int i = 0; i < baseArray.length; ++i) {
            information += "[";
            int j;
            for (j = 0; j < baseArray[0].length; ++j) {
                information += baseArray[i][j] + "\t";
            }
            // instead of adding tab to last value, appends the closing bracket
            information += "]\n";
        }

        information += "Danger: Grid will display true if\nsection is in danger of exploding.\n";

        // loop through the dangerGrid to display temperatures in a good format
        boolean[][] danger = this.getDangerGrid();
        for (int i = 0; i < danger.length; ++i) {
            information += "[";
            int j;
            for (j = 0; j < danger[0].length; ++j) {
                information += danger[i][j] + "\t";
            }
            // instead of adding tab to last value, appends the closing bracket
            information += "]\n";
        }

        return information;
    }
}