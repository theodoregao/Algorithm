package problems.ch1.section3;

import collections.LinkedStack;
import collections.Stack;

/**
 * 1.3.10 Write a filter InfixToPostfix that converts an arithmetic expression from infix to postfix.
 * @author Theodore
 *
 */
public class _10_InfixToPostfix {
    
    public static void main(String[] args) {
        System.out.println(new Expression("( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ))").toPostfixExpression());
        System.out.println(new Expression("1 + 2*3 - 4 * ( 5 - 6    )").toPostfixExpression());
    }
    
    static class Expression {
        Stack<String> operands = new LinkedStack<String>();
        Stack<Character> operators = new LinkedStack<Character>();
        String infixExpression;
        
        Expression(String infixExpression) {
            this.infixExpression = infixExpression;
        }
        
        String toPostfixExpression() {
            
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
                            || operators.peek() == '/')) performOperation();
                    operators.push(operator);
                    break;
                    
                case '*':
                case '/':
                    while (!operators.isEmpty() &&
                            (operators.peek() == '*' || operators.peek() == '/')) performOperation();
                    operators.push(operator);
                    break;
                    
                case '(':
                    operators.push('(');
                    break;
                    
                case ')':
                    while (operators.peek() != '(') performOperation();
                    operators.pop();
                    break;
                    
                default:
                    break;
                }
            }
            
            while (!operators.isEmpty()) performOperation();
            
            return operands.pop();
        }
        
        private void performOperation() {
            String right = operands.pop();
            String left = operands.pop();
            char operator = operators.pop();
            operands.push(left + " " + right + " " + operator);
        }
        
    }

}
