package com.algorithm.princeton.week01;

public class QuickFind {
	
	private int[] ids;
	
	public QuickFind(int size) {
		ids = new int[size];
		
		for (int i=0; i<ids.length; i++) {
			ids[i] = i;
		}
	}
	
	public void union(int p, int q) {
		int idp = ids[p];
		int idq = ids[q];
		
		for (int i=0; i<ids.length; i++) {
			if (ids[i] == idp) {
				ids[i] = idq;
			}
		}
	}
	
	public boolean isConnected(int p, int q) {
		return ids[p] == ids[q];
	}
	
	public void print() {
		for (int i=0; i<ids.length; i++) {
			System.out.print(ids[i] + " ");
		}
	}

	public static void main(String[] args) {
		QuickFind quickFind = new QuickFind(10);
		quickFind.union(0, 9);
		quickFind.union(3, 4);
		quickFind.union(2, 9);
		quickFind.union(1, 6);
		quickFind.union(0, 6);
		quickFind.union(6, 5);
		
		quickFind.print();
	}

}
