import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JOptionPane;



/**
 * @author María Mercedes Retolaza Reyna, 16339 
 * @version 2.2
 */
public class Manejador {
	
	private FloydWarshall fw;
	private Vertex<String>[] v ;
	private Vertex <String> aux1,aux2;
	private Set<String> ciudades =new TreeSet<String>();;
	private ArrayList<String> ciudadesList = new ArrayList<String>();
	 String [] hacergrafo(ArrayList<String> array, int conta){
		fw = new FloydWarshall(8);
		int co=0;
		for(int i = 0; i < array.size(); i++) {
			String temp = array.get(i);
			String[] partes = temp.split(" ");
			// Anadir ciudades a cmbBox
			ciudades.add(partes[0]);
			ciudades.add(partes[1]);
			co++;
		}
		v= new Vertex[ciudades.size()];
		co=0;
		for(String i: ciudades ){
		v[co]= new Vertex<String>(i,i,co);	
		co++;
		}
		for(int i = 0; i < array.size(); i++) {
			String temp = array.get(i);
			String[] partes = temp.split(" ");
			for(Vertex j: v ){
					if (j.getName().equals(partes[0])){
						aux1=j;
					}
				}
			for(Vertex j: v ){
				if (j.getName().equals(partes[1])){
					aux2=j;
				}
			}
			fw.addEdge(aux1, aux2, Double.parseDouble(partes[2]));
		}
		ciudadesList.addAll(ciudades);
		String [] c =  ciudadesList.toArray(new String[ciudadesList.size()]);
		return c;
	}
	 String corta(int origen, int destino,String o , String d){
		 fw.floydWarshall();
		 double a= fw.getEdge(origen,destino);
		 if(a==Double.POSITIVE_INFINITY){
			 return "No hay camino entre ellos";
		 }
		 fw.mostrarIntermedias(origen, destino);
		 String cen = fw.centroGrafo();
		int vi=8;
		 System.out.println("The adjacency matrix for the given graph is: ");
	        System.out.print("  ");
	        for (int i = 0; i < vi; i++)
	            System.out.print(i + " ");
	        System.out.println();
	        for (int i = 0; i < vi; i++) 
	        {
	        	//System.out.print(fw.g.get(i).getName()  + " ");
	        	System.out.print(i + " ");
	            for (int j = 0; j < vi; j++) 
	                System.out.print(fw.getEdge(i, j) + " ");
	            System.out.println();
	        }
		 return "El peso de: "+o+" a: "+ d+ "es: "+a +" y la ruta es: "+v[origen].getName()+ fw.inter+"--> "+ v[ destino].getName()+ " y el centro "
		 		+ "es: "+cen;
	 }
	 void modificarGrafo(int o, int d, double w ){
		 fw.setEdge(o, d, w);
	 }
	
}
