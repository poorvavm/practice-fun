/*
 * You are playing the following Bulls and Cows game with your friend: 
 * You write down a number and ask your friend to guess what the number is. 
 * Each time your friend makes a guess, you provide a hint that indicates how many digits in 
 * said guess match your secret number exactly in both digit and position (called "bulls") 
 * and how many digits match the secret number but locate in the wrong position (called "cows"). 
 * Your friend will use successive guesses and hints to eventually derive the secret number.
 * 
 * For example:
 * Secret number:  "1807"
 * Friend's guess: "7810"
 * Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)
 * 
 * Write a function to return a hint according to the secret number and friend's guess, 
 * use A to indicate the bulls and B to indicate the cows. 
 * In the above example, your function should return "1A3B".
 * 
 * Please note that both secret number and friend's guess may contain duplicate digits, 
 * 
 * for example:
 * Secret number:  "1123"
 * Friend's guess: "0111"
 * In this case, the 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow, 
 * and your function should return "1A1B".
 * 
 * You may assume that the secret number and your friend's guess only contain digits, 
 * and their lengths are always equal.
 */

package leetcode;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class P299_BullsAndCows {

	/*
	 * Rule 1 : if a match just increment bulls
	 * Rule 2 : for every s digit increment array and for every g decrement array
	 * Rule 3 : if at anytime s digit array value goes below 0 means it exists in guess and can be count as cow
	 * Rule 4 : if at anytime g digit array value goes above 0 means it exists in secret and can be count as cow 
	 */
	public String getHint(String secret, String guess) {
		int bulls = 0;
		int cows = 0;
		int[] numbers = new int[10];
		for (int i = 0; i < secret.length(); i++) {
			int s = Character.getNumericValue(secret.charAt(i));
			int g = Character.getNumericValue(guess.charAt(i));
			if (s == g)
				bulls++;
			else {
				if (numbers[s] < 0)
					cows++;
				if (numbers[g] > 0)
					cows++;
				numbers[s]++;
				numbers[g]--;
			}
		}
		return bulls + "A" + cows + "B";
	}

	@Test
	public void test() {
		assertTrue("Test1", getHint("1807", "7810").compareTo("1A3B") == 0);
		assertTrue("Test2", getHint("1123", "0111").compareTo("1A1B") == 0);
	}

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(P299_BullsAndCows.class);
		System.out.println("All Tests passed : " + result.wasSuccessful());
		for (Failure failure : result.getFailures()) {
			System.out.println("Failed Test cases" + failure.toString());
		}
	}
}
