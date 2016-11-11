package problems.dp;

public class CoinChange {
    
    public int solutionCount(int total, int[] coins) {
        
        int[][] solutions = new int[coins.length][total + 1];
        
        for (int i = 0; i < coins.length; i++) solutions[i][0] = 1;
        
        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j <= total; j++)
                solutions[i][j] = (i <= 0 ? 0 : solutions[i - 1][j]) + (j < coins[i] ? 0 : solutions[i][j - coins[i]]);
        }
        
        return solutions[coins.length - 1][total];
    }
    
    public int solutionCountOptSpace(int total, int[] coins) {
        
        int[] solutions = new int[total + 1];
        solutions[0] = 1;
        
        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j <= total; j++)
                solutions[j] = solutions[j] + (j < coins[i] ? 0 : solutions[j - coins[i]]);
        }
        
        return solutions[total];
    }
    
    public int minimumCoin(int total, int[] coins) {
        int[] minimals = new int[total + 1];
        minimals[0] = 0;
        for (int i = 1; i <= total; i++) minimals[i] = Integer.MAX_VALUE / 2;
        
        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j <= total; j++) {
                minimals[j] = Math.min(minimals[j], (j - coins[i] >= 0 ? minimals[j - coins[i]] + 1 : Integer.MAX_VALUE / 2));
            }
        }
        
        return minimals[total];
    }
    
    public static void main(String[] args) {
        CoinChange cc = new CoinChange();
        int total = 15;
        int coins[] = {1, 2, 3, 4};
        System.out.println(cc.solutionCount(total, coins));
        System.out.println(cc.solutionCountOptSpace(total, coins));
        System.out.println(cc.minimumCoin(total, coins));
    }

}
