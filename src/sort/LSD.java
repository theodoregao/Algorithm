package sort;

public class LSD {
    
    public static void sort(String[] strings, int w) {
        int N = strings.length;
        int R = 256;
        String[] aux = new String[N];
        
        for (int d = w - 1; d >= 0; d--) {
            int[] count = new int[R + 1];
            for (int i = 0; i < N; i++) count[strings[i].charAt(d) + 1]++;
            for (int r = 0; r < R; r++) count[r + 1] += count[r];
            for (int i = 0; i < N; i++) aux[count[strings[i].charAt(d)]++] = strings[i];
            for (int i = 0; i < N; i++) strings[i] = aux[i];
        }
    }
    
    public static void main(String[] args) {
        int w = 64;
        String[] strings = SortUtil.randomStrings(1000000, w);
        sort(strings, w);
        System.out.println(SortUtil.isSorted(strings));
    }

}
