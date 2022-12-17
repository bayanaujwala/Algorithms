package graph;

/**
 * Class that represents the HeapNode(vertex,cost,stops)
 *
 */
public class HeapNode {

  public int vertex;
  public int cost;
  public int stops;

  /**
   * Creates an object of type HeapNode
   * 
   * @param vertex flight vertex
   * @param cost   cost to the vertex
   * @param stops  numbre of stopes.
   */
  public HeapNode(int vertex, int cost, int stops) {
    this.vertex = vertex;
    this.cost = cost;
    this.stops = stops;
  }

}
