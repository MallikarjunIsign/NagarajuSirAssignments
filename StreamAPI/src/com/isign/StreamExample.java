package com.isign;

import java.util.*;
import java.util.stream.*;

class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}

public class StreamExample {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
            new Person("Alice", 30),
            new Person("Bob", 15),
            new Person("Charlie", 25),
            new Person("David", 20)
        );

        //  Filter out people who are under 18
        List<Person> adults = people.stream()
            .filter(person -> person.getAge() >= 18)
            .collect(Collectors.toList());

        //  Sort the remaining people by their name
        List<Person> sortedAdults = adults.stream()
            .sorted(Comparator.comparing(Person::getName))
            .collect(Collectors.toList());

        //  Collect their names into a list
        List<String> names = sortedAdults.stream()
            .map(Person::getName)
            .collect(Collectors.toList());

        //  Create a map where the key is the person's name and the value is their age
        Map<String, Integer> nameToAgeMap = sortedAdults.stream()
            .collect(Collectors.toMap(Person::getName, Person::getAge));

        // Printing results
        System.out.println("Filtered and Sorted List of Adults:");
        sortedAdults.forEach(System.out::println);

        System.out.println("\nList of Names:");
        names.forEach(System.out::println);

        System.out.println("\nMap of Names to Ages:");
        nameToAgeMap.forEach((name, age) -> System.out.println(name + ": " + age));
    }
}

