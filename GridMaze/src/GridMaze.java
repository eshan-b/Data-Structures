import java.io.File;
import java.util.*;

public class GridMaze {
    int[][] maze;
    boolean[][] visited;
    int width, height;
    int pathLength;

    final int OPEN = 1, WALL = 0, MARK = 2;
    final int startX, startY, goalX, goalY;

    // vector directions
    final int[] row = { -1, 0, 0, 1 };
    final int[] col = { 0, -1, 1, 0 };

    public GridMaze(int[][] maze, int startX, int startY, int goalX, int goalY) {
        this.maze = maze;
        this.width = maze[0].length;
        this.height = maze.length;
        this.pathLength = 0;

        this.startX = startX; this.startY = startY;
        this.goalX = goalX; this.goalY = goalY;

        this.visited = new boolean[height][width];
    }

    private boolean isSafe(int x, int y) {
        return ((x >= 0 && x < width) &&
                (y >= 0 && y < height) &&
                (maze[x][y] == OPEN) &&
                (!visited[x][y]));
    }

    public int solveMaze() {
        // check if it is invalid input
        if ((height == 0) || (maze[startX][startY] == 0)
                          || (maze[goalX][goalY] == 0))
            return -1;

        Queue<QueueNode> q = new LinkedList<>();

        // initialize head node
        visited[startX][startY] = true;
        q.add(new QueueNode(startX, startY, 0));

        // iterate through queue
        while(!q.isEmpty()) {
            QueueNode node = q.poll();

            int currX = node.x, currY = node.y;
            int dist = node.dist;

            // check if reached destination
            if (currX == goalX && currY == goalY) return dist;

            for (int i = 0; i < row.length; i++) {
                // adjacent points
                int adjX = currX + row[i], adjY = currY + col[i];

                // add adjacent nodes to queue
                try {
                    if (isSafe(adjX, adjY)) {
                        visited[adjX][adjY] = true;
                        q.add(new QueueNode(adjX, adjY, dist + 1));
                    }
                } catch (IndexOutOfBoundsException e) { return -1; }
            }
        }

        // cannot reach destination
        return -1;
    }

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("rat.dat"));
        int cases = in.nextInt();

        for (int i = 0; i < cases; i++) {
            int length = in.nextInt(), width = in.nextInt();
            int[][] maze = new int[length][width];
            in.nextLine();

            // default start & end positions
            int startX = 0, startY = 0;
            int goalX = maze[0].length - 1, goalY = maze.length - 1;

            // initialize maze
            for (int row = 0; row < length; row++) {
                String line = in.nextLine();

                for (int col = 0; col < width; col++) {
                    char curr = line.charAt(col);

                    switch (curr) {
                        case 'S' -> {
                            maze[row][col] = 1;
                            startX = row; startY = col;
                        }
                        case 'E' -> {
                            maze[row][col] = 1;
                            goalX = row; goalY = col;
                        }
                        case 'G' -> maze[row][col] = 0;
                        case '.' -> maze[row][col] = 1;
                    }
                }
            }

            GridMaze b = new GridMaze(maze, startX, startY, goalX, goalY);
            int ans = b.solveMaze();
            System.out.println(ans == -1 ? "Oh Rem please help me" :
                               ans == 1 ? ans + " second" : ans + " seconds");
        }
    }
}

class QueueNode {
    int x, y, dist;

    public QueueNode(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}