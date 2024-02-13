package controllers;

import dtos.IssueBillRequestDTO;
import dtos.IssueBillResponseDTO;
import enums.ResponseStatus;
import exceptions.GateNotFoundException;
import models.Bill;
import services.BillService;

public class BillController {
    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    public IssueBillResponseDTO issueBill(IssueBillRequestDTO requestDTO) {
        IssueBillResponseDTO responseDTO = new IssueBillResponseDTO();
        Bill bill;

        try {
            bill = billService.issueBill(requestDTO.getGateId(), requestDTO.getTicket());
        } catch (GateNotFoundException gateNotFoundException) {
            responseDTO.setResponseStatus(ResponseStatus.FAILED);
            responseDTO.setFailureMessage(gateNotFoundException.getMessage());
            return responseDTO;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        responseDTO.setBill(bill);
        responseDTO.setResponseStatus(ResponseStatus.SUCCESS);

        return responseDTO;
    }
}
