package com.ovidonius.ui;

import com.ovidonius.models.Ticket;
import com.ovidonius.repository.TicketParser;
import com.ovidonius.services.TicketService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainWindow extends JFrame {

    private SearchPanel searchPanel;
    private TicketResultPanel ticketResultPanel;
    private TicketService ticketService;

    public MainWindow(String ticketsFilePath) {
        setTitle("E-Train Schedule");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Инициализация компонентов
        ticketService = new TicketService(ticketsFilePath);  // Передаем путь к файлу
        initComponents();
    }

    private void initComponents() {
        // Панели
        searchPanel = new SearchPanel();
        ticketResultPanel = new TicketResultPanel();

        // Добавляем панели в окно
        add(searchPanel, BorderLayout.NORTH);
        add(ticketResultPanel, BorderLayout.CENTER);

        // Обработка кнопки поиска
        searchPanel.getSearchButton().addActionListener(e -> {
            // Получаем запрос с параметрами поиска
            SearchPanel.SearchQuery query = searchPanel.getQuery();

            // Получаем результаты на основе фильтров
            List<Ticket> tickets = ticketService.searchTickets(query);

            // Обновляем отображение с результатами
            ticketResultPanel.updateResults(tickets);
        });

        // Обработка кнопки покупки
        ticketResultPanel.getBuyButton().addActionListener(e -> {
            Ticket selected = ticketResultPanel.getSelectedTicket();
            if (selected != null) {
                JOptionPane.showMessageDialog(this, "Билет куплен:\n" + selected);
            } else {
                JOptionPane.showMessageDialog(this, "Пожалуйста, выберите билет");
            }
        });
    }

    public static void main(String[] args) {
        // Путь к файлу с билетами, например:
        String ticketsFilePath = "tickets.txt";  // Укажите путь к файлу с билетами

        SwingUtilities.invokeLater(() -> new MainWindow(ticketsFilePath).setVisible(true));
    }
}
