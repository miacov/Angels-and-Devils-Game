/**
* This class creates the object ChessBoard.
*
* The object has as attributes an integer N which is the size of the board, and a 2D table where all the data
* of the board is stored in (also is visual info for the user playing). ChessBoard has all the main methods that are needed to 
* build present a ChessBoard to the user.
*
* @author Marios Iacovou
* @since 04/03/2019 
**/
public class ChessBoard {
	protected int N;
	protected String[][] Board;
	
	/**
	* Constructor with ChessBoard size.
	* 
	* This constructor takes in the size of the board and creates the chessboard table.
	* If the size isn't at least 1 then the constructor terminates, as it's not really a
	* usable board.
	*
	* @param N The size of the board
	**/ 	
	public ChessBoard(int N) {
		if (N <= 0) {
			System.out.println("Error: Size of board is incorrect! Has to be > 0");
			System.exit(1);		
		}
		
		this.N = N;
		
		Board = new String[N+2][N+2];
		for (int i = 0; i <= N + 1; i++) {			
			for (int j = 0; j <= N + 1; j++) {			
				if ((i == 0 || i == N + 1) && j >= 1 && j <= N) {
					Board[i][j] = Integer.toString(j);
				}				
				else if (i >= 1 && i <= N && (j == 0 || j == N + 1)) {
					Board[i][j] = Integer.toString(i);
				}			
				else if ((i != 0 && i != N + 1 && j != 0 && j != N + 1)) {
					Board[i][j] = "+";
				}
				else 
					Board[i][j] = " ";			
			}	
		}
	}
	
	/**
	* Method to print the ChessBoard.
	* 
	* This method prints the ChessBoard in a way the user can see the data useful to him.
	* The y and x coordinates are printed around the board, empty positions are shown as +.
	* This is inherited by GameChessBoard. If a GameChessBoard is printed, then the position of
	* the angel is marked with an A, and where the demon points are there is an @ symbol.
	**/ 	
	public void print() {	
		int max_length = Board[0][N].length();
		
		for (int i = 0; i <= N + 1; i++) {			
			for (int j = 0; j <= N + 1; j++) {
				int current_length = Board[i][j].length();
				System.out.print(Board[i][j]);
				// To be aligned on the first digit of the outside numbers
				for (int k = 0; k < max_length - current_length; k++) {
					System.out.print(" ");
				}
				System.out.print("  ");
			}
			System.out.println();
		}
	}
	
	/**
	 * Gives the chessboard
	 * 
	 * @return Returns chessboard data table
	 */
	public String[][] getBoard() {
		return Board;
	}
	
	/**
	 * Gives the chessboard size
	 * 
	 * @return Returns size of chessboard
	 */
	public int getSize() {
		return N;
	}
	
	/**
	 * Returns the content of a chessboard block.
	 * 
	 * @param x content location's x in board
	 * @param y content location's y in board
	 * @return Returns string of chessboard block.
	 */
	public String getBlockContent(int x, int y) {
		return Board[x][y];
	}
	
	/**
	 * Changes the content of a chessboard block.
	 * 
	 * @param x x of block
	 * @param y y of block
	 * @param s new content of block
	 */
	public void changeBlockContent(int x, int y, String s) {
		Board[x][y] = s;
	}
				
}

