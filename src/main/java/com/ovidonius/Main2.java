package com.ovidonius;

import com.ovidonius.models.Offer;
import com.ovidonius.models.Ticket;
import com.ovidonius.repository.TicketParser;
import com.ovidonius.services.OfferGenerator;

import java.net.URL;
import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        URL resource = Main.class.getClassLoader().getResource("tickets.txt");
        if (resource == null) {
            System.out.println("tickets.txt не найден");
            return;
        }

        String inputFile = resource.getPath();
        List<Ticket> tickets = TicketParser.readTicketsFromFile(inputFile);

        System.out.println(tickets.get(0).getOffer().getDirection());

        // Сохранение в новый файл
        String outputFile = "src/main/resources/tickets_copy.txt";
        TicketParser.writeTicketsToFile(outputFile, tickets);

        System.out.println("Тикеты сохранены в файл: " + outputFile);
    }
}
