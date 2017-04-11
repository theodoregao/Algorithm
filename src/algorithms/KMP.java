package algorithms;

public class KMP {
    
    private String pattern;
    private int[][] dfa;
    
    public KMP(String pattern) {
        this.pattern = pattern;
        int M = pattern.length();
        int R = 256;
        dfa = new int[R][M];
        // first column
        dfa[pattern.charAt(0)][0] = 1;
        // use x to track previous status
        for (int x = 0, j = 1; j < M; j++) {
            // use previous status x to fill next incorrect cells
            for (int c = 0; c < R; c++) dfa[c][j] = dfa[c][x];
            // fill next correct cell
            dfa[pattern.charAt(j)][j] = j + 1;
            x = dfa[pattern.charAt(j)][x];
        }
    }
    
    public int search(String text) {
        int i, j, N = text.length(), M = pattern.length();
        for (i = 0, j = 0; i < N && j < M; i++)
            j = dfa[text.charAt(i)][j];
        if (j == M) return i - M;
        else return -1;
    }
    
    public static void main(String[] args) {
        String text = "AABRAACADABRAACAADABRA";
        String pattern = "AACAA";
        KMP kmp = new KMP(pattern);
        System.out.println(kmp.search(text));
        System.out.println(text.substring(kmp.search(text), kmp.search(text) + pattern.length()));
    }

}
