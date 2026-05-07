package collectionsIntro;

import java.util.*;

public class SetExercises {

//    Set nu permite duplicate
//    HashSet - fara ordine, LinkedHashSet - pastreaza ordinea insertiei
//    pentru obiecte custom, equals() si hashCode() trebuie suprascrise

    public static void main(String[] args) {

        // Exercise 4 - Unique words counter
        String sentence = "the quick brown fox jumps over the lazy dog the fox";
        Set<String> uniqueWords = new HashSet<>(Arrays.asList(sentence.split("\\s+")));
        System.out.println(uniqueWords);
        System.out.println("Unique count: " + uniqueWords.size());
    }
}