package dtos;

import models.Ticket;

public class IssueBillRequestDTO {
    private Long gateId;
    private Ticket ticket;

    public Long getGateId() {
        return gateId;
    }

    public void setGateId(Long gateId) {
        this.gateId = gateId;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
