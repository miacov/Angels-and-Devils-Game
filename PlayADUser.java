import java.util.Scanner;

/**
* This class let's the user play the game Angels and Devils.
*
* The game is played from the play method.
* In the game, the goal of player 1, playing as the Angel, is to navigate to the corners of the created ChessBoard (using class GameChessBoard).
* Its antagonist, player 2, controls the Devils. Each round a new demon is placed on the board. The goal of player 2 is to surround the
* angel, leaving him no available points to travel to, or block out the escapes
*
* @author Marios Iacovou
* @since 04/03/2019 
**/
public class PlayADUser {
	
	/** 
	 * Method to get Angel's or Devil's coordinate from user.
	 * 
	 * This method keeps repeating the instruction to the user to give the value of Angel's cord.
	 * or Devil's cord. until a valid integer is given or a negative value is given and the program terminates.
	 * 
	 * @param c String indicates whether it's x or y coordinate
	 * @param s String indicates which coordinate, Angel's or Devil's
	 * @return Returns the user's Angel's or Devil's x choice accordingly
	 */
	public static int getCoord(char c, String s) {
		int cord = 0;
		Scanner sc = new Scanner(System.in);
		System.out.print(c + " ? " + s + " = ");
		while (!sc.hasNextInt()) {
			System.out.println("Error: Value is not integer!");
			System.out.print(c + " ? " + s + " = ");
			sc.next();
		}
		cord = sc.nextInt();
		if (cord < 0) {
			System.out.println("User entered negative value. Ending game...");
			System.exit(0);
		}
		return cord;	
	}
	
	/**
	 * Method used to play the game Angels and Devils in User vs. User mode.
	 * 
	 * This method controls the entire game.
	 * The user is asked to give the coordinates to which the Angel will move. Wrong coordinates here result in an error message and the user
	 * is require to reenter the values. If the user indicates negative values the program terminates. After the user is done with the angel
	 * the other player will now make his move as the devil. The game is won when any of the 2 parties succeeds in their goal noted in the class'
	 * description above.
	 * This method is also used by PlayGame if the user chooses to play in User vs. User mode.
	 * 
	 * @param size Size of the ChessBoard
	 * @param power Power of the Angel
	 */
	public static void play(int size, int power) {
		GameChessBoard cb = new GameChessBoard(size,power);
		System.out.println("Begin the game...\n");
		
		int x,y; // Used as coordinates
		
		cb.print();
		
		boolean game_end = false; // Game doesn't end until one side wins
		
		while(!game_end) {
			// Angel's move
			System.out.println();
			
			x = getCoord('x', "Angel");
			y = getCoord('y', "Angel");
			
			while(!cb.isAccessible(x, y, "Angel")) {
				System.out.println("Error: inaccessible square.");
				x = getCoord('x', "Angel");
				y = getCoord('y', "Angel");
			}
			cb.moveAngel(x, y);
			System.out.println(new Point(x,y));
			cb.print();
			System.out.println();
			
			// If the Angel touches the corners then the game ends with the Angel victorious. 
			if (cb.angelAtEdge()) {
				System.out.println("Angel wins.");
				game_end = true;
			}
			
			// Devil's move
			if (!game_end) {
				x = getCoord('x', "Devil");
				y = getCoord('y', "Devil");
			
				while(!cb.isAccessible(x, y, "Devil")) {
					System.out.println("Error: inaccessible square.");
					x = getCoord('x', "Devil");
					y = getCoord('y', "Devil");
				}	
				cb.placeDemon(x, y);
				System.out.println(new Point(x, y));
				cb.print();
				
				// If the Angel has no other accessible points or escapes are blocked then the game ends with the Devil victorious.
				if (cb.countAccessible("Angel") == 1 || cb.countOpenEscapes() == 0) {
					System.out.println();
					System.out.println("Devil wins.");
					game_end = true;
				}	
			}
		}
		
		System.out.println("End of game.");	
	}

	/**
	 * Standalone main method used to play the game Angels and Devils in User vs. User mode.
	 * 
	 * When the game starts the user is asked to give the size of the board he's creating,
	 * as well as the power of the angel. Wrong values are marked with errors. 
	 * After the game is initialized correctly, the function play is called to play the game.
	 * 
	 * @param args Not used
	 */
	public static void main(String[] args) {
		System.out.println("Angel game: User vs. User");
		int size = PlayAD.getInitialSize();		
		int power = PlayAD.getInitialPower();
		play(size,power);
	}

}
