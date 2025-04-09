package com.ovidonius.repository;

import com.ovidonius.models.Direction;
import com.ovidonius.models.Offer;
import com.ovidonius.models.Ticket;
import com.ovidonius.models.Train;
import com.ovidonius.models.enums.TrainClass;
import com.ovidonius.models.enums.TrainType;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TicketRepository {

    // Метод для сохранения тикетов в файл (в формате текста)
    // Метод для сохранения тикетов в файл (в формате текста)
    public static void saveTicketsToFile(List<Ticket> tickets, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Ticket ticket : tickets) {
                writer.write(ticket.toString());
                writer.write("\n\n"); // Пустая строка для разделения тикетов
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    // Метод для загрузки тикетов из файла (в формате текста)
    public static List<Ticket> loadTicketsFromFile(String fileName) {
        List<Ticket> tickets = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            StringBuilder ticketData = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                // Считываем данные тикета
                ticketData.append(line).append("\n");

                // Когда встретили пустую строку, предполагаем, что завершился тикет
                if (line.trim().isEmpty()) {
                    Ticket ticket = parseTicket(ticketData.toString());
                    tickets.add(ticket);
                    ticketData.setLength(0); // Очистить StringBuilder для следующего тикета
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tickets;
    }


    private static Ticket parseTicket(String ticketData) {
        // Разделяем строку на строки с помощью новой строки
        String[] lines = ticketData.split("\n");

        // Проверяем, что данные правильные, и строка не пуста
        if (lines.length < 3) {
            throw new IllegalArgumentException("Некорректный формат тикета: " + ticketData);
        }

        // Получаем Ticket ID
        String ticketId = lines[0].split(": ")[1].trim();

        // Извлекаем строку предложения (Offer), пропуская "Offer: " в начале
        String offerData = lines[1].split(": ", 2)[1].trim();

        // Убираем лишний "Offer: " в случае его наличия
        if (offerData.startsWith("Offer: ")) {
            offerData = offerData.substring(7).trim();  // Убираем первый "Offer: "
        }

        // Проверяем, что предложение правильно разделено
        if (offerData.isEmpty()) {
            throw new IllegalArgumentException("Некорректный формат данных предложения: " + offerData);
        }

        // Извлекаем данные из offerData
        String[] offerParts = offerData.split(", ");

        if (offerParts.length < 6) {
            throw new IllegalArgumentException("Некорректный формат данных предложения: " + offerData);
        }

        // Парсим данные
        String trainId = offerParts[0].split(" ")[1].trim(); // "Train TRAIN-1744232141144"
        TrainType trainType = TrainType.valueOf(offerParts[1].split(": ")[1].trim()); // "Type: FREIGHT"
        TrainClass trainClass = TrainClass.valueOf(offerParts[2].split(": ")[1].trim()); // "Class: FIRST_CLASS"
        double price = Double.parseDouble(offerParts[3].split(": ")[1].trim().replace(" USD", ""));
        LocalDateTime departureTime = LocalDateTime.parse(offerParts[4].split(": ")[1].trim());
        LocalDateTime arrivalTime = LocalDateTime.parse(offerParts[5].split(": ")[1].trim());

        // Создаем объект Train
        Train train = new Train(trainType, trainClass);
        // Создаем объект Direction (здесь я использую значения "Bucharest" и "Pitesti" как пример)
        Direction direction = new Direction("Bucharest", "Pitesti"); // Понадобится адаптировать под реальные данные

        // Создаем объект Offer с извлеченными данными
        Offer offer = new Offer(direction, train);
        offer.setPrice(price);
        offer.setDepartureTime(departureTime);
        offer.setArrivalTime(arrivalTime);

        // Создаем объект Ticket с извлеченными данными
        Ticket ticket = new Ticket(offer);
        ticket.setTicketId(ticketId);
        ticket.setIssuedAt(LocalDateTime.parse(lines[2].split(": ")[1].trim()));

        return ticket;
    }
}
