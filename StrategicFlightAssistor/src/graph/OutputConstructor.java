package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class responsible for creating the output in a particular format
 *
 */
public class OutputConstructor {

  /**
   * Creates and returns the constructed output
   * 
   * @param cost      Minimum cost found from source to vertex
   * @param src       Source of the flight
   * @param dest      Destination of the flight
   * @param parent    Parent array that holds the parent of each vertex that can
   *                  be used to trace the route
   * @param algo      Algorithm used
   * @param startTime Execution starting time
   * @return output as a String
   */
  public String returnPath(int cost, int src, int dest, int[] parent, String algo, long startTime) {

    // If cost is equal to MAX_VALUE, that means no path has been found from source
    // to destination under the given constraints
    if (cost == Integer.MAX_VALUE) {
      return String.format("No Possible shortest path exists between Path (%d -> %d) using %s ",
          src, dest, algo);
    }

    // Route constructed from the parent array
    List<Integer> path = new ArrayList<>();
    new PathRetriever().getPath(parent, dest, path);
    String route = path.stream().map(p -> String.valueOf(p))
        .collect(Collectors.joining("->", "(", ")"));

    // Capturing the end time of the execution
    long executionTime = System.currentTimeMillis() - startTime;

    // Constructing the output in the format: Path (<source> -> <destination>) using
    // <algorithm>: Minimum Cost = <cost>, Route = <route>, ExecutionTime = <time
    // taken>
    return String.format(
        "Path (%d -> %d) using %s: Minimum Cost = %d, Route = %s, ExecutionTime = %d ms ", src,
        dest, algo, cost, route, (int) executionTime);
  }

}
