package sort.simplify;

class ShellSort {
	
	public static void sort(int[] items) {
		// 3 steps: h, traversal h (2), insertion sort (3)
		int h = 1; while (h < items.length / 3) h = h * 3 + 1;
		while (h >= 1) {
			for (int i = 0; i < items.length; i++) {
				for (int j = i; j >= h && items[j] < items[j - h]; j -= h)
					SortUtil.swap(items, j, j - h);
			}
			h /= 3;
		}
	}

}
