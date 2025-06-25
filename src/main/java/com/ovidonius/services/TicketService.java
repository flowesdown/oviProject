package com.ovidonius.services;

import com.ovidonius.models.Ticket;
import com.ovidonius.models.enums.DiscountType;
import com.ovidonius.models.enums.TrainClass;
import com.ovidonius.models.enums.TrainType;
import com.ovidonius.repository.TicketParser;
import com.ovidonius.repository.TicketRepository;
import com.ovidonius.ui.SearchPanel;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(String resourceFilePath) {
        this.ticketRepository = new TicketRepository(resourceFilePath);
    }

    public List<Ticket> searchTickets(SearchPanel.SearchQuery query) {
        List<Ticket> result = new ArrayList<>();
        double discount = Optional.ofNullable(query.getDiscountType())
                .orElse(DiscountType.NONE)
                .getMultiplier();

        for (Ticket ticket : ticketRepository.getTickets()) {
            if (isTicketValid(ticket, query)) {
                result.add(copyTicketWithDiscount(ticket, discount));
            }
        }
        return result;
    }

    public void buyTheTicket(Ticket ticket) {
        if (ticket == null) return;

        ticketRepository.addBoughtTicket(ticket);
        TicketParser.writeBoughtTicketToFile(
                ticketRepository.getBoughtTicketFilePath(),
                ticketRepository.getBoughtTickets()
        );
    }

    private boolean isTicketValid(Ticket ticket, SearchPanel.SearchQuery query) {
        return isPriceAcceptable(ticket, query.getMaxPrice()) &&
                isViaStationValid(ticket, query) &&
                isStationMatch(ticket, query) &&
                isTimeInRange(ticket, query.getDepartureTimeStart(), query.getDepartureTimeEnd()) &&
                isTrainTypeValid(ticket, query.getTrainType()) &&
                isTrainClassValid(ticket, query.getTrainClass());
    }

    private boolean isPriceAcceptable(Ticket ticket, Double maxPrice) {
        return maxPrice == null || ticket.getOffer().getPrice() < maxPrice;
    }

    private boolean isViaStationValid(Ticket ticket, SearchPanel.SearchQuery query) {
        if (!query.isViaStationEnabled()) return true;
        return ticket.getOffer().getDirection().getPath().contains(query.getViaStation());
    }

    private boolean isStationMatch(Ticket ticket, SearchPanel.SearchQuery query) {
        List<?> path = ticket.getOffer().getDirection().getPath();
        return path.size() >= 2 &&
                Objects.equals(query.getFromStation(), path.get(0)) &&
                Objects.equals(query.getToStation(), path.get(path.size() - 1));
    }

    private boolean isTimeInRange(Ticket ticket, Date start, Date end) {
        Date departure = toDate(ticket.getOffer().getDepartureTime());
        Date arrival = toDate(ticket.getOffer().getArrivalTime());
        return !departure.before(start) && !arrival.after(end);
    }

    private boolean isTrainTypeValid(Ticket ticket, TrainType requiredType) {
        if (requiredType == null) return true;
        return ticket.getOffer().getTrain().getType() == requiredType;
    }

    private boolean isTrainClassValid(Ticket ticket, TrainClass requiredClass) {
        if (requiredClass == null) return true;
        return ticket.getOffer().getTrain().getTrainClass() == requiredClass;
    }

    private static Date toDate(LocalDateTime dateTime) {
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    private Ticket copyTicketWithDiscount(Ticket original, double discount) {
        Ticket copy = new Ticket(original);
        double discountedPrice = copy.getOffer().getPrice() * discount;
        copy.getOffer().setPrice(discountedPrice);
        return copy;
    }
}