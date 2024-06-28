package com.isign;

import java.util.*;
import java.util.stream.*;

class Person {
    String name;
    int age;
    List<String> hobbies;

    Person(String name, int age, List<String> hobbies) {
        this.name = name;
        this.age = age;
        this.hobbies = hobbies;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}

public class StreamExample {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
            new Person("Alice", 30, Arrays.asList("Reading", "Hiking")),
            new Person("Bob", 15, Arrays.asList("Gaming", "Cycling")),
            new Person("Charlie", 25, Arrays.asList("Swimming", "Hiking")),
            new Person("David", 20, Arrays.asList("Reading", "Gaming")),
            new Person("Eve", 35, Arrays.asList("Swimming", "Cycling"))
        );

        //Filter out people who are under 20
        List<Person> adults = people.stream()
            .filter(person -> person.getAge() >= 20)
            .collect(Collectors.toList());

        //  Sort by age
        List<Person> sortedByAge = adults.stream()
            .sorted(Comparator.comparingInt(Person::getAge))
            .collect(Collectors.toList());

        // Collect their names into a list
        List<String> names = sortedByAge.stream()
            .map(Person::getName)
            .collect(Collectors.toList());

        // Group people by age
        Map<Integer, List<Person>> peopleByAge = sortedByAge.stream()
            .collect(Collectors.groupingBy(Person::getAge));

        // Calculate the average age
        double averageAge = sortedByAge.stream()
            .mapToInt(Person::getAge)
            .average()
            .orElse(0.0);

        //  Extract all hobbies and collect them into a single list
        List<String> allHobbies = sortedByAge.stream()
            .flatMap(person -> person.getHobbies().stream())
            .distinct()
            .collect(Collectors.toList());

        // Print results
        System.out.println("Filtered and Sorted List of Adults:");
        sortedByAge.forEach(System.out::println);

        System.out.println("\nList of Names:");
        names.forEach(System.out::println);

        System.out.println("\nPeople Grouped by Age:");
        peopleByAge.forEach((age, peopleList) -> {
            System.out.println(age + ": " + peopleList);
        });

        System.out.println("\nAverage Age:");
        System.out.println(averageAge);

        System.out.println("\nAll Hobbies:");
        allHobbies.forEach(System.out::println);
    }
}

