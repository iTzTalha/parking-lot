package models;

import enums.BillStatus;

import java.util.Date;
import java.util.List;

public class Bill extends BaseModel {
    private String billNumber;
    private Ticket ticket;
    private Date exitTime;
    private Gate gateGeneratedAt;
    private Operator generatedBy;
    private double amount;
    private List<Payment> payments;
    private BillStatus billStatus;

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

    public Gate getGateGeneratedAt() {
        return gateGeneratedAt;
    }

    public void setGateGeneratedAt(Gate gateGeneratedAt) {
        this.gateGeneratedAt = gateGeneratedAt;
    }

    public Operator getGeneratedBy() {
        return generatedBy;
    }

    public void setGeneratedBy(Operator generatedBy) {
        this.generatedBy = generatedBy;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public BillStatus getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(BillStatus billStatus) {
        this.billStatus = billStatus;
    }
}
