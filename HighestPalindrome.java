public class HighestPalindrome {
    public static void main(String[] args) {
        //  k == the number of changes allowed
        int k = 1;

        // first input
        String inputString = "3943";
        String highestPalindrome = findHighestPalindrome(inputString, k);
        System.out.println(highestPalindrome);

        // second input
        inputString = "2150";
        highestPalindrome = findHighestPalindrome(inputString, k);
        System.out.println(highestPalindrome);

        // third input
        inputString = "5135";
        highestPalindrome = findHighestPalindrome(inputString, k);
        System.out.println(highestPalindrome);
    }

    private static String findHighestPalindrome(String string, int k) {
        // This condition checks if the integer representation of the input string is negative.
        // If so, it means the input is invalid, and the method returns "-1" to indicate an invalid input
        if (Integer.parseInt(string) < 0) {
            return "-1";
        }

        // If k (the number of changes allowed) is zero
        if (k == 0) {
            if (isPalindrome(string)) {
                return string;
            } else {
                return "-1";
            }
        }


        return helper(string, k, "0");
    }

    // Objective : Determine string is palindrome or not
    // The idea is :
    // - The loop compares the characters from the beginning and end of the string towards the center
    // - If, at any point, the characters do not match, it means the string is not a palindrome
    // - Otherwise, string is palindrome
    private static boolean isPalindrome(String string) {
        // The loop only needs to go up to half the length of the string because, in a palindrome, characters are mirrored around the center
        for (int i = 0; i < string.length() / 2; i++) {
            // In each iteration, it compares the character at position i with the character at the corresponding position from the end of the string
            // If at any point the characters do not match, it means the string is not a palindrome, given false return
            if (string.charAt(i) != string.charAt(string.length() - 1 - i)) {
                return false;
            }
        }
        // If the loop completes without finding a mismatch, the method returns true, indicating that the string is a palindrome
        return true;
    }


    // Objective : Determine highestPalindrome in digit
    // With recursive, it will be replaced all in string then compare for get highestPalindrome
    private static String helper(String input, int k, String highestPalindrome) {
        // If k (the number of changes allowed) is zero
        if (k == 0) {
            // There are two conditions :
            // - First condition checks if the current string (string) is a palindrome using the isPalindrome method. If it is, the condition evaluates to true
            // - Second condition compares the numerical values of the current string (string) and the current highestPalindrome. If the current string has a higher numerical value ( in this case is 0 ), this part of the condition evaluates to true
            if (isPalindrome(input) && Integer.parseInt(input) > Integer.parseInt(highestPalindrome)) {
                // If so, it updates highestPalindrome with the current string
                highestPalindrome = input;
            }

            return highestPalindrome;
        }

        // If k (the number of changes allowed) is not zero
        for (int i = 0; i < input.length(); i++) {
            for (char digit = '0'; digit <= '9'; digit++) {
                // Generate new string :
                // - Gets the substring from the beginning of the original string up to (but not including) position i
                // - Digit is the new digit to be inserted
                // - Gets the substring starting from the position next to i until the end of the original string
                String newString = input.substring(0, i) + digit + input.substring(i + 1);
                // Recursive with new string
                highestPalindrome = helper(newString, k - 1, highestPalindrome);
            }
        }
        // return highestPalindrome value
        return highestPalindrome;
    }
}
