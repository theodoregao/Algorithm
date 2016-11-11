package problems.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordBreak {
    
    public List<String> wordBreak(String string, Set<String> words) {
        
        words.add("");
        
        boolean[][] table = new boolean[string.length()][string.length()];
        int[][] pos = new int[string.length()][string.length()];
        
        for (int len = 1; len <= string.length(); len++) {
            for (int i = 0, j = i + len; i <= string.length() - len; i++, j++) {
//                System.out.println(i + ", " + (j - 1) + ": " + string.substring(i, j));
                table[i][j - 1] = words.contains(string.substring(i, j));
                pos[i][j - 1] = 0;
                for (int k = i + 1; !table[i][j - 1] && k < j; k++) {
//                    System.out.println(string.substring(i, k) + " " + string.substring(k, j));
                    table[i][j - 1] = table[i][k - 1] && words.contains(string.substring(k, j));
                    pos[i][j - 1] = k;
                }
//                System.out.println();
            }
        }
        
        for (int i = 0; i < string.length(); i++) {
            for (int j = i; j < string.length(); j++)
                System.out.println((i + j) + ", " + j);
            System.out.println();
        }
        
        System.out.print("  ");
        for (int i = 0; i < table.length; i++) System.out.print(string.charAt(i));
        System.out.println();
        System.out.print("  ");
        for (int i = 0; i < table.length; i++) System.out.print(i % 10);
        System.out.println();
        for (int i = 0; i < table.length; i++) {
            System.out.print("" + string.charAt(i) + (i % 10));
            for (int j = 0; j < table.length; j++) System.out.print(table[i][j] ? "T" : "F");
            System.out.println();
        }
        System.out.println();
        
        List<String> list = new ArrayList<>();
        if (table[0][string.length() - 1]) {
            int index = string.length();
            while (index > 0) {
                list.add(0, string.substring(pos[0][index - 1], index));
                index = pos[0][index - 1];
            }
        }
        return list;
    }
    
    public List<String> wordBreakRecurse(String string, Set<String> words) {
        Map<String, List<String>> wordMap = new HashMap<>();
        for (String word: words) wordMap.put(word, Arrays.asList(new String[] {word}));
        return wordBreakUtil(string, wordMap);
    }
    
    private List<String> wordBreakUtil(String string, Map<String, List<String>> words) {
        if (string.length() == 0) return new ArrayList<>();
        if (words.containsKey(string)) return words.get(string);
        for (int i = 1; i < string.length(); i++) {
            List<String> left = wordBreakUtil(string.substring(0, i), words);
            if (left == null) continue;
            List<String> right = wordBreakUtil(string.substring(i), words);
            if (right == null) continue;
            List<String> rst = new ArrayList<>();
            rst.addAll(left);
            rst.addAll(right);
            return rst;
        }
        return null;
    }
    
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        for (String word: new String[] {"I", "love", "word", "break", "a", "b", "c", "d"}) set.add(word);
        List<String> rst = new WordBreak().wordBreak("abIlovebreakwordcd", set);
        if (rst != null) for (String word: rst) System.out.println(word);
    }

}
