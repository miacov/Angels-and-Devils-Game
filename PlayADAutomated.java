/**
* This class let's the computer play the Angels and Devils game
*
* In the game, the goal of AI 1, playing as the Angel, is to navigate to the corners of the created ChessBoard (using class AutoChessBoard).
* Its antagonist, AI 2, controls the Devils. Each round a new demon is placed on the board. The goal of AI 2 is to surround the
* angel, leaving him no available points to travel to, or block out the escapes.
* 
* Strategies used explained:
* Note: The Angel has an advantage in a finite board so the angel is expected to be found victorious almost every
* 		round the game is played!
* Angel's strategy:
* - The angel moves to an escape whenever an escape point is accessible to it.
* - The angel moves in points not accessible to him in his previous position, in order
*   to help reduce backtracking.
* - The angel makes a random move in an accessible position, whenever his previous position
* 	is the same as the current (stayed in same spot) or when he's on his first move.
* 
* Devils's strategy:
* - Blocks out a point in one of the edges of the board, according to the Angel's current direction.
*   If that edge of the board is filled with demons already, the devil blocks a position in one of the
*   angel's currently accessible points. 
* 	
* @author Marios Iacovou
* @since 05/03/2019 
**/
public class PlayADAutomated {
	
	/**
	 * Method used to play the game Angels and Devils in Automated mode
	 * 
	 * This method controls the entire game.
	 * 
	 * This is an automated version of the Angel and Devils game.
	 * Each round the AI controlling the angel or the devils tries to position its character accordingly to win
	 * the game using methods from AutoChessBoard. The game is won when any of the 2 parties succeeds in their goal noted in the class'
	 * description above.
	 * This method is also used by PlayGame if the user chooses to play in Automated mode.
	 * 
	 * @param size Size of the ChessBoard
	 * @param power Power of the Angel
	 * @throws InterruptedException for Thread.sleep(ms) command
	 */
	public static void play(int size, int power) throws InterruptedException {
		AutoChessBoard a_cb = new AutoChessBoard(size,power);
		System.out.println("Begin the game...\n");
		
		Point move;
		
		a_cb.print();
		
		boolean game_end = false; // Game doesn't end until one side wins
		
		while(!game_end) {		
			// Angel's move
			System.out.println();
			Thread.sleep(3000);
			
			move = a_cb.findAngelMove();
			a_cb.moveAngel(move);
			
			System.out.println("Angel moves to: " + move);		
			a_cb.print();
			System.out.println();			
			
			if (a_cb.angelAtEdge()) {
				System.out.println("Angel wins.");
				game_end = true;
			}
			
			// Devil's move
			if (!game_end) {
				Thread.sleep(3000);
				
				move = a_cb.findDevilMove();
				a_cb.placeDemon(move);
				
				System.out.println("Devil is placed at: " + move);
				a_cb.print();
				
				if (a_cb.countAccessible("Angel") == 1 || a_cb.countOpenEscapes() == 0) {
					System.out.println();
					System.out.println("Devil wins.");
					game_end = true;
				}	
			}
		}
		
		System.out.println("End of game.");	
	}
		
	
	/**
	 * Standalone main method used to play the game Angels and Devils in Automated mode.
	 * 
	 * When the game starts the user is asked to give the size of the board
	 * as well as the power of the angel that will be used by the computer to play. 
	 * Wrong values are marked with errors. 
	 * After the game is initialized correctly, the function play is called to automate the game.
	 * 
	 * @param args Not used
	 * @throws InterruptedException for Thread.sleep(ms) command
	 */
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Angel game: Automated");
		int size = PlayAD.getInitialSize();		
		int power = PlayAD.getInitialPower();
		play(size,power);
	}

}
