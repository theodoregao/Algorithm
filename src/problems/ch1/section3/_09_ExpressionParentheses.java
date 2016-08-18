package problems.ch1.section3;

import collections.Stack;
import collections.impl.stack.LinkedStack;

/**
 * 1.3.9 Write a program that takes from standard input an expression without left parentheses and prints the equivalent infix expression with the parentheses inserted. For
example, given the input:
1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )
your program should print
( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) )
 * @author Theodore
 *
 */
public class _09_ExpressionParentheses {
    
    public static void main(String[] args) {
        System.out.println(getExpressionParentheses("1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )"));
        System.out.println(getExpressionParentheses("1 + 1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )"));
        System.out.println(getExpressionParentheses("1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) ) + 1"));
        System.out.println(getExpressionParentheses("1 + 2 ) * 3 - 4 ) * 5 - 6 + 1) ) )"));
    }
    
    static String getExpressionParentheses(String expression) {
        Stack<String> operands = new LinkedStack<String>();
        Stack<String> operators = new LinkedStack<String>();
        
        String[] tokens = expression.split(" ");
        
        for (String token: tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("*")) operators.push(token);
            else if (token.equals(")")) {
                String right = operands.pop();
                String left = operands.pop();
                operands.push("( " + left + " " + operators.pop() + " " + right + " )");
            }
            else operands.push(token);
        }
        
        while (!operators.isEmpty()) {
            String right = operands.pop();
            String left = operands.pop();
            operands.push(left + " " + operators.pop() + " " + right);
        }
        
        return operands.pop();
    }

}
