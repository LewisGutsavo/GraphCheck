package entities;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Main
{
    // Utility function to perform DFS traversal on the graph
    public static void DFS(Graph graph, int v, boolean[] discovered)
    {
        // mark the current node as discovered
        discovered[v] = true;
 
        // do for every edge (v, u)
        for (int u: graph.adjList.get(v))
        {
            // if `u` is not yet discovered
            if (!discovered[u]) {
                DFS(graph, u, discovered);
            }
        }
    }
 
    // Function to check if all vertices with a non-zero degree in a graph
    // belong to a single connected component
    public static boolean isConnected(Graph graph, int n)
    {
        // to keep track of whether a vertex is visited or not
        boolean[] visited = new boolean[n];
 
        // start DFS from the first vertex with a non-zero degree
        for (int i = 0; i < n; i++)
        {
            if (graph.adjList.get(i).size() > 0)
            {
                DFS(graph, i, visited);
                break;
            }
        }
 
        // if a single DFS call couldn't visit all vertices with a non-zero degree,
        // the graph contains more than one connected component
        for (int i = 0; i < n; i++)
        {
            if (!visited[i] && graph.adjList.get(i).size() > 0) {
                return false;
            }
        }
        return true;
    }
 
    // Utility function to return the total number of vertices in a graph
    // with an odd degree
    public static int countOddVertices(Graph graph)
    {
        int count = 0;
        for (List<Integer> list: graph.adjList)
        {
            if ((list.size() & 1) == 1) {
                count++;
            }
        }
        return count;
    }
 
    public static void main(String[] args)
    {
        // List of graph edges as per the above diagram
        List<Edge> edges = Arrays.asList(new Edge(0, 1), new Edge(0, 3),
                new Edge(1, 2), new Edge(1, 3), new Edge(1, 4),
                new Edge(2, 3), new Edge(3, 4));
 
        // total number of nodes in the graph (labelled from 0 to 4)
        int n = 5;
 
        // create an undirected graph from the given edges
        Graph graph = new Graph(edges, n);
 
        // check if all vertices with a non-zero degree belong to a
        // single connected component
        boolean is_connected = isConnected(graph, n);
 
        // find the total number of vertices with an odd degree
        int odd = countOddVertices(graph);
 
        // An Eulerian path exists if all non-zero degree vertices are connected,
        // and zero or two vertices have an odd degree
 
        if (is_connected && (odd == 0 || odd == 2))
        {
            System.out.println("The graph has an Eulerian path");
 
            // A connected graph has an Eulerian cycle if every vertex has an
            // even degree
            if (odd == 0) {
                System.out.println("The graph has an Eulerian cycle");
            }
            // The graph has an Eulerian path but not an Eulerian cycle
            else {
                System.out.println("The Graph is Semi–Eulerian");
            }
        }
        else {
            System.out.println("The Graph is not Eulerian");
        }
    }
}
