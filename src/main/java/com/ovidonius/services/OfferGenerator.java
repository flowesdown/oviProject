package com.ovidonius.services;

import com.ovidonius.graph.CityGraph;
import com.ovidonius.models.Direction;
import com.ovidonius.models.Offer;
import com.ovidonius.models.Train;
import com.ovidonius.models.enums.StationType;
import com.ovidonius.models.enums.TrainClass;
import com.ovidonius.models.enums.TrainType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OfferGenerator {
    private static final Random random = new Random();

    // Метод для генерации заданного числа оферов
    public static List<Offer> generateOffers(int count) {
        // Инициализация графа городов
        CityGraph.init();

        List<Offer> offers = new ArrayList<>();

        while(offers.size() < count) {
            Offer offer = generateOffer(); // Генерируем один случайный офер

            // Добавляем созданный Offer в список, если он не пустой
            if (offer != null) {
                offers.add(offer);
            }
        }

        return offers;
    }

    // Метод для генерации одного случайного офера
    private static Offer generateOffer() {
        // Генерируем случайный город как стартовый пункт
        StationType startCity = getRandomCity();

        // Получаем список соседей для выбранного города
        List<StationType> neighbors = startCity.getNeighbors();

        // Если у города нет соседей, пропускаем эту поездку
        if (neighbors.isEmpty()) {
            return null;
        }

        // Случайным образом выбираем один из соседних городов для конечной точки
        StationType endCity = neighbors.get(random.nextInt(neighbors.size()));

        // Создаем Direction для выбранных городов
        Direction direction = new Direction(startCity.name(), endCity.name());

        // Если направление не пустое
        if (direction.getTime() > 0) {
            // Генерируем случайный тип поезда и класс поезда
            TrainType trainType = getRandomTrainType();
            TrainClass trainClass = getRandomTrainClass();

            // Создаем поезд с типом и классом
            Train train = new Train(trainType, trainClass);

            // Создаем Offer на основе Direction и Train
            return new Offer(direction, train);
        }

        return null; // Возвращаем null, если направление не существует
    }

    // Метод для получения случайного города
    private static StationType getRandomCity() {
        StationType[] cities = StationType.values();
        return cities[random.nextInt(cities.length)];
    }

    // Метод для получения случайного типа поезда
    private static TrainType getRandomTrainType() {
        TrainType[] trainTypes = TrainType.values();
        return trainTypes[random.nextInt(trainTypes.length)];
    }

    // Метод для получения случайного класса поезда
    private static TrainClass getRandomTrainClass() {
        TrainClass[] trainClasses = TrainClass.values();
        return trainClasses[random.nextInt(trainClasses.length)];
    }

    // Пример вызова метода и вывода результата
    public static void main(String[] args) {
        List<Offer> offers = generateOffers(10); // Генерируем 10 случайных оферов
        for (Offer offer : offers) {
            System.out.println(offer);
        }
    }
}
