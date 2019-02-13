package com.example.streams;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class SecondPartTasks {

    private SecondPartTasks() {
    }

    private static Stream<String> handleFile(String filePath) throws RuntimeException {
        try (var reader = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
            // toList and fromList to prevent laziness
            return reader.lines().collect(Collectors.toList()).stream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Найти строки из переданных файлов, в которых встречается указанная подстрока.
    public static List<String> findQuotes(List<String> paths, CharSequence sequence) throws RuntimeException {
        return paths.stream().flatMap(SecondPartTasks::handleFile).filter(s -> s.contains(sequence)).collect(Collectors.toList());
    }

    // В квадрат с длиной стороны 1 вписана мишень.
    // Стрелок атакует мишень и каждый раз попадает в произвольную точку квадрата.
    // Надо промоделировать этот процесс с помощью класса java.util.Random и посчитать, какова вероятность попасть в мишень.
    public static double piDividedBy4() {
        throw new UnsupportedOperationException();
    }

    // Дано отображение из имени автора в список с содержанием его произведений.
    // Надо вычислить, чья общая длина произведений наибольшая.
    public static String findPrinter(Map<String, List<String>> compositions) {
        return new ArrayList<>(compositions.entrySet()).stream().max(new Comparator<Map.Entry<String, List<String>>>() {
            @Override
            public int compare(Map.Entry<String, List<String>> o1, Map.Entry<String, List<String>> o2) {
                return o1.getValue().stream().collect(Collectors.joining()).toCharArray().length - o2.getValue().stream().collect(Collectors.joining()).toCharArray().length;
            }
        }).get().getKey();
    }

    // Вы крупный поставщик продуктов. Каждая торговая сеть делает вам заказ в виде Map<Товар, Количество>.
    // Необходимо вычислить, какой товар и в каком количестве надо поставить.
    public static Map<String, Integer> calculateGlobalOrder(List<Map<String, Integer>> orders) {
        throw new UnsupportedOperationException();
    }
}