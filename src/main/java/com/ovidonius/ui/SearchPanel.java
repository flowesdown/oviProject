package com.ovidonius.ui;

import com.ovidonius.models.enums.StationType;
import com.ovidonius.models.enums.TrainClass;
import com.ovidonius.models.enums.TrainType;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class SearchPanel extends JPanel {

    private JComboBox<StationType> fromStationBox;
    private JComboBox<StationType> toStationBox;
    private JCheckBox viaStationCheck;
    private JComboBox<StationType> viaStationBox;
    private JComboBox<TrainType> trainTypeBox;
    private JComboBox<TrainClass> trainClassBox;
    private JTextField maxPriceField;
    private JButton searchButton;
    private JSpinner departureStartTimeSpinner;
    private JSpinner departureEndTimeSpinner;

    public SearchPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 8, 8, 8);

        int y = 0;

        // Станции
        fromStationBox = new JComboBox<>(StationType.values());
        toStationBox = new JComboBox<>(StationType.values());
        viaStationCheck = new JCheckBox("Через станцию");
        viaStationBox = new JComboBox<>(StationType.values());
        viaStationBox.setEnabled(false);

        // Тип поезда и класс
        trainTypeBox = new JComboBox<>(TrainType.values());
        trainClassBox = new JComboBox<>(TrainClass.values());

        // Максимальная цена
        maxPriceField = new JTextField(10);

        // Кнопка поиска
        searchButton = new JButton("Найти поезда");

        // Настройка времени (для начала и окончания)
        departureStartTimeSpinner = new JSpinner(new SpinnerDateModel());
        departureEndTimeSpinner = new JSpinner(new SpinnerDateModel());

        // Слушатель чекбокса
        viaStationCheck.addActionListener(e -> viaStationBox.setEnabled(viaStationCheck.isSelected()));

        // Добавляем на панель
        gbc.gridy = y++;
        add(new JLabel("Станция отправления:"), gbc);
        add(fromStationBox, gbc);

        gbc.gridy = y++;
        add(new JLabel("Станция прибытия:"), gbc);
        add(toStationBox, gbc);

        gbc.gridy = y++;
        add(viaStationCheck, gbc);
        add(viaStationBox, gbc);

        gbc.gridy = y++;
        add(new JLabel("Тип поезда:"), gbc);
        add(trainTypeBox, gbc);

        gbc.gridy = y++;
        add(new JLabel("Класс поезда:"), gbc);
        add(trainClassBox, gbc);

        gbc.gridy = y++;
        add(new JLabel("Максимальная цена:"), gbc);
        add(maxPriceField, gbc);

        gbc.gridy = y++;
        add(new JLabel("Время отправления (начало):"), gbc);
        add(departureStartTimeSpinner, gbc);

        gbc.gridy = y++;
        add(new JLabel("Время отправления (конец):"), gbc);
        add(departureEndTimeSpinner, gbc);

        gbc.gridy = y++;
        gbc.gridwidth = 2;
        add(searchButton, gbc);
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public StationType getFromStation() {
        return (StationType) fromStationBox.getSelectedItem();
    }

    public StationType getToStation() {
        return (StationType) toStationBox.getSelectedItem();
    }

    public boolean isViaStationEnabled() {
        return viaStationCheck.isSelected();
    }

    public StationType getViaStation() {
        return (StationType) viaStationBox.getSelectedItem();
    }

    public TrainType getTrainType() {
        return (TrainType) trainTypeBox.getSelectedItem();
    }

    public TrainClass getTrainClass() {
        return (TrainClass) trainClassBox.getSelectedItem();
    }

    public Double getMaxPrice() {
        try {
            return Double.parseDouble(maxPriceField.getText());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    // Добавляем метод для получения времени отправления
    public java.util.Date getDepartureTimeStart() {
        return (java.util.Date) departureStartTimeSpinner.getValue();
    }

    public java.util.Date getDepartureTimeEnd() {
        return (java.util.Date) departureEndTimeSpinner.getValue();
    }

    // Структура запроса для фильтрации билетов
    public static class SearchQuery {
        private final StationType fromStation;
        private final StationType toStation;
        private final boolean viaStationEnabled;
        private final StationType viaStation;
        private final TrainType trainType;
        private final TrainClass trainClass;
        private final Double maxPrice;
        private final java.util.Date departureTimeStart;
        private final java.util.Date departureTimeEnd;

        public SearchQuery(StationType fromStation, StationType toStation, boolean viaStationEnabled,
                           StationType viaStation, TrainType trainType, TrainClass trainClass,
                           Double maxPrice, java.util.Date departureTimeStart, java.util.Date departureTimeEnd) {
            this.fromStation = fromStation;
            this.toStation = toStation;
            this.viaStationEnabled = viaStationEnabled;
            this.viaStation = viaStation;
            this.trainType = trainType;
            this.trainClass = trainClass;
            this.maxPrice = maxPrice;
            this.departureTimeStart = departureTimeStart;
            this.departureTimeEnd = departureTimeEnd;
        }

        // Геттеры
        public StationType getFromStation() {
            return fromStation;
        }

        public StationType getToStation() {
            return toStation;
        }

        public boolean isViaStationEnabled() {
            return viaStationEnabled;
        }

        public StationType getViaStation() {
            return viaStation;
        }

        public TrainType getTrainType() {
            return trainType;
        }

        public TrainClass getTrainClass() {
            return trainClass;
        }

        public Double getMaxPrice() {
            return maxPrice;
        }

        public java.util.Date getDepartureTimeStart() {
            return departureTimeStart;
        }

        public java.util.Date getDepartureTimeEnd() {
            return departureTimeEnd;
        }
    }

    // Получаем параметры из панели и возвращаем запрос
    public SearchQuery getQuery() {
        return new SearchQuery(
                getFromStation(),
                getToStation(),
                isViaStationEnabled(),
                getViaStation(),
                getTrainType(),
                getTrainClass(),
                getMaxPrice(),
                getDepartureTimeStart(),
                getDepartureTimeEnd()
        );
    }
}
