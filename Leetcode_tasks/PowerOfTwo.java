
//231. Power of Two
//Given an integer n, return true if it is a power of two. Otherwise, return false.
//An integer n is a power of two, if there exists an integer x such that n == 2x.
public class PowerOfTwo {
    public static void main(String[] args) {
        System.out.println(isPowerOfTwo(32));
    }

    public static boolean isPowerOfTwo(int n) {
        if (n == 1) {
            return true;
        }

        double res = n;

        while (res >= 2) {
            if (res == 2) {
                return true;
            }
            res = res/2 - res%2;
        }

        return false;
    }
}