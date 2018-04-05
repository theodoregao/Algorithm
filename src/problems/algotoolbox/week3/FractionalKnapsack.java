package problems.algotoolbox.week3;
import java.util.Scanner;

public class FractionalKnapsack {
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;
        
        while (capacity > 0) {
        	int index = -1;
        	for (int i = 0; i < values.length; i++) {
        		if (weights[i] > 0 && (index == -1 || 1.0 * values[index] / weights[index] < 1.0 * values[i] / weights[i])) index = i;
        	}
        	if (index == -1) break;
        	if (capacity >= weights[index]) value += values[index];
        	else value += 1.0 * values[index] / weights[index] * capacity;
        	capacity -= weights[index];
        	weights[index] = 0;
        }
        
        return value;
    }
    
    private static String toString(double value) {
		return String.format("%.3f", value);
	}

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));

//    	System.out.println(toString(getOptimalValue(50, new int[] {60, 100, 120}, new int[] {20, 50, 30})));
//    	System.out.println(toString(getOptimalValue(10, new int[] {500}, new int[] {30})));
    }
} 
