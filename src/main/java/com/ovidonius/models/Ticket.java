package com.ovidonius.models;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class Ticket {
    private String ticketId;      // Уникальный идентификатор тикета
    private Offer offer;          // Информация о предложении
    private LocalDateTime issuedAt; // Дата и время выпуска тикета

    // Конструктор тикета
    public Ticket(Offer offer) {
        this.ticketId = generateTicketId();
        this.offer = offer;
        this.issuedAt = LocalDateTime.now();
    }

    // Генерация уникального идентификатора для тикета
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
