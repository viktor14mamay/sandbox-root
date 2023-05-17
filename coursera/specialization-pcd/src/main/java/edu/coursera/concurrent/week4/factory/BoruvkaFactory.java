package edu.coursera.concurrent.week4.factory;

import edu.coursera.concurrent.week4.algo.AbstractBoruvka;
import edu.coursera.concurrent.week4.components.Component;
import edu.coursera.concurrent.week4.edges.Edge;
import java.util.Queue;

/**
 * An abstract interface to implement when manufacturing components and edges in the graph.
 *
 * @param <C> Component type to create.
 * @param <E> Edge type to create.
 */
public interface BoruvkaFactory<C extends Component, E extends Edge> {
  /**
   * Create a new component with the given ID.
   *
   * @param nodeId Node ID
   * @return New component with ID nodeId
   */
  C newComponent(int nodeId);

  /**
   * Create a new edge given the components that edge links, and its weight.
   *
   * @param from Source component.
   * @param to Destination component.
   * @param weight Weight of the edge.
   * @return New edge.
   */
  E newEdge(C from, C to, double weight);

  AbstractBoruvka<C> generateBoruvka();

  Queue<C> createQueue();
}
