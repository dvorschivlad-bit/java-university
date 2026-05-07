package collectionsIntro;

import java.util.*;

public class MapExercises {



    public static void main(String[] args) {


        String input = "apple banana apple orange banana apple";
        Map<String, Integer> frequency = new HashMap<>();
        for (String word : input.split("\\s+"))
            frequency.put(word, frequency.getOrDefault(word, 0) + 1);
        System.out.println(frequency);


        Map<String, String> phoneBook = new HashMap<>();
        phoneBook.put("Alice", "0722-111-222");
        phoneBook.put("Bob", "0733-333-444");
        phoneBook.put("Charlie", "0744-555-666");

        System.out.println("Bob: " + phoneBook.get("Bob"));
        for (Map.Entry<String, String> entry : phoneBook.entrySet())
            System.out.println(entry.getKey() + " -> " + entry.getValue());


        final int CAPACITY = 3;
        LinkedHashMap<Integer, String> lruCache = new LinkedHashMap<>(CAPACITY, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
                return size() > CAPACITY;
            }
        };
        lruCache.put(1, "one");
        lruCache.put(2, "two");
        lruCache.put(3, "three");
        lruCache.get(1);
        lruCache.put(4, "four");
        System.out.println(lruCache);


        Map<String, Integer> map1 = new HashMap<>(Map.of("a", 1, "b", 2, "c", 3));
        Map<String, Integer> map2 = new HashMap<>(Map.of("b", 10, "c", 20, "d", 4));
        Map<String, Integer> merged = new HashMap<>(map1);
        for (Map.Entry<String, Integer> entry : map2.entrySet())
            merged.merge(entry.getKey(), entry.getValue(), Integer::sum);
        System.out.println(merged);
    }
}