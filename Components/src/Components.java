import java.io.*;
import java.util.*;

public class Components {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("components.dat"));
        int n = in.nextInt();
        Map<String, List<String>> components = new HashMap<>();

        in.nextLine();
        while (n-- > 0) {
            LinkedList<String> line = new LinkedList<>(Arrays.asList(in.nextLine().split("\\s+")));
            String element = line.poll();
            line.poll(); // remove ">>"
            components.put(element, line);
        }

        n = in.nextInt();
        in.nextLine();
        while (n-- > 0) {
            String query = in.nextLine();
            List<String> results = new ArrayList<>();
            processList(query, components, results);

            // convert results to frequency table
            Map<String, Integer> finalMap = new TreeMap<>();
            results.forEach(s -> finalMap.merge(s, 1, Integer::sum));
            finalMap.forEach((x, y) -> System.out.println(x + " " + y));
            System.out.println("***");
        }
    }

    private static void processList(String query, Map<String, List<String>> components, List<String> results) {
        List<String> items = components.get(query);
        if (items != null) {
            for (String item : items) {
                if (components.containsKey(item)) processList(item, components, results);
                else results.add(item);
            }
        }
    }
}
