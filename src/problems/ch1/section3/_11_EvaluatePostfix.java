package problems.ch1.section3;

import collections.LinkedStack;
import collections.Stack;

/**
 * 1.3.11 Write a program EvaluatePostfix that takes a postfix expression from standard input, evaluates it, and prints the value. (Piping the output of your program from
the previous exercise to this program gives equivalent behavior to Evaluate.
 * @author Theodore
 *
 */
public class _11_EvaluatePostfix {
    
    public static void main(String[] args) {
        System.out.println(new Expression("( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ))").evaluate());
        System.out.println(new Expression("1 + 2*3 - 4 * ( 5 - 6    )").evaluate());
    }
    
    static class Expression {
        String infixExpression;
        
        Expression(String infixExpression) {
            this.infixExpression = infixExpression;
        }
        
        int evaluate() {
            Stack<Integer> operands = new LinkedStack<Integer>();
            
            String postfixExpression = toPostfixExpression();
            String[] tokens = postfixExpression.split(" ");
            
            for (String token: tokens) {
//                System.out.println(token);
                char operator = token.charAt(0);
                if (Character.isDigit(operator)) {
                    operands.push(Integer.parseInt(token));
                }
                else {
                    operands.push(performOperation(operands.pop(), operands.pop(), operator));
                }
            }
            return operands.pop();
        }
        
        private int performOperation(int right, int left, char operator) {
            switch (operator) {
            case '+':
                return left + right;
            case '-':
                return left - right;
            case '*':
                return left * right;
            case '/':
                return left / right;
            default:
                return 0;
            }
        }
        
        String toPostfixExpression() {
            Stack<String> operands = new LinkedStack<String>();
            Stack<Character> operators = new LinkedStack<Character>();
            
            String expression = infixExpression;
            expression = expression.replace("+", " + ");
            expression = expression.replace("-", " - ");
            expression = expression.replace("*", " * ");
            expression = expression.replace("/", " / ");
            expression = expression.replace("(", " ( ");
            expression = expression.replace(")", " ) ");
            
            String[] tokens = expression.split(" ");
            for (String token: tokens) {
                token = token.trim();
                if (token.length() == 0) continue;
                else if (Character.isDigit(token.charAt(0))) {
                    operands.push(token);
                    continue;
                }
                
                char operator = token.charAt(0);
                switch (operator) {
                case '+':
                case '-':
                    while (!operators.isEmpty() &&
                            (operators.peek() == '+'
                            || operators.peek() == '-'
                            || operators.peek() == '*'
                            || operators.peek() == '/')) performOperation(operands, operators);
                    operators.push(operator);
                    break;
                    
                case '*':
                case '/':
                    while (!operators.isEmpty() &&
                            (operators.peek() == '*' || operators.peek() == '/')) performOperation(operands, operators);
                    operators.push(operator);
                    break;
                    
                case '(':
                    operators.push('(');
                    break;
                    
                case ')':
                    while (operators.peek() != '(') performOperation(operands, operators);
                    operators.pop();
                    break;
                    
                default:
                    break;
                }
            }
            
            while (!operators.isEmpty()) performOperation(operands, operators);
            
            return operands.pop();
        }
        
        private void performOperation(Stack<String> operands, Stack<Character> operators) {
            String right = operands.pop();
            String left = operands.pop();
            char operator = operators.pop();
            operands.push(left + " " + right + " " + operator);
        }
        
    }

}
