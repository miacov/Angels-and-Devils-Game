/**
* This class creates the object Point.
*
* The object has a x parameter and y parameter, which act as coordinates in a yy' and xx' axis system (reverse from traditional).
* The class has several functions to compare Points and print them.
*
* @author Marios Iacovou
* @since 19/02/2019 
**/
public class Point {
		protected int x;
		protected int y;
		
		/**
		* Default constructor
		*
		* Initializes the attributes with the default values
		**/ 	
		public Point() {
			x = 0;
			y = 0;
		}
		
		/**
		* Constructor with 2 int values.
		* 
		* This constructor takes in 2 int values, acting as x and y, and creates
		* a Point using them.
		*
		* @param x The x of the Point
		* @param y The y of the Point
		**/ 	
		public Point(int x, int y) {
			this.x = x;	
			this.y = y;
		}
		
		/**
		* Copy constructor for Point
		*
		* @param p Point to be copied
		**/ 
		public Point(Point p) {
			x = p.getX();
			y = p.getY();
		}
				
		/**
		 * Moves a Point.
		 * 
		 * This method moves the point to the desired y and x given.
		 * 
		 * @param x New x of Point
		 * @param y New y of Point
		 **/
		public void move(int x, int y) {
			this.x = new Integer(x);
			this.y = new Integer(y);
		}
		
		/**
		 * Gives the x of the Point
		 * 
		 * @return Returns attribute x of the Point
		 **/
		public int getX() {
			return x;
		}
		
		/**
		 * Gives the y of the Point
		 * 
		 * @return Returns attribute y of the Point
		 **/
		public int getY() {
			return y;
		}
		
		/**
		 * Sets the x of the Point
		 * 
		 * @param x Desired x
		 **/
		public void setX(int x) {
			this.x = new Integer(x);
		}
		
		/**
		 * Sets the y of the Point
		 * 
		 * @param y Desired y
		 **/
		public void setY(int y) {
			this.y = new Integer(y);
		}
		
		/**
		 * Prints the Point in the form x = X y = Y.
		 * 
		 * This method overrides the default toString() method to print a Point.
		 * 
		 * @return Returns x = X y = Y
		 **/
		public String toString() { 
			return "x = " + x + " y = " + y;		
		}
		
		/**
		 * Compares 2 Points in x and y.
		 * 
		 * This method tests if 2 Points are exactly the same.
		 * They must have the same x and y.
		 * 
		 * @param other The Point in comparison
		 * @return Returns a boolean value of whether the 2 Points are identical
		 **/		
		public boolean same(Point other) {
			return (this.x == other.x && this.y == other.y);
		}
		
		/**
		 * Checks if a point exists in an array.
		 * 
		 * Goes through an array to see if a point matches a point in the array.
		 * 
		 * @param arr Array to be checked for matches
		 * @return Returns a boolean value of whether or not the point exist in the array
		 **/
		public boolean existsInArray(Point[] arr) {
			for (int i = 0; i < arr.length; i++) {
				if (same(arr[i])) {
					return true;
				}		
			}	
			return false;
		}
		
		/**
		 * Gets random Point from an array of points
		 * 
		 * @param arr Array of points
		 * @return random point from array
		 */
		public static Point getRandomPoint(Point[] arr) {
			int random_num = (int)(Math.random()*((arr.length - 1 - 0) + 1)) + 0;
			return arr[random_num];
		}
		
		/**
		 * Compares 2 Points in x.
		 * 
		 * This method tests if this Point has a greater x than the other.
		 * 
		 * @param other The Point in comparison
		 * @return Returns a boolean value of whether this Point is greater in x than the other
		 **/
		public boolean greaterInX(Point other) {
			return this.x > other.x;
		}
		
		/**
		 * Compares 2 Points in y.
		 * 
		 * This method tests if Point 1 has a greater y than 2.
		 * 
		 * @param other The Point in comparison
		 * @return Returns a boolean value of whether this Point is greater in y than the other
		 **/
		public boolean greaterInY(Point other) {
			return this.y > other.y;
		}

}

