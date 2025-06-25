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

        searchPanel.getSearchButton().addActionListener(e -> performSearch());
        ticketResultPanel.getBuyButton().addActionListener(e -> purchaseSelectedTicket());
    }

    private void performSearch() {
        SearchPanel.SearchQuery query = searchPanel.getQuery();
        List<Ticket> tickets = ticketService.searchTickets(query);
        ticketResultPanel.updateResults(tickets);
    }

    private void purchaseSelectedTicket() {
        Ticket selected = ticketResultPanel.getSelectedTicket();
        if (selected != null) {
            JOptionPane.showMessageDialog(this, "Ticket purchased:\n" + selected);
            ticketService.buyTheTicket(selected);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a ticket.");
        }
    }
}
