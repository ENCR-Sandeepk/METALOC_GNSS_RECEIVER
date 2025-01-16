/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

/**
 *
 * @author sandeepk
 */
public class Validate_IP_Address {

    public static boolean inRange(int n) {
        // check if every split is in range 0-255
        return n >= 0 && n <= 255;
    }

    public static boolean hasLeadingZero(String n) { // check if every split has leading zero or not.
        if (n.length() > 1) {
            if (n.charAt(0) == '0') {
                return true;
            }
        }
        return false;
    }

    public static int isValid(String s) {
        String[] parts = s.split("\\.");
        if (parts.length
                != 4) { // if number of splitting element is not
            // 4 it is not a valid IP address
            return 0;
        }
        for (String part : parts) {
            if (hasLeadingZero(part)) {
                return 0;
            }
            if (part.length() == 0) {
                return 0;
            }
            try {
                int num = Integer.parseInt(part);
                if (!inRange(num)) {
                    return 0;
                }
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        return 1;
    }
}
