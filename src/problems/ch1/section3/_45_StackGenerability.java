package problems.ch1.section3;

import collections.Stack;
import collections.impl.stack.LinkedStack;

public class _45_StackGenerability {
    
    static boolean isValidStackOperation(String[] operations) {
        int n = 0;
        for (String op: operations) {
            if (op.equals("-")) {
                if (--n < 0) return false;
            }
            else n++;
        }
        return true;
    }
    
    static boolean isValidPermutation(int[] permutation) {
        int n = 0;
        Stack<Integer> stack = new LinkedStack<>();
        for (int k: permutation) {
            if (stack.size() > 0 && stack.peek() == k) stack.pop();
            else if (stack.size() > 0 && stack.peek() > k) return false;
            else {
                for ( ; n <= k; n++) stack.push(n);
                if (stack.size() > 0) stack.pop();
                else return false;
            }
        }
        return true;//stack.size() == 0;
    }
    
    public static void main(String[] args) {
        System.out.println(isValidStackOperation(new String[] {"a", "-", "b", "-"}));
        System.out.println(isValidStackOperation(new String[] {"-", "a", "b", "c"}));
        System.out.println(isValidStackOperation(new String[] {"a", "-", "a", "-", "-"}));
        
        System.out.println();
        System.out.println(isValidPermutation(new int[] {5, 4, 3, 2, 1}));
        System.out.println(isValidPermutation(new int[] {4, 3, 5, 2, 1}));
        System.out.println(isValidPermutation(new int[] {4, 3, 5, 1, 2}));
        System.out.println(isValidPermutation(new int[] {4, 3, 5, 2}));
    }

}
