/*
* Autores:
*	Santiago Hern�ndez Arias C�digo 1631281
* Nombre: StartUpWindow.java
* Descripci�n: archivo de implementacion de la clase StartUpWindow
* Fecha de creaci�n: 6/3/2017
* Fecha de modificaci�n: 6/3/2017
*/

package focus;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

// TODO: Auto-generated Javadoc
/**
 * The Class StartUpWindow.
 */
public class StartUpWindow extends JFrame {
	
	/** The selection box for the set of icons. */
	private JComboBox<String> cajaSeleccion;
	
	/** The label for the start window icon. */
	private JLabel etiquetaSeleccion;
	
	/** The background image for this window. */
	private ImageIcon imagen1;
	
	/** The open button. */
	private JButton botonAbrir;
	
	/** The start up window main container. */
	private Container contenedor;
	
	/**
	 * Instantiates a new start up window.
	 */
	public StartUpWindow()
	{
		super("Seleccionar tema del juego");
		
		initialize();
		
		pack();
		
		setResizable(false); //Evita el redimencionamiento de la ventana
		setVisible(true);	//Hace visible la ventana
		setLocationRelativeTo(null); //Centra la ventana
	}
	
	/**
	 * Initialize.
	 */
	public void initialize()
	{
		contenedor = this.getContentPane();
		contenedor.setLayout(new FlowLayout());
		
		contenedor.setBackground(Color.red);
		
		ListenEventAction myListener = new ListenEventAction();
		
		String[] pkm = {"Fire Pokemon", "Water Pokemon", "Grass Pokemon", "Dragon Pokemon", "Various"};
		cajaSeleccion = new JComboBox<String>(pkm);
		contenedor.add(cajaSeleccion);
		
		botonAbrir = new JButton("Comenzar Juego");
		botonAbrir.addActionListener(myListener);
        contenedor.add(botonAbrir);
		
		etiquetaSeleccion = new JLabel();
		imagen1 = new ImageIcon(getClass().getResource("/imagenes/startIcon.png"));
		etiquetaSeleccion.setIcon(imagen1);
		contenedor.add(etiquetaSeleccion);
	}
	
	/**
	 * The Class ListenEventAction.
	 */
	private class ListenEventAction implements ActionListener
	{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent evento)
		{
			// TODO Auto-generated method stub
			
			if (evento.getSource() == botonAbrir)
			{
				String seleccion = (String) cajaSeleccion.getSelectedItem();
				
				/*
				 * closes158 the start up window before opening the main GUI
				 */
				dispose();
				
				/*
				 * Creates the main GUI
				 */
				GUI mainWindow = new GUI(seleccion);
				mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
			}		
		}
		
	   }

}
