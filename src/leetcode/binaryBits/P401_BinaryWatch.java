package leetcode.binaryBits;

/** ---------------------------------------------
 * A binary watch has 4 LEDs on the top which represent the hours (0-11), 
 * and the 6 LEDs on the bottom represent the minutes (0-59).
 * 
 * Each LED represents a zero or one, with the least significant bit on the right.
 * ----------------------------
 * |______8___4___2___1________|
 * |......0...0...1...1........|
 * |---------------------------|
 * |__32___16___8___4___2___1__|
 * |...0....1...1...0...0...1..|
 * ----------------------------
 * 
 * For example, the above binary watch reads "3:25".
 * 
 * Given a non-negative integer n which represents the number of LEDs that are 
 * currently on, return all possible times the watch could represent.
 * 
 * Example:
 * 
 * Input: n = 1
 * Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 * 
 * Note:
 *  - The order of output does not matter.
 *  - The hour must not contain a leading zero, for example "01:00" is not valid, 
 *    it should be "1:00".
 *  - The minute must be consist of two digits and may contain a leading zero, for example "10:2" 
 *    is not valid, it should be "10:02".
 * --------------------------------------------- */

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class P401_BinaryWatch {

    public List<String> readBinaryWatch(int num) {
        List<String> pnc = new ArrayList<String>();
        if (num < 1 || num > 10)
            return pnc;

        return pnc;
    }

    public static boolean problem_1(int n) {
        return false;
    }

    public static boolean problem(int n) {
        return problem_1(n);
    }

    public static void main(String[] args) {
        assertTrue("Test1", problem(94));
        assertFalse("Test2", problem(97));
        System.out.println("All Tests passed");
    }
}