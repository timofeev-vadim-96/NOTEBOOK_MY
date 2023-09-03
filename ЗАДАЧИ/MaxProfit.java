//Ищем максимальную разницу в цене, при этом продать можно только после покупки.
class MaxProfit {
    public static void main(String[] args) {
        int [] prices = new int[]{11,4,22,14,6,4};
        System.out.println(maxProfit(prices));
    }
    public static int maxProfit(int[] arr) {
        int minPrice = Integer.MAX_VALUE;
        int todayDiff = 0;
        int maxProfit = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < minPrice) minPrice = arr[i];
            todayDiff = arr[i] - minPrice;
            if (todayDiff > maxProfit) maxProfit = todayDiff;
        }
        return maxProfit;
    }
}