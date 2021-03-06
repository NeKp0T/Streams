package com.example.streams;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.example.streams.Album;
import com.example.streams.Artist;

public final class FirstPartTasks {

    public static final int NINETY_FIVE = 95;

    private FirstPartTasks() {
    }

    // Список названий альбомов
    public static List<String> allNames(Stream<Album> albums) {
        return albums.map(Album::getName).collect(Collectors.toList());
    }

    // Список названий альбомов, отсортированный лексикографически по названию
    public static List<String> allNamesSorted(Stream<Album> albums) {
        return albums.map(Album::getName).sorted().collect(Collectors.toList());
    }

    // Список треков, отсортированный лексикографически по названию, включающий все треки альбомов из 'albums'
    public static List<String> allTracksSorted(Stream<Album> albums) {
        return albums.map(Album::getTracks).flatMap(Collection::stream).map(Track::getName).sorted().collect(Collectors.toList());
    }

    // Список альбомов, в которых есть хотя бы один трек с рейтингом более 95, отсортированный по названию
    public static List<Album> sortedFavorites(Stream<Album> s) {
        return s.filter(album -> album.getTracks().stream().anyMatch(track -> track.getRating() > NINETY_FIVE)).sorted(Comparator.comparing(Album::getName)).collect(Collectors.toList());
    }

    // Сгруппировать альбомы по артистам
    public static Map<Artist, List<Album>> groupByArtist(Stream<Album> albums) {
        return albums.collect(
                Collectors.toMap(
                        Album::getArtist,
                        Collections::singletonList,
                        (albums1, albums2) -> Stream.concat(albums1.stream(), albums2.stream()).collect(Collectors.toList()))
        );
    }

    // Сгруппировать альбомы по артистам (в качестве значения вместо объекта 'Artist' использовать его имя)
    public static Map<Artist, List<String>> groupByArtistMapName(Stream<Album> albums) {
        return albums.collect(
                Collectors.toMap(
                        Album::getArtist,
                        album -> Collections.singletonList(album.getName()),
                        (albums1, albums2) -> Stream.concat(albums1.stream(), albums2.stream()).collect(Collectors.toList()))
        );
    }

    // Число повторяющихся альбомов в потоке
    public static long countAlbumDuplicates(Stream<Album> albums) {
        return albums.filter(new Predicate<Album>() {
            Set<Album> encountered = new TreeSet<Album>(Comparator.comparing(Album::getName));

            @Override
            public boolean test(Album album) {
                return !encountered.add(album);
            }
        }).count();
    }

    // Альбом, в котором максимум рейтинга минимален
    // (если в альбоме нет ни одного трека, считать, что максимум рейтинга в нем --- 0)
    public static Optional<Album> minMaxRating(Stream<Album> albums) {
        return albums.min(Comparator.comparing(FirstPartTasks::getMaxRating));
    }

    private static int getMaxRating(Album a) {
        return a.getTracks().stream().mapToInt(Track::getRating).max().orElse(0);
    }

    private static Double getAverageRating(Album a) {
        return a.getTracks().stream().mapToInt(Track::getRating).average().orElse(0);
    }

    // Список альбомов, отсортированный по убыванию среднего рейтинга его треков (0, если треков нет)
    public static List<Album> sortByAverageRating(Stream<com.example.streams.Album> albums) {
        return albums.sorted(Comparator.comparing(a -> -getAverageRating(a))).collect(Collectors.toList());
    }

    // Произведение всех чисел потока по модулю 'modulo'
    // (все числа от 0 до 10000)
    public static int moduloProduction(IntStream stream, int modulo) {
        return stream.reduce(1, (a, b) -> a * b % modulo);
    }

    // Вернуть строку, состояющую из конкатенаций переданного массива, и окруженную строками "<", ">"
    // см. тесты
    public static String joinTo(String... strings) {
        return Stream.of(strings).collect(Collectors.joining(", ", "<", ">"));
    }

    // Вернуть поток из объектов класса 'clazz'
    public static <R> Stream<R> filterIsInstance(Stream<?> s, Class<R> clazz) {
        return s.flatMap(o -> {
            try {
                return Stream.of(clazz.cast(o));
            } catch (ClassCastException e) {
                return Stream.empty();
            }
        });
    }
}