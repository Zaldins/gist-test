import java.util.HashMap;
import java.util.Stack;

public class BalancedBracket {
    static HashMap<Character, Character> bracketPairs = new HashMap<>();

    public static void main(String[] args) {

        // first input
        String expression = "{ [( ) ] }";
        boolean isBalanced = isBalancedBracket(expression);
        System.out.println(isBalanced ? "YES" : "NO");

        // second input
        expression = "{ }";
        isBalanced = isBalancedBracket(expression);
        System.out.println(isBalanced ? "YES" : "NO");

        // third input
        expression = "{ [ ,   ] }";
        isBalanced = isBalancedBracket(expression);
        System.out.println(isBalanced ? "YES" : "NO");
    }

    public static boolean isBalancedBracket(String expression) {
        // This initializes a stack that will be used to keep track of the opening brackets encountered so far
        Stack<Character> stack = new Stack<>();

        // Initializes a map (bracketPairs) that stores mappings of opening and closing brackets
        // The code initializes mappings for '(', '{', and '['.
        bracketPairs.put('(', ')');
        bracketPairs.put('{', '}');
        bracketPairs.put('[', ']');

        // Iterate through each character in the input
        for (int i = 0; i < expression.length(); i++) {

            char c = expression.charAt(i);

            // The conditions used are :
            // - If the current character is an opening bracket, it is pushed onto the stack
            // - If the current character is a closing bracket, the method checks whether the stack is not empty (there's a corresponding opening bracket on the stack) and if the mapping matches
            // - If so, the opening bracket is popped from the stack
            // - If the character is a comma (','), it is ignored
            // - If the character is whitespace, it is ignored
            // - If the character is neither an opening bracket, a closing bracket, a comma, nor whitespace, it is considered an invalid character, and the method returns
            if (bracketPairs.containsKey(c)) {
                stack.push(c);
            } else if (!stack.isEmpty() && bracketPairs.get(stack.peek()) == c) {
                stack.pop();
            } else if (c == ',') {
                continue;
            } else if (Character.isWhitespace(c)) {
                continue;
            } else {
                return false;
            }
        }

        // The method returns true if the stack is empty (meaning all opening brackets had matching closing brackets) and there are no invalid characters
        return stack.isEmpty() && !invalidCharsExist(expression);
    }

    private static boolean invalidCharsExist(String expression) {
        for (char c : expression.toCharArray()) {
            // Check chars for invalid chars :
            // - Checks if the character is not a valid opening bracket
            // - Checks if the character is not a valid closing bracket
            // - Checks if the character is not whitespace
            // - Checks if the character is not a comma
            if (!bracketPairs.containsKey(c) && !bracketPairs.containsValue(c) && !Character.isWhitespace(c) && c != ',') {
                return true;
            }
        }

        // It means there are no invalid characters
        return false;
    }
}
