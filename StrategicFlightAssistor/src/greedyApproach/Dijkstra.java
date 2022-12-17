package greedyApproach;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import graph.Edge;
import graph.GraphCreation;
import graph.HeapNode;
import graph.OutputConstructor;

/**
 * Class that solves the StrategicFlightAssistor Problem in Greedy approach
 * (Dijkstra Algorithm)
 *
 */
public class Dijkstra {

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

    // applying Dijkstra algorithm
    return applyDijkstraAlgorithm(edgesList, src, dest, k, startTime);

  }

  /**
   * Dijkstra algorithm to find the cheapest route with the given constraints.
   * 
   * @param edgesList created list of edges during graph creation
   * @param src       Source of booking
   * @param dest      Destination of booking
   * @param k         Maximum number of stops or connections allowed
   * @param startTime Start Time of the execution
   * @return
   */
  private String applyDijkstraAlgorithm(List<Edge> edgesList, int src, int dest, int k,
      long startTime) {

    // Object of class that constructs the output and returns
    OutputConstructor returner = new OutputConstructor();
    // Capturing count of the number of edges in the graph
    int n = edgesList.size();
    // Created a PriorityQueue that accepts paramter of type
    // Heapnode(vertex,distance,stops) and priority is assigned based on cost
    PriorityQueue<HeapNode> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
    // Source vertex as a HeapNode is pushed into the priority Queue
    pq.add(new HeapNode(src, 0, 0));

    // Creating Price Array to hold cost to any particular vertex from the source
    // vertex
    int[] price = new int[n];
    // initializing values of the array elements to infinity
    Arrays.fill(price, Integer.MAX_VALUE);
    // Cost from source to source is zero. So populated
    price[src] = 0;

    // Creating stops Array to track number of stops
    int[] stops = new int[n];
    // initializing values of the array elements to infinity
    Arrays.fill(stops, Integer.MAX_VALUE);
    // Number of stops from source to source is 0. So populated
    stops[src] = 0;

    // boolean array to track vertices for which minimum
    // cost is already found
    boolean[] MinCostTracked = new boolean[n];
    MinCostTracked[src] = true;

    // stores predecessor of a vertex (to a print path)
    int[] parent = new int[n];
    parent[src] = -1;

    // Running the priority queue until it is empty. This gurantees that all the
    // connected vertices are considered
    while (!pq.isEmpty()) {
      // Polling an element from the priority queue
      HeapNode node = pq.poll();
      int u = node.vertex;
      int cost = node.cost;
      int stopsCount = node.stops;

      // If destination is already reached, we are returning the output with the
      // Algorithm used, Minimum Cost, Route and the
      // Execution time taken
      if (u == dest) {
        return returner.returnPath(cost, src, dest, parent, "Dijkstra", startTime);
      }

      // If Maximum number of stops is reached, we continue the loop
      if (stopsCount == k + 1) {
        continue;
      }

      // Looping through all the edges of the list
      for (Edge edge : edgesList) {
        // Only if the edge consists of our source, will be going for the relaxation
        // process
        if (edge.getU() == u) {

          int v = edge.getV();
          int weight = edge.getWeight();

          // Relaxation step
          if (!MinCostTracked[v] && (price[u] + weight) < price[v] && edge.getF1Status()) {
            // Inserts the neighbour into the priority queue
            pq.offer(new HeapNode(v, price[u] + weight, stopsCount + 1));
            // Updating the cost
            price[v] = price[u] + weight;
            // Capturing the parent and storing it in the parent array
            parent[v] = u;
            // Updating stops count
            stops[v] = stopsCount;
          } else if (stopsCount < stops[v] && edge.getF1Status()) {
            pq.offer(new HeapNode(v, price[u] + weight, stopsCount + 1));
          }
        }

      }

      // Mark vertex u as visited to avoid revisiting
      MinCostTracked[u] = true;

    }

    // Returns the output with the Algorithm used, Minimum Cost, Route and the
    // Execution time taken
    return returner.returnPath(price[dest], src, dest, parent, "Dijkstra", startTime);
  }

}
