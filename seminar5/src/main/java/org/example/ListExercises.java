package collectionsIntro;

import java.util.*;

public class ListExercises {

    public static void main(String[] args) {


        List<String> names = new ArrayList<>();
        Collections.addAll(names, "Alice", "Bob", "Charlie", "Diana", "Edward");
        System.out.println(names);
        names.remove(2);
        System.out.println(names);

        List<Integer> numbers = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50, 15, 25, 35, 45, 55));
        int sum = 0;
        for (int n : numbers) sum += n;
        double average = (double) sum / numbers.size();
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + average);


        List<Integer> toReverse = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        int left = 0, right = toReverse.size() - 1;
        while (left < right) {
            int temp = toReverse.get(left);
            toReverse.set(left, toReverse.get(right));
            toReverse.set(right, temp);
            left++;
            right--;
        }
        System.out.println(toReverse);


        List<Student> students = new ArrayList<>();
        Collections.addAll(students,
                new Student("Alice", 92),
                new Student("Bob", 85),
                new Student("Charlie", 97),
                new Student("Diana", 78),
                new Student("Edward", 88)
        );
        System.out.println(students);

        Student top = students.get(0);
        for (Student s : students)
            if (s.getGrade() > top.getGrade()) top = s;
        System.out.println("Highest grade: " + top);

        // Exercise 8 - Sort by name
        students.sort(Comparator.comparing(Student::getName));
        System.out.println(students);


        students.sort((a, b) -> b.getGrade() - a.getGrade());
        System.out.println(students);


        List<Student> withDuplicates = new ArrayList<>(Arrays.asList(
                new Student("Alice", 92),
                new Student("Bob", 85),
                new Student("Alice", 92),
                new Student("Charlie", 97),
                new Student("Bob", 85)
        ));
        List<Student> noDuplicates = new ArrayList<>(new LinkedHashSet<>(withDuplicates));
        System.out.println(noDuplicates);
    }
}