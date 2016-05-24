package utils;

import edu.princeton.cs.algs4.StdDraw;

public class Arrays {

    public static void visualize(int[] a) {
        visualize(a, null);
    }

    public static void visualize(int[] a, int hightlight) {
        visualize(a, new int[] {hightlight});
    }
    
    public static void visualize(int[] a, int[] highlights) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < min) min = a[i];
            if (a[i] > max) max = a[i];
        }
        double deltaX = 1.0 / (a.length - 1);
        double deltaY = 1.0 / (max - min + 1);

        StdDraw.setPenColor();
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.BLUE);
        for (int i = 1; i < a.length; i++) {
            StdDraw.setPenRadius(0.005);
            StdDraw.point((i - 1) * deltaX, (a[i - 1] - min) * deltaY);
            StdDraw.point(i * deltaX, (a[i] - min) * deltaY);
            StdDraw.setPenRadius();
            StdDraw.line((i - 1) * deltaX, (a[i - 1] - min) * deltaY, i * deltaX, (a[i] - min) * deltaY);
        }
        
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(0.01);
        if (highlights != null) for (int i = 0; i < highlights.length; i++) {
            if (highlights[i] >= 0 && highlights[i] < a.length)
                StdDraw.point(highlights[i] * deltaX, (a[highlights[i]] - min) * deltaY);
        }
    }
    
    public static void visualizeWithY(int[] a, int y) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < min) min = a[i];
            if (a[i] > max) max = a[i];
        }
        double deltaX = 1.0 / (a.length - 1);
        double deltaY = 1.0 / (max - min + 1);

        StdDraw.setPenColor();
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.BLUE);
        for (int i = 1; i < a.length; i++) {
            StdDraw.setPenRadius(0.005);
            StdDraw.point((i - 1) * deltaX, (a[i - 1] - min) * deltaY);
            StdDraw.point(i * deltaX, (a[i] - min) * deltaY);
            StdDraw.setPenRadius();
            StdDraw.line((i - 1) * deltaX, (a[i - 1] - min) * deltaY, i * deltaX, (a[i] - min) * deltaY);
        }
        
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius();
        StdDraw.line(0, (y - min) * deltaY, 1, (y - min) * deltaY);
    }
    
}
