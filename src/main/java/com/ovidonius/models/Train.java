package com.ovidonius.models;

import com.ovidonius.models.enums.TrainClass;
import com.ovidonius.models.enums.TrainType;
import lombok.Getter;

@Getter
public class Train {
    private TrainType type;          // Тип поезда (EXPRESS, REGULAR и т.д.)
    private TrainClass trainClass;  // Класс поезда (FIRST_CLASS, SECOND_CLASS)
    private String trainId;         // Уникальный идентификатор поезда

    // Конструктор с автогенерацией ID (если создаём новый поезд вручную)
    public Train(TrainType type, TrainClass trainClass) {
        this.type = type;
        this.trainClass = trainClass;
        this.trainId = generateTrainId();
    }

    // Конструктор с готовыми данными (например, из файла)
    public Train(String trainId, TrainType type, TrainClass trainClass) {
        this.trainId = trainId;
        this.type = type;
        this.trainClass = trainClass;
    }

    // Генерация ID по умолчанию
    private String generateTrainId() {
        return "TRAIN-" + System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "Train ID: " + trainId + ", Type: " + type + ", Class: " + trainClass;
    }
}
