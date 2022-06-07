/**
* This class creates the object AutoChessBoard.
*
* The object has as attributes an integer N which is the size of the board, a 2D table where all the data
* of the board is stored in, inherited from ChessBoard, the Angel (inherited from GameChessBoard), which is
* a PowerPoint (using class PowerPoint), and Previous_Angel which is used to have 2 positions saved for the angel, 
* his current in Angel and his previous position in Previous_Angel. This is helpful to play the game automatically.
* AutoChessBoard has all the main methods that are needed to play the game Angels and Demons in Automated mode.
*
* @author Marios Iacovou
* @since 04/03/2019 
**/
public class AutoChessBoard extends GameChessBoard {
	private PowerPoint Previous_Angel = null;

	/**
	* Constructor with board size and angel's power.
	* 
	* This constructor inherits from the constructor of GameChessBoard.
	*
	* @param N The size of the board
	* @param K The power of the angel
	**/
	public AutoChessBoard(int N, int K) {
		super(N, K);
	}
	
	/**
	 * This method calculates the next move for the Angel.
	 * 
	 * There are 3 different scenarios for the angel.
	 * 1) Can escape on this move,
	 * 2) Is not on his first move or stayed in the same position and can't escape yet,
	 * 3) Is on his first move and can't escape yet.
	 * To check if he can escape on this move a method checkForEscape is called.
	 * For scenario 3, a random position is chosen as the angel's next move.
	 * For scenario 2, a position that was not accessible to the previous angel is chosen.
	 * When the next move is calculated then the angel is moved to the new position
	 * and his previous position is given to Previous_Angel.
	 * 
	 * @return Returns the next position for the Angel to go to
	 */
	public Point findAngelMove() {
		Point next_pos = null;
		PowerPoint save_pos = new PowerPoint (Angel); // saves current position to be used as previous later
		int accessible = countAccessible("Angel");
		Point[] a_points = buildAccessibleArray(accessible, "Angel");
		
		boolean found_pos = false;
		// Check if angel can escape on this move
		next_pos = checkForEscape(a_points);
		if (next_pos != null) {
			found_pos = true;
		}
	
		// If angel is on his first move or stayed in same position and can't escape yet
		if ((Previous_Angel == null || Angel.same(Previous_Angel)) && !found_pos){
			next_pos = Point.getRandomPoint(a_points);
			Previous_Angel = save_pos;
		}
		// If angel is not on his first move and can't escape yet
		else if (Previous_Angel != null && !found_pos) {
			int prev_accessible = countAccessible(Previous_Angel);
			Point[] prev_a_points = buildAccessibleArray(prev_accessible, Previous_Angel);		
			next_pos = findNewPos(a_points, prev_a_points);
			Previous_Angel = save_pos;
		}
		
		return next_pos;
	}
	
	/**
	 * Finds an accessible point that doesn't belong in previous points.
	 * 
	 * Checks current accessible points array for the angel in comparison with previous accessible
	 * points (previous angel) to find the new point to move to, that doesn't belong
	 * in the previous points array.
	 * 
	 * @param a_points Array with current accessible points
	 * @param prev_a_points Array with previous angel's accessible points
	 * @return Returns the angel's next point to move to
	 */
	private Point findNewPos(Point[] a_points, Point[] prev_a_points) {
		for (int i = 0; i < a_points.length; i++) {
			if (!a_points[i].existsInArray(prev_a_points)) {
				return a_points[i];			
			}			
		}
		return null;
	}
	
	
	/**
	 * Checks if any open escapes are accessible to the angel on his move.
	 * 
	 * Checks the table with all accessible moves to the angel on his move
	 * in comparison with the table with all accessible edges. If any of the
	 * accessible edges are the same points as accessible points to the angel now
	 * then the angel can move to an escape on his current move.
	 * 
	 * @param a_points Currently accessible points to the angel
	 * @return Returns whether or not any escapes are accessible on this angel's move
	 */
	private Point checkForEscape(Point[] a_points) {	
		int open_escapes = countOpenEscapes();
		Point[] a_edges = buildAccessibleEdgesArray(open_escapes);
		for (int i = 0; i < a_points.length; i++) {
			if (a_points[i].existsInArray(a_edges))
				return a_points[i];
		}		
		return null;
	}
	
	/**
	 * This method builds a table of all open escape points.
	 * 
	 * Helps to see if there are any points for the angel to move to to escape with the next move.
	 * 
	 * @param escapes How many escapes are available
	 * @return returns the table of accessible escape points.
	 */
	private Point[] buildAccessibleEdgesArray(int escapes) {
		Point[] arr = new Point[escapes];
		int counter = 0;
		for (int i = 1; i <= N; i++) {
			if (i == 1 || i == N) {
				for (int j = 1; j <= N; j++) {
					if (Board[i][j].equals("+")) {
						arr[counter] = new Point(i,j);
						counter++;
					}
				}	
			}
			else {
				if (Board[i][1].equals("+")) {			
					arr[counter] = new Point(i,1);
					counter++;
				}
				if (Board[i][N].equals("+")) {
					arr[counter] = new Point(i,N);
					counter++;
				}		
			}		
		}
		return arr;
	}
	
	/**
	 * This method calculates the next move for the Devil.
	 * 
	 * For the devil there are 2 possible moves:
	 * 1) a move in one of the 4 edges of the board according to the angel's direction,
	 * 2) a move in one of the points in angel's accessible range.
	 * Move 1 is chosen almost all the time, except when the edge chosen is filled with
	 * demons. In that case, move 2 is made.
	 * To find the angel's direction, the method findAngelDirection.
	 * To find the new position for a demon, findNewPos is called.
	 * 
	 * @return Returns the new position for a demon to be placed
	 */
	public Point findDevilMove() {
		String angel_direction = findAngelDirection();
		return findNewPos(angel_direction);
	}
	
	/**
	 * Finds the new position for a demon.
	 * 
	 * Using a string that indicates the angel's direction, a point is chosen for 
	 * a demon to be placed at one of the 4 edges. (Also checks not to place a demon
	 * on top of a previous demon)
	 * If the side indicated is filled with demons, then the new point will be one in the angel's
	 * accessible points range. (Also checks not to place a demon on top of the Angel).
	 * 
	 * @param direction Angel's direction
	 * @return Returns the point for the demon to be placed
	 */
	private Point findNewPos(String direction) {
		int random_num = randomize (1, N);
		
		// Move in angel's accessible points range
		if (devilBlockedSide(direction)) {
			int accessible = countAccessible("Angel");
			Point[] a_points = buildAccessibleArray(accessible,"Angel");
			Point p = Point.getRandomPoint(a_points);
			while (p.same(Angel))
				p = Point.getRandomPoint(a_points);
			return p;
		}
		
		// Move in edges
		if (direction.equals("Up")) {
			while (Board[1][random_num].equals("@")) {
				random_num = randomize(1,N);
			}
			return new Point(1,random_num);
		}
		
		else if (direction.equals("Down")) {
			while (Board[N][random_num].equals("@")) {
				random_num = randomize(1,N);
			}
			return new Point(N,random_num);
		}
		
		else if (direction.equals("Left")) {
			while (Board[random_num][1].equals("@")) {
				random_num = randomize(1,N);
			}
			return new Point(random_num, 1);
		}
		
		else {
			while (Board[random_num][N].equals("@")) {
				random_num = randomize(1,N);
			}
			return new Point(random_num, N);
		}
	}	
	
	/**
	 * Tests to see if a side of the board is completely blocked by the Devil.
	 * 
	 * According to the choice of side (the string), this method checks to see
	 * if that side is completely blocked by the devil by looking for "+"'s.
	 * Helpful when deciding where the automated devil will place his next demon.
	 * 
	 * @param direction Side of board being tested
	 * @return Returns whether or not side s is blocked
	 */
	private Boolean devilBlockedSide(String direction) {
		if (direction.equals("Up")) {
			for (int j = 1; j <= N; j++) {
				if (Board[1][j].equals("+"))
					return false;
			}
		}	
		
		else if (direction.equals("Down")) {
			for (int j = 1; j <= N; j++) {
				if (Board[N][j].equals("+"))
					return false;
			}
		}
		
		else if (direction.equals("Left")) {
			for (int i = 1; i <= N; i++) {
				if (Board[i][1].equals("+"))
					return false;
			}
		}
		
		else if (direction.equals("Right")) {
			for (int i = 1; i <= N; i++) {
				if (Board[i][N].equals("+"))
					return false;
			}
		}
		
		// Side is not blocked
		return true;
	}
	
	/**
	 * Finds which direction the angel is currently facing.
	 * 
	 * To find the angel's direction the board is cut into 4 imaginary rectangles / squares.
	 * According to each square one of two possible directions are chosen based on
	 * which edge line the square is touching.
	 * - The first square is the Right and Down square, which covers the area from the center of the
	 *   board, diagonally to the South-East corner.
	 * - The second square is the Left and Down square (to the left of the first one), which covers 
	 *   the area from the point at the left of the center of the board, diagonally to the South-West corner.
	 * - The third square is the Right and Up square (above the first one), which covers the
	 *   area from the point above the center of the board, diagonally to the North-East corner.
	 * - The fourth square is the Left and Up square (above the second one), which covers the
	 * 	 area from the point above and left the center of board, diagonally to the North-West corner.  
	 * One exception to this is when the angel stays at the same position for his first move on
	 * an odd board, which then we have all four directions as possible.
	 * To get a result, we randomize a range of numbers using the randomize method.
	 * 
	 * @return Returns a string with the angel's direction, Left, Up, Right, or Down
	 */
	private String findAngelDirection() {
		// find board center
		int board_center;
		if (N % 2 == 0)
			board_center = N/2 + 1;
		else
			board_center = (N+1) / 2;
		
		int random_num;
	
		// Angel stayed in center first move and board is odd
		if (Angel.x == board_center && Angel.y == board_center && board_center == (N+1) / 2) {
			random_num = randomize(0,3);
			if (random_num == 0)
				return "Left";
			else if (random_num == 1)
				return "Up";
			else if (random_num == 2)
				return "Right";
			else
				return "Down";
		}
		
		// First square, Right and Down
		if (Angel.x >= board_center && Angel.y >= board_center) {
			random_num = randomize(0,2);
			if (random_num == 0)
				return "Right";
			else
				return "Down";	
		}
		
		// Second square, Left and Down
		if (Angel.x >= board_center && Angel.y < board_center) {
			random_num = randomize(0,2);
			if (random_num == 0)
				return "Left";
			else
				return "Down";	
		}
		
		// Third square, Right and Up
		if (Angel.x < board_center && Angel.y >= board_center) {
			random_num = randomize(0,1);
			if (random_num == 0)
				return "Right";
			else
				return "Up";	
		}
		
		// Fourth square, Left and Up
		if (Angel.x < board_center && Angel.y < board_center) {
			random_num = randomize(0,1);
			if (random_num == 0)
				return "Left";
			else
				return "Up";	
		}
		return "";
	}
	
	/**
	 * Gives a random integer in a range
	 * 
	 * Useful to make decisions with multiple possible answers, such as
	 * finding an angel's direction from his current position.
	 * 
	 * @param min Minimum value in range
	 * @param max Maximum value in range
	 * @return Returns an integer in range [min, max]
	 */	
	private static int randomize(int min, int max) {
		return (int)(Math.random()*((max - min) + 1)) + min;	
	}
	
}
