import java.util.ArrayList;
import java.util.List;

/**
 * 1072. Flip Columns For Maximum Number of Equal Rows (Medium) //имхо сложная
 *
 * You are given an m x n binary matrix matrix.
 * You can choose any number of columns in the matrix and flip every cell in that column
 * (i.e., Change the value of the cell from 0 to 1 or vice versa).
 * Return the maximum number of rows that have all values equal after some number of flips.
 */
public class FlipColumnsForMaximumNumberOfEqualRows {
    public static void main(String[] args) {
        int[][] matrix = {{0, 0, 0}, {0, 0, 1}, {1, 1, 0}}; //2
//        int [][] matrix = {{0, 0, 0},{0,1,0},{0,0,1},{0,0,0},{0,0,0},{1,1,0},{1,1,1},{1,1,1},{1,1,1},{1,0,1}}; //6
//        int[][] matrix = {{0, 1}, {1, 0}}; //2
        System.out.println(maxEqualRowsAfterFlips(matrix));
    }

    public static int maxEqualRowsAfterFlips(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;

        if (rows == 1 || columns == 1) {
            return rows;
        }

        int equalRows = countEqualRows(matrix);

        if (equalRows >= rows / 2 + rows % 2) {
            return equalRows;
        }

        List<List<Integer>> swaps = new ArrayList<>();
        for (int[] arr : matrix) {
            List<Integer> zeroIds = new ArrayList<>();
            List<Integer> oneIds = new ArrayList<>();
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == 0) {
                    zeroIds.add(j);
                } else {
                    oneIds.add(j);
                }
            }
            if (zeroIds.size() == oneIds.size()) {
                swaps.add(zeroIds);
                swaps.add(oneIds);
            } else {
                if (zeroIds.size() != arr.length && oneIds.size() != arr.length) {
                    List<Integer> biggerList = zeroIds.size() > oneIds.size() ? zeroIds : oneIds;
                    swaps.add(biggerList);
                }
            }
        }
        int generalMaxEquals = 0;
        for (int i = 0; i < swaps.size(); i++) {
            int currentEquals = 1;
            for (int j = 0; j < swaps.size(); j++) {
                if (i != j && swaps.get(i).equals(swaps.get(j))) {
                    currentEquals++;
                }
            }
            if (currentEquals > generalMaxEquals) {
                generalMaxEquals = currentEquals;
            }
        }

        return Math.max(generalMaxEquals, equalRows);
    }

    private static int countEqualRows(int[][] matrix) {
        int equalRows = 0;
        outer:
        for (int[] ints : matrix) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (ints[j] != ints[j - 1]) {
                    continue outer;
                }
            }
            equalRows++;
        }
        return equalRows;
    }
}
