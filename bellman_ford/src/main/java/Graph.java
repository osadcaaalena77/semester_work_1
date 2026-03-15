import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Graph {
    private long time;
    private boolean flagNegativeCycle;
    private int iteration;
    private int vershins;
    private List<Edge> edges;


    public int getVershins() {
        return vershins;
    }

    public long getTime() {
        return time;
    }

    public boolean getFlagNegativeCycle() {
        return flagNegativeCycle;
    }

    public int getEdges() {
        return edges.size();
    }

    public int getIteration() {
        return iteration;
    }


    public Graph(int vershins) {
        this.vershins = vershins;
        this.edges = new ArrayList<>();
    }

    public void addEdge(int from, int to, int weight) {
        edges.add(new Edge(from, to, weight));
    }

    public void bellmanFord(int vershinaStart) {
        long startTime = System.nanoTime();

        int[] result = new int[vershins];
        Arrays.fill(result, (int) 1e8);
        result[vershinaStart] = 0;
        iteration = 0;

        for (int i = 1; i < vershins; i++) {
            for (Edge edge : edges) {
                iteration++;
                if (result[edge.from] != (int) 1e8 && result[edge.from] + edge.weight < result[edge.to]) {
                    result[edge.to] = result[edge.from] + edge.weight;
                }
            }
        }
        flagNegativeCycle = false;
        for (Edge edge : edges) {
            iteration++;
            if (result[edge.from] != (int) 1e8 && (result[edge.from] + edge.weight < result[edge.to])) {
                flagNegativeCycle = true;
                break;
            }
        }
        time = System.nanoTime() - startTime;
    }

    public static Graph buildGraph(int v) {
        Graph graph = new Graph(v);
        Random random = new Random();
        int to;
        int weight;
        int from;

        for (int i = 0; i < v * 2; i++) {
            to = random.nextInt(v);
            from = random.nextInt(v);
            if (from == to) {
                to--;
            }
            weight = (to % 2 == 0)  ? random.nextInt(100) + 1: -random.nextInt(100) - 1;
            graph.addEdge(from, to, weight);
        }

        return graph;
    }
}
