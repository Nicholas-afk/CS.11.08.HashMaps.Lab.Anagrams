import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AnagramSolver {

    public static HashMap<String, ArrayList<String>> anagrams(String filename) {
        HashMap<String, ArrayList<String>> anagramMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String word = line.trim().toLowerCase();
                String sortedWord = sortString(word);

                if (!anagramMap.containsKey(sortedWord)) {
                    anagramMap.put(sortedWord, new ArrayList<>());
                }
                anagramMap.get(sortedWord).add(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Iterator<Map.Entry<String, ArrayList<String>>> iterator = anagramMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, ArrayList<String>> entry = iterator.next();
            if (entry.getValue().size() <= 1) {
                iterator.remove();
            }
        }

        return anagramMap;
    }

    public static ArrayList<String> mostFrequentAnagram(HashMap<String, ArrayList<String>> anagrams) {
        ArrayList<String> largest = new ArrayList<>();

        for (ArrayList<String> list : anagrams.values()) {
            if (list.size() > largest.size()) {
                largest = list;
            }
        }

        return largest;
    }

    public static void printKeyValuePairs(HashMap<String, ArrayList<String>> anagrams) {
        for (Map.Entry<String, ArrayList<String>> entry : anagrams.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }

    private static String sortString(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
