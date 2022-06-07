/**
* This class creates the object PowerPoint.
*
* The object has a x parameter and y parameter, which it inherits from its superclass Point.
* The K of the PowerPoint is another parameter that is used as the power of the point.
* The superclass Point has all the necessary methods, so this one only has a simple toString extension
* and one K getter.
*
* @author Marios Iacovou
* @since 19/02/2019 
**/
public class PowerPoint extends Point {
		private int K;
		
		/**
		* Default constructor
		*
		* Initializes the attributes with the default values
		**/ 	
		public PowerPoint() {
			super();
			K = 0;
		}
		
		/**
		* Constructor with 3 int values.
		* 
		* This constructor takes in 3 int values, the x, y, and K of a PowerPoint,
		* and creates a PowerPoint using them.
		*
		* @param x The x of the PowerPoint
		* @param y The y of the PowerPoint
		* @param K The K of the PowerPoint
		**/ 	
		public PowerPoint(int x, int y, int K) {	
			super(x,y);
			this.K = K;
		}
		
		/**
		* Copy constructor for PowerPoint
		*
		* @param gp PowerPoint to be copied
		**/ 
		public PowerPoint(PowerPoint gp) {
			super(gp.getX(), gp.getY());
			K = gp.getK();
		}
		
		/**
		* Constructor with 2 int values.
		* 
		* This constructor takes in 2 int values, acting as x and y, and creates
		* a PowerPoint using them. Uses the other constructor and just sets K as 0.
		*
		* @param x The x of the PowerPoint
		* @param y The y of the PowerPoint
		**/ 	
		public PowerPoint(int x, int y) {
			this(x,y,0);
		}
		
		/**
		 * Gives the K of the PowerPoint
		 * 
		 * @return Returns attribute K of the PowerPoint
		 **/
		public int getK() {
			return K;
		}
		
		/**
		 * Prints the PowerPoint in the form x = X y = Y k = K.
		 * 
		 * This method overrides the default toString() method to print a PowerPoint.
		 * It uses the printable Point and expands upon it with K.
		 * 
		 * @return Returns x = X y = Y k = K
		 **/
		public String toString() { 
			return super.toString() + " k = " + K;		
		}

}

