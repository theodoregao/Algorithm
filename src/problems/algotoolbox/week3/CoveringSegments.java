package problems.algotoolbox.week3;
import java.util.*;

public class CoveringSegments {

    private static int[] optimalPoints(Segment[] segments) {
        List<Integer> points = new ArrayList<>();
        
        List<Segment> segs = new ArrayList<>(Arrays.asList(segments));
        
        while (!segs.isEmpty()) {
        	int minEnd = Integer.MAX_VALUE;
        	for (Segment seg: segs) {
        		if (seg.end < minEnd) minEnd = seg.end;
        	}
        	points.add(minEnd);
        	for (int i = segs.size() - 1; i >= 0; i--)
        		if (segs.get(i).start <= minEnd)
        			segs.remove(segs.get(i));
        }
        
//        System.out.println(points);
        
        return toArray(points);
    }
    
    private static int[] toArray(List<Integer> points) {
    	int[] arr = new int[points.size()];
    	for (int i = 0; i < points.size(); i++) arr[i] = points.get(i);
    	return arr;
    }

    private static class Segment {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    	
//    	System.out.println(optimalPoints(new Segment[] {
//    			new Segment(1, 3),
//    			new Segment(2, 5),
//    			new Segment(3, 6)
//    	}));
//    	
//    	System.out.println(optimalPoints(new Segment[] {
//    			new Segment(4, 7),
//    			new Segment(1, 3),
//    			new Segment(2, 5),
//    			new Segment(5, 6)
//    	}));
    }
}
 
