package problems.dp;

public class MaxSumForNonAdjacentElements {
    
    public int maxSum(int arr[]) {
        
        int exc = 0;
        int inc = arr[0];
        int temp;
        
        for (int i = 1; i < arr.length; i++) {
            temp = exc + arr[i];
            exc = Math.max(exc, inc);
            inc = Math.max(inc, temp);
        }
        
        return Math.max(exc, inc);
    }

    public static void main(String args[]) {
        MaxSumForNonAdjacentElements msn = new MaxSumForNonAdjacentElements();
        int arr[] = { 2, 10, 13, 4, 2, 15, 10 };
        System.out.println(msn.maxSum(arr));

    }
}
