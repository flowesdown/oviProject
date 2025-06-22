package com.ovidonius.ui;

import com.ovidonius.models.Ticket;
import com.ovidonius.services.TicketService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainWindow extends JFrame {

    private SearchPanel searchPanel;
    private TicketResultPanel ticketResultPanel;
    private TicketService ticketService;

    public MainWindow(String resourcesFilePath) {
        setTitle("E-Train Schedule");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        ticketService = new TicketService(resourcesFilePath);
        initComponents();
    }

    private void initComponents() {
        searchPanel = new SearchPanel();
        ticketResultPanel = new TicketResultPanel();

        add(searchPanel, BorderLayout.NORTH);
        add(ticketResultPanel, BorderLayout.CENTER);

        searchPanel.getSearchButton().addActionListener(e -> {
            SearchPanel.SearchQuery query = searchPanel.getQuery();
            List<Ticket> tickets = ticketService.searchTickets(query);
            ticketResultPanel.updateResults(tickets);
        });

        ticketResultPanel.getBuyButton().addActionListener(e -> {
            Ticket selected = ticketResultPanel.getSelectedTicket();
            if (selected != null) {
                JOptionPane.showMessageDialog(this, "Билет куплен:\n" + selected);
                ticketService.buyTheTicket(selected);
            } else {
                JOptionPane.showMessageDialog(this, "Пожалуйста, выберите билет");
            }
        });
    }
}
