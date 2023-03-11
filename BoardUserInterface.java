import javax.swing.*;
import java.awt.*;

//This class extends JPanel so that it can add the buttons and panels to the frame.
//That allows the user to see the game and interact with it.

public class BoardUserInterface extends JPanel {

	private static GameState gameState;
	private static GameLogic gameLogic;
	public static Cube[][] matrix;
	private static JPanel panelForButtons;

	/**
	*Constructor initializes the game's state and its logic
	*@param GameState The initial state of the game and the board
	*@param GameLogic The logic behind the game that decides what to do
	*/
    public BoardUserInterface(GameState GameState, GameLogic GameLogic) {
		this.gameState = GameState;
		this.gameLogic = gameLogic;

		this.setLayout(new GridLayout(gameState.getSize(), gameState.getSize()));

		matrix = new Cube[gameState.getSize()][gameState.getSize()];

		//Creates the panels for the buttons to be placed on and then they're added to the frame
		for(int i = 0; i < gameState.getSize(); i++) {
			if(i % 2 == 0) {
				panelForButtons = new JPanel(new FlowLayout());
				panelForButtons.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 40));
				panelForButtons.setBackground(Color.white);
			} else {
				panelForButtons = new JPanel(new FlowLayout());
				panelForButtons.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
				panelForButtons.setBackground(Color.white);
				panelForButtons.setBorder(BorderFactory.createEmptyBorder());
			}

			for(int j = 0; j < gameState.getSize(); j++){
				Cube temp = new Cube(i, j, gameState.getCurrentStatus(i, j));
				matrix[i][j] = temp;
				temp.setPreferredSize(new Dimension(40, 40));
                panelForButtons.add(temp);
			}
			// Add the FlowLayout JPanel to the GridLayout
			this.add(panelForButtons);
		}
    }

    //updates the status of the board's cubes instances following the game state
	//calls setType() from the class Cube, as needed.
    public void update(){
		for(int a = 0; a < gameState.getSize(); a++) {
			for(int b = 0; b < gameState.getSize(); b++) {
				matrix[a][b].setType(gameState.getCurrentStatus(a,b));
			}
		}
    }
}
