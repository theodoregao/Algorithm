package collections.impl.general;

public class BinaryIndexedTree {
    
    private int[] items;
    private int n;
    
    public BinaryIndexedTree(int n) {
        this.n = n;
        items = new int[n + 1];
    }
    
    private int lowBit(int pos) {
        return pos & -pos;
    }
    
    public void update(int pos, int value) {
        while (pos <= n) {
            items[pos] += value;
            pos += lowBit(pos);
        }
    }
    
    public void update(int s, int e, int value) {
        update(s, value);
        update(e + 1, -value);
    }
    
    public int get(int pos) {
        int sum = 0;
        while (pos > 0) {
            sum += items[pos];
            pos -= lowBit(pos);
        }
        return sum;
    }
    
    public static void main(String[] args) {
        pointUpdateIntervalQuery();
        intervalUpdatePointQuery();
        
        int[] values = new int[] {1, 3, 4, 5, 2};
        System.out.println(getInverseNumber(values));
    }
    
    private static void pointUpdateIntervalQuery() {
        BinaryIndexedTree binaryIndexedTree = new BinaryIndexedTree(100);
        for (int i = 1; i <= 100; i++) binaryIndexedTree.update(i, i);
        for (int i = 1; i <= 100; i++) System.out.println(binaryIndexedTree.get(i));
    }
    
    private static void intervalUpdatePointQuery() {
        BinaryIndexedTree binaryIndexedTree = new BinaryIndexedTree(5);
        binaryIndexedTree = new BinaryIndexedTree(100);
        binaryIndexedTree.update(1, 3, 1);
        binaryIndexedTree.update(2, 5, 1);
        binaryIndexedTree.update(3, 4, 1);
        for (int i = 1; i <= 5; i++) System.out.println(binaryIndexedTree.get(i));
    }
    
    private static int getInverseNumber(int[] values) {
        int count = 0;
        BinaryIndexedTree bit = new BinaryIndexedTree(values.length);
        for (int i = 1; i <= values.length; i++) {
            bit.update(values[i - 1], 1);
            count += i - bit.get(values[i - 1]);
        }
        return count;
    }

}
