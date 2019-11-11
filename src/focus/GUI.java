/*
* Autores:
*	Santiago Hern�ndez Arias C�digo 1631281
* Nombre: GUI.java
* Descripci�n: archivo de implementacion de la clase GUI
* Fecha de creaci�n: 6/3/2017
* Fecha de modificaci�n: 6/3/2017
*/

package focus;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


// TODO: Auto-generated Javadoc
/**
 * The Class GUI.
 */
public class GUI extends JFrame {
	
	/** The Constant GRIDSIZE. */
	private static final int GRIDSIZE = 6;
	
	/** The main container. */
	private Container contenedor;
	
	/** The buttons array. */
	private Fbutton[][] botones;
	
	/** The buttons grid. */
	private JPanel marco;
	
	/** The integer variables. */
	private int iteration, id1, id2, rowId1, columnId1, rowId2, columnId2, puntaje;
	
	private Timer timerStart, timerPush;
	
	/**
	 * Instantiates a new gui.
	 *
	 * @param seleccion: dictates the set of images that will be used as icons for the buttons
	 */
	public GUI(String seleccion)
	{
		super("Juego de Focus");
		
		initialize(seleccion);
		
		pack();
		
		setResizable(false); //Evita el redimencionamiento de la ventana
		setVisible(true);	//Hace visible la ventana
		setLocationRelativeTo(null); //Centra la ventana
		
		timerStart.start();
	}
	
	/**
	 * Initialize.
	 *
	 * @param seleccion the seleccion
	 */
	public void initialize(String seleccion)
	{
		contenedor = this.getContentPane();
		contenedor.setLayout(new FlowLayout());
		setMechanics(seleccion);
		
		this.iteration = 0;
		this.id1 = 0;
		this.id2 = 0;
		this.puntaje = 0;
		this.rowId1 = 0;
		this.columnId1 = 0;
		this.rowId2 = 0;
		this.columnId2 = 0;
		
		
	}
	
	/**
	 * Sets the game mechanics
	 *
	 * @param seleccion: water or fire pokemon
	 */
	public void setMechanics(String seleccion)
	{
		botones = new Fbutton[GRIDSIZE][GRIDSIZE];
		
		marco = new JPanel();
		marco.setLayout(new GridLayout(GRIDSIZE, GRIDSIZE));
		
		ArrayList<Fbutton> pokemonButtons = new ArrayList<Fbutton>();
		ListenEventAction myListener = new ListenEventAction();
		
		/*
		 * Instantiates the timers;
		 */
		timerStart = new Timer(2500, myListener);
		timerPush = new Timer(700, myListener);
		
		/*
		 * Instantiates the buttons and adds them into an array
		 */
		for(int a = 1; a <= 18; a++)
		{
			ImageIcon imagen1 = new ImageIcon(getClass().getResource("/" + seleccion + "/" + String.valueOf(a) + ".png"));
			Fbutton button1 = new Fbutton(a, imagen1);
			button1.addActionListener(myListener);
			
			ImageIcon imagen2 = new ImageIcon(getClass().getResource("/" + seleccion + "/" + String.valueOf(a) + ".png"));
			Fbutton button2 = new Fbutton(a, imagen2);
			button2.addActionListener(myListener);
			
			pokemonButtons.add(button1);
			pokemonButtons.add(button2);
			
		}
		
		/*
		 * Shuffles the array of buttons
		 */
		long seed = System.nanoTime();
	    Collections.shuffle(pokemonButtons, new Random(seed));
	    
	    int contador = 0;
	    
	    /*
		 * Adds the buttons to the grid
		 */
	    for(int a = 0; a < GRIDSIZE; a++)
	    {
	    	for(int b = 0; b < GRIDSIZE; b++)
		    {
	    		Fbutton button = pokemonButtons.get(contador);
	    		button.setRow(a);
	    		button.setColumn(b);
	    		botones[a][b] = button;
	    		marco.add(botones[a][b]);
		    	
		    	contador++;
		    }
	    }
	    
	    /*
	     * adds the grid to the main container
	     */
	    contenedor.add(marco);
		
	}
	
	/*
	 * The update function which takes place after a second button has been pressed
	 */
	private void updateButtons() {
		  SwingUtilities.invokeLater(new Runnable() {
		    public void run() { 
		       botones[rowId1][columnId1].toggleOnOff();
			   botones[rowId1][columnId1].setEnabled(true);
			   
			   botones[rowId2][columnId2].toggleOnOff();
			   botones[rowId2][columnId2].setEnabled(true);
			   
			   setEnabled(true);
			   
		       timerPush.stop();
		    }
		  });
	  }
	
	/*
	 * When the game starts, it toggles buttons off and enables them after a brief delay
	 */
	private void firstSight()
	{
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() { 
		    	for(int a = 0; a < GRIDSIZE; a++)
			    {
			    	for(int b = 0; b < GRIDSIZE; b++)
				    {
			    		botones[a][b].toggleOnOff();
			    		botones[a][b].setEnabled(true);
				    }
			    }
				timerStart.stop();
		    }
		  });
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
			/*
			 * Handle event when source is a Fbutton
			 */
			if(evento.getSource() instanceof Fbutton)
			{
				if(iteration==0)
				{
					((Fbutton) evento.getSource()).toggleOnOff();
					((Fbutton) evento.getSource()).setEnabled(false);
					id1 = ((Fbutton) evento.getSource()).getId();
					
					rowId1 = ((Fbutton) evento.getSource()).getRow();
					columnId1 = ((Fbutton) evento.getSource()).getColumn();
					
					iteration = 1;
				}
				else
				{
					((Fbutton) evento.getSource()).toggleOnOff();
					((Fbutton) evento.getSource()).setEnabled(false);
					id2 = ((Fbutton) evento.getSource()).getId();
					
					rowId2 = ((Fbutton) evento.getSource()).getRow();
					columnId2 = ((Fbutton) evento.getSource()).getColumn();
					
					if(id1 != id2)
					{
						setEnabled(false);
						timerPush.start();
					}
					else
					{	
						botones[rowId1][columnId1].setEnabled(false);
						puntaje += 1;
					}
					
					iteration = 0;
				}
				
				if(puntaje == 18)
				{
					ImageIcon imagen = new ImageIcon(getClass().getResource("/imagenes/pika.png"));
					JOptionPane.showMessageDialog(null, "Has Ganado", "Resultado", JOptionPane.DEFAULT_OPTION, imagen);
					
					
					dispose();
					
					CloseWindow closeWindow = new CloseWindow();
					closeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
			}
			/*
			 * Handle event when source is a Timer
			 */
			else if(evento.getSource() instanceof Timer)
			{
				if(evento.getSource() == timerPush)
				{
					updateButtons();
				}
				if(evento.getSource() == timerStart)
				{
					firstSight();
				}
			}
			
		}
		
	 }

}
