//Class to determin if string has an equal number of opening and closing brackets
public class BracketMatching {
    public static boolean isBalanced(String string){

        //create new stack object from LinkedListStack.java file
        LinkedListStack<Character> stack = new LinkedListStack<>();

        //for loop to iterate through string
        for(char ch : string.toCharArray()){

            //add character to stack if opening bracket
            if(ch == '(' || ch == '{' || ch == '['){
                stack.push(ch);

            //check it closing bracket
            } else if(ch == ')' || ch == '}' || ch == ']'){
                if(stack.isEmpty()){
                    return false; // Unmatched closing bracket
                }

                //match closing bracket with appropriate closing bracket
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
        public static void main(String[] args) {
            String emptyString = "";
            String balancedChars = "[{(((())))}]";
            String unbalancedChars = "({({})";

            System.out.println(BracketMatching.isBalanced(emptyString));
            System.out.println(BracketMatching.isBalanced(balancedChars));
            System.out.println(BracketMatching.isBalanced(unbalancedChars));
        }
}
