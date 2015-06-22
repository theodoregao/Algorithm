package com.algorithms.selection;

import java.util.Random;

public class MaxMinSelection {
	
	private static final int SIZE = 1000000;
	
	private static final int[] DATA = new int[SIZE];
	
	private static int MAX_POSITION;
	private static int MIN_POSITION;
	
	private int compareCount;
	
	public MaxMinSelection() {
		init();
	}
	
	public int findMax(boolean isResetCount, int[] data) {
		int[] array = data;
		if (array == null) {
			array = DATA;
		}
		
		if (isResetCount) {
			resetCompareCount();
		}
		
		int maxPosition = 0;
		
		for (int i=1; i<array.length; i++) {
			if (compare(array[maxPosition], array[i]) < 0) {
				maxPosition = i;
			}
		}
		
		System.out.println("max position found: " + maxPosition + ": " + array[maxPosition]);
		
		if (isResetCount) {
			System.out.println("total compare count: " + compareCount);
		}
		
		return maxPosition;
	}
	
	public int findMin(boolean isResetCount, int[] data) {
		int[] array = data;
		if (array == null) {
			array = DATA;
		}
		
		if (isResetCount) {
			resetCompareCount();
		}
		
		int minPosition = 0;
		
		for (int i=1; i<array.length; i++) {
			if (compare(array[minPosition], array[i]) > 0) {
				minPosition = i;
			}
		}
		
		System.out.println("min position found: " + minPosition + ": " + array[minPosition]);
		
		if (isResetCount) {
			System.out.println("total compare count: " + compareCount);
		}
		
		return minPosition;
	}
	
	public void findMaxMin() {
		resetCompareCount();
		
		findMax(false, null);
		findMin(false, null);
		
		System.out.println("findMaxMin(): total compare count: " + compareCount);
	}
	
	public void findMaxMinWithGroup() {
		resetCompareCount();
		int[] maxArray = new int[DATA.length / 2];
		int[] minArray = new int[DATA.length / 2];
		
		for (int i=0; i<DATA.length; i+=2) {
			if (compare(DATA[i], DATA[i+1]) > 0) {
				maxArray[i/2] = DATA[i];
				minArray[i/2] = DATA[i+1];
			}
			else {
				maxArray[i/2] = DATA[i+1];
				minArray[i/2] = DATA[i];
			}
		}

		findMax(false, maxArray);
		findMin(false, minArray);
		System.out.println("findMaxMinWithGroup(): total compare count: " + compareCount);
	}
	
	public void findMaxMinWithDivideAndConquer() {
		resetCompareCount();
		
		Pair result = doDivideAndConquer(0, DATA.length-1);

		System.out.println("min position found: " + result.x + ": " + DATA[result.x]);
		System.out.println("max position found: " + result.y + ": " + DATA[result.y]);
		
		System.out.println("findMaxMin(): total compare count: " + compareCount);
	}
	
	private Pair doDivideAndConquer(int startPosition, int endPosition) {
		int maxPosition, minPosition;
		
		if (endPosition == startPosition) {
			return new Pair(startPosition, startPosition);
		}
		
		if (endPosition - startPosition == 1) {
			if (compare(DATA[startPosition], DATA[endPosition]) > 0) {
				return new Pair(startPosition, startPosition);
			}
			else {
				return new Pair(endPosition, startPosition);
			}
		}

		Pair left = doDivideAndConquer(startPosition, (endPosition + startPosition) / 2);
		Pair right = doDivideAndConquer((endPosition + startPosition) / 2 + 1, endPosition);
		
		maxPosition = compare(DATA[left.x], DATA[right.x]) > 0 ? left.x : right.x;
		
		minPosition = compare(DATA[left.y], DATA[right.y]) < 0 ? left.y : right.y;
		
		return new Pair(maxPosition, minPosition);
	}
	
	private void resetCompareCount() {
		compareCount = 0;
	}
	
	private int compare(int x, int y) {
		increaseCompareCount();
		return x - y;
	}
	
	private void increaseCompareCount() {
		compareCount++;
	}
	
	private void init() {
		System.out.println("init()");
		Random random = new Random();
		
		for (int i=0; i<DATA.length; i++) {
			DATA[i] = i + 1;
		}
		
		for (int i=0; i<DATA.length; i++) {
			swap(DATA, i, random.nextInt(DATA.length));
		}
		
		for (int i=0; i<DATA.length; i++) {
			if (DATA[i] == 1) {
				MIN_POSITION = i;
				System.out.println("init: min " + i + ": " + DATA[i]);
			}
			if (DATA[i] == DATA.length) {
				MAX_POSITION = i;
				System.out.println("init: max " + i + ": " + DATA[i]);
			}
		}
	}
	
	public void print() {
//		for (int i=0; i<DATA.length; i++) {
//			System.out.println(i + ": " + DATA[i]);
//		}
		System.out.println("MAX_POSITION: " + MAX_POSITION + ": " + DATA[MAX_POSITION]);
		System.out.println("MIN_POSITION: " + MIN_POSITION + ": " + DATA[MIN_POSITION]);
	}
	
	private static void swap(int[] array, int i, int j) {
		int t = array[i];
		array[i] = array[j];
		array[j] = t;
	}
	
	private static class Pair {
		public int x;
		public int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) {
		MaxMinSelection maxMinSelection = new MaxMinSelection();
		maxMinSelection.print();
		maxMinSelection.findMaxMin();
		maxMinSelection.findMaxMinWithGroup();
		maxMinSelection.findMaxMinWithDivideAndConquer();
	}

}
