import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeightedStrings {
    public static void main(String[] args) {

        // first input
        String stringInput = "abbcccd";
        int[] queries = {1, 3, 9, 8};

        List<String> results = weightedStrings(stringInput, queries);
        System.out.println(results);

        // second input
        stringInput = "abbddd";
        queries = new int[]{1, 4, 8, 12};

        results = weightedStrings(stringInput, queries);
        System.out.println(results);

        // third input
        stringInput = "abcdef";
        queries = new int[]{1, 3, 14, 16};

        results = weightedStrings(stringInput, queries);
        System.out.println(results);
    }

    public static List<String> weightedStrings(String input, int[] queries) {
        // Initialize character weights
        Map<Character, Integer> charWeights = new HashMap<>();

        // Initialize for substring characters
        List<String> substrings = new ArrayList<>();

        // Initialize for result after calculation
        List<String> results = new ArrayList<>();

        // Objective : Iterate over characters from 'a' to 'z'
        // The idea is :
        //  - Java representation single quote of char is ASCII number.
        //  - As we know, 'a' to 'z' is ordered ASCII Number. e.g : 'a' is 97, 'b' is 98, and so on
        //  - So that, loop for initialize character use single quote of char with 'a' is first position and 'z' is last position in loop
        for (char current = 'a'; current <= 'z'; current++) {
            // Objective : Assign a weight to each character and put it in the Map
            // The idea is :
            // - Map variable will have content such as : { a : 1, b : 2, .... }
            // - As we know before, ASCII representation of ordered number
            // - the pattern is : current-'a' + 1.
            // - if current is 'a' ( 97 ) - 'a' ( 97 ) + 1 will result value 1
            // - if current is 'b' ( 98 ) - 'a' ( 97 ) + 1 will result value 2
            // - and so on
            charWeights.put(current, current - 'a' + 1);
        }

        // Objective : Details the strings in the array
        // The idea is :
        // - Store single character or repeated characters in single index of arraylist.
        // - Arraylist will have content such as : ['a', 'bb', 'b', 'ccc', 'cc', 'c', 'd']
        // - To make this content, need two loops. Outer loop and Inner loop
        // - Outer loop iterates over each character in the input parameter. 'i' represents the current index
        // - Inner loop starts from the next index (i + 1) and looks for consecutive repeated characters. ( for detail, will be explained below )
        for (int i = 0; i < input.length(); i++) {
            // Initialize substring with current character at index 'i'
            String substring = String.valueOf(input.charAt(i));

            // Looking for consecutive repeated characters in input parameter
            for (int j = i + 1; j < input.length(); j++) {
                // Check if the character at index 'j' is the same as the character at index 'i' then append the character to the substring
                // Otherwise, break out of the inner loop
                if (input.charAt(j) == input.charAt(i)) {
                    substring += input.charAt(j);
                } else {
                    break;
                }
            }
            // Add the formed substring to Arraylist
            substrings.add(substring);
        }

        // Objective : Give 'Yes' for weight of string same as queries. Otherwise, 'No' in Arraylist
        // The idea is :
        // - Store result in Arraylist with total content of arraylist same as queries
        // - Arraylist will have content such as : ['Yes', 'Yes', 'Yes', 'No']. Because 4 index in queries, Arraylist has 4 index too
        // - To make this content, need two loops. Outer loop and Inner loop
        // - Outer loop iterates over each query in the queries
        // - Inner loop iterates over each substring in the substrings. ( for detail, will be explained below )
        for (int query : queries) {
            // Initial found with false as default
            boolean found = false;

            // Iterate all substring
            for (String substring : substrings) {
                // Do calculation
                int substringWeight = calculateSubstringWeight(substring, charWeights);

                // Compares the calculated weight of the substring with the current query
                // If there is a match, it adds "Yes" to the results list, sets found to true, and breaks out of the inner loop
                if (query == substringWeight) {
                    results.add("Yes");
                    found = true;
                    break;
                }
            }

            // If inner loop ends but calculation still not match with query, it adds "No" to the results list.
            if (!found) {
                results.add("No");
            }
        }

        // return result
        return results;
    }


    // Objective : Calculate weight
    // The idea is :
    // - Initiate weight with 0
    // - Convert the string to a character array and iterate it. It useful for break substring that contains repeated character became each array
    // - Get basic weight from charWeights that has been initialized before then add with weight
    // - If loop ends, return the weight value
    private static int calculateSubstringWeight(String substring, Map<Character, Integer> charWeights) {
        int weight = 0;
        for (char c : substring.toCharArray()) {
            weight += charWeights.get(c);
        }

        return weight;
    }
}
