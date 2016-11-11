package problems.dp;

public class LongestIncreasingSubsequence {
    
    public int longestSubsequenceWithActualSolution(int arr[]){
        int max = 0;
        int index = 0;
        int[] less = new int[arr.length];
        int[] indices = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            less[i] = 1;
            indices[i] = -1;
        }
        
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) if (arr[i] > arr[j]) {
                if (less[i] < less[j] + i) {
                    indices[i] = j;
                    less[i] = less[j] + 1;
                    
                    if (max < less[i]) {
                        max = less[i];
                        index = i;
                    }
                }
            }
        }
        
        for (int i = index; i != -1; i = indices[i]) System.out.println(arr[i]);
        System.out.println();
        
        return max;
    }
    
    public static void main(String args[]){
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        int arr[] = {23,10,22,5,33,8,9,21,50,41,60,80,99, 22,23,24,25,26,27};
        int result = lis.longestSubsequenceWithActualSolution(arr);
        System.out.println(result);
    }
}
