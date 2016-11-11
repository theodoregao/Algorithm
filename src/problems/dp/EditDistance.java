package problems.dp;

public class EditDistance {
    
    public int dynamicEditDistance(char[] str1, char[] str2) {
        
        int[][] distances = new int[str1.length + 1][str2.length + 1];
        
        for (int i = 0; i <= str1.length; i++) distances[i][0] = i;
        for (int j = 0; j <= str2.length; j++) distances[0][j] = j;
        
        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length; j++)
                distances[i][j] = str1[i - 1] == str2[j - 1] ? distances[i - 1][j - 1] :
                        Math.min((Math.min(distances[i - 1][j], distances[i][j - 1])), distances[i - 1][j - 1]) + 1;
        }
        
        for (int i = 0; i <= str1.length; i++) {
            for (int j = 0; j <= str2.length; j++) System.out.print(distances[i][j]);
            System.out.println();
        }
        
        return distances[str1.length][str2.length];
    }

    public static void main(String args[]){
        String str1 = "azced";
        String str2 = "abcdef";
        EditDistance editDistance = new EditDistance();
        int result = editDistance.dynamicEditDistance(str1.toCharArray(), str2.toCharArray());
        System.out.print(result);
    }
    
}
