package com.ovidonius.services;

import com.ovidonius.models.Ticket;
import com.ovidonius.repository.TicketParser;
import com.ovidonius.ui.SearchPanel;

import java.util.List;

public class TicketService {

    private String ticketsFilePath;

    public TicketService(String ticketsFilePath) {
        this.ticketsFilePath = ticketsFilePath;
    }

    public List<Ticket> searchTickets(SearchPanel.SearchQuery query) {
        // Здесь будет логика поиска билетов с использованием query и ticketsFilePath
        // Например, можно использовать TicketParser для чтения билетов из файла:
        List<Ticket> allTickets = TicketParser.readTicketsFromFile(ticketsFilePath);

        // Далее фильтровать билеты на основе параметров query
        // Для упрощения здесь вернем все билеты
        return allTickets;
    }
}
