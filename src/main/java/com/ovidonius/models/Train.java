package com.ovidonius.models;

import com.ovidonius.models.enums.TrainClass;
import com.ovidonius.models.enums.TrainType;
import lombok.Getter;

@Getter
public class Train {
    private TrainType type;   // Тип поезда (EXPRESS, REGULAR и т.д.)
    private TrainClass trainClass;  // Класс поезда (FIRST_CLASS, SECOND_CLASS)
    private String trainId;   // Уникальный идентификатор поезда

    // Конструктор, принимающий тип поезда и класс
    public Train(TrainType type, TrainClass trainClass) {
        this.type = type;
        this.trainClass = trainClass;
        this.trainId = generateTrainId();  // Генерация уникального идентификатора поезда
    }

    // Генерация уникального идентификатора для поезда
    private String generateTrainId() {
        return "TRAIN-" + System.currentTimeMillis();
    }


    // Переопределение метода toString для удобства вывода
    @Override
    public String toString() {
        return "Train ID: " + trainId + ", Type: " + type + ", Class: " + trainClass;
    }
}
