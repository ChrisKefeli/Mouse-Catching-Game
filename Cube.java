import javax.swing.JButton;
import javax.swing.ImageIcon;

/********************************************************************
 * The Cube is a type of JButton that represents a cube in the game
*********************************************************************/

public class Cube extends JButton {

	private int row;
	private int column;
	private static int type;

    /**
     * Constructor method initializing the row, column and type of the cube;
	 */
    public Cube(int row, int column, int type) {
		this.row = row;
		this.column = column;
		this.type = type;

		this.setType(this.type);
		this.setBorderPainted(false);
    }

	/*this is a getter method
	*it is used to set type of a square and this icon of the square*/
    public void setType(int type) {
		if (type == 0){
			this.type = 0;
			this.setIcon(new ImageIcon("square-0.png"));

		} else if (type == 1){
			this.type = 1;
			this.setIcon(new ImageIcon("square-1.png"));

		} else {
			this.type = 2;
			this.setIcon(new ImageIcon("square-2.png"));
		}
    }

	/*this is a getter method
	*it is used to get the row
	*@return int This returns the row*/
    public int getRow() {
		return this.row;
    }

	/*this is a getter method
	*it is used to get the column
	*@return int This returns the column*/
    public int getColumn() {
		return this.column;
    }
}
