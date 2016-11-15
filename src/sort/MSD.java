package sort;

public class MSD {
    
    private static final int R = 256;
    private static final int MIN_SORT_SIZE = 15;
    private static String[] aux;
    
    private static int charAt(String s, int d) {
        return d < s.length() ? s.charAt(d) : -1;
    }
    
    public static void sort(String[] strings) {
        aux = new String[strings.length];
        sortInternal(strings, 0, strings.length - 1, 0);
    }
    
    private static void sortInternal(String[] strings, int lo, int hi, int d) {
        if (lo + MIN_SORT_SIZE >= hi) {
            insertionSort(strings, lo, hi);
            return;
        }
        
        int[] count = new int[R + 2];
        for (int i = lo; i <= hi; i++) count[charAt(strings[i], d) + 2]++;
        for (int r = 0; r < R + 1; r++) count[r + 1] += count[r];
        for (int i = lo; i <= hi; i++) aux[count[charAt(strings[i], d) + 1]++] = strings[i];
        for (int i = lo; i <= hi; i++) strings[i] = aux[i - lo];
        
        for (int r = 0; r < R; r++) sortInternal(strings, lo + count[r], lo + count[r + 1] - 1, d + 1);
    }
    
    private static void insertionSort(String[] strings, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j >= 1 && strings[j - 1].compareTo(strings[j]) > 0; j--)
                SortUtil.swap(strings, j - 1, j);
        }
    }
    
    public static void main(String[] args) {
        String[] strings = SortUtil.randomStrings(10000000, -64);
        sort(strings);
        System.out.println(SortUtil.isSorted(strings));
    }

}
