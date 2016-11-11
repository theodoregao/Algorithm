package problems.dp;

public class CuttingRod {
    
    public int maxProfit(int[] prices, int n) {
        
        int[] profits = new int[n + 1];
        int[] cutAt = new int[n + 1];
        profits[0] = 0;
        for (int i = 1; i <= n; i++) profits[i] = Integer.MIN_VALUE;
        
        for (int i = 1; i <= prices.length; i++) {
            for (int j = i; j <= n; j++) {
                if (profits[j] < profits[j - i] + prices[i - 1]) cutAt[j] = i;
                profits[j] = Math.max(profits[j], profits[j - i] + prices[i - 1]);
            }
        }
        
        for (int i = n; i > 0; ) {
            System.out.println(cutAt[i] + ": " + prices[cutAt[i] - 1]);
            i -= cutAt[i];
        }
        
        return profits[n];
    }
    
    public int maxProfitRecurse(int[] prices, int n) {
        int profit = n <= prices.length ? prices[n - 1] : Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) profit = Math.max(profit, maxProfitRecurse(prices, i) + maxProfitRecurse(prices, n - i));
        return profit;
    }
    
    public static void main(String args[]){
        CuttingRod cr = new CuttingRod();
        int[] prices = {3,5,8,9,10,20,22,25};
        long t1 = System.currentTimeMillis();
//        int r = cr.maxProfitRecurse(prices, 20);
        int r = cr.maxProfit(prices, 20);
        long t2 = System.currentTimeMillis();
        System.out.println(r);
        System.out.println(t2 - t1);
}
}
