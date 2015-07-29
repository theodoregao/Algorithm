package com.algorithm.princeton.week01;

public class WeightedQuickUnion {
	
	private int[] ids;
	private int[] sizes;
	
	public WeightedQuickUnion(int size) {
		ids = new int[size];
		sizes = new int[size];
		
		for (int i=0; i<size; i++) {
			ids[i] = i;
			sizes[i] = 1;
		}
	}
	
	public int root(int p) {
		int root = p;
		while (ids[root] != root) {
			root = ids[root];
		}
		return root;
	}
	
	public void union(int p, int q) {
		int rootp = root(p);
		int rootq = root(q);
		
		if (isConnected(p, q)) {
			return;
		}
		
		if (sizes[rootp] < sizes[rootq]) {
			ids[rootp] = rootq;
			sizes[rootq] += sizes[rootp];
		}
		else {
			ids[rootq] = rootp;
			sizes[rootp] += sizes[rootq];
		}
	}
	
	public boolean isConnected(int p, int q) {
		return root(p) == root(q);
	}
	
	public void print() {
		for (int i=0; i<ids.length; i++) {
			System.out.print(ids[i] + " ");
		}
	}

	public static void main(String[] args) {
		WeightedQuickUnion weightedQuickUnion = new WeightedQuickUnion(10);
//		weightedQuickUnion.union(4, 3);
//		weightedQuickUnion.union(3, 8);
//		weightedQuickUnion.union(6, 5);
//		weightedQuickUnion.union(9, 4);
//		weightedQuickUnion.union(2, 1);
//		weightedQuickUnion.union(8, 9);
//		weightedQuickUnion.union(5, 0);
//		weightedQuickUnion.union(7, 2);
//		weightedQuickUnion.union(6, 1);
//		weightedQuickUnion.union(1, 0);
//		weightedQuickUnion.union(6, 7);
		weightedQuickUnion.union(2, 7);
		weightedQuickUnion.union(0, 5);
		weightedQuickUnion.union(0, 4);
		weightedQuickUnion.union(9, 5);
		weightedQuickUnion.union(8, 9);
		weightedQuickUnion.union(3, 6);
		weightedQuickUnion.union(7, 3);
		weightedQuickUnion.union(4, 3);
		weightedQuickUnion.union(4, 1);
		
		weightedQuickUnion.print();
	}

}
