package problems.dp;

public class MatrixMultiplicationCost {
    
    public int findCost(int arr[]) {
        int[][] costs = new int[arr.length - 1][arr.length - 1];
        
        for (int len = 1; len < costs.length; len++) {
            for (int j = len, i = 0; j < costs.length; j++, i++) {
                costs[i][j] = Math.min(costs[i][j - 1] + arr[i] * arr[j] * arr[j + 1], costs[i + 1][j] + arr[i] * arr[i + 1] * arr[j + 1]);
            }
        }
        
        return costs[0][costs.length - 1];
    }
    
    public int findCostLessMemoryMoreCode(int arr[]) {
        int[] costs = new int[arr.length - 1];
        int[] temp = new int[arr.length - 1];
        
        for (int len = 1; len < costs.length; len++) {
            for (int j = len, i = 0; j < costs.length; j++, i++) {
                costs[j] = Math.min(temp[j - 1] + arr[i] * arr[j] * arr[j + 1], temp[j] + arr[i] * arr[i + 1] * arr[j + 1]);
            }
            for (int j = len; j < costs.length; j++) temp[j] = costs[j];
        }
        
        return costs[costs.length - 1];
    }
    
    public static void main(String args[]) {
        MatrixMultiplicationCost mmc = new MatrixMultiplicationCost();
        int arr[] = {4,2,3,5,3};
        int cost = mmc.findCost(arr);
        System.out.print(cost);
    }

}
