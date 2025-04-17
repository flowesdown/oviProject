package com.ovidonius.repository;

import com.ovidonius.models.*;
import com.ovidonius.models.enums.StationType;
import com.ovidonius.models.enums.TrainClass;
import com.ovidonius.models.enums.TrainType;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class TicketParser {

    public static List<Ticket> readTicketsFromFile(String filename) {
        List<Ticket> tickets = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            StringBuilder ticketBlock = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Ticket ID:")) {
                    if (ticketBlock.length() > 0) {
                        tickets.add(parseTicket(ticketBlock.toString()));
                        ticketBlock.setLength(0);
                    }
                }
                ticketBlock.append(line).append("\n");
            }

            if (ticketBlock.length() > 0) {
                tickets.add(parseTicket(ticketBlock.toString()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return tickets;
    }

    public static void writeTicketsToFile(String filename, List<Ticket> tickets) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Ticket ticket : tickets) {
                writer.write(serializeTicket(ticket));
                writer.newLine(); writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Ticket parseTicket(String block) {
        String[] lines = block.split("\n");

        String ticketId = lines[0].split(": ")[1].trim();
        String issuedAtStr = lines[1].split(": ")[1].trim();
        LocalDateTime issuedAt = LocalDateTime.parse(issuedAtStr);

        // Direction
        String pathLine = lines[2].split(": ")[1];
        List<StationType> path = new ArrayList<>();
        for (String name : pathLine.split(" -> ")) {
            path.add(StationType.valueOf(name));
        }
        int distance = Integer.parseInt(lines[3].split(": ")[1]);
        int time = Integer.parseInt(lines[4].split(": ")[1]);

        Direction direction = new Direction(path.get(0).name(), path.get(path.size() - 1).name());

        // Train
        String trainId = lines[5].split(": ")[1];
        TrainType trainType = TrainType.valueOf(lines[6].split(": ")[1]);
        TrainClass trainClass = TrainClass.valueOf(lines[7].split(": ")[1]);

        Train train = new Train(trainId, trainType, trainClass);

        // Offer
        double price = Double.parseDouble(lines[8].split(": ")[1]);
        LocalDateTime departure = LocalDateTime.parse(lines[9].split(": ")[1]);
        LocalDateTime arrival = LocalDateTime.parse(lines[10].split(": ")[1]);

        Offer offer = new Offer(direction, train);
        offer.setPrice(price);
        offer.setDepartureTime(departure);
        offer.setArrivalTime(arrival);

        Ticket ticket = new Ticket(offer);
        ticket.setTicketId(ticketId);
        ticket.setIssuedAt(issuedAt);

        return ticket;
    }

    private static String serializeTicket(Ticket ticket) {
        StringBuilder sb = new StringBuilder();

        Offer offer = ticket.getOffer();
        Direction direction = offer.getDirection();
        Train train = offer.getTrain();

        sb.append("Ticket ID: ").append(ticket.getTicketId()).append("\n");
        sb.append("Issued At: ").append(ticket.getIssuedAt()).append("\n");
        sb.append("Path: ").append(
                String.join(" -> ", direction.getPath().stream().map(Enum::name).toList())
        ).append("\n");
        sb.append("Distance: ").append(direction.getDistance()).append("\n");
        sb.append("Time: ").append(direction.getTime()).append("\n");

        sb.append("Train ID: ").append(train.getTrainId()).append("\n");
        sb.append("Train Type: ").append(train.getType()).append("\n");
        sb.append("Train Class: ").append(train.getTrainClass()).append("\n");

        sb.append("Price: ").append(offer.getPrice()).append("\n");
        sb.append("Departure Time: ").append(offer.getDepartureTime()).append("\n");
        sb.append("Arrival Time: ").append(offer.getArrivalTime());

        return sb.toString();
    }
}
