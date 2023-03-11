import java.util.Random;

public class GameState {

	public static final int FREE_CUBE = 0;
	public static final int SELECTED = 1;
	public static final int RED_CUBE = 2;
	public static final int MAX_SELECTED = 5;

	private int boardSize;
	private Point redCube;

	private static Random aRandom = new Random();
	private static final int min = 0;
	private static final int max = 1;
	public static int[][] matrix;
	private static int lowerBound;
	private static int upperBound;
	private static int randomIntx;
	private static int randomInty;
	private static int newNumberx;
	private static int newNumbery;
	private static int randomInteger;
	private static boolean done;
	private static int randomInti;
	private static int randomIntj;

    /**
     * Constructor
	 * initializes the state to the size of board
     *  The parameter size is the size of the board
     */
    public GameState(int size) {
		this.boardSize = size;
		matrix = new int[size][size];

		int counter = 0;

		//this generates and randomly places the snakes on the game board until there are 5 snakes
		done = false;
		while(!done) {
			randomInti = aRandom.nextInt(size);
			randomIntj = aRandom.nextInt(size);
			randomInteger = aRandom.nextInt(9);
			if (randomInteger == 1 && counter < MAX_SELECTED) {
				matrix[randomInti][randomIntj] = randomInteger;
				counter++;
			}else if (counter == MAX_SELECTED) {
				done = true;
			}
		}

		//this makes the rest of the cubes that aren't snakes, grey cubes
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(matrix[i][j] != 1) {
					matrix[i][j] = 0;
				}
			}
		}

		/*this calculates the space that the mouse can spawn in at the start
		*of the game depending on the size of the board */
		if(size % 2 == 0) {
			lowerBound = (size / 2) - 1;
			upperBound = (size/2);
		} else {
			lowerBound = (size / 2) - 1;
			upperBound = (size/2) + 1;
		}

		/*this randomly places the mouse somewhere in the 2x2 or
		*3x3 (depending on the size) square in the centre of the board */
		done = false;
		while(!done) {
			randomIntx = aRandom.nextInt((upperBound-lowerBound)+1)+lowerBound;
			randomInty = aRandom.nextInt((upperBound-lowerBound)+1)+lowerBound;

			if(matrix[randomIntx][randomInty] == 0) {
				this.setCube(randomIntx, randomInty);
				matrix[randomIntx][randomInty] = 2;
				done = true;
			}
		}
    }

	/*this is a getter method
	*it is used to get the size of the board
	*@return int This returns the size of the board*/
    public int getSize(){
		return this.boardSize;
   }

    /**
     * returns the current status (FREE_CUBE, SELECTED or RED_CUBE) of a given cube in the game
     *
     * i is the x coordinate of the cube
     * j is the y coordinate of the cube
     * return the status of the cube at location (i,j)
     */
    public int getCurrentStatus(int i, int j){
		return matrix[i][j];
    }


    /**
     * Sets the status of the cube at coordinate (i,j) to SELECTED
     * i is the x coordinate of the cube
     * j is the y coordinate of the cube
     */
    public void select(int i, int j){
		matrix[i][j] = SELECTED;
    }

    /**
     * Puts the red cube at coordinate (i,j). Clears the previous location
     * of the red cube.
     *
      * i is the x coordinate of the cube
     * j is the y coordinate of the cube
     */
    public void setCube(int i, int j){
		this.redCube = new Point(i, j);
   }

    /* Getter method for the current red cube
     * return the location of the curent red cube
	 *@return Point Conatins the coordinates of the current red cube*/
    public Point getCurrentCube(){
		return redCube;
    }

}
