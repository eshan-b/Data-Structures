import java.util.Scanner;

public class Bacon {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        Graph G = new Graph("movies.txt", "/");

        String start = "Bacon, Kevin";
        BFS path = new BFS(G, start);

        System.out.println("Enter actor:");
        String actor = in.nextLine();

        System.out.println("Path:");
        System.out.println(String.join(" ",
                            path.calcPath(actor)));

        System.out.println("Bacon Number: " + path.calcDistance(actor) / 2);

        path.findMaxDistance();
    }
}
