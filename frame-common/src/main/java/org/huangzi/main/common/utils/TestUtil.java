package org.huangzi.main.common.utils;

import lombok.*;
import org.huangzi.main.common.entity.TestEntity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author: XGLLHZ
 * @date: 2020/4/28 上午9:52
 * @description:
 */
public class TestUtil {

    public static void main(String[] args) {
//        List<String> list = Arrays.asList("B", "D", "A", "C", "B");
//        list.forEach(System.out::println);
//        list.stream().filter((s) -> s.startsWith("A")).forEach(System.out::println);
//        list.stream().filter((s) -> s.startsWith("A")).forEach(s -> System.out.println(s + "哈哈哈"));
//        list.stream().sorted().forEach(System.out::println);
//        boolean result1 = list.stream().anyMatch(s -> s.startsWith("A"));
//        boolean result2 = list.stream().allMatch(s -> s.startsWith("D"));
//        boolean result3 = list.stream().noneMatch(s -> s.startsWith("F"));
//        System.out.println(result1 + " " + result2 + " " + result3);
//        System.out.println(list.stream().filter(s -> s.startsWith("D")).count());
//        Optional<String> optional = list.stream().sorted().reduce((s1, s2) -> s1 + "-" + s2);
//        optional.ifPresent(System.out::println);
        /*int size = 1000000;
        long m = System.nanoTime();
        List<String> list1 = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            UUID uuid = UUID.randomUUID();
            list1.add(uuid.toString());
        }
        long n = System.nanoTime();
        long s = TimeUnit.NANOSECONDS.toMillis(n - m);
        System.out.println("for 循环 add 耗时: " + s +  "ms");
        long a = System.nanoTime();
        long res = list1.stream().sorted().count();
        long b = System.nanoTime();
        long c = TimeUnit.NANOSECONDS.toMillis(b - a);
        System.out.println("串行排序耗时:" + c);*/
        /*long a = System.nanoTime();
        long res = list1.parallelStream().sorted().count();
        long b = System.nanoTime();
        long c = TimeUnit.NANOSECONDS.toMillis(b - a);
        System.out.println("并行排序耗时: " + c + "ms");*/
//        list.stream().filter("D"::equals).forEach(s1 -> System.out.println(s1 + "遍历输出相等"));
//        list.stream().filter("D"::startsWith).forEach(System.out::println);
//        Map<Integer, String> map = new HashMap<>(5);
//        for (int i = 1; i < 5; i++) {
//            map.putIfAbsent(i, "name" + i);
//        }
//        System.out.println(map.getOrDefault(7, "name"));
//        map.merge(1, "name1", String::concat);
//        System.out.println(map.get(1));
//        LocalDate today = LocalDate.now();
//        LocalDate tomorrow = today.plusDays(1);
//        LocalDate yesterday = today.minusDays(1);
//        System.out.println(yesterday + " " + today + " " + tomorrow);
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println(now);
//        list.forEach(System.out::println);
//        list.stream().distinct().sorted().forEach(s -> System.out.print(s + " "));
//        List<String> list1 = list.stream().map(s -> s + s).collect(Collectors.toList());
//        list1.forEach(s -> System.out.print(s + " "));

        /*@Data
        @ToString
        @NoArgsConstructor
        @AllArgsConstructor
        @EqualsAndHashCode(callSuper = false)
        class Student implements Serializable {

            private Integer id;

            private String name;

            private Integer score;

        }*/

//        List<Student> list1 = new ArrayList<>(5);
        /*for (int i = 0; i < 10; i++) {
            Student student = new Student(i, "哈哈哈", null);
            list1.add(student);
        }*/
        /*list1.add(new Student(1, "陈杨", null));
        list1.add(new Student(2, "邓庆旭", null));
        list1.add(new Student(3, "付杨", null));
        list1.add(new Student(4, "郭浩", null));
        list1.add(new Student(5, "贺宇轩", null));*/

        /*List<Student> list2 = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            Student student = new Student(i, "哈哈哈", i);
            list2.add(student);
        }*/
        /*list2.add(new Student(1, "陈杨", 99));
        list2.add(new Student(2, "邓庆旭", 88));
        list2.add(new Student(3, "付杨", 77));
        list2.add(new Student(4, "郭浩", 66));
        list2.add(new Student(5, "贺宇轩", 55));*/

//        list.forEach(System.out::println);

//        List<String> list1 = list.stream().map(Student::getName).collect(Collectors.toList());
//        list1.forEach(s -> System.out.print(s + " "));

//        Optional<Integer> optional = list.stream().map(Student::getId).reduce(Integer::sum);
//        optional.ifPresent(System.out::println);

//        Optional<Integer> optional = list.stream().map(Student::getScore).filter(s -> s < 60).reduce(Integer::sum);
//        System.out.println(optional.orElse(0));

//        Map<Integer, String> map = list.stream().collect(Collectors.toMap(Student::getId, Student::getName));
//        System.out.println(map);

//        Map<Integer, String> map = list.stream().collect(Collectors.toMap(Student::getId, Student::getName, (a, b) -> b));
//        System.out.println(map);

//        Map<Integer, Student> map = list1.stream().collect(Collectors.toMap(Student::getId, student -> student));
//        System.out.println(map);

        /*List<Student> list = list1.stream()
                .filter(a ->
                        list2.stream().anyMatch(b ->
                                a.getId().equals(b.getId()))).collect(Collectors.toList());

        list.forEach(System.out::println);*/

        /*Runtime runtime = Runtime.getRuntime();
        runtime.gc();

        long xx = runtime.freeMemory();
        long x = System.nanoTime();
        Map<Integer, Student> map = list2.stream().collect(Collectors.toMap(Student::getId, student -> student));
        List<Student> list = list1.stream().map(a -> {
            a.setScore(map.get(a.getId()).getScore());
            return a;
        }).collect(Collectors.toList());
        long yy = runtime.freeMemory();
        long y = System.nanoTime();
        long z = TimeUnit.NANOSECONDS.toMillis(y - x);
        System.out.println("耗时: " + z + " 占用内存: " + (xx - yy));*/

        /*Runtime runtime1 = Runtime.getRuntime();
        runtime1.gc();
        long mm = runtime1.freeMemory();
        long m = System.nanoTime();
        for (Student student : list1) {
            for (Student student1 : list2) {
                if (student.getId().equals(student1.getId())) {
                    student.setScore(student1.getScore());
                }
            }
        }
        long nn = runtime1.freeMemory();
        long n = System.nanoTime();
        long s = TimeUnit.NANOSECONDS.toMillis(n - m);
        System.out.println("耗时: " + s + " 占用内存: " + (mm - nn));*/

//        TestEntity testEntity = TestEntity.builder().id(1).testName("人世间子").testMessage("成功").build();

//        System.out.println(TestEntity.builder().id(2).testName("哈哈哈").build().getTestName());

        //创建了一个对象
        String s1 = "s";
        String s2 = "s";
//        System.out.println(System.identityHashCode(s1));
//        System.out.println(System.identityHashCode(s2));

        String s3 = "q";
        String s4 = s3;
        System.out.println(System.identityHashCode(s3));
        System.out.println(System.identityHashCode(s4));

    }

}
