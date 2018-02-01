/*
 * Project10.java
 *
 *   A program that plays and scores a round of the game Poker Dice.  In this game,
 *   five dice are rolled.  The player is allowed to select a number of those five dice
 *   to re-roll.  The dice are re-rolled and then scored as if they were a poker hand.  
 *   The following hands MUST be scored in this assignment:
 *      * High card
 *      * One Pair
 *      * Two Pair
 *      * Three of a Kind
 *      * Full House
 *      * Four of a Kind
 *      * Five of a Kind
 *   For extra credit, you may also implement:
 *      * Straight
 *
 * @author ENTER YOUR NAME HERE
 *
 */
package osu.cse1223;

import java.util.Arrays;
import java.util.Scanner;

public class Yeah {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		int[] dice = new int[5];// size of the dice being rolled
		String output = "";
		String finalOutput = "";
		boolean check = true;
		while (check) {
			resetDice(dice); // makes all the dice zero
			rollDice(dice); // generates values of dice
			output = diceToString(dice);
			System.out.println("Your current dice is: " + output);
			promptForReroll(dice, keyboard);// tells which dice should be rerolled
			System.out.println("Keeping the remaining dice. . . ");
			System.out.println("Rerolling. . . ");
			rollDice(dice);
			finalOutput = diceToString(dice);
			System.out.println("Your final dice: " + finalOutput);
			System.out.println(getResult(dice)); // this method tells the score you got from the dice being played
			check = promptForPlayAgain(keyboard);// asks the user if they want to play again
			System.out.println();
		}
		System.out.println("Goodbye!");
		keyboard.close();
	}

	// Given an array of integers as input, sets every element of the array to zero.
	private static void resetDice(int[] dice) {
		int index = 0;
		while (index < dice.length) {
			dice[index] = 0; // at any index it will be set to zero
			index++;
		}
	}

	// Given an array of integers as input, checks each element of the array. If the
	// value
	// of that element is zero, generate a number between 1 and 10 and replace the
	// zero with
	// it. Otherwise, leave it as is and move to the next element.
	private static void rollDice(int[] dice) {
		int index = 0;
		while (index < dice.length) {
			if (dice[index] == 0) {
				dice[index] = (int) (Math.random() * 10) + 1; // this will generate a number from 1-10
			}
			index++;
		}

	}

	// Given an array of integers as input, create a formatted String that contains
	// the
	// values in the array in the order they appear in the array. For example, if
	// the
	// array contains the values [0, 3, 7, 5, 2] then the String returned by this
	// method
	// should be "0 3 7 5 2".
	private static String diceToString(int[] dice) {
		String values = "";
		int index = 0;
		while (index < dice.length) {
			values = values + dice[index] + " ";
			index++;

		}
		return values;
	}

	// Given an array of integers and a Scanner as input, prompt the user with a
	// message
	// to indicate which dice should be re-rolled. If the user enters a valid index
	// (between
	// 0 and the total number of dice -1) then set the die at that index to zero. If
	// the
	// user enters a -1, end the loop and return to the calling program. If the user
	// enters
	// any other invalid index, provide an error message and ask again for a valid
	// index.
	private static void promptForReroll(int[] dice, Scanner inScanner) {
		System.out.print("Select a die to re-roll (-1 to keep remaining dice): ");
		int reroll = Integer.parseInt(inScanner.nextLine()); // better to use parseInt instead of nextLine() so you dont
																// overwrite anything
		boolean check1 = true;
		String output = "";
		while (check1) {
			if (reroll >= 0 && reroll <= dice.length - 1) {
				dice[reroll] = 0;
				output = diceToString(dice); // this makes all the dice strings
				System.out.println("Your current dice is: " + output);
				System.out.print("Select a die to re-roll (-1 to keep remaining dice): ");
				reroll = Integer.parseInt(inScanner.nextLine());
				check1 = true;
			} else if (reroll < -1 || reroll > dice.length - 1) { // restrictions for the area of use with reroll
				System.out.println("Error: Index must be between 0 and 4 (-1 to quit)! ");
				System.out.print("Select a die to re-roll (-1 to keep remaining dice): ");
				reroll = Integer.parseInt(inScanner.nextLine());
				check1 = true;
			} else if (reroll == -1) {// if this is false the main method will exit the loop
				check1 = false;
			}

		}

	}

	// Given a Scanner as input, prompt the user to play again. The only valid
	// entries
	// from the user are 'Y' or 'N', in either upper or lower case. If the user
	// enters
	// a 'Y' the method should return a value of true to the calling program. If the
	// user
	// enters a 'N' the method should return a value of false. If the user enters
	// anything
	// other than Y or N (including an empty line), an error message should be
	// displayed
	// and the user should be prompted again until a valid response is received.
	private static boolean promptForPlayAgain(Scanner inScanner) {
		boolean check = false;
		System.out.print("Would you like to play again [Y/N]?: ");
		String guess = inScanner.nextLine();
		while (!guess.equals("Y") && !guess.equals("y") && !guess.equals("N") && !guess.equals("n")) { // used .equals
																										// since we are
																										// using strings
																										// and !to show
																										// that its not
																										// true
			System.out.println("ERROR! Only 'Y' or 'N' allowed as input!");
			System.out.print("Would you like to play again [Y/N]?: ");
			guess = inScanner.nextLine();
		}
		if (guess.equalsIgnoreCase("y")) { // this will make the main method start the game over
			check = true;
		}
		if (guess.equalsIgnoreCase("n")) {
			check = false;
		}
		return check;
	}

	// Given an array of integers, determines the result as a hand of Poker Dice.
	// The
	// result is determined as:
	// * Five of a kind - all five integers in the array have the same value
	// * Four of a kind - four of the five integers in the array have the same value
	// * Full House - three integers in the array have the same value, and the
	// remaining two
	// integers have the same value as well (Three of a kind and a pair)
	// * Three of a kind - three integers in the array have the same value
	// * Two pair - two integers in the array have the same value, and two other
	// integers
	// in the array have the same value
	// * One pair - two integers in the array have the same value
	// * Highest value - if none of the above hold true, the Highest Value in the
	// array
	// is used to determine the result
	//
	// The method should evaluate the array and return back to the calling program a
	// String
	// containing the score from the array of dice.
	//
	// EXTRA CREDIT: Include in your scoring a Straight, which is 5 numbers in
	// sequence
	// [1,2,3,4,5] or [2,3,4,5,6] or [3,4,5,6,7] etc..
	private static String getResult(int[] dice) {
		int index = 0;
		String output = "";
		int special = 0;
		int full = 0;
		int five = 0;
		int four = 0;
		int three = 0;
		int[] values = getCounts(dice);
		while (index < values.length) {
			if (values[index] == 3) {
				output = "Three of a kind!";
				three = 1;
				full++;// used to check full house
			} else if (values[index] == 4) {
				output = "Four of a kind!";
				four = 1;

			} else if (values[index] == 5) {
				output = "Five of a kind!";
				five = 1;

			}
			if (values[index] == 2) {
				output = "One Pair!";
				special++; // also used to check for full house
			}
			if (special == 2) {
				output = "Two Pair!";
			}
			if (full == 1 && special == 1) {
				output = "Full House!";
			}
			if (full < 2 && special < 1 && three != 1 && four != 1 && five != 1) { // case where any of the other ones
																					// wernet possilbe
				int high = 0;// I could turn this into a method of itself but this will also do the trick
				int index1 = 0;
				while (index1 < dice.length) {
					if (dice[index1] > high) {
						high = dice[index1];
					}

					index1++;
					output = "Highest Card " + high;
				}
				Arrays.sort(dice);	
				
				if (dice[4] - dice[0] == 4) {
					output = "Straight";
				}
			}
			index++;
		}
		return output;

	}

	// Given an array of integers as input, return back an array with the counts of
	// the
	// individual values in it. You may assume that all elements in the array will
	// have
	// a value between 1 and 10. For example, if the array passed into the method
	// were:
	// [1, 2, 3, 3, 7]
	// Then the array of counts returned back by this method would be:
	// [1, 1, 2, 0, 0, 0, 1, 0, 0, 0]
	// (Where index 0 holds the counts of the value 1, index 1 holds the counts of
	// the value
	// 2, index 2 holds the counts of the value 3, etc.)
	// HINT: This method is very useful for determining the score of a particular
	// hand
	// of poker dice. Use it as a helper method for the getResult() method above.
	private static int[] getCounts(int[] dice) {
		int[] values = new int[10];
		int index = 0;
		while (index < dice.length) {
			if (dice[index] == 1) { // adds the value of index to the according value
				values[0]++;
			} else if (dice[index] == 2) {
				values[1]++;
			} else if (dice[index] == 3) {
				values[2]++;
			} else if (dice[index] == 4) {
				values[3]++;
			} else if (dice[index] == 5) {
				values[4]++;
			} else if (dice[index] == 6) {
				values[5]++;
			} else if (dice[index] == 7) {
				values[6]++;
			} else if (dice[index] == 8) {
				values[7]++;
			} else if (dice[index] == 9) {
				values[8]++;
			} else if (dice[index] == 10) {
				values[9]++;
			}
			index++;
		}
		return values;

	}

}
