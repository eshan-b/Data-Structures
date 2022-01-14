import java.io.*;
import java.util.*;

public class FixMisspellings {
    public static void main(String[] args) throws Exception {
        Scanner file = new Scanner(new File("misspellings.txt"));
        Map<String, String> map = new HashMap<>();
        while (file.hasNext()) {
            file.nextLine();
            String word1 = file.next(), word2 = file.next();
            map.put(word1, word2.substring(1, word2.length() - 1));
        }

        Scanner in = new Scanner(System.in);
        String[] arr = in.nextLine().split("\\s+");
        for (int i = 0; i < arr.length; i++) {
            String curr = arr[i];
            if (map.get(curr) != null)
                arr[i] = map.get(curr);
        }
        System.out.println(String.join(" ", arr));
    }
}
