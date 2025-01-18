import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.println("abd(jnb)asdf ==> "+reverseSubstrings("abd(jnb)asdf"));
        System.out.println("abdjnbasdf ==> "+reverseSubstrings("abdjnbasdf"));
        System.out.println("dd(df)a(ghhh) ==> "+reverseSubstrings("dd(df)a(ghhh)"));
    }

    public static String reverseSubstrings(String s) {
        if (!isValidString(s)) {
            throw new IllegalArgumentException("Invalid input string.");
        }
        Stack<StringBuilder> stack = new Stack<>();
        stack.push(new StringBuilder());
        for (char c : s.toCharArray()) {
            if (c == ')') {
                StringBuilder temp = stack.pop();
                StringBuilder reversedSubstring = new StringBuilder(temp).reverse();
                reversedSubstring.append(")");
                reversedSubstring.insert(0,"(");
                stack.peek().append(reversedSubstring);
            } else if (c == '(') {
                stack.push(new StringBuilder());
            } else {
                stack.peek().append(c);
            }
        }
        return stack.pop().toString();
    }

    public static boolean isValidString(String s) {
        Pattern validPattern = Pattern.compile("^[a-z()]{1,2000}$");
        Matcher matcher = validPattern.matcher(s);
        if (!matcher.matches()) {
            return false;
        }

        // Check if the parentheses are balanced
        int balance = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                balance++;
            } else if (c == ')') {
                balance--;
                if (balance < 0) {
                    return false;
                }
            }
        }
        return balance == 0;
    }
}