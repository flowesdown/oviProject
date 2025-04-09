package com.ovidonius;

import com.ovidonius.models.Ticket;
import com.ovidonius.repository.TicketRepository;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Загружаем билеты из файла (если они есть)
        List<Ticket> loadedTickets = TicketRepository.loadTicketsFromFile("tickets.txt");

        // Выводим информацию о первом билете
        System.out.println("//////////////////////////////////////////////////////////////");

        // Печать пути, времени и дистанции
        System.out.println("Path: " + loadedTickets.get(0).getOffer().getDirection().getPath());
        System.out.println("Time: " + loadedTickets.get(0).getOffer().getDirection().getTime());
        System.out.println("Distance: " + loadedTickets.get(0).getOffer().getDirection().getDistance());
        System.out.println("Price: " + loadedTickets.get(0).getOffer().getPrice());
        System.out.println("Train Type: " + loadedTickets.get(0).getOffer().getTrain().getType());

        System.out.println("//////////////////////////////////////////////////////////////");

        // Сохраняем обновленные билеты обратно в файл
        TicketRepository.saveTicketsToFile(loadedTickets, "tickets.txt");

        // Загружаем и выводим все билеты (включая новые)
        System.out.println("Loaded Tickets:");
        for (Ticket ticket : loadedTickets) {
            System.out.println(ticket);
        }
    }
}
