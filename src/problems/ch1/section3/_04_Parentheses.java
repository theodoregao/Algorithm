package problems.ch1.section3;

import collections.LinkedStack;
import collections.Stack;

/**
 * 1.3.4 Write a stack client Parentheses that reads in a text stream from standard input
and uses a stack to determine whether its parentheses are properly balanced. For example, your program should print true for [()]{}{[()()]()} and false for [(]).
 * @author Theodore
 *
 */
public class _04_Parentheses {
    
    public static void main(String[] args) {
        System.out.println("[()]{}{[()()]()}: " + valid("[()]{}{[()()]()}"));
        System.out.println("[(]): " + valid("[(])"));
        
    }
    
    static boolean valid(String input) {
        Stack<Character> stack = new LinkedStack<Character>();
        for (Character ch: input.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') stack.push(ch);
            else {
                switch (ch) {
                case ')':
                    if (stack.pop() != '(') return false;
                    break;
                    
                case ']':
                    if (stack.pop() != '[') return false;
                    break;
                    
                case '}':
                    if (stack.pop() != '{') return false;
                    break;
                    
                default:
                    return false;
                }
            }
        }
        return true;
    }

}
