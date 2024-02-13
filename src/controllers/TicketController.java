package controllers;

import dtos.IssueTicketRequestDTO;
import dtos.IssueTicketResponseDTO;
import enums.ResponseStatus;
import exceptions.GateNotFoundException;
import models.Ticket;
import services.TicketService;

public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public IssueTicketResponseDTO issueTicket(IssueTicketRequestDTO requestDTO) {
        IssueTicketResponseDTO responseDTO = new IssueTicketResponseDTO();
        Ticket ticket;

        try {
            ticket = ticketService.issueTicket(requestDTO.getGateId(), requestDTO.getVehicleNumber(), requestDTO.getVehicleType(), requestDTO.getOwnerName());
        } catch (GateNotFoundException gateNotFoundException) {
            responseDTO.setResponseStatus(ResponseStatus.FAILED);
            responseDTO.setFailureMessage(gateNotFoundException.getMessage());
            return responseDTO;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        responseDTO.setTicket(ticket);
        responseDTO.setResponseStatus(ResponseStatus.SUCCESS);

        return responseDTO;
    }
}
