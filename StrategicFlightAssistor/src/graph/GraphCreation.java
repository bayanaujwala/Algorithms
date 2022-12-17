package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Responsible for creating the graph from the given flights array
 *
 */
public class GraphCreation {
  
  /**
   * Creates a graph using the flights array and returns the edges as a list
   * @param flights
   * @return
   */
  public List<Edge> createGraph(Object[][] flights) {

    List<Edge> edgesList = new ArrayList<>();
    int flight = 0;

    //Iterating through the flights array
    while (flight < flights.length) {
      Edge e = new Edge((int) flights[flight][0], (int) flights[flight][1],
          (int) flights[flight][2], (boolean) flights[flight][3]);
      edgesList.add(e);
      flight++;
    }

    return edgesList;
  }

}
