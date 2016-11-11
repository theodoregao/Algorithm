package problems.dp;

public class Knapsack01 {
    
    public int bottomUpDP(int[] values, int[] weights, int weight) {
        
        int[] maxValue = new int[weight + 1];
        int[] preMaxValue = new int[weight + 1];
        
        for (int i = 0; i < values.length; i++) {
            for (int j = weights[i]; j <= weight; j++)
                maxValue[j] = Math.max(preMaxValue[j], preMaxValue[j - weights[i]] + values[i]);
            for (int j = 0; j <= weight; j++) preMaxValue[j] = maxValue[j];
        }
        
        return maxValue[weight];
    }

    public static void main(String args[]){
        Knapsack01 k = new Knapsack01();
        int val[] = {22, 20, 15, 30, 24, 54, 21, 32, 18, 25};
        int wt[] = {4, 2, 3, 5, 5, 6, 9, 7, 8, 10};
        int r = k.bottomUpDP(val, wt, 30);
//        int r1 = k.topDownRecursive(val, wt, 30);
        System.out.println(r);
//        System.out.println(r1);
    }
    
}
