package problems.ch2.section4;

import collections.impl.StdRandom;

public class _27_FindTheMinimum {
    
    public static void main(String[] args) {
        MaxHeapWithMinValue heap = new MaxHeapWithMinValue();
        for (int i = 0; i < 5; i++) {
            heap.insert(StdRandom.uniform(100));
        }
        while (!heap.isEmpty()) {
            System.out.println(heap.getMin() + ", " + heap.deleteMax());
        }
        System.out.println(heap.getMin());
    }
    
    static class MaxHeapWithMinValue {
        int[] items = new int[1024];
        int size;
        int min = Integer.MAX_VALUE;
        
        public boolean isEmpty() {
            return size == 0;
        }
        
        public int size() {
            return size;
        }
        
        public int getMin() {
            return min;
        }
        
        public void insert(int item) {
            if (min > item) min = item;
            items[++size] = item;
            swim(size);
        }
        
        public int deleteMax() {
            int item = items[1];
            swap(1, size--);
            sink(1);
            if (size == 0) min = Integer.MAX_VALUE;
            return item;
        }
        
        private void swim(int k) {
            while (k > 1 && items[k / 2] < items[k]) {
                 swap(k, k / 2);
                 k /= 2;
            }
        }
        
        private void sink(int k) {
            while (k * 2 <= size) {
                int j = k * 2;
                if (j < size && items[j] < items[j + 1]) j++;
                if (items[k] > items[j]) break;
                swap(k, j);
                k = j;
            }
        }
        
        private void swap(int i, int j) {
            int item = items[i];
            items[i] = items[j];
            items[j] = item;
        }
    }

}
