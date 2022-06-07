/**
* This class creates the object GameChessBoard.
*
* The object has as attributes an integer N which is the size of the board, and a 2D table where all the data
* of the board is stored in, inherited from ChessBoard. The last attribute is the Angel, which is
* a PowerPoint (using class PowerPoint). The angel has a power given by the user, which indicates how far he can travel
* on the board. GameChessBoard has all the main methods that are needed to play the game Angels and Demons in User vs.
* User mode.
*
* @author Marios Iacovou
* @since 04/03/2019 
**/
public class GameChessBoard extends ChessBoard {
	protected PowerPoint Angel;
	
	/**
	* Constructor with board size and angel's power.
	* 
	* This constructor inherits from the constructor of the normal Chessboard without the angel.
	* and also takes in the size of the board and the power of the angel
	* It creates the chessboard table and positions the angel at the center of the board.
	* Constructor terminates if the power of angel less than 0 or is greater than half the size of the board 
	* This chessboard needs to be at least 3 in size, as anything less would result in instant victory
	* of the angel. Error messages are printed accordingly.
	*
	* @param N The size of the board
	* @param K The power of the angel
	**/ 	
	public GameChessBoard(int N, int K) {
		super(N);
		
		if (N <= 2) {
			System.out.println("Error: Size of board is incorrect! Has to be > 2");
			System.exit(1);		
		}	
		if (K <= 0) {
			System.out.println("Error: Power of Angel is incorrect! Has to be >= 1");
			System.exit(1);		
		}	
		if (K > N / 2) {
			System.out.println("Error: Power of Angel is greater than half the size of the board!");
			System.exit(1);		
		}
		
		if (N % 2 == 0)
			Angel = new PowerPoint(N/2 + 1, N/2 + 1, K);
		else
			Angel = new PowerPoint((N+1)/2, (N+1)/2, K);		
		
		changeBlockContent(Angel.x, Angel.y, "A");
	}
	
	/**
	 * Moves the Angel to desired spot using x,y.
	 * 
	 * Method replaces the old angel spot with a + and 
	 * changes the current spot of the angel to show that he's there.
	 * 
	 * @param x New x of Angel
	 * @param y New y of Angel 
	 */
	public void moveAngel (int x, int y) {
		changeBlockContent(Angel.x, Angel.y, "+");	
		
		Angel.setY(y);
		Angel.setX(x);
		
		changeBlockContent(Angel.x, Angel.y, "A");	
	}
	
	/**
	 * Moves the Angel to desired spot using a point.
	 * 
	 * Gets x and y of point and uses other moveAngel method with
	 * x and y to move the angel.
	 * 
	 * @param p New point of Angel
	 */
	public void moveAngel (Point p) {
		moveAngel(p.getX(), p.getY());
	}
	
	/**
	 * Places a demon at the desired spot.
	 * 
	 * The point indicated is replaced with a demon sign.
	 * 
	 * @param x The x of new demon
	 * @param y The y of new demon
	 */
	public void placeDemon (int x, int y) {
		changeBlockContent(x, y, "@");	
	}
	
	/**
	 * Places a demon at the desired spot.
	 * 
	 * Gets x and y of point and uses other placeDemon method
	 * with x and y to place a demon at a spot.

	 * @param p New point of Demon
	 */
	public void placeDemon (Point p) {
		placeDemon(p.getX(), p.getY());
	}
	
	/**
	 * This method determines whether or not the devil or angel can move to a point.
	 * 
	 * The user enters either the Angel or Devil to test the points for. First we find how
	 * many accessible points there are with countAccessible, then we build a table from those points,
	 * with buildAccessibleArray. Then we test the point given by the user to see whether or not it's accessible.
	 * Further details can also be found in the description of the method countAccessible and buildAccessibleArray.
	 * 
	 * @param x The x of the point being tested for accessibility
	 * @param y The y of the point being tested for accessibility
	 * @param CharacterChoice String which determines if we are finding the Angel's or Devil's points
	 * @return returns a boolean to indicate if the given point is indeed available
	 */
	public boolean isAccessible(int x, int y, String CharacterChoice) {
		int accessible = countAccessible(CharacterChoice);
		Point[] a_points = buildAccessibleArray(accessible, CharacterChoice);		
		Point test_point = new Point(x,y);
		
		return test_point.existsInArray(a_points);
	}
	
	/**
	 * This method finds how many accessible points the devil or angel has.
	 * 
	 * The user chooses whether he's finding spots for the devil or angel.
	 * For the angel we create the accessible bounds. He can only move to blocks
	 * |X-x| less or equal to K and |Y-y| less or equal to K that are not blocked by the devil, or are not
	 * out of bounds. He can stay at the same spot as well.
	 * For the devil the accessible spots are all except the one the angel is sitting
	 * on, points he blocked before, and whatever is out of bounds.
	 * This method is helpful to indicate that the game is won by the devil, if the angel
	 * has 1 accessible move (staying still).
	 * 
	 * @param CharacterChoice String which determines if we are finding the Angel's or Devil's points
	 * @return returns how many accessible points are available
	 */
	public int countAccessible(String CharacterChoice) {
		int accessible = 0;
		
		if (CharacterChoice.equals("Angel")) {
			accessible = countAccessible(Angel); // calls angel specific method as it exists
		}
		
		if (CharacterChoice.equals("Devil")) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (Board[i][j].equals("+")) {
						accessible++;
					}
				}
			}			
		}	
		return accessible;	
	}
	
	/**
	 * Finds an angel's amount of accessible points.
	 * 
	 * See description of countAccessible(String CharacterChoice).
	 * Specific method only for angels for expansion reasons.
	 * 
	 * @param A Angel to count
	 * @return returns how many accessible points are available
	 */
	protected int countAccessible(PowerPoint A) {
		int accessible = 0;
		
		int p = A.getK();
		int cx = A.getX();
		int cy = A.getY();
		
		// Accessible Bounds
		int x_up = cx - p;
		if (x_up < 1) x_up = 1;
		int x_down = cx + p;
		if (x_down > N) x_down = N;
		int y_right = cy + p;
		if (y_right > N) y_right = N;
		int y_left = cy - p;	
		if (y_left < 1) y_left = 1;
		
		for (int i = x_up; i <= x_down; i++) {
			for (int j = y_left; j <= y_right; j++) {
				if (Board[i][j].equals("+") || Board[i][j].equals("A")) {
					accessible++;
				}
			}
		}
		return accessible;	
	}
		
	/**
	 * This method builds a table of all accessible points the angel or devil has.
	 * 
	 * Further details can be found in the description of countAccessible, which
	 * this method needs to run.
	 * 
	 * @param accessible How many points were found to be accessible
	 * @param CharacterChoice String which determines if we are building the Angel's or Devil's table
	 * @return returns the table of accessible points
	 */
	protected Point[] buildAccessibleArray (int accessible, String CharacterChoice) {
		Point[] arr = new Point[accessible];
		int counter = 0;
		
		if (CharacterChoice.equals("Angel")) {
			arr = buildAccessibleArray(accessible, Angel); // calls angel specific method as it exists
		}
		
		if (CharacterChoice.equals("Devil")) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (Board[i][j].equals("+")) {
						arr[counter] = new Point(i,j);
						counter++;
					}
				}
			}			
		}	
		return arr;		
	}
	
	/**
	 * Builds an angel's Array of accessible points.
	 * 
	 * See description of buildAccessibleArray(int accessible, String CharacterChoice).
	 * Specific method only for angels for expansion reasons.
	 * 
	 * @param accessible How many points were found to be accessible
	 * @param A Angel being tested 
	 * @return returns the table of accessible points
	 */
	protected Point[] buildAccessibleArray (int accessible, PowerPoint A) {
		Point[] arr = new Point[accessible];
		int counter = 0;
		
		int p = A.getK();
		int cx = A.getX();
		int cy = A.getY();
		
		// Accessible Bounds
		int x_up = cx - p;
		if (x_up < 1) x_up = 1;
		int x_down = cx + p;
		if (x_down > N) x_down = N;
		int y_right = cy + p;
		if (y_right > N) y_right = N;
		int y_left = cy - p;	
		if (y_left < 1) y_left = 1;
				
		for (int i = x_up; i <= x_down; i++) {
			for (int j = y_left; j <= y_right; j++) {
				if (Board[i][j].equals("+") || Board[i][j].equals("A")) {
					arr[counter] = new Point(i,j);
					counter++;
				}
			}
		}	
		return arr;
	}
	
	/**
	 * Counts all open escape points.
	 * 
	 * Checks all edges to find how many escape points are still open.
	 * Also useful to see if the devil blocked out all the escapes and wins the game.
	 * 
	 * @return returns amount of open escapes
	 */
	public int countOpenEscapes() {
		int escapes = 0;
		for (int i = 1; i <= N; i++) {
			if (i == 1 || i == N) {
				for (int j = 1; j <= N; j++) {
					if (Board[i][j].equals("+"))
						escapes++;
				}	
			}
			else {
				if (Board[i][1].equals("+"))
					escapes++;
				if (Board[i][N].equals("+"))
					escapes++;	
			}		
		}
		return escapes;
	}
	
	/**
	 * This method finds out if the angel is at the edges.
	 * 
	 * This method is helpful to indicate that the game is won by the angel.
	 * 
	 * @return Returns whether the angel is at the corners or not
	 */
	public boolean angelAtEdge() {
		int x = Angel.getX();
		int y = Angel.getY();
		return x == N || x == 1 || y == 1 || y == N;
	}
	
					
}
