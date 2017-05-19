/*
 * The Floyd-Warshall algorithm is used to find the shortest path between
 * all pairs of nodes in a weighted graph with either positive or negative
 * edge weights but without negative edge cycles.
 * 
 * The running time of the algorithm is O(n^3), being n the number of nodes in
 * the graph.
 *
 * This implementation is self contained and has no external dependencies. It 
 * does not try to be a model of good Java OOP, but a simple self contained 
 * implementation of the algorithm.
 */
/**
 * @author María Mercedes Retolaza Reyna, 16339 S
 * @version 2.2
 */
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class FloydWarshall< E extends Comparable<E>> implements Graph<E> {

    // graph represented by an adjacency matrix
    private double[][] graph,p;
    private boolean negativeCycle;
    private double[] max;
    public ArrayList<Vertex<E>> g = new ArrayList<Vertex<E>>();
    public String inter="";
    private int centro;
    public String retorno="";
    private double minimo=Double.POSITIVE_INFINITY;
    public FloydWarshall(int n) {
        this.graph = new double[n][n];
        this.p=new double[n][n];
        this.max= new double[n];
        initGraph();
    }
   

    public boolean hasNegativeCycle() {
        return this.negativeCycle;
    }

    public double getEdge(int from, int to) 
    {
        try 
        {
            return graph[from][to];
        }
        catch (ArrayIndexOutOfBoundsException index) 
        {
        }
        return -1;
    }
    
    public void setEdge(int from, int to,double res) 
    {
        try 
        {
            graph[from][to]=res;
        }
        catch (ArrayIndexOutOfBoundsException index) 
        {
        }
    }
    
    // all-pairs shortest path
    public double[][] floydWarshall() {
        double[][] distances;
        int n = this.graph.length;
        int conta=0;
        distances = Arrays.copyOf(this.graph, n);
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    conta=(int) distances[i][j];
                    distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
                    if (conta>distances[i][k] + distances[k][j]){
                         this.p[i][j]=k;
                    }
                   
                }
            }

            if (distances[k][k] < 0.0) {
                this.negativeCycle = true;
            }
        }

        return distances;
    }
    @Override
    public void addVertex(Vertex v) {
        
    }
    @Override
    public int size() {
        // TODO Auto-generated method stub
        return this.g.size();
    }
    public void initGraph() {
         for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph.length; j++) {
                    if (i == j) {
                        graph[i][j] = 0;
                    } else {
                        graph[i][j] = Double.POSITIVE_INFINITY;
                    }
                }
            }
    }

    @Override
    public void addEdge(Vertex v1, Vertex v2, double weight) {
        // TODO Auto-generated method stub
        this.g.add(v1);
        this.g.add(v2);
        int from,to;
        from=v1.getId();
        to=v2.getId();
        graph[from][to] = weight;
    }
    public String centroGrafo(){
        int n=0;
        int size=this.graph.length;
        // Encontrar los maximos de cada columna de la matriz
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                int num1=(int) getEdge(j, i);
                n=j;
                n++;
                int num2=(int) getEdge(n, i);
                if(num1>num2)
                {
                    max[i]=num1;
                }
                else
                {
                    max[i]=num2;
                }
            }
            n++;
        }
        // Encontrar el minimo de los maximos de cada columna
        for(int i=0;i<size;i++)
        {
            int num1=(int) max[i];
            if(num1<minimo)
            {
                centro=i;
                minimo=num1;
            }
        }
        for (Vertex k: this.g){
            if(k.getId()==centro){
                retorno = k.getName();
            }
        }
        return retorno;
    }
      public void mostrarIntermedias(int num1, int num2)
        {
          //inter+=num1;
          //System.out.println(num1+",coma"+num2);
          //System.out.println(inter);
            inter+="--> "+this.g.get((int) p[num1][num2]).getName();
            if((int)this.p[num1][num2]!=0)
            {
                mostrarIntermedias(num1,(int) p[num1][num2]);
                System.out.print(", "+p[num1][num2]);
                
                mostrarIntermedias((int) p[num1][num2],num2);
                return;
            }
            else return;
        }
}