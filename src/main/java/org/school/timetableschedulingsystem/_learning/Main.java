package org.school.timetableschedulingsystem._learning;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@ToString
class Student {
    int age;
    String name;
}

public class Main {
    public static void main(String[] args) {

        Comparator<Student> comparator = (student, t1) ->
                (student.age < t1.age) ? 1 : -1;

        List<Student> students = new ArrayList<>();
        students.add(new Student(26, "John"));
        students.add(new Student(12, "Jane"));
        students.add(new Student(31, "James"));
        students.sort(comparator);
        System.out.println(students.stream().reduce(
                1,
                (integer, student) -> {
                    System.out.println("upper");
                    System.out.println(integer + student.age);
                    return integer + student.age;
                },
                (integer, integer2) -> {
                    System.out.println("lower");
                    System.out.println(integer * integer2);
                    return integer * integer2;
                })
        );

        System.out.println(students.stream().reduce(0, (integer, student) -> integer + student.age, Integer::sum));


//        Collector<?, ?, ?> collector;
//        collector = Collector.of(
//                () -> "hello",
//                (s, o) -> System.out.println("test"),
//                (s, s2) -> s, "hello"
//        );
//        students.stream().collect(collector);

        System.out.println(students);
    }

}
