public class BracketMatching {
    public static boolean isBalanced(String string){
        LinkedListStack<Character> stack = new LinkedListStack<>();
        for(char ch : string.toCharArray()){
            if(ch == '(' || ch == '{' || ch == '['){
                stack.push(ch);
            } else if(ch == ')' || ch == '}' || ch == ']'){
                if(stack.isEmpty()){
                    return false; // Unmatched closing bracket
                }
                char top = stack.pop();
                if((ch == ')' && top != '(') ||
                   (ch == '}' && top != '{') ||
                   (ch == ']' && top != '[')){
                    return false; // Mismatched brackets
                }
            }
        }
        return stack.isEmpty(); // True if all opening brackets are matched
    }
}
