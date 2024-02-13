package repositories;

import models.Ticket;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class TicketRepository {
    private final Map<Long, Ticket> ticketMap = new TreeMap<>();
    private static Long previousId = 0L;

    public Ticket saveTicket(Ticket ticket) {
        //Save to DB and return saved ticket with id
        previousId++;
        ticket.setId(previousId);
        ticket.setCreatedAt(new Date());
        ticketMap.put(previousId, ticket);
        return ticket;
    }
}
