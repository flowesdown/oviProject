package com.ovidonius.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Ticket {

    private String ticketId;
    private Offer offer;
    private LocalDateTime issuedAt;

    public Ticket(Offer offer) {
        this.ticketId = generateTicketId();
        this.offer = offer;
        this.issuedAt = LocalDateTime.now();
    }
    public Ticket(Ticket other) {
        this.ticketId = other.ticketId;
        this.offer = new Offer(other.offer); // тоже нужен copy-конструктор в Offer
    }


    private String generateTicketId() {
        return "TICKET-" + System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "Ticket ID: " + ticketId + "\n" +
                "Offer: " + offer.toString() + "\n" +
                "Issued At: " + issuedAt;
    }
}
