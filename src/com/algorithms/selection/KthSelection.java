package com.algorithms.selection;

import java.util.Random;

public class KthSelection {
	
	private static final int SIZE = 1000000;
	private static final int GROUP_SIZE = 5;
	
	private int compareCount;
	
	public int select(int k) {
		int[] data = initData();
		int result = select(data, k);
		System.out.println("compareCount: " + compareCount);
		return result;
	}
	
	private int select(int[] inputData, int k) {
		int[] data = new int[inputData.length];
		for (int i=0; i<inputData.length; i++) {
			data[i] = inputData[i];
		}
		if (data.length < 10) {
			int[] tempData = new int[data.length];
			for (int i=0; i<data.length; i++) {
				tempData[i] = data[i];
			}
			bubbleSort(tempData, 0, data.length-1);
			return tempData[k];
		}
		
		int groupCount = data.length / GROUP_SIZE;
		int[] middles = new int[groupCount];
		
		int[] smallList = new int[data.length];
		int[] largeList = new int[data.length];
		int smallCount = 0;
		int largeCount = 0;
		
		for (int i=0; i<groupCount; i++) {
			bubbleSort(data, GROUP_SIZE * i, GROUP_SIZE * (i + 1) - 1);
			middles[i] = data[i * GROUP_SIZE + GROUP_SIZE / 2];
		}
		
		int middle = select(middles, middles.length / 2);
		
		for (int i=0; i<groupCount; i++) {
			int compareResult = compare(middles[i], middle);
			if (compareResult == 0) {
				for (int j=0; j<GROUP_SIZE/2; j++) {
					int small = data[i * GROUP_SIZE + j];
					smallList[smallCount++] = small;
					if ((i+1) * GROUP_SIZE - j - 1 < data.length) {
						int large = data[(i+1) * GROUP_SIZE - j -1];
						largeList[largeCount++] = large;
					}
				}
			}
			else if (compareResult > 0) {
				largeList[largeCount++] = middles[i];
				for (int j=0; j<GROUP_SIZE/2; j++) {
					int small = data[i * GROUP_SIZE + j];
					if (compare(small, middle) > 0) {
						largeList[largeCount++] = small;
					}
					else {
						smallList[smallCount++] = small;
					}
					
					if ((i+1) * GROUP_SIZE - j - 1 < data.length) {
						int large = data[(i+1) * GROUP_SIZE - j -1];
						largeList[largeCount++] = large;
					}
				}
			}
			else if (compareResult < 0) {
				smallList[smallCount++] = middles[i];
				for (int j=0; j<GROUP_SIZE/2; j++) {
					int small = data[i * GROUP_SIZE + j];
					smallList[smallCount++] = small;
					
					if ((i+1) * GROUP_SIZE - j - 1 < data.length) {
						int large = data[(i+1) * GROUP_SIZE - j -1];
						if (compare(large, middle) > 0) {
							largeList[largeCount++] = large;
						}
						else {
							smallList[smallCount++] = large;
						}
					}
				}
			}
		}
		
		for (int i=groupCount * GROUP_SIZE; i<data.length; i++) {
			if (compare(data[i], middle) > 0) {
				largeList[largeCount++] = data[i];
			}
			else {
				smallList[smallCount++] = data[i];
			}
		}
		
		if (smallCount == k) {
			return middle;
		}
		else if (smallCount > k) {
			int[] nextData = new int[smallCount];
			for (int i=0; i<smallCount; i++) {
				nextData[i] = smallList[i];
			}
			return select(nextData, k);
		}
		else {
			int[] nextData = new int[largeCount];
			for (int i=0; i<largeCount; i++) {
				nextData[i] = largeList[i];
			}
			return select(nextData, k - 1 - smallCount);
		}
	}
	
	private int[] initData() {
		int[] data = new int[SIZE];
		Random random = new Random();
		
		for (int i=0; i<data.length; i++) {
			data[i] = i;
		}
		
		for (int i=0; i<data.length; i++) {
			swap(data, i, random.nextInt(data.length));
		}
		return data;
	}
	
	private void swap(int[] data, int i, int j) {
		int t = data[i];
		data[i] = data[j];
		data[j] = t;
	}
	
	private int compare(int[] data, int i, int j) {
		compareCount++;
		return data[i] - data[j];
	}
	
	private int compare(int x, int y) {
		compareCount++;
		return x - y;
	}
	
	public boolean bubbleSort(int[] data, int start, int end) {
		for (int i=0; i<end-start; i++) {
			for (int j=start; j<end-i; j++) {
				if (compare(data, j, j+1) > 0) {
					swap(data, j, j+1);
				}
			}
		}
		for (int i=start; i<end-1; i++) {
			if (data[i] > data[i+1]) {
				System.out.println("------------------------------sort error");
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Random random = new Random();
		int value = random.nextInt(SIZE);
		System.out.println(value);
		System.out.println(new KthSelection().select(value));
	}

}
