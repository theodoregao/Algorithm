package sort.simplify;

class InsertionSort {
	
	public static void sort(int[] items) {
		// 3 steps: traversal, find insertion position, swap
		for (int i = 0; i < items.length; i++) {
			for (int j = i; j > 0 && items[j] < items[j - 1]; j--)
				SortUtil.swap(items, j, j - 1);
		}
	}

}
