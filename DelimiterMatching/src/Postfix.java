import java.util.Stack;

public class Postfix {
    private String str;

    public Postfix(String str) { setStr(str); }

    public void setStr(String str) { this.str = str; }

    public double run() {
        Stack<Double> s = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char curr = str.charAt(i);

            if (Character.isDigit(curr)) s.push((double) (curr - '0'));
            else {
                double val1 = s.pop(), val2 = s.pop();

                switch (curr) {
                    case '+' -> s.push(val2 + val1);
                    case '-' -> s.push(val2 - val1);
                    case '*' -> s.push(val2 * val1);
                    case '/' -> s.push(val2 / val1);
                    default -> throw new IllegalStateException("Unexpected value: " + curr);
                }
            }
        }
        return s.pop();
    }

    @Override
    public String toString() { return this.str + " = " + run(); }

    public static void main(String[] args) {
        Postfix p = new Postfix("27+12++");
        System.out.println(p);

        p.setStr("1234+++");
        System.out.println(p);

        p.setStr("93*8/4+");
        System.out.println(p);

        p.setStr("33+7*92/+");
        System.out.println(p);

        p.setStr("93/2*79*+4-");
        System.out.println(p);

        p.setStr("55+2*4/9+");
        System.out.println(p);
    }
}
