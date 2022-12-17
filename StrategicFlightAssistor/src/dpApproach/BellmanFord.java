package dpApproach;

import java.util.Arrays;
import java.util.List;

import graph.Edge;
import graph.GraphCreation;
import graph.OutputConstructor;

/**
 * Class that solves the StrategicFlightAssistor Problem in Dynamic Programming
 * approach (BellmanFord Algorithm)
 *
 */
public class BellmanFord {

  /**
   * Method that returns the cheapest route details.
   * 
   * @param flights flights 2d array -> each entry represents an edge
   *                (u,v,weight,f1status)
   * @param src     Source of booking
   * @param dest    Destination of booking
   * @param k       Maximum number of stops or connections allowed
   * @return String output with the minimum cost considering the provided
   *         constraints
   */
  public String getCheapestRoute(Object[][] flights, int src, int dest, int k) {

    // Capturing the start time of the execution
    long startTime = System.currentTimeMillis();
    // Creating graph from flights array and storing the edges in a list
    List<Edge> edgesList = new GraphCreation().createGraph(flights);

    // base cases

    // applying BellmanFord algorithm
    return applyBellmanFordAlgorithm(edgesList, src, dest, k, startTime);

  }

  /**
   * BellmanFord algorithm to find the cheapest route with the given constraints.
   * 
   * @param edgesList created list of edges during graph creation
   * @param src       Source of booking
   * @param dest      Destination of booking
   * @param k         Maximum number of stops or connections allowed
   * @param startTime Start Time of the execution
   * @return
   */
  private String applyBellmanFordAlgorithm(List<Edge> edgesList, int src, int dest, int k,
      long startTime) {

    // Object of class that constructs the output and returns
    OutputConstructor returner = new OutputConstructor();
    // Capturing count of the number of edges in the graph
    int n = edgesList.size();

    // Creating Price Array to hold cost to any particular vertex from the source
    // vertex
    int price[] = new int[n];
    // initializing values of the array elements to infinity
    Arrays.fill(price, Integer.MAX_VALUE);
    // Cost from source to source is zero. So populated
    price[src] = 0;

    // Creating parent array to hold the parent of each vertex which can be used to
    // trace the route
    int parent[] = new int[n];
    Arrays.fill(parent, -1);

    // since we are looking for utmost k stops, we run the loop from 0 to k
    for (int maxStops = 0; maxStops <= k; maxStops++) {
      int[] temp = Arrays.copyOf(price, price.length);
      // Looping through all the edges of the list
      for (Edge e : edgesList) {

        int u = e.getU();
        int v = e.getV();
        int wt = e.getWeight();
        boolean s = e.getF1Status();

        // if there is no route from source to u, then we will not able to go from u to
        // v
        // and if the path doesn't allow users traveling with a F1 status, we will not
        // consider it
        if (price[u] != Integer.MAX_VALUE && s) {

          // if we encounter a smaller price, then we will update it
          temp[v] = Math.min(temp[v], price[u] + wt);
          // Capturing the parent and storing it in the parent array
          parent[v] = u;
        }

      }
      price = Arrays.copyOf(temp, temp.length);

    }

    // At the end of the for loop, price array will hold the minimum travel cost for
    // each vertex from source.

    // Returns the output with the Algorithm used, Minimum Cost, Route and the
    // Execution time taken
    return returner.returnPath(price[dest], src, dest, parent, "BellmanFord", startTime);

  }

}
