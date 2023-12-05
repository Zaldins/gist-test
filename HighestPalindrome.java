public class HighestPalindrome {
    public static void main(String[] args) {
        String string = "3943";
        int k = 1;

        String highestPalindrome = findHighestPalindrome(string, k);
        System.out.println(highestPalindrome);
    }

    private static String findHighestPalindrome(String string, int k) {
        if (Integer.parseInt(string) < 0) {
            return "-1";
        }

        if (k == 0) {
            if (isPalindrome(string)) {
                return string;
            } else {
                return "-1";
            }
        }

        return helper(string, k, "0");
    }

    private static boolean isPalindrome(String string) {
        for (int i = 0; i < string.length() / 2; i++) {
            if (string.charAt(i) != string.charAt(string.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    private static String helper(String string, int k, String highestPalindrome) {
        if (k == 0) {
            if (isPalindrome(string) && Integer.parseInt(string) > Integer.parseInt(highestPalindrome)) {
                highestPalindrome = string;
            }
            return highestPalindrome;
        }

        for (int i = 0; i < string.length(); i++) {
            for (char digit = '0'; digit <= '9'; digit++) {
                String newString = string.substring(0, i) + digit + string.substring(i + 1);
                highestPalindrome = helper(newString, k - 1, highestPalindrome);
            }
        }

        return highestPalindrome;
    }
}
