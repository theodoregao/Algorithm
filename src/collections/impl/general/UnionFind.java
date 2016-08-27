package collections.impl.general;

import java.io.IOException;

import collections.impl.StdRandom;

public class UnionFind {
    
    private int count;
    private int[] ids;
    private int[] sizes;
    
    public UnionFind(int n) {
        count = n;
        ids = new int[n];
        sizes = new int[n];
        for (int i = 0; i < n; i++) { ids[i] = i; sizes[i] = 1; }
    }
    
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) return;
        if (sizes[i] < sizes[j]) { ids[i] = j; sizes[j] += sizes[i]; }
        else { ids[j] = i; sizes[i] += sizes[j]; }
//        if (i < j) ids[i] = j;
//        else ids[j] = i;
        count--;
    }
    
    public int find(int p) {
        if (p == ids[p]) return p;
        else return ids[p] = find(ids[p]);
    }
    
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
    
    public int count() {
        return count;
    }
    
    public static void main(String[] args) throws IOException {
        int n = 1000000;
        int k = 0;
        UnionFind unionFind = new UnionFind(n);
        while (true) {
            k++;
            int p = StdRandom.uniform(n);
            int q = StdRandom.uniform(n);
            unionFind.union(p, q);
//            System.out.println("connect " + p + " " + q + " : " + unionFind.count);
            if (unionFind.count == 1) break;
        }
        System.out.println(unionFind.count() + " components " + k);
//        for (int i = 0; i < 1000; i++) System.out.println(unionFind.find(i));
    }
}
