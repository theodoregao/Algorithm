package problems.ch2.section5;

import java.util.Comparator;

import collections.impl.StdRandom;

public class _24_StablePriorityQueue {
    
    static class HeapSort<Item> {
        
        private Comparator<Item> comparator;
        
        public HeapSort(Comparator<Item> comparator) {
            this.comparator = comparator;
        }
        
        public void sort(Item[] items) {
            int[] indexes = new int[items.length];
            for (int i = 0; i < items.length; i++) indexes[i] = i;
            sort(items, indexes);
        }
        
        private void sort(Item[] items, int[] indexes) {
            int n = items.length;
            for (int k = n / 2; k >= 1; k--) sink(items, indexes, k, n);
            while (n > 1) {
                swap(items, indexes, 1, n--);
                sink(items, indexes, 1, n);
            }
        }
        
        private void swim(Item[] itemWrappers, int[] indexes, int k) {
            while (k > 1 && less(itemWrappers, indexes, k / 2, k)) {
                swap(itemWrappers, indexes, k / 2, k);
                k /= 2;
            }
        }
        
        private void sink(Item[] items, int[] indexes, int k, int n) {
            while (2 * k <= n) {
                int j = 2 * k;
                if (j < n && less(items, indexes, j, j + 1)) j++;
                if (!less(items, indexes, k, j)) break;
                swap(items, indexes, k, j);
                k = j;
            }
        }
        
        private boolean less(Item[] items, int indexes[], int i, int j) {
            int cmp = comparator.compare(items[i - 1], items[j - 1]);
            return (cmp != 0 ? cmp : (indexes[i - 1] - indexes[j - 1])) < 0;
        }
        
        private void swap(Item[] items, int[] indexes, int i, int j) {
            Item temp = items[i - 1];
            items[i - 1] = items[j - 1];
            items[j - 1] = temp;
            int index = indexes[i - 1];
            indexes[i - 1] = indexes[j - 1];
            indexes[j - 1] = index;
        }
        
    }
    
    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort(new Comparator<IntegerWrapper>() {

            @Override
            public int compare(IntegerWrapper o1, IntegerWrapper o2) {
                return o1.value - o2.value;
            }
        });
        
        int n = 100;
        IntegerWrapper[] integerWrappers = new IntegerWrapper[n];
        for (int i = 0; i < n; i++) integerWrappers[i] = new IntegerWrapper(StdRandom.uniform(n / 5), i);
        heapSort.sort(integerWrappers);
        for (int i = 0; i < n; i++) System.out.println(integerWrappers[i]);
    }
    
    private static class IntegerWrapper {
        int value;
        int id;
        
        public IntegerWrapper(int value, int id) {
            this.value = value;
            this.id = id;
        }
        
        @Override
        public String toString() {
            return IntegerWrapper.class.getSimpleName() + "{ " + value + ", " + id + " }";
        }
    }

}
