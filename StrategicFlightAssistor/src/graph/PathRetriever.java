package graph;

import java.util.List;

/**
 * Class for tracing the route from source to destination
 */
public class PathRetriever {

  /**
   * Method that traces and returns the route using parent array
   * 
   * @param parent Parent Array that conatins parent of each vertex
   * @param dest   Destination
   * @param path   route list
   */
  public void getPath(int[] parent, int dest, List<Integer> path) {
    if (dest < 0) {
      return;
    }
    getPath(parent, parent[dest], path);
    path.add(dest);

  }

}
