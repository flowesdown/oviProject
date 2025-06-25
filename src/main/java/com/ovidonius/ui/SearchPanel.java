package com.ovidonius.ui;

import com.ovidonius.models.enums.*;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class SearchPanel extends JPanel {

    private JComboBox<StationType> fromStationBox;
    private JComboBox<StationType> toStationBox;
    private JCheckBox viaStationCheck;
    private JComboBox<StationType> viaStationBox;
    private JComboBox<TrainType> trainTypeBox;
    private JComboBox<TrainClass> trainClassBox;
    private JComboBox<DiscountType> discountTypeBox;
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

        fromStationBox = new JComboBox<>(StationType.values());
        toStationBox = new JComboBox<>(StationType.values());
        viaStationCheck = new JCheckBox("Via station");
        viaStationBox = new JComboBox<>(StationType.values());
        viaStationBox.setEnabled(false);
        discountTypeBox = new JComboBox<>(DiscountType.values());
        trainTypeBox = new JComboBox<>(TrainType.values());
        trainClassBox = new JComboBox<>(TrainClass.values());
        maxPriceField = new JTextField(10);
        searchButton = new JButton("Find trains");
        departureStartTimeSpinner = new JSpinner(new SpinnerDateModel());
        departureEndTimeSpinner = new JSpinner(new SpinnerDateModel());

        viaStationCheck.addActionListener(e -> viaStationBox.setEnabled(viaStationCheck.isSelected()));

        addLabeledComponent("Departure station:", fromStationBox, gbc, y++);
        addLabeledComponent("Arrival station:", toStationBox, gbc, y++);
        addViaStationComponents(gbc, y++);
        addLabeledComponent("Train type:", trainTypeBox, gbc, y++);
        addLabeledComponent("Train class:", trainClassBox, gbc, y++);
        addLabeledComponent("Max price:", maxPriceField, gbc, y++);
        addLabeledComponent("Departure time (start):", departureStartTimeSpinner, gbc, y++);
        addLabeledComponent("Departure time (end):", departureEndTimeSpinner, gbc, y++);
        addLabeledComponent("Discount type:", discountTypeBox, gbc, y++);

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        add(searchButton, gbc);
    }

    private void addViaStationComponents(GridBagConstraints gbc, int y) {
        gbc.gridy = y;

        gbc.gridx = 0;
        add(viaStationCheck, gbc);

        gbc.gridx = 1;
        add(viaStationBox, gbc);
    }

    private void addLabeledComponent(String label, Component component, GridBagConstraints gbc, int y) {
        gbc.gridy = y;

        gbc.gridx = 0;
        add(new JLabel(label), gbc);

        gbc.gridx = 1;
        add(component, gbc);
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

    public Date getDepartureTimeStart() {
        return (Date) departureStartTimeSpinner.getValue();
    }

    public Date getDepartureTimeEnd() {
        return (Date) departureEndTimeSpinner.getValue();
    }

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
                getDepartureTimeEnd(),
                (DiscountType) discountTypeBox.getSelectedItem()
        );
    }

    public static class SearchQuery {
        private final StationType fromStation;
        private final StationType toStation;
        private final boolean viaStationEnabled;
        private final StationType viaStation;
        private final TrainType trainType;
        private final TrainClass trainClass;
        private final Double maxPrice;
        private final Date departureTimeStart;
        private final Date departureTimeEnd;
        private final DiscountType discountType;

        public SearchQuery(StationType fromStation, StationType toStation, boolean viaStationEnabled,
                           StationType viaStation, TrainType trainType, TrainClass trainClass,
                           Double maxPrice, Date departureTimeStart, Date departureTimeEnd,
                           DiscountType discountType) {
            this.fromStation = fromStation;
            this.toStation = toStation;
            this.viaStationEnabled = viaStationEnabled;
            this.viaStation = viaStation;
            this.trainType = trainType;
            this.trainClass = trainClass;
            this.maxPrice = maxPrice;
            this.departureTimeStart = departureTimeStart;
            this.departureTimeEnd = departureTimeEnd;
            this.discountType = discountType;
        }

        public DiscountType getDiscountType() {
            return discountType;
        }

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

        public Date getDepartureTimeStart() {
            return departureTimeStart;
        }

        public Date getDepartureTimeEnd() {
            return departureTimeEnd;
        }
    }
}
