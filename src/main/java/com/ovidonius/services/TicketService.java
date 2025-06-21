package com.ovidonius.services;

import com.ovidonius.models.Ticket;
import com.ovidonius.repository.TicketParser;
import com.ovidonius.repository.TicketRepository;
import com.ovidonius.ui.SearchPanel;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TicketService {
    TicketRepository ticketRepository;
    public TicketService(String resourceFilePath) {
        this.ticketRepository=new TicketRepository(resourceFilePath);
    }

    public List<Ticket> searchTickets(SearchPanel.SearchQuery query) {
        List<Ticket> goodTickets = new ArrayList<>();
        Date startDate = query.getDepartureTimeStart();
        Date endDate = query.getDepartureTimeEnd();
        for (Ticket ticket : ticketRepository.getTickets()) {
            boolean fromMatches = query.getFromStation().equals(
                    ticket.getOffer().getDirection().getPath().getFirst()
            );
            boolean toMatches = query.getToStation().equals(
                    ticket.getOffer().getDirection().getPath().getLast()
            );
            Date ticketDepartureDate = localDateTimeToDate(ticket.getOffer().getDepartureTime());
            Date ticketArrivalDate = localDateTimeToDate(ticket.getOffer().getArrivalTime());
            boolean departureAfterStart = !ticketDepartureDate.before(startDate);
            boolean arrivalBeforeEnd = !ticketArrivalDate.after(endDate);
            if (fromMatches && toMatches && departureAfterStart && arrivalBeforeEnd) {
                goodTickets.add(ticket);
            }
        }
        return goodTickets;
    }

    public void buyTheTicket(Ticket ticket) {
        if(ticket!=null){
            ticketRepository.addBoughtTicket(ticket);
            TicketParser.writeBoughtTicketToFile(ticketRepository.getBoughtTicketFilePath(), ticketRepository.getBoughtTickets());
        }
    }
    private Date localDateTimeToDate(LocalDateTime ldt) {
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }
}
