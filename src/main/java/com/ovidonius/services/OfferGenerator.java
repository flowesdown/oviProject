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

    public static List<Offer> generateOffers(int count) {
        CityGraph.init();
        List<Offer> offers = new ArrayList<>();

        while (offers.size() < count) {
            Offer offer = generateOffer();
            if (offer != null) {
                offers.add(offer);
            }
        }

        return offers;
    }

    private static Offer generateOffer() {
        StationType startCity = getRandomCity();
        StationType endCity = generateDifferentEndCity(startCity);

        System.out.printf("Generating offer: %s -> %s%n", startCity, endCity);

        Direction direction = new Direction(startCity.name(), endCity.name());

        if (direction.getTime() > 0) {
            TrainType trainType = getRandomTrainType();
            TrainClass trainClass = getRandomTrainClass();
            Train train = new Train(trainType, trainClass);
            return new Offer(direction, train);
        }

        return null;
    }

    private static StationType generateDifferentEndCity(StationType startCity) {
        StationType endCity = getRandomCity();
        while (endCity == startCity) {
            endCity = getRandomCity();
        }
        return endCity;
    }

    private static StationType getRandomCity() {
        StationType[] cities = StationType.values();
        return cities[random.nextInt(cities.length)];
    }

    private static TrainType getRandomTrainType() {
        TrainType[] trainTypes = TrainType.values();
        return trainTypes[random.nextInt(trainTypes.length)];
    }

    private static TrainClass getRandomTrainClass() {
        TrainClass[] trainClasses = TrainClass.values();
        return trainClasses[random.nextInt(trainClasses.length)];
    }
}
