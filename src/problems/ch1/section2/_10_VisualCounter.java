package problems.ch1.section2;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.2.10 Develop a class VisualCounter that allows both increment and decrement
operations. Take two arguments N and max in the constructor, where N specifies the
maximum number of operations and max specifies the maximum absolute value for
the counter. As a side effect, create a plot showing the value of the counter each time its
tally changes.
 * @author Theodore
 *
 */
public class _10_VisualCounter {

    public static void main(String[] args) {
        final int n = 100000;
        final int threshold = 100;
        
        VisualCounter visualCounter = new VisualCounter(n, threshold);
        
        for (int i = 0; i < n; i++) {
            if (StdRandom.bernoulli()) visualCounter.increase();
            else visualCounter.decrease();
        }
    }
    
    static class VisualCounter {
        
        int n;
        int threshold;
        
        double dx, dy;
        
        double x, y;
        
        VisualCounter(int n, int threshold) {
            this.n = n;
            this.threshold = threshold;
            
            dx = 1.0 / n;
            dy = 1.0 / (2 * threshold);
            
            x = 0;
            y = 0.5;
        }
        
        void increase() {
            if (n-- < 0) return;
            if (y + dy > 1.0) {
                drawHorizontal();
                return;
            }
            drawPoint();
            StdDraw.line(x, y, x += dx, y += dy);
        }
        
        void decrease() {
            if (n-- < 0) return;
            if (y - dy < 0) {
                drawHorizontal();
                return;
            }
            drawPoint();
            StdDraw.line(x, y, x += dx, y -= dy);
        }
        
        void drawPoint() {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius(0.001);
            StdDraw.point(x, y);
            StdDraw.setPenColor();
            StdDraw.setPenRadius();
        }
        
        void drawHorizontal() {
            drawPoint();
            StdDraw.line(x, y, x += dx, y);
        }
    }
    
}
