import java.util.*;

public class Matching {
    private String str;

    public Matching(String str) { setStr(str); }

    public void setStr(String str) { this.str = str; }

    public String run() {
        Stack<Character> s = new Stack<>();
        ArrayList<Character> open = new ArrayList<>(Arrays.asList('{', '[', '(', '<')),
                             close = new ArrayList<>(Arrays.asList('}', ']', ')', '>'));

        for (int i = 0; i < this.str.length(); i++) {
            char curr = this.str.charAt(i);

            if (open.contains(curr))
                s.push(curr);
            else if (close.contains(curr)) {
                if (!s.isEmpty()) {
                    char temp = s.peek();

                    if (open.contains(temp) && (open.indexOf(temp) == close.indexOf(curr)))
                        s.pop();
                    else return "Matching error";
                } else return "Missing left (opening) delimiter";
            }
        }
        if (!s.isEmpty()) return "Missing right (closing) delimiter";

        return "Correct";
    }

    @Override
    public String toString() { return this.str + " -> " + run(); }

    public static void main(String[] args) {
        Matching m = new Matching("c[d]");
        System.out.println(m);

        m.setStr("a{b[c]d}e");
        System.out.println(m);

        m.setStr("a{b(c]d}e");
        System.out.println(m);

        m.setStr("a[b{c}d]e}");
        System.out.println(m);

        m.setStr("a{b(c)");
        System.out.println(m);
    }
}
