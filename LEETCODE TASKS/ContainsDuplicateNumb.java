/**
 * 217. Contains Duplicate
 * Дан массив чисел. Вернуть true, если хотя бы одно число встречается дважды
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ContainsDuplicateNumb {
        public boolean containsDuplicate(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int n:nums) {
            list.add(n);
        }
        Collections.sort(list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) == list.get(i-1)) return true;
        }
        return false;
    }
}