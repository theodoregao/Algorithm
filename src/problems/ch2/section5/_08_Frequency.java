package problems.ch2.section5;

import java.util.Arrays;
import java.util.Comparator;

import collections.StdRandom;

public class _08_Frequency {
    
    static class Frequency {
        String key;
        int count;
        
        public Frequency(String key) {
            this.key = key;
            count = 1;
        }
        
        @Override
        public String toString() {
            return key + ": " + count;
        }
        
        public static Comparator<Frequency> COMPARATOR_KEY = new Comparator<_08_Frequency.Frequency>() {
            
            @Override
            public int compare(Frequency o1, Frequency o2) {
                return o1.key.compareTo(o2.key);
            }
        };
        
        public static Comparator<Frequency> COMPARATOR_COUNT = new Comparator<_08_Frequency.Frequency>() {
            
            @Override
            public int compare(Frequency o1, Frequency o2) {
                return o2.count - o1.count;
            }
        };
    }
    
    static class SortedFrequencyList {
        Frequency[] frequencies = new Frequency[16];
        int size = 0;
        
        public void insert(String[] keys) {
            for (int i = 0; i < keys.length; i++) insert(keys[i]);
        }
        
        public void insert(String key) {
            Frequency frequency = new Frequency(key);
            int pos = binarySearch(frequency);
            if (pos >= 0) frequencies[pos].count++;
            else {
                pos = -pos - 1;
                if (size == frequencies.length) resize();
                for (int i = size; i > pos; i--) {
                    frequencies[i] = frequencies[i - 1];
                }
                frequencies[pos] = frequency;
                size++;
            }
        }
        
        public Frequency[] getFrequencies() {  
            return Arrays.copyOf(frequencies, size);
        }
        
        private void resize() {
            Frequency[] newFrequencies = new Frequency[frequencies.length * 2];
            for (int i = 0; i < size; i++) newFrequencies[i] = frequencies[i];
        }
        
        private int binarySearch(Frequency frequency) {
            int lo = 0;
            int hi = size - 1;
            int mid = (lo + hi) >>> 1;
            
            while (lo <= hi) {
                int compare = Frequency.COMPARATOR_KEY.compare(frequency, frequencies[mid]);
                if (compare == 0) return mid;
                else if (compare < 0) hi = mid - 1;
                else lo = mid + 1;
                mid = (lo + hi) >>> 1;
            }
            
            if (lo >= 0 && lo < size && Frequency.COMPARATOR_KEY.compare(frequencies[lo], frequency) == 0) return lo;
            else return -lo - 1;
        }
    }
    
    static class QuickSort {
        public static void sort(Frequency[] frequencies) {
            StdRandom.shuffle(frequencies);
            sort(frequencies, 0, frequencies.length - 1);
        }
        
        private static void sort(Frequency[] frequencies, int lo, int hi) {
            if (lo >= hi) return;
            int pos = partition(frequencies, lo, hi);
            sort(frequencies, lo, pos - 1);
            sort(frequencies, pos + 1, hi);
        }
        
        private static int partition(Frequency[] frequencies, int lo, int hi) {
            Frequency frequency = frequencies[lo];
            
            int l = lo, r = hi + 1;
            while (l < r) {
                while (Frequency.COMPARATOR_COUNT.compare(frequencies[++l], frequency) < 0) if (l >= hi) break;
                while (Frequency.COMPARATOR_COUNT.compare(frequency, frequencies[--r]) < 0) ;
                if (l < r) swap(frequencies, l, r);
            }
            swap(frequencies, lo, r);
            return r;
        }
        
        private static void swap(Frequency[] frequencies, int i, int j) {
            Frequency temp = frequencies[i];
            frequencies[i] = frequencies[j];
            frequencies[j] = temp;
        }
        
        private static boolean isSorted(Frequency[] frequencies) {
            for (int i = 1; i < frequencies.length; i++) 
                if (Frequency.COMPARATOR_COUNT.compare(frequencies[i], frequencies[i - 1]) < 0) {
                    System.out.println("check is sorted failed at " + i + ": " + frequencies[i]);
                    return false;
                }
            return true;
        }
    }
    
    public static void main(String[] args) {
        
        String[] keys = new String[] {"gao", "shun", "c++", "java", "python", "object-c", "swift", "javascript", "php", "sql", "android", "ios", "mfc", "gradle", "algorithm", "graph"};
        SortedFrequencyList sortedFrequencyList = new SortedFrequencyList();
        sortedFrequencyList.insert(keys);
        for (int i = 0; i < 1000000; i++) sortedFrequencyList.insert(keys[StdRandom.uniform(keys.length)]);
        Frequency[] frequencies = sortedFrequencyList.getFrequencies();
        QuickSort.sort(frequencies);
        System.out.println(frequencies.length + " items sorted: " + QuickSort.isSorted(frequencies));
        for (int i = 0; i < frequencies.length; i++) System.out.println(frequencies[i]);
        
    }

}
