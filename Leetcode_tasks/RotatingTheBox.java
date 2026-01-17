import java.util.Arrays;

/**
 * 1861. Rotating the Box (Medium)
 *
 * Повернуть коробку с камнями на 90 градусов так, чтобы камни под действием гравитации упали либо на преграду, либо
 * на край коробки
 */
public class RotatingTheBox {
    private static final char STONE = '#';

    private static final char EMPTY = '.';

    public static void main(String[] args) {
//        char[][] box = {
//                {'#', '#', '*', '.', '*', '.'},
//                {'#', '#', '#', '*', '.', '.'},
//                {'#', '#', '#', '.', '#', '.'}};
//        char[][] box = {{'#', '.','#'}};
        char[][] box = {{'#'}, {'.'},{'#'}};
        char[][] result = rotateTheBox(box);
        print2DArray(result);
    }

    public static char[][] rotateTheBox(char[][] box) {
        int rows = box.length;
        int columns = box[0].length;

        char[][] result = new char[columns][rows];
        int m = rows - 1;
        for (int i = 0; i < rows; i++) {
            int j = columns - 1;
            while (j >= 0) {
                //в ячейке камень и ему есть куда падать вправо
                if (box[i][j] == STONE && j != columns - 1 && box[i][j + 1] == EMPTY) {
                    int stoneIndex = j;
                    j++;
                    //пока мы не вышли за предел строки, и ячейка пуста
                    while (j != columns && box[i][j] == EMPTY) {
                        box[i][stoneIndex] = EMPTY;
                        box[i][j] = STONE;
                        stoneIndex = j;
                        j++;
                    }
                }
                j--;
            }
            for (int k = 0; k < columns; k++) {
                result[k][m] = box[i][k];
            }
            m--;
        }
        return result;
    }

    public static void print2DArray(char[][] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("Массив пустой или null.");
            return;
        }

        for (char[] row : arr) {
            System.out.println(Arrays.toString(row));
        }
    }
}
