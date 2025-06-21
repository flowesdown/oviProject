package com.ovidonius.repository;

import com.ovidonius.models.Ticket;
import java.util.List;

public class TicketRepository {
    private List<Ticket> tickets;
    private List<Ticket> boughtTickets;
    private String ticketFilePath;
    private String boughtTicketFilePath;

    public TicketRepository(String resourcesFilePath) {
        this.ticketFilePath = resourcesFilePath + "/tickets.txt";
        this.boughtTicketFilePath = resourcesFilePath + "/boughtTickets.txt";
        this.tickets = TicketParser.readTicketsFromFile(ticketFilePath);
        this.boughtTickets = TicketParser.readTicketsFromFile(boughtTicketFilePath);
    }
    public List<Ticket> getTickets() {return tickets;}
    public List<Ticket> getBoughtTickets() {return boughtTickets;}
    public String getTicketFilePath() {return ticketFilePath;}
    public String getBoughtTicketFilePath() {return boughtTicketFilePath;}
    private void deleteTicket(Ticket ticket) {
        tickets.remove(ticket);
    }
    public void addBoughtTicket(Ticket ticket) {
        boughtTickets.add(ticket);
        deleteTicket(ticket);
    }

}
