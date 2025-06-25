package com.ovidonius.ui;

import com.ovidonius.models.Ticket;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TicketResultPanel extends JPanel {
    private DefaultListModel<Ticket> ticketListModel;
    private JList<Ticket> ticketJList;
    private JButton buyButton;

    public TicketResultPanel() {
        setLayout(new BorderLayout());
        ticketListModel = new DefaultListModel<>();
        ticketJList = new JList<>(ticketListModel);
        ticketJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(ticketJList);

        buyButton = new JButton("Buy the ticket");

        add(new JLabel("Search result:"), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buyButton, BorderLayout.SOUTH);
    }

    public void updateResults(List<Ticket> tickets) {
        ticketListModel.clear();
        for (Ticket ticket : tickets) {
            ticketListModel.addElement(ticket);
        }
    }

    public JButton getBuyButton() {
        return buyButton;
    }

    public Ticket getSelectedTicket() {
        return ticketJList.getSelectedValue();
    }
}
