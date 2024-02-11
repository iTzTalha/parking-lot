package models;

import enums.GateStatus;
import enums.GateType;

public class Gate extends BaseModel {
    private String gameNumber;
    private GateStatus gateStatus;
    private GateType gateType;
    private Operator gateOperator;

    public String getGameNumber() {
        return gameNumber;
    }

    public void setGameNumber(String gameNumber) {
        this.gameNumber = gameNumber;
    }

    public GateStatus getGateStatus() {
        return gateStatus;
    }

    public void setGateStatus(GateStatus gateStatus) {
        this.gateStatus = gateStatus;
    }

    public GateType getGateType() {
        return gateType;
    }

    public void setGateType(GateType gateType) {
        this.gateType = gateType;
    }

    public Operator getGateOperator() {
        return gateOperator;
    }

    public void setGateOperator(Operator gateOperator) {
        this.gateOperator = gateOperator;
    }
}
