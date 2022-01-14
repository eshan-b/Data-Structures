import java.util.*;

public class TwoStack {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // example input: (1+((2+3)*(4*5))))
        Queue<String> expression = new LinkedList<>(Arrays.asList(in.nextLine().trim().split("")));

        Stack<String> opStack = new Stack<>();
        Stack<Double> numStack = new Stack<>();

        while (!expression.isEmpty()) {
            String c = expression.poll();

            switch (c) {
                case "(" -> {} // break
                case "+", "-", "*", "/" -> opStack.push(c);
                case ")" -> numStack.push(calc(opStack, numStack));
                default -> numStack.push(Double.parseDouble(c));
            }
        }

        System.out.println(numStack.pop());
    }

    static double calc(Stack<String> ops, Stack<Double> nums) {
        double curr = nums.pop();

        if (!ops.isEmpty()) {
            String op = ops.pop();
            double next = nums.pop();

            switch (op) {
                case "+" -> curr += next;
                case "-" -> curr = next - curr;
                case "*" -> curr *= next;
                case "/" -> curr = nums.pop() / curr;
                default -> {}
            }
        }

        return curr;
    }
}
