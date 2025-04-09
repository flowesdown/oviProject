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
    private Direction direction;  // Направление (маршрут)
    private Train train;          // Поезд
    private double price;         // Цена билета
    private LocalDateTime departureTime;  // Время отправления
    private LocalDateTime arrivalTime;    // Время прибытия

    // Конструктор, принимающий Direction и Train
    public Offer(Direction direction, Train train) {
        this.direction = direction;
        this.train = train;
        this.price = calculatePrice();
        this.departureTime = calculateDepartureTime();
        this.arrivalTime = calculateArrivalTime();
    }

    // Метод для расчета цены билета
    private double calculatePrice() {
        // Получаем время в пути из Direction (например, время в минутах)
        int travelTime = direction.getTime();  // Используем время, которое хранится в Direction
        double basePrice = travelTime * 1.5;   // Базовая цена: 1.5 за каждую минуту пути

        // Коэффициенты для типа поезда
        double typeCoefficient = getTypeCoefficient(train.getType());
        double classCoefficient = getClassCoefficient(train.getTrainClass());

        // Цена: время в пути * коэффициенты типа и класса поезда
        return basePrice * typeCoefficient * classCoefficient;
    }

    // Коэффициент для типа поезда
    private double getTypeCoefficient(TrainType type) {
        switch (type) {
            case EXPRESS:
                return 1.5;  // Экспресс более дорогой
            case REGULAR:
                return 1.0;  // Обычный поезд
            case FREIGHT:
                return 0.8;  // Грузовой поезд
            case LOCAL:
                return 0.9;  // Местный поезд
            default:
                return 1.0;
        }
    }

    // Коэффициент для класса поезда
    private double getClassCoefficient(TrainClass trainClass) {
        switch (trainClass) {
            case FIRST_CLASS:
                return 2.0;  // Первый класс дороже
            case SECOND_CLASS:
                return 1.5;  // Второй класс дешевле
            default:
                return 1.0;
        }
    }

    // Метод для расчета времени отправления
    private LocalDateTime calculateDepartureTime() {
        // Получаем текущее время
        LocalDateTime now = LocalDateTime.now();

        // Получаем количество часов, которое можно прибавить к текущему времени
        Random random = new Random();
        int randomHours = random.nextInt(5) + 1;  // Случайное количество часов (от 1 до 5)

        // Время отправления - это текущее время + случайное количество часов
        LocalDateTime departureTime = now.plusHours(randomHours);

        // Добавляем к времени отправления время из Direction (в часах)
        int travelTimeInHours = direction.getTime() / 60;  // Преобразуем минуты в часы
        departureTime = departureTime.plusHours(travelTimeInHours);

        return departureTime;
    }

    // Метод для расчета времени прибытия
    private LocalDateTime calculateArrivalTime() {
        // Время прибытия = время отправления + время в пути
        int travelTimeInMinutes = direction.getTime();  // Время в пути в минутах

        // Убедимся, что прибытие происходит после отправления
        LocalDateTime arrivalTime = departureTime.plusMinutes(travelTimeInMinutes);

        // Проверим, что время прибытия всегда позже времени отправления
        if (arrivalTime.isBefore(departureTime)) {
            arrivalTime = departureTime.plusMinutes(travelTimeInMinutes);
        }

        return arrivalTime;
    }

    // Переопределение toString для удобного вывода
    @Override
    public String toString() {
        return "Offer: " + "Train " + train.getTrainId() + ", Type: " + train.getType() +
                ", Class: " + train.getTrainClass() + ", Price: " + price + " USD" +
                ", Departure Time: " + departureTime + ", Arrival Time: " + arrivalTime;
    }
}
