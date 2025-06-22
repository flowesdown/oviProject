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
        List<Offer> offers = OfferGenerator.generateOffers(1500);
        for(Offer offer : offers){
            tickets.add(new Ticket(offer));
        }
        TicketParser.writeTicketsToFile(filePath, tickets);
    }
}
