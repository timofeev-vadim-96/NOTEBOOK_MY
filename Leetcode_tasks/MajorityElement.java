import java.util.Map;

/**
 * 169. Majority Element
 * Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times. 
You may assume that the majority element always exists in the array.
 */
public class MajorityElement {
        public static void main(String[] args) {
        int [] ints = new int[]{3,2,3};
        System.out.println(majorityElement(ints));
    }
    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i: nums) {
            if (!map.containsKey(i)) {
                map.put(i, 1);
            } else {
                Integer quantity = map.get(i);
                map.put(i, ++quantity);
            }
        }

        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (entry.getValue() >= (nums.length/2 + nums.length%2)) {
                System.out.println(map);
                return entry.getKey();
            }
        }

        return 0;
    }
}
