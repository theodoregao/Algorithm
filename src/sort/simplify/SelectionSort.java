package sort.simplify;

class SelectionSort {
	
	public static void sort(int[] items) {
		// 4 steps: traversal, min, find min, swap
		for (int i = 0; i < items.length; i++) {
			int min = i;
			for (int j = i + 1; j < items.length; j++) if (items[min] > items[j]) min = j;
			SortUtil.swap(items, i, min);
		}
	}

}
