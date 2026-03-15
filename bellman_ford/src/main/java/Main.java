import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String args[]) {

        if (!new File("results").exists()) {
            new File("results").mkdir();
        }

        try (FileWriter file = new FileWriter("results/result1.csv")) {
            file.write("количество вершин; время; итерации; negativeCycle\n");

            for (int v = 100; v <= 10000; v += 150) {
                Graph graph = Graph.buildGraph(v);
                 graph.bellmanFord(0);

                 file.write(v + "; " + graph.getTime() + "; " + graph.getIteration() + "; " + graph.getFlagNegativeCycle() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
