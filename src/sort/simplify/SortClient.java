package sort.simplify;

import java.util.Random;

class SortClient {
	
	public static void main(String[] args) {
		int n = 1000000;
		Random random = new Random();
		int[] items = new int[n];
		for (int i = 0; i < n; i++) items[i] = random.nextInt();
//		SelectionSort.sort(items);
//		InsertionSort.sort(items);
//		ShellSort.sort(items);
//		MergeSort.sort(items);
//		MergeSort.bottomUpSort(items);
//		QuickSort.sort(items);
//		QuickSort.threeWaySort(items);
		new HeapSort(items).sort();
		
		System.out.println("is sorted: " + SortUtil.isSorted(items));
	}

}
