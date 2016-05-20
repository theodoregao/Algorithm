package problems.ch1.section3;

import collections.ResizingArrayStack;
import collections.Stack;

public class _44_TextEditorBuffer {
    
    static class TextEditorBuffer {
        
        private Stack<Character> stackLeft = new ResizingArrayStack<>();
        private Stack<Character> stackRight = new ResizingArrayStack<>();
        private int size = 0;
        
        void insert(char c) {
            stackLeft.push(c);
        }
        
        char delete() {
            return stackLeft.pop();
        }
        
        void left(int k) {
            while (!stackLeft.isEmpty() && k-- > 0) stackRight.push(stackLeft.pop());
        }
        
        void right(int k) {
            while (!stackRight.isEmpty() && k-- > 0) stackLeft.push(stackRight.pop());
        }
        
        int size() {
            return size;
        }
        
        @Override
        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            for (Character c: stackLeft) stringBuffer.append(c);
            stringBuffer.reverse();
            for (Character c: stackRight) stringBuffer.append(c);
            return stringBuffer.toString();
        }
        
    }
    
    public static void main(String[] args) {
        TextEditorBuffer textEditorBuffer = new TextEditorBuffer();
        for (char ch = 'a'; ch <= 'z'; ch++) textEditorBuffer.insert(ch);
        System.out.println(textEditorBuffer);

        textEditorBuffer.left(25);
        textEditorBuffer.delete();
        textEditorBuffer.right(2);
        textEditorBuffer.delete();
        System.out.println(textEditorBuffer);
    }

}
