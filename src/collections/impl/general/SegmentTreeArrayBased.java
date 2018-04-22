package collections.impl.general;

public class SegmentTreeArrayBased {
    
    private int[] items;
    private int size;
    
    public SegmentTreeArrayBased(int[] values) {
        size = values.length;
        items = new int[size * 4 + 1];
        for (int i = 0; i < size; i++) items[i] = Integer.MIN_VALUE;
        build(values, 0, size - 1, 1);
    }
    
    private void build(int[] values, int lo, int hi, int curPos) {
        if (lo == hi) {
//            System.out.println(curPos);
            items[curPos] = values[lo];
            return;
        }
        int mid = lo + (hi - lo) / 2;
        build(values, lo, mid, 2 * curPos);
        build(values, mid + 1, hi, 2 * curPos + 1);
        items[curPos] = Math.max(items[2 * curPos], items[2 * curPos + 1]);
    }
    
    // position range (1, size)
    public int getMax(int lo, int hi) {
        return getMax(1, size, lo, hi, 1);
    }
    
    private int getMax(int lo, int hi, int qlo, int qhi, int curPos) {
        if (lo >= qlo && hi <= qhi) return items[curPos];
        else if (lo > qhi || hi < qlo) {
            return Integer.MIN_VALUE;
        }
        else {
            int mid = lo + (hi - lo) / 2;
            return Math.max(getMax(lo, mid, qlo, qhi, curPos * 2),
                    getMax(mid + 1, hi, qlo, qhi, curPos * 2 + 1));
        }
    }
    
    // position range (1, size)
    public void update(int pos, int value) {
        update (1, size, 1, pos, value);
    }
    
    private void update(int lo, int hi, int curPos, int pos, int value) {
        if (lo == hi) {
            items[curPos] = value;
            return;
        }
        int mid = lo + (hi - lo) / 2;
        if (pos <= mid) update(lo, mid, 2 * curPos, pos, value);
        else update(mid + 1, hi, 2 * curPos + 1, pos, value);
        items[curPos] = Math.max(items[2 * curPos], items[2 * curPos + 1]);
    }
    
    public static void main(String[] args) {
                                                                       //1 2 3 4 5 6 7 8
        SegmentTreeArrayBased st = new SegmentTreeArrayBased(new int[] {-1,3,5,7,4,0,2,5});
        System.out.println(st.getMax(6, 6));
        System.out.println(st.getMax(4, 6));
        st.update(5, 10);
        System.out.println(st.getMax(1, 7));
        System.out.println(st.getMax(6, 7));
    }
}
