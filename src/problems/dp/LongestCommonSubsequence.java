package problems.dp;

public class LongestCommonSubsequence {

    public int lcsDynamic(char str1[], char str2[]){
        // 可优化为线性
        int[][] max = new int[str1.length + 1][str2.length + 1];
        for (int i = 0; i <= str1.length; i++) max[i][0] = 0;
        for (int j = 0; j <= str2.length; j++) max[0][j] = 0;
        
        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length; j++) {
                if (str1[i - 1] == str2[j - 1]) max[i][j] = max[i - 1][j - 1] + 1;
                else max[i][j] = Math.max(max[i - 1][j], max[i][j - 1]);
            }
        }
        
        return max[str1.length][str2.length];
    }
    
    public static void main(String args[]){
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        String str1 = "abcdef";
        String str2 = "acbcf";
        
        int result = lcs.lcsDynamic(str1.toCharArray(), str2.toCharArray());
        System.out.print(result);
}

}
