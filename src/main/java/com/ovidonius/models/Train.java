package com.ovidonius.models;

import com.ovidonius.models.enums.TrainClass;
import com.ovidonius.models.enums.TrainType;
import lombok.Getter;

@Getter
public class Train {

    private TrainType type;
    private TrainClass trainClass;
    private String trainId;

    public Train(TrainType type, TrainClass trainClass) {
        this.type = type;
        this.trainClass = trainClass;
        this.trainId = generateTrainId();
    }

    public Train(String trainId, TrainType type, TrainClass trainClass) {
        this.trainId = trainId;
        this.type = type;
        this.trainClass = trainClass;
    }

    private String generateTrainId() {
        return "TRAIN-" + System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "Train ID: " + trainId +
                ", Type: " + type +
                ", Class: " + trainClass;
    }
}
