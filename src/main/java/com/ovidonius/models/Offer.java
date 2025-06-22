package com.ovidonius.models;

import com.ovidonius.models.enums.TrainClass;
import com.ovidonius.models.enums.TrainType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Random;

@Getter
@Setter
public class Offer {

    private Direction direction;
    private Train train;
    private double price;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    public Offer(Direction direction, Train train) {
        this.direction = direction;
        this.train = train;
        this.price = calculatePrice();
        this.departureTime = calculateDepartureTime();
        this.arrivalTime = calculateArrivalTime();
    }

    private double calculatePrice() {
        int travelTime = direction.getTime();
        double basePrice = travelTime * 1.5;

        double typeCoefficient = getTypeCoefficient(train.getType());
        double classCoefficient = getClassCoefficient(train.getTrainClass());

        return basePrice * typeCoefficient * classCoefficient;
    }

    private double getTypeCoefficient(TrainType type) {
        switch (type) {
            case EXPRESS:
                return 1.5;
            case REGULAR:
                return 1.0;
            case FREIGHT:
                return 0.8;
            case LOCAL:
                return 0.9;
            default:
                return 1.0;
        }
    }

    private double getClassCoefficient(TrainClass trainClass) {
        switch (trainClass) {
            case FIRST_CLASS:
                return 2.0;
            case SECOND_CLASS:
                return 1.5;
            default:
                return 1.0;
        }
    }

    private LocalDateTime calculateDepartureTime() {
        LocalDateTime now = LocalDateTime.now();
        Random random = new Random();
        int randomHours = random.nextInt(5) + 1;
        LocalDateTime departure = now.plusHours(randomHours);

        int travelTimeInHours = direction.getTime() / 60;
        departure = departure.plusHours(travelTimeInHours);

        return departure;
    }

    private LocalDateTime calculateArrivalTime() {
        int travelTimeInMinutes = direction.getTime();
        LocalDateTime arrival = departureTime.plusMinutes(travelTimeInMinutes);

        if (arrival.isBefore(departureTime)) {
            arrival = departureTime.plusMinutes(travelTimeInMinutes);
        }

        return arrival;
    }

    @Override
    public String toString() {
        return "Offer: Path " + direction.getPath() +
                ", Train " + train.getTrainId() +
                ", Type: " + train.getType() +
                ", Class: " + train.getTrainClass() +
                ", Price: " + price + " USD" +
                ", Departure Time: " + departureTime +
                ", Arrival Time: " + arrivalTime;
    }
}
