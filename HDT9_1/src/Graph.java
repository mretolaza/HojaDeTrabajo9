/**
 * @author María Mercedes Retolaza Reyna, 16339 
 * @authorLuis Diego Fernandez, 16344
 * @authorJavier Ramos, 16206 
 * @authorGadhi Rodriguez, 16206 
 *
 * @param <T>
 */
public interface Graph<T extends Comparable<T>> {
        boolean  addVertex(Vertex<T> v);
        boolean addEdge(Vertex<T> v1, Vertex<T> v2);
        int size();
        boolean removeEdge(Vertex<T> v1, Vertex<T> v2);
        boolean hasEdge(Vertex<T> v1, Vertex<T> v2);

    }

    class Vertex<E extends Comparable<E>> {
      E vertex;
    }