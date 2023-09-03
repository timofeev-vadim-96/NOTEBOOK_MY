//Задачи с игральными кубиками
public class cubsChance {
    public static void main(String[] args) {
//        System.out.println(calculateChance(18));
        getCombo();
    }

    //Шанс выпадения определенной суммы трех кубов. Сложность O(n^3)
    public static double calculateChance (int sum){ 
        double counter = 0;
        double needSum = 0;
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 6; j++) {
                for (int k = 1; k <= 6; k++) {
                    if (i + j + k == sum){
                        needSum++;
                    }
                    counter++;
                }
            }
        }
        return (double) Math.round(needSum / counter * 10000) /100; // уже в процентах
    }
    
    //Все комбинации кубов. Сложность O(n^3)
    public static void getCombo (){ 
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 6; j++) {
                for (int k = 1; k <= 6; k++) {
                    System.out.printf("%s, %s, %s\n", i, j, k);;
                }
            }
        }
    }
}