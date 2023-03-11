public class Point {

	private static int xaxis;
	private static int yaxis;

    /**
	*Constructor initializes the x and y coordinates of the point
    *@param x This is the x coodinate
	*@param y This is the y coordinate
    */
    public Point(int x, int y){
		this.xaxis = x;
		this.yaxis = y;
	}

	/*this is a getter method
	*it is used to get the X coordinate
	*@return int This returns the X coordinate*/
    public int getX(){
		return xaxis;
    }

	/*this is a getter method
	*it is used to get the Y coordinate
	*@return int This returns the Y coordinate*/
    public int getY(){
		return yaxis;
    }

	/*this is a getter method
	*it is used to set the values of instance variables x and y*/
    public void reset(int x, int y){
		this.xaxis = x;
		this.yaxis = y;
	}
 }
