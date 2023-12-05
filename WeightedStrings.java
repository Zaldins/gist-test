import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeightedStrings {
    public static void main(String[] args) {
        String string = "abbcccd";
        int[] queries = {1, 3, 9, 8};

        List<String> results = weightedStrings(string, queries);

        System.out.println(results);
    }

    public static List<String> weightedStrings(String string, int[] queries) {
        // Initialize character weights
        Map<Character, Integer> charWeights = new HashMap<>();
        for (char c = 'a'; c <= 'z'; c++) {
            charWeights.put(c, c - 'a' + 1);
        }

        // Calculate weights for all possible substrings
        List<String> substrings = new ArrayList<>();
        for (int i = 0; i < string.length(); i++) {
            String substring = String.valueOf(string.charAt(i));

            for (int j = i + 1; j < string.length(); j++) {
                if (string.charAt(j) == string.charAt(i)) {
                    substring += string.charAt(j);
                } else {
                    break;
                }
            }

            substrings.add(substring);
        }

        // Check if query weight matches any substring weight
        List<String> results = new ArrayList<>();
        for (int query : queries) {
            boolean found = false;
            for (String substring : substrings) {
                int substringWeight = calculateSubstringWeight(substring, charWeights);
                if (query == substringWeight) {
                    results.add("Yes");
                    found = true;
                    break;
                }
            }

            if (!found) {
                results.add("No");
            }
        }

        return results;
    }

    private static int calculateSubstringWeight(String substring, Map<Character, Integer> charWeights) {
        int weight = 0;
        for (char c : substring.toCharArray()) {
            weight += charWeights.get(c);
        }

        return weight;
    }
}
