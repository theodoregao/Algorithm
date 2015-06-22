package com.algorithms.selection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SelectSecond {
	
	private static final int SIZE = 1000000;
	
	private static final Data[] DATA = new Data[SIZE];
	
	private int compareCount;
	
	public SelectSecond() {
		init();
	}
	
	private void init() {
		Random random = new Random();
		
		for (int i=0; i<DATA.length; i++) {
			DATA[i] = new Data(i + 1);
		}
		
		for (int i=0; i<DATA.length; i++) {
			swap(DATA, i, random.nextInt(DATA.length));
		}
	}
	
	// ½õ±êÈüËã·¨
	public Data findSecond() {
		System.out.println("data size: " + SIZE);
		List<Data> data = Arrays.asList(DATA);
		while (data.size() > 1) {
			List<Data> nextData = new ArrayList<Data>();
			for (int i=0; i<data.size(); i+=2) {
				if (i+1 < data.size()) {
					if (compare(data, i, i+1) > 0) {
						data.get(i+1).next = data.get(i).next;
						data.get(i).next = data.get(i+1);
						nextData.add(data.get(i));
					}
					else {
						data.get(i).next = data.get(i+1).next;
						data.get(i+1).next = data.get(i);
						nextData.add(data.get(i+1));
					}
				}
				else {
					nextData.add(data.get(i));
				}
			}
			data = nextData;
		}
		Data second = findMax(data.get(0));
		System.out.println("compareCount: " + compareCount);
		return second;
	}
	
	private int compare(List<Data> data, int i, int j) {
		compareCount++;
		return data.get(i).value - data.get(j).value;
	}
	
	private int compare(int x, int y) {
		compareCount++;
		return x - y;
	}
	
	private Data findMax(Data data) {
		Data max = data.next;
		Data next = max.next;
		while (next != null) {
			if (compare(max.value, next.value) < 0) {
				max = next;
			}
			next = next.next;
		}
		return max;
	}
	
	private static void swap(Data[] array, int i, int j) {
		Data t = array[i];
		array[i] = array[j];
		array[j] = t;
	}
	
	class Data {
		public int value;
		public Data next;
		
		public Data(int value) {
			this.value = value;
			next = null;
		}
		
		public void print() {
			System.out.print(value);
			Data nextData = next;
			while (nextData != null) {
				System.out.print(" --> " + nextData.value);
				nextData = nextData.next;
			}
		}
	}
	
	public static void main(String[] args) {
		SelectSecond selectSecond = new SelectSecond();
		selectSecond.findSecond().print();
	}

}
