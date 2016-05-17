package problems.ch1;

import edu.princeton.cs.algs4.StdDraw;

/**
 * 1.1.32 Histogram. Suppose that the standard input stream is a sequence of double
values. Write a program that takes an integer N and two double values l and r from the
command line and uses StdDraw to plot a histogram of the count of the numbers in the
standard input stream that fall in each of the N intervals defined by dividing (l , r) into
N equal-sized intervals.
 * @author Theodore
 *
 */
public class _32_Histogram {

    public static void main(String[] args) {
        
        final double l = 0.0;
        final double r = 1.0;
        
        final int n = 10;
        final double interval = (r - l) / n;
        
        final double[] values = new double[] {
                0.437, 0.336, 0.277, 0.082, 0.021, 0.886, 0.364, 0.294, 0.006, 0.664, 
                0.514, 0.124, 0.344, 0.814, 0.263, 0.682, 0.941, 0.248, 0.134, 0.377, 
                0.478, 0.136, 0.235, 0.023, 0.134, 0.349, 0.832, 0.818, 0.203, 0.560, 
                0.141, 0.187, 0.470, 0.255, 0.763, 0.579, 0.945, 0.711, 0.792, 0.025, 
                0.242, 0.901, 0.591, 0.595, 0.418, 0.353, 0.628, 0.974, 0.611, 0.240, 
                0.596, 0.023, 0.149, 0.346, 0.196, 0.551, 0.113, 0.124, 0.635, 0.503, 
                0.524, 0.717, 0.860, 0.458, 0.435, 0.731, 0.015, 0.587, 0.178, 0.499, 
                0.412, 0.243, 0.972, 0.793, 0.571, 0.932, 0.884, 0.234, 0.507, 0.724, 
                0.874, 0.152, 0.510, 0.076, 0.274, 0.272, 0.112, 0.642, 0.599, 0.408, 
                0.812, 0.889, 0.022, 0.278, 0.375, 0.240, 0.191, 0.689, 0.241, 0.025 };
        
        final int[] counts = new int[n];
        
        for (int i = 0; i < values.length; i++) counts[(int) ((values[i] - l) / interval)]++;
        
        int maxHeight = 0;
        for (int i = 0; i < counts.length; i++) if (counts[i] > maxHeight) maxHeight = counts[i];
        
        final double deltaX = 1.0 / n;
        double x = deltaX / 2;
        double y;
        for (int i = 0; i < counts.length; i++) {
            y = 1.0 * counts[i] / maxHeight / 2;
            if (i % 2 == 0) StdDraw.setPenColor(StdDraw.GRAY); else StdDraw.setPenColor();
            StdDraw.filledRectangle(x, y, deltaX / 2, y);
            x += deltaX;
        }
    }
    
}
