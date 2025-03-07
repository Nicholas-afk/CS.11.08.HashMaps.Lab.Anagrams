import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AnagramProcessor {

    public HashMap<String, ArrayList<String>> anagrams(String filename) {
        HashMap<String, ArrayList<String>> anagramMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String word = line.trim().toLowerCase();
                String sortedWord = sortString(word);

                anagramMap.computeIfAbsent(sortedWord, k -> new ArrayList<>()).add(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return anagramMap;
    }

    public ArrayList<String> mostFrequentAnagram(HashMap<String, ArrayList<String>> anagrams) {
        return anagrams.values().stream()
                .max(Comparator.comparingInt(ArrayList::size))
                .orElse(new ArrayList<>());
    }

    public void printKeyValuePairs(HashMap<String, ArrayList<String>> anagrams) {
        for (Map.Entry<String, ArrayList<String>> entry : anagrams.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }

    private String sortString(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
