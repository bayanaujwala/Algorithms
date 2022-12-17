package graph;

/**
 * Class that represents an Edge between 2 vertices(u,v), also carrying
 * information about the cost of the edge and if F1 visa is allowed in the path
 * or not
 *
 */
public class Edge {

  int u;
  int v;
  int weight;
  boolean f1Status;

  /**
   * Creates an object of type Edge (u,v)
   * 
   * @param u        vertex u
   * @param v        vertex v
   * @param weight   weight of the edge
   * @param f1Status f1 visa status of the edge
   */
  public Edge(int u, int v, int weight, boolean f1Status) {
    super();
    this.u = u;
    this.v = v;
    this.weight = weight;
    this.f1Status = f1Status;
  }

  /**
   * Gets the vertex u
   * 
   * @return vertex u as integer
   */
  public int getU() {
    return u;
  }

  /**
   * Gets the vertex v
   * 
   * @return vertex v as integer
   */
  public int getV() {
    return v;
  }

  /**
   * Gets the weight of the edge
   * 
   * @return weight as integer
   */
  public int getWeight() {
    return weight;
  }

  /**
   * Gets the F1 visa st=atus along the edge
   * 
   * @return f1 status as boolean
   */
  public boolean getF1Status() {
    return f1Status;
  }

}
