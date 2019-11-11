/*
* Autores:
*	Santiago Hern�ndez Arias C�digo 1631281
* Nombre: Fbutton.java
* Descripci�n: archivo de implementacion de la clase Fbutton
* Fecha de creaci�n: 6/3/2017
* Fecha de modificaci�n: 6/3/2017
*/

package focus;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

// TODO: Auto-generated Javadoc
/**
 * The Class Fbutton.
 */
public class Fbutton extends JButton {
	
	/** The column. */
	private int id, row, column;
	
	/** The pokemon. */
	private ImageIcon pokemon;
	
	/**
	 * Instantiates a new fbutton.
	 *
	 * @param id the id
	 * @param pokemon the pokemon
	 */
	public Fbutton(int id, ImageIcon pokemon)
	{
		this.setId(id);
		this.setRow(0);
		this.setColumn(0);
		this.pokemon = pokemon;
		this.setPreferredSize(new Dimension(80, 80));
		this.setIcon(pokemon);
		this.setDisabledIcon(pokemon);
		this.setVisible(true);
		this.setEnabled(false);
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Toggle on.
	 */
	public void toggleOn()
	{
		this.setIcon(pokemon);
	}
	
	/**
	 * Toggle off.
	 */
	public void toggleOff()
	{
		ImageIcon imagen = new ImageIcon(getClass().getResource("/imagenes/pball.png"));
		this.setIcon(imagen);
	}
	
	/**
	 * Toggle on off.
	 */
	public void toggleOnOff()
	{
		if(this.getIcon().equals(this.pokemon))
		{
			toggleOff();
		}
		else
		{
			toggleOn();
		}
	}

	/**
	 * Gets the row.
	 *
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Sets the row.
	 *
	 * @param row the new row
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * Gets the column.
	 *
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Sets the column.
	 *
	 * @param column the new column
	 */
	public void setColumn(int column) {
		this.column = column;
	}

}
