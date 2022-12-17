package main;

import dpApproach.BellmanFord;
import greedyApproach.Dijkstra;

public class StrategicFlightAssistor {

  public static void main(String[] args) {
    Object[][] flights = { { 0, 1, 100, true }, { 1, 2, 100, true }, { 2, 0, 100, true },
        { 1, 3, 600, true }, { 2, 3, 200, true } };

    System.out.println(new BellmanFord().getCheapestRoute(flights, 0, 3, 1));
    System.out.println(new Dijkstra().getCheapestRoute(flights, 0, 3, 1));

  }

}
