import java.io.FileInputStream;
import java.util.*;

public class BipartiteGraph {
    private Map<String, Set<String>> graph;
    private int E;

    public BipartiteGraph() { this.graph = new HashMap<>(); }

    public BipartiteGraph(String filename, String delimiter) throws Exception {
        this.graph = new HashMap<>();
        Scanner in = new Scanner(new FileInputStream(filename));
        while (in.hasNextLine()) {
            String[] names = in.nextLine().split(delimiter);
            for (int i = 1; i < names.length; i++)
                addEdge(names[0], names[i]);
        }
    }

    /* --- Vertices --- */
    public int V() { return this.graph.size(); }

    public boolean hasVertex(String v) {
        return this.graph.containsKey(v);
    }

    private void isVertex(String v) {
        assert hasVertex(v) : v + " is not a vertex";
    }

    private void addVertex(String v) {
        if (!hasVertex(v)) this.graph.put(v, new HashSet<>());
    }

    public Iterable<String> vertices() {
        return this.graph.keySet();
    }

    public int degree(String v) {
        isVertex(v);
        return this.graph.get(v).size();
    }

    /* --- Edges --- */
    public int E() { return this.E; }

    public boolean hasEdge(String v, String w) {
        isVertex(v); isVertex(w);
        return this.graph.get(v).contains(w);
    }

    public void addEdge(String v, String w) {
        if (!hasVertex(v)) addVertex(v);
        if (!hasVertex(w)) addVertex(w);
        if (!hasEdge(v, w)) E++;

        this.graph.get(v).add(w);
        this.graph.get(w).add(v);
    }

    public Iterable<String> adjacentTo(String v) {
        isVertex(v);
        return this.graph.get(v);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (String v : vertices()) {
            s.append(v).append(": ");
            for (String w : adjacentTo(v)) s.append(w).append(" ");
            s.append('\n');
        }
        return s.toString();
    }


    public static void main(String[] args) throws Exception {
        BipartiteGraph graph = new BipartiteGraph("movies.txt", "/");
        Scanner in = new Scanner(System.in);

        System.out.println("Enter an actor or a movie:");
        String v = in.nextLine();
        if (graph.hasVertex(v)) {
            for (String adj : graph.adjacentTo(v))
                System.out.println(adj);
        }
    }
}
