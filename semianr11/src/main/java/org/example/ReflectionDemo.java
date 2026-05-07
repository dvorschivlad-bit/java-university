package org.example;

import java.lang.reflect.*;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@interface Info {
    String author();
    String version();
}

@Info(author = "Vlad", version = "1.0")
class Student implements Comparable<Student>, Cloneable {

    private String name;
    private int age;

    public Student() {
        this.name = "Unknown";
        this.age = 0;
    }

    public Student(String name) {
        this.name = name;
        this.age = 0;
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void sayHello() {
        System.out.println("Hello, I'm " + name + "!");
    }

    public String greet(String other) {
        return "Hello " + other + ", I am " + name + ".";
    }

    private void secret() {
        System.out.println("Secret method called on " + name);
    }

    @Override
    public int compareTo(Student other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + "}";
    }
}

class ReflectionUtils {

    public static void inspect(Object obj) throws IllegalAccessException {
        System.out.println("Inspecting: " + obj.getClass().getSimpleName());
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            System.out.println("  " + field.getName() + " = " + field.get(obj));
        }
    }

    public static String toJson(Object obj) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            sb.append("\"").append(fields[i].getName()).append("\":");
            Object value = fields[i].get(obj);
            if (value instanceof String) sb.append("\"").append(value).append("\"");
            else sb.append(value);
            if (i < fields.length - 1) sb.append(",");
        }
        return sb.append("}").toString();
    }

    public static Object fromCsv(Class<?> studentClass, String headerLine, String rowLine) throws Exception {
        String[] headers = headerLine.split(",");
        String[] values  = rowLine.split(",");
        Object obj = studentClass.getDeclaredConstructor().newInstance();
        for (int i = 0; i < headers.length; i++) {
            Field field = studentClass.getDeclaredField(headers[i].trim());
            field.setAccessible(true);
            if (field.getType() == int.class) field.set(obj, Integer.parseInt(values[i].trim()));
            else field.set(obj, values[i].trim());
        }
        return obj;
    }
}

class Main {

    public static void main(String[] args) {
        try {
            Class<?> studentClass = Student.class;


            System.out.println("1. Class Information ");
            System.out.println("Class name:  " + studentClass.getSimpleName());
            System.out.println("Package:     " + studentClass.getPackageName());
            System.out.println("Superclass:  " + studentClass.getSuperclass().getSimpleName());
            System.out.print("Interfaces:  ");
            for (Class<?> iface : studentClass.getInterfaces())
                System.out.print(iface.getSimpleName() + " ");
            System.out.println();


            System.out.println("\n2. Annotation Data");
            if (studentClass.isAnnotationPresent(Info.class)) {
                Info info = studentClass.getAnnotation(Info.class);
                System.out.println("Author:  " + info.author());
                System.out.println("Version: " + info.version());
            }


            System.out.println("\n 3. Declared Fields ");
            for (Field field : studentClass.getDeclaredFields())
                System.out.println(field.getType().getSimpleName() + " " + field.getName());


            System.out.println("\n 4. Constructors ");
            for (Constructor<?> c : studentClass.getDeclaredConstructors())
                System.out.println(c);


            System.out.println("\n 5. Declared Methods ");
            for (Method method : studentClass.getDeclaredMethods())
                System.out.println(method.getReturnType().getSimpleName() + " " + method.getName());


            System.out.println("\n 6. Create Object Dynamically ");
            Object s1 = studentClass.getDeclaredConstructor().newInstance();
            System.out.println("Created: " + s1);


            System.out.println("\n 7. Invoke Public Method ");
            studentClass.getMethod("sayHello").invoke(s1);


            System.out.println("\n 8. Invoke Method With Argument ");
            Object s2 = studentClass.getDeclaredConstructor(String.class, int.class).newInstance("Alice", 30);
            Object result = studentClass.getMethod("greet", String.class).invoke(s2, "Bob");
            System.out.println("Result: " + result);


            System.out.println("\n 9. Access Private Field ");
            Field nameField = studentClass.getDeclaredField("name");
            nameField.setAccessible(true);
            System.out.println("Before: " + nameField.get(s2));
            nameField.set(s2, "Charlie");
            System.out.println("After:  " + s2);


            System.out.println("\n 10. Invoke Private Method ");
            Method secretMethod = studentClass.getDeclaredMethod("secret");
            secretMethod.setAccessible(true);
            secretMethod.invoke(s2);


            System.out.println("\n 11. Constructor Selection ");
            Object c1 = studentClass.getDeclaredConstructor().newInstance();
            System.out.println("No-arg:     " + c1);

            Object c2 = studentClass.getDeclaredConstructor(String.class).newInstance("Maria");
            System.out.println("Name only:  " + c2);

            Object c3 = studentClass.getDeclaredConstructor(String.class, int.class).newInstance("Vlad", 21);
            System.out.println("Name + age: " + c3);


            System.out.println("\n 12. Object Inspector ");
            ReflectionUtils.inspect(c3);


            System.out.println("\n 13. JSON Serializer ");
            System.out.println(ReflectionUtils.toJson(c3));


            System.out.println("\n 14. CSV Mapper ");
            Object fromCsv = ReflectionUtils.fromCsv(studentClass, "name,age", "Elena,22");
            System.out.println("From CSV: " + fromCsv);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}