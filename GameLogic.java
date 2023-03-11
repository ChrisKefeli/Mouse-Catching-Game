import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.util.*;

//This class implements the interface ActionListener so that it is called when the player makes a move.
//It calculates the next step of the game
//It updates state and userInterface.

public class GameLogic implements ActionListener {

	private static int size;
	private static GameUserInterface gameUserInterface;
	private static GameState gameState;
	private static BoardUserInterface boardUserInterface;
	private static Cube[][] cubeMatrix;
	private static int[][] intMatrix;
	private static JFrame f;
	private static Random aRandom = new Random();

	/**
	*Constructor method initializing the size.
	*@param size The size of the board that the game will be played on
	*
	*It also creates the game's userInterface, the game's state instances,
	*the game's userInterface, the Matrix that contains all the cubes,
	*The matrix that contains ints and the quit plus reset buttons
	*/
    public GameLogic(int size) {
		this.size = size;
		this.gameState = new GameState(size);
		this.gameUserInterface = new GameUserInterface(gameState, this);

		//Adds ActionListeners to the quit and reset buttons
		gameUserInterface.getReset().addActionListener(this);
		gameUserInterface.getQuit().addActionListener(this);

		this.boardUserInterface = gameUserInterface.boardUserInterface;
		this.cubeMatrix = boardUserInterface.matrix;
		this.intMatrix = gameState.matrix;
    }

    /**
	*This method starts the game by adding ActionListeners the all the cubes*/
    public void start(){
		for(int i = 0; i < cubeMatrix.length; i++){
			for(int j = 0; j < cubeMatrix.length; j++) {
				cubeMatrix[i][j].addActionListener(this);
			}
		}
    }

    /**
	*This method resets the game by closing the frame and starting
	*up a new instance of the game
	*/
    public void reset(){
		gameUserInterface.dispose();
		new GameLogic(size);
		this.start();
    }

	/**
	*This method handles all the logic in the game and decides what to do next depending on the @param e
	*@param e This is the ActionEvent that occurs when the user clicks a button in the game
	*/
    public void actionPerformed(ActionEvent e) {

		//if the Quit button was pressed it closes the game
		if(e.getSource() == gameUserInterface.getQuit()) {
			System.exit(0);

		/*if a Cube was pressed meaning the game is being played
		*1 of 3 things may happen
		*First, if a grey cube was pressed and it's next to a snake it turns
		*the grey cube into a snake, generates a new location for the mouse to move too
		*and updates the board*/
		} else if(e.getSource() instanceof Cube) {
			Cube clicked = (Cube) e.getSource();

				for(int x = 0; x < size; x++) {
					for(int y = 0; y < size; y++) {
						cubeMatrix[x][y].setBorderPainted(false);
					}
				}
				clicked.setBorderPainted(true);

				if(intMatrix[clicked.getRow()][clicked.getColumn()] == 0) {
					if(this.check(clicked) == true) {
						intMatrix[gameState.getCurrentCube().getX()][gameState.getCurrentCube().getY()] = gameState.FREE_CUBE;
						gameState.select(clicked.getRow(), clicked.getColumn());
						moveMouse();
						boardUserInterface.update();

						/*Second, if the mouse has moved to the edge of the board, it displays a message saying you lost*/
						if(gameState.getCurrentCube().getX() == 0 || gameState.getCurrentCube().getX() == size - 1 || gameState.getCurrentCube().getY() == 0 || gameState.getCurrentCube().getY() == size - 1) {
							f = new JFrame();
							JOptionPane.showMessageDialog(f,"You lost the game. Nice try though.");
							this.reset();
						}
					}
				/*Finally, if the mouse was clicked and it is next to a snake, it displys a message saying you win*/
				} else if(intMatrix[clicked.getRow()][clicked.getColumn()] == 2) {
					if(this.check(clicked) == true) {
						f = new JFrame();
						JOptionPane.showMessageDialog(f,"You win the game!");
						this.reset();
					}
				}
		//if the reset button was clicked it resets the game
		} else if(e.getSource() == gameUserInterface.getReset()) {
			this.reset();
		}
    }

	/**
	*This method checks if the cube pressed is next to a snake and returns true if it was
	*@param cube This the cube that was pressed and looks around it to see if there was a snake nearby
	*/
	public boolean check(Cube cube){
		int i = cube.getRow();
		int j = cube.getColumn();

		for (int x = Math.max(0, i - 1); x <= Math.min(i + 1, intMatrix.length); x++) {
			for (int y = Math.max(0, j - 1); y <= Math.min(j + 1, intMatrix[i].length); y++) {
				if (x >= 0 && y >= 0 && x < intMatrix.length && y < intMatrix[i].length) {
					if(x!=i || y!=j){
						if(intMatrix[x][y] == 1) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	*This method moves the mouse to a random location other
	*than a snake and where it where it currently is.
	*/
	public void moveMouse() {
		while(true) {
			int randomNumberX = aRandom.nextInt(size - 0)+0;
			int randomNumberY = aRandom.nextInt(size - 0)+0;

			if(intMatrix[randomNumberX][randomNumberY] != 1 && intMatrix[randomNumberX][randomNumberY] != 2) {
				gameState.setCube(randomNumberX, randomNumberY);
				intMatrix[randomNumberX][randomNumberY] = gameState.RED_CUBE;
				break;
			}
		}
	}
}
