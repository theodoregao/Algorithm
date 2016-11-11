package problems.dp;

/**
 * Dynamic Programming | Set 19 (Word Wrap Problem)
 *
 * Given a sequence of words, and a limit on the number of characters that
 * can be put in one line (line width). Put line breaks in the given sequence
 * such that the lines are printed neatly. Assume that the length of each word
 * is smaller than the line width.
 */
public class TextJustification {
    
    public String justify(String words[], int width) {
        int[][] costs = new int[words.length][words.length];
        for (int i = 0; i < words.length; i++) {
            int cost = width;
            for (int j = i; j < words.length; j++, cost--) {
                cost -= words[j].length();
                costs[i][j] = cost >= 0 ? cost * cost : Integer.MAX_VALUE;
            }
        }
        
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) System.out.print(String.format("%4d", costs[i][j] == Integer.MAX_VALUE ? -1 : costs[i][j]));
            System.out.println();
        }
        
        int[] minimalCosts = new int[words.length];
        int[] breaks = new int[words.length];
        for (int i = 0; i < minimalCosts.length; i++) minimalCosts[i] = Integer.MAX_VALUE;
        
        int currentCost;
        for (int i = 0; i < words.length; i++) {
//                for (int k = 0; k < i; k++) System.out.print(String.format("%4d", minimalCosts[k]));
//                System.out.println();
//                for (int k = 0; k < i; k++) System.out.print(String.format("%4d", breaks[k]));
//                System.out.println();
            for (int j = 0; j <= i; j++) {
                if (costs[j][i] != Integer.MAX_VALUE) {
                    currentCost = (j > 0 ? minimalCosts[j - 1] : 0) + costs[j][i];
//                        System.out.println(j + ": " + currentCost + " vs. " + minimalCosts[i]);
                    if (currentCost < minimalCosts[i]) {
                        minimalCosts[i] = currentCost;
                        breaks[i] = j;
//                            System.out.println(i + " set " + minimalCosts[i] + " with " + j);
                    }
                }
            }
//                System.out.println();
        }
        
        System.out.println("min cost: " + minimalCosts[words.length - 1]);
        for (int k = 0; k < words.length; k++) System.out.print(String.format("%4d", k));
        System.out.println();
        for (int k = 0; k < words.length; k++) System.out.print(String.format("%4d", words[k].length()));
        System.out.println();
        for (int k = 0; k < words.length; k++) System.out.print(String.format("%4d", minimalCosts[k]));
        System.out.println();
        for (int k = 0; k < words.length; k++) System.out.print(String.format("%4d", breaks[k]));
        System.out.println();
        
        StringBuilder stringBuilder = new StringBuilder();
        
        int nextIndex = 0;
        for (int i = words.length - 1; i >= 0; ) {
            nextIndex = breaks[i];
            while (i >= nextIndex) {
                stringBuilder.insert(0, words[i--] + " ");
            }
            stringBuilder.insert(0, "\n");
        }
        
        return stringBuilder.toString();
    }

    public static void main(String args[]){
        int width = 12;
//        String words[] = {"Tushar","likes","to","write","code","at", "free", "time"};
        String words[] = {"I", "do", "word", "am", "break", "and", "programing", "I", "he"};
        TextJustification textJustification = new TextJustification();
        String result = textJustification.justify(words, width);
        for (int i = 1; i <= width; i++) System.out.print(i % 10);
        System.out.println(result);
    }
}
