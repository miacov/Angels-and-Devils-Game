import java.util.Scanner;

/**
* This class let's the user choose which way to play the game Angel and Devils.
*
* This class presents the user with a choice of which way to play the game Angel and Devils, after initializing the
* board size and power of angel. The 2 modes include user vs. user and automated gameplay, both played from the 
* standalone classes for playing the game PlayADUser and PlayADAutomated.
*
* @author Marios Iacovou
* @since 05/03/2019 
**/

public class PlayAD {
	
	/** 
	 * Method to get ChessBoard size from user
	 * 
	 * This method asks the user for the size of the board to play on. If the user gives a wrong
	 * value then the program terminates with an error message.
	 * 
	 * @return Returns the size of the ChessBoard
	 */
	public static int getInitialSize() {
		int size = 0;
		Scanner sc = new Scanner(System.in);
		System.out.print("Size of the board ? ");
		if(sc.hasNextInt()) {
			size = sc.nextInt();
		}
		else {
			System.out.println("Error: Value is not integer!");
			System.exit(1);
		}
		return size;
	}
	
	/** 
	 * Method to get Angel's power from user
	 * 
	 * This method asks the user for the power of the angel in the game. If the user gives a wrong
	 * value then the program terminates with an error message.
	 * 
	 * @return Returns the power of the Angel
	 */
	public static int getInitialPower() {
		int power = 0;
		Scanner sc = new Scanner(System.in);
		System.out.print("Power of Angel ? ");
		if(sc.hasNextInt()) {
			power = sc.nextInt();
		}
		else {
			System.out.println("Error: Value is not integer!");
			System.exit(1);
		}
		return power;
	}
	
	/** 
	 * Method to get Angel's power from user
	 * 
	 * This method asks the user for the power of the angel in the game. If the user gives a wrong
	 * value then the program terminates with an error message.
	 * 
	 * @return Returns the power of the Angel
	 */
	public static int getChoice() {
		int choice = 0;
		Scanner sc = new Scanner(System.in);
		System.out.print("Choice 1 or 2 ? ");
		if(sc.hasNextInt()) {
			choice = sc.nextInt();
		}
		else {
			System.out.println("Error: Wrong choice!");
			System.exit(1);
		}
		return choice;
	}
	
	/**
	 * Main method used to play the game.
	 *
	 * The user gets the choice of manual or automated game and then main calls the play method of the user's choice
	 * from classes PlayADAutomated or PlayADUser to play.
	 * 
	 * @param args Not used
	 * @throws InterruptedException for Thread.sleep(ms) command in ComputerPlayAD's play method
	 */	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("The Angel game.");
		System.out.println("\nChoose playing style:");
		System.out.println("1 : User vs User");
		System.out.println("2 : Automated");	
		int choice = getChoice();
		System.out.println();
		
		int size = getInitialSize();		
		int power = getInitialPower();
			
		if (choice == 1) {
			System.out.println("\nAngel game: User vs. User");
			PlayADUser.play(size, power);
		}
		else if (choice == 2) {
			System.out.println("\nAngel game: Automated");
			PlayADAutomated.play(size, power);
		}
		else {
			System.out.println("Error: This choice doesn't exist!");
			System.exit(-1);
		}
	
	}

}
