package problems.ch2.section3;

import java.util.Comparator;

import collections.Heap;

public class _30_DynamicMedianFinding {
    
    public static void main(String[] args) {
        MedianFindingHeap medianFindingHeap = new MedianFindingHeap();
        for (int i = 1; i <= 7; i++) medianFindingHeap.insert(i);
        System.out.println(medianFindingHeap.median());
    }
    
    static class MedianFindingHeap {
        private Heap<Integer> maxHeap = new Heap<Integer>(new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
            
        });
        private Heap<Integer> minHeap = new Heap<Integer>(new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
            
        });
        
        public double median() {
            if (maxHeap.size() == minHeap.size()) return (maxHeap.top() + minHeap.top()) / 2.0;
            else if (maxHeap.size() > minHeap.size()) return maxHeap.top();
            else return minHeap.top();
        }
        
        public void insert(int value) {
            if (maxHeap.size() == 0) maxHeap.insert(value);
            else if (value > maxHeap.top()) minHeap.insert(value);
            else maxHeap.insert(value);
            balence();
        }
        
        private void balence() {
            if (maxHeap.size() - minHeap.size() > 1) minHeap.insert(maxHeap.deleteTop());
            else if (minHeap.size() - maxHeap.size() > 1) maxHeap.insert(minHeap.deleteTop());
        }
    }

}
