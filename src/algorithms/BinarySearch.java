package algorithms;

public class BinarySearch {
    
    public static int search(int[] a, int key) {
        return search(a, key, 0, a.length - 1);
    }
    
    public static int search(int[] a, int key, int lo, int hi) {
        int mid = (lo + hi) / 2;
        
        while (lo <= hi) {
            if (key == a[mid]) return mid;
            else if (key < a[mid]) hi = mid - 1;
            else /*if (key > a[mid])*/ lo = mid + 1;
            mid = (lo + hi) / 2;
        }
        return -1;
    }
    
    public static int left(int[] a, int key) {
        return left(a, key, 0, a.length - 1);
    }

    public static int left(int[] a, int key, int lo, int hi) {
        int mid = (lo + hi) / 2;
        
        while (lo <= hi) {
            if (key <= a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            mid = (lo + hi) / 2;
        }
        
        if (lo >= 0 && lo < a.length && a[lo] == key) return lo;
        else return -lo - 1;
    }
    
    public static int right(int[] a, int key) {
        return right(a, key, 0, a.length - 1);
    }
    
    public static int right(int[] a, int key, int lo, int hi) {
        int mid = (lo + hi) / 2;
        
        while (lo <= hi) {
            if (key < a[mid]) hi = mid - 1;
            else if (key >= a[mid]) lo = mid + 1;
            mid = (lo + hi) / 2;
        }
        
        if (hi >= 0 && hi < a.length && a[hi] == key) return hi;
        else return -lo - 1;
    }
    
}
