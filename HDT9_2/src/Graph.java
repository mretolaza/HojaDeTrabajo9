
/**
 * @author María Mercedes Retolaza Reyna, 16339 
 * @param <T>
 */
public interface Graph<T extends Comparable<T>> {
		void  addVertex(Vertex<T> v);
        void addEdge(Vertex<T> v1, Vertex<T> v2,double weight);
        int size();
        void initGraph();
    }

    class Vertex<E extends Comparable<E>> {
    	private String name;  
    	private	E data;
    	private int id;
    	
		public static final int INFINITY = Integer.MAX_VALUE;
    	
		public Vertex(String name, E data, int id) {
			super();
			this.name = name;
			this.data = data;
			this.id = id;
		}
		public Vertex(String name, E data) {
			super();
			this.name = name;
			this.data = data;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public E getData() {
			return data;
		}
		public void setData(E data) {
			this.data = data;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		@Override
		public String toString() {
			return super.toString();
		}
    }