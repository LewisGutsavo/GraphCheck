package entities;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Graph {
	 // A list of lists to represent an adjacency list
    List<List<Integer>> adjList;
 
    // Constructor
    Graph(List<Edge> edges, int n)
    {
        adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
        // add edges to the undirected graph
        for (Edge edge: edges)
        {
            adjList.get(edge.source).add(edge.dest);
            adjList.get(edge.dest).add(edge.source);
        }
    }
}

