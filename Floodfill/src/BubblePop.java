import java.io.File;
import java.util.*;
import java.util.stream.IntStream;

public class BubblePop {
    private final char[][] arr;
    private final char bubble;
    private int counter;

    public BubblePop(char[][] arr, char bubble) {
        this.arr = arr;
        this.bubble = bubble;
        this.counter = 0;
    }

    private void dfs(int r, int c) {
        if (arr[r][c] == bubble) {
            arr[r][c] = 'X'; // popped
            counter++;

            if (r >= 1) dfs(r - 1, c);
            if (r < arr.length - 1) dfs(r + 1, c);
            if (c >= 1) dfs(r, c - 1);
            if (c < arr[0].length - 1) dfs(r, c + 1);
        }
    }

    public int getCounter() { return counter; }

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("bubble.txt"));
        char[][] arr = IntStream.range(0, 10)
                                .mapToObj(i -> in.nextLine().toCharArray())
                                .toArray(char[][]::new);

        int cases = in.nextInt();
        while (cases-- > 0) {
            in.nextLine();
            int x = in.nextInt(), y = in.nextInt();

            char bubble = arr[x][y];
            BubblePop b = new BubblePop(arr, bubble);
            b.dfs(x, y);

            int popped = b.getCounter();
            System.out.println(popped < 3 ? "NO" : "YES " + popped);
        }
    }
}
