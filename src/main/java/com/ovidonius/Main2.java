package com.ovidonius;

import com.ovidonius.graph.CityGraph;
import com.ovidonius.models.Offer;
import com.ovidonius.models.Ticket;
import com.ovidonius.repository.TicketParser;
import com.ovidonius.services.OfferGenerator;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        CityGraph.init();

        String filePath = "src/main/resources/tickets.txt";
        List<Ticket> tickets = TicketParser.readTicketsFromFile(filePath);
//        System.out.println(tickets.get(0).getOffer().getDirection());
//        for(Ticket ticket : tickets) {
//            System.out.println(ticket.getOffer());
//        }
        List<Offer> offers = OfferGenerator.generateOffers(500);
//        List<Ticket> tickets = new ArrayList<>(offers.size());
        for(Offer offer : offers){
            tickets.add(new Ticket(offer));
        }


        // Сохранение в новый файл
        TicketParser.writeTicketsToFile(filePath, tickets);

        System.out.println("Тикеты сохранены в файл: " + filePath);
    }
}
