import javax.swing.*;
import java.awt.*;

 /*the class GameUserInterface provides the user interface of the Game. It extends
 *JFrame and lays out an instance of BoardUserInterface and two instances of JButton.
 *One to reset and the second the quit the game at any time.*/

public class GameUserInterface extends JFrame {

	private JButton reset;
	private JButton quit;
	private GameState state;
	private GameLogic gameLogic;
	public static BoardUserInterface boardUserInterface;

    /**
	*Constructor initializes the board state and game's logic
	*@param GameState The initial state of the game and the board
	*@param GameLogic The logic behind the game that decides what to do
    */
    public GameUserInterface(GameState state, GameLogic gameLogic) {
		this.state = state;
		this.gameLogic = gameLogic;

		//Assigns attributes to the JFrame
		this.setTitle("Game - Catch the Mouse!");
		this.setBackground(Color.white);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		//Initializes the board
		boardUserInterface = this.getBoardUserInterface();
		this.add(boardUserInterface, BorderLayout.CENTER);

		// Create a JPanel for the reset and quit buttons and add it to the JFrame
		reset = new JButton("Reset");
		quit = new JButton("Quit");
		JPanel controlPanel = new JPanel();
		controlPanel.add(reset);
		controlPanel.add(quit);
		controlPanel.setBackground(Color.white);
		controlPanel.setBorder(BorderFactory.createEmptyBorder());
		this.add(controlPanel, BorderLayout.SOUTH);

		this.pack();
		this.setVisible(true);
    }

	/*this is a getter method
	*it is used to get the reset button
	*@return JButton this returns the reset button*/
	public JButton getReset(){
		return this.reset;
	}

	/*this is a getter method
	*it is used to get the quit button
	*@return JButton this returns the quit button*/
	public JButton getQuit(){
		return this.quit;
	}

	/*this is a getter method
	*it is used to get the BoardUserInterface to be used in the constructor
	*@return BoardUserInterface this returns the BoardUserInterface*/
    public BoardUserInterface getBoardUserInterface(){
		BoardUserInterface boardUserInterface = new BoardUserInterface(this.state, this.gameLogic);
		return boardUserInterface;
   }
}
