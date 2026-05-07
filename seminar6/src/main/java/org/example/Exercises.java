package genericsIntro;

import java.util.ArrayList;
import java.util.List;

public class Exercises {

    static class Wrapper<T> {
        private T value;

        public Wrapper(T value) {
            this.value = value;
        }

        public T getValue() { return value; }
        public void setValue(T value) { this.value = value; }
    }

    static class Stack<T> {
        private List<T> items = new ArrayList<>();

        public void push(T item) { items.add(item); }

        public T pop() {
            if (isEmpty()) throw new RuntimeException("Stack is empty.");
            return items.remove(items.size() - 1);
        }

        public T peek() {
            if (isEmpty()) throw new RuntimeException("Stack is empty.");
            return items.get(items.size() - 1);
        }

        public boolean isEmpty() { return items.isEmpty(); }

        @Override
        public String toString() { return items.toString(); }
    }

    public static <T> void printTwice(T value) {
        System.out.println(value);
        System.out.println(value);
    }

    public static <T extends Comparable<T>> T max(T a, T b) {
        return a.compareTo(b) >= 0 ? a : b;
    }

    public static <T> int countElements(List<T> list) {
        return list.size();
    }

    public static <T> void copy(List<? extends T> src, List<? super T> dest) {
        for (T o : src) dest.add(o);
    }

    public static void main(String[] args) {

        Wrapper<String> nameWrapper = new Wrapper<>("Vlad");
        System.out.println(nameWrapper.getValue());
        nameWrapper.setValue("Ion");
        System.out.println(nameWrapper.getValue());

        Wrapper<Integer> scoreWrapper = new Wrapper<>(95);
        System.out.println(scoreWrapper.getValue());

        printTwice("Hello");
        printTwice(42);

        System.out.println(max(10, 25));
        System.out.println(max("apple", "banana"));

        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        System.out.println(countElements(numbers));

        List<Integer> src = new ArrayList<>(List.of(1, 2, 3));
        List<Number> dest = new ArrayList<>();
        copy(src, dest);
        System.out.println(dest);

        Stack<String> stack = new Stack<>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack.isEmpty());
    }
}