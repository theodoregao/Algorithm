package sort;

public class QuickSortString {
    
    private static int charAt(String s, int d) {
        return d < s.length() ? s.charAt(d) : -1;
    }
    
    public static void sort(String[] strings) {
        SortUtil.shuffle(strings);
        sortInternal(strings, 0, strings.length - 1, 0);
    }
    
    private static void sortInternal(String[] strings, int lo, int hi, int d) {
        if (hi <= lo) return;
        int lt = lo, gt = hi, i = lo + 1;;
        int v = charAt(strings[lo], d);
        while (i <= gt) {
            int ch = charAt(strings[i], d);
            if (ch < v) SortUtil.swap(strings, lt++, i++);
            else if (ch > v) SortUtil.swap(strings, i, gt--);
            else i++;
        }

        sortInternal(strings, lo, lt - 1, d);
        if (v >= 0) sortInternal(strings, lt, gt, d + 1);
        sortInternal(strings, gt + 1, hi, d);
    }
    
    public static void main(String[] args) {
        String[] strings = SortUtil.randomStrings(10000000, -64);
        sort(strings);
        System.out.println(SortUtil.isSorted(strings));
    }
}
