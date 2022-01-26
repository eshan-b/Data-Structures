import java.util.*;

public class BFS {
    private Map<String, String> prev = new HashMap<>();
    private Map<String, Integer> distance = new HashMap<>();

    public BFS(BFSGraph graph, String source) {
        Queue<String> q = new LinkedList<>();
        q.add(source); distance.put(source, 0);

        while (!q.isEmpty()) {
            String vertex = q.remove();
            for (String node : graph.adjacentTo(vertex)) {
                if (!distance.containsKey(node)) {
                    q.add(node);
                    distance.put(node, distance.get(vertex) + 1);
                    prev.put(node, vertex);
                }
            }
        }
    }

    public int calcDistance(String v) { return distance.get(v); }

    public List<String> calcPath(String vertex) {
        List<String> path = new LinkedList<>();
        while (vertex != null) {
            path.add(vertex);
            vertex = prev.get(vertex);
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) throws Exception {
        BFSGraph graph = new BFSGraph("airline.txt", " ");
        Scanner in = new Scanner(System.in);

        System.out.println("Enter start:");
        String start = in.nextLine();
        BFS findPath = new BFS(graph, start);

        System.out.println("Enter destination:");
        String destination = in.nextLine();

        System.out.println("Path:");
        System.out.println(String.join(" ",
                           findPath.calcPath(destination)));

        System.out.println("Distance: " + findPath.calcDistance(destination));

    }
}
