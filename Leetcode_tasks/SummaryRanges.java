
/**
 * 228. Summary Ranges
 * Крч дан отсортированный массив уникальных чисел (в т.ч. отрицательных), нужно вывести список с диапазонами этих чисел. 
 * Если числа в массиве идут без разрывна (1,2,3), то "1->3", а если рядом стояющие числа отличаются более, чем на 1,
 * например (1,3), то так и записывать в список "1", "3".
 */


import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        List<Integer> ints = new ArrayList<>(); //список для текущей комбинации
        if (nums.length == 0) return list;
        for (int i = 0; i < nums.length; i++) {
            ints.add(nums[i]);
            long diff = 0; //long, т.к. при сумме двух Integer.MAX - они не поместятся в int
            if (!(i == nums.length - 1)) {
                diff = Math.abs(nums[i + 1] - nums[i]);
            }
            if (i == nums.length - 1 || diff > 1) { //если мы на последнем элементе, то логично, что пора добавить последний элемент
                if (ints.size() > 1) {
                    list.add(ints.get(0) + "->" + ints.get(ints.size() - 1));
                } else list.add(String.valueOf(ints.get(0)));
                ints.clear();
            }
        }
        return list;
    }
}

