import java.util.*;

public class BFS {
    private Map<String, String> prev = new HashMap<>();
    private Map<String, Integer> distance = new HashMap<>();
    private Graph graph;

    public BFS(Graph graph, String source) {
        this.graph = graph;
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

    public int calcDistance(String v) {
        return this.distance.get(v) == null ? 0 : this.distance.get(v);
    }

    public List<String> calcPath(String v) {
        List<String> path = new LinkedList<>();
        while (v != null) {
            path.add(v);
            v = prev.get(v);
        }
        Collections.reverse(path);
        return path;
    }

    public void findMaxDistance() {
        int maxDistance = 0;
        List<String> maximums = new LinkedList<>();

        for (String vertex : graph.vertices()) {
            int curr = calcDistance(vertex);
            if (curr > maxDistance) maxDistance = curr;
        }

        // only keep the max vertices
        for (String vertex : graph.vertices()) {
            if (calcDistance(vertex) == maxDistance)
                maximums.add(vertex);
        }

        // adjusted to divide by 2 for Bacon number
        System.out.println("Maximum Distance: " + maxDistance / 2);

        System.out.println("Max Vertices:");
        maximums.forEach(System.out::println);
    }
}
