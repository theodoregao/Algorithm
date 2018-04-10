package problems.ds.week1;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}

class check_brackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);

        while (true) {
        String text = reader.readLine();
        Bracket error = null;
        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        for (int position = 0; error == null && position < text.length(); ++position) {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                opening_brackets_stack.push(new Bracket(next, position));
            }

            if (next == ')' || next == ']' || next == '}') {  
            	if (opening_brackets_stack.isEmpty() || !opening_brackets_stack.peek().Match(next)) {
            		error = new Bracket(next, position);
                }
            	else opening_brackets_stack.pop();
            }
        }

        if (error != null) {
        	System.out.println(error.position + 1);
        }
        else if (!opening_brackets_stack.isEmpty())
        	System.out.println(opening_brackets_stack.pop().position + 1);
        else System.out.println("Success");
        }
    }
}
