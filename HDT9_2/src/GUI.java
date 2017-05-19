import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JTextField;

/**
 * @author María Mercedes Retolaza Reyna, 16339 
 * Clase que se encarga de dar la interfaz al usuario.
 */
public class GUI extends JFrame implements ActionListener{
		/*Atributos*/
		private Container contenedor;  
		JLabel labelTitulo;/*declaramos el objeto Label*/
		public JTextArea areaDeTexto;
		JButton botonAbrir,btnNewButton,btnCargar;/*declaramos el objeto Boton*/
		JScrollPane scrollPaneArea;
		JFileChooser fileChooser; /*Declaramos el objeto fileChooser*/
		String texto="";
		public  ArrayList<String> array2 = new ArrayList<String>();
		private JTextField txtNoHayArchivo;
		private Manejador m = new Manejador();
		public int conta=0,ansInt,ansIn;
		String [] lista;
		private String origen, destino;
		private double numero;
		/**
		 * Constructor que arma el GUI.
		 */
		public GUI(){
			contenedor=getContentPane();
			contenedor.setLayout(null);
			
			/*Creamos el objeto*/
			fileChooser=new JFileChooser();
			 
			/*Propiedades del Label, lo instanciamos, posicionamos y
			 * activamos los eventos*/
			labelTitulo= new JLabel();
			labelTitulo.setText("l Tribunal Supremo Electorial");
			labelTitulo.setBounds(110, 20, 180, 23);
			
			areaDeTexto = new JTextArea();
			areaDeTexto.setEditable(false);
			//para que el texto se ajuste al area
			areaDeTexto.setLineWrap(true);
			//permite que no queden palabras incompletas al hacer el salto de linea
			areaDeTexto.setWrapStyleWord(true);
		   	scrollPaneArea = new JScrollPane();
			scrollPaneArea.setBounds(20, 50, 350, 270);
	        scrollPaneArea.setViewportView(areaDeTexto);
	       	
			/*Propiedades del boton, lo instanciamos, posicionamos y
			 * activamos los eventos*/
			botonAbrir= new JButton();
			botonAbrir.setText("Boton 1");
			botonAbrir.setBounds(474, 281, 91, 23);
			botonAbrir.addActionListener(this);
			
			/*Agregamos los componentes al Contenedor*/
			contenedor.add(labelTitulo);
			contenedor.add(scrollPaneArea);
			contenedor.add(botonAbrir);
			
			btnNewButton = new JButton("Boton 2");
			btnNewButton.setBounds(620, 281, 89, 23);
			getContentPane().add(btnNewButton);
			btnNewButton.addActionListener(this);
			
			JTextPane txtpnInstruccionesnBoton = new JTextPane();
			txtpnInstruccionesnBoton.setEditable(false);
			txtpnInstruccionesnBoton.setFont(new Font("Dialog", Font.PLAIN, 14));
			txtpnInstruccionesnBoton.setBackground(SystemColor.control);
			txtpnInstruccionesnBoton.setText("Instrucciones: \r\nCargar: Cargar datos guategrafo.txt\r\nBoton 1: Preguntar el nombre de la ciudad origen y ciudad destino.\r\nBoton 2: Modificar grafo");
			txtpnInstruccionesnBoton.setBounds(440, 50, 269, 101);
			getContentPane().add(txtpnInstruccionesnBoton);
			
			txtNoHayArchivo = new JTextField();
			txtNoHayArchivo.setEditable(false);
			txtNoHayArchivo.setForeground(Color.RED);
			txtNoHayArchivo.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
			txtNoHayArchivo.setText("No hay archivo cargado.");
			txtNoHayArchivo.setBackground(Color.DARK_GRAY);
			txtNoHayArchivo.setBounds(509, 190, 204, 34);
			getContentPane().add(txtNoHayArchivo);
			txtNoHayArchivo.setColumns(10);
			
			btnCargar = new JButton("Cargar");
			btnCargar.setBounds(410, 197, 89, 23);
			getContentPane().add(btnCargar);
			btnCargar.addActionListener(this);
       		//Asigna un titulo a la barra de titulo
			setTitle("Proyecto");
			//tamaño de la ventana
			setSize(750,363);
			//pone la ventana en el Centro de la pantalla
			setLocationRelativeTo(null);
			
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent evento) {
			//Si presiono el boton de cargar
			if (evento.getSource()==btnCargar){
				abrirArchivo();
				 lista = m.hacergrafo(array2,conta);
				
			}
			//Si presiono el boton 1 
			if (evento.getSource()==botonAbrir){
				if (txtNoHayArchivo.getText().equals("Archivo Cargado")){
					origen = (String) JOptionPane.showInputDialog(null,"Seleccione un ciudad de origen ",
							   "ORIGEN", JOptionPane.QUESTION_MESSAGE, null,
							  lista,"Seleccione");
					for(int i = 0; i < lista.length; i++) {
						  if(lista[i].equals(origen)) {
						    ansInt = i;
						    break;
						  }
						}
					if(origen!=null){
						destino = (String) JOptionPane.showInputDialog(null,"Seleccione un empleado de destino ",
								   "DESTINO", JOptionPane.QUESTION_MESSAGE, null,
								   lista,"Seleccione");
						for(int i = 0; i < lista.length; i++) {
							  if(lista[i].equals(destino)) {
							    ansIn = i;
							    break;
							  }
							}
				}
					areaDeTexto.setText(m.corta(ansInt, ansIn, origen, destino));
					
				}
				else{
					JOptionPane.showMessageDialog(null, "NO HA CARGADO EL ARCHIVO",
							  "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			//Si presiono el boton 2
			if (evento.getSource()==btnNewButton){
				if (txtNoHayArchivo.getText().equals("Archivo Cargado")){
					origen = (String) JOptionPane.showInputDialog(null,"Seleccione un ciudad de origen ",
							   "ORIGEN", JOptionPane.QUESTION_MESSAGE, null,
							  lista,"Seleccione");
					for(int i = 0; i < lista.length; i++) {
						  if(lista[i].equals(origen)) {
						    ansInt = i;
						    break;
						  }
						}
					if(origen!=null){
						destino = (String) JOptionPane.showInputDialog(null,"Seleccione un empleado de destino ",
								   "DESTINO", JOptionPane.QUESTION_MESSAGE, null,
								   lista,"Seleccione");
						for(int i = 0; i < lista.length; i++) {
							  if(lista[i].equals(destino)) {
							    ansIn = i;
							    break;
							  }
							}
				}
					if (destino!=null){
						numero=Double.parseDouble(JOptionPane.showInputDialog("Ingrese un" +
								  " numero para ser el nuevo peso entre ciudades"));
					}
					m.modificarGrafo(ansInt, ansIn, numero);
					areaDeTexto.setText("Se ha modificado el grafo");
				}
				else{
					JOptionPane.showMessageDialog(null, "NO HA CARGADO EL ARCHIVO",
							  "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
				}
			}
			}
		

		/**
		 * Permite mostrar la ventana que carga 
		 * archivos en el area de texto
		 * @return arreglo con los campos del texto.
		 */
		private void  abrirArchivo() {
						
	 		//texto="";
	 		String bfRead;
	 		/*llamamos el metodo que permite cargar la ventana*/
			fileChooser.showOpenDialog(this);
			/*abrimos el archivo seleccionado*/
			File abre=fileChooser.getSelectedFile();

			/*recorremos el archivo, lo leemos para plasmarlo
			 *en el area de texto*/
			if(abre!=null)
			{ 				
				txtNoHayArchivo.setText("Archivo Cargado");
				try {
					BufferedReader ar = new BufferedReader(new FileReader(abre));
					while ((bfRead = ar.readLine()) != null) {
						 //String array []=  bfRead.split(";");
						 array2.add(bfRead);
						 conta++;
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "No se encontro archivo");
				}
			} 	
		}
	}
