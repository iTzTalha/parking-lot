package models;

import enums.BillAmountCalculationStrategyType;
import enums.ParkingLotStatus;
import enums.SlotAssignmentStrategyType;
import enums.VehicleType;

import java.util.List;

public class ParkingLot extends BaseModel {
    private Location location;
    private List<ParkingFloor> parkingFloors;
    private List<Gate> gates;
    private List<VehicleType> supportedVehicleTypes;
    private ParkingLotStatus parkingLotStatus;
    private SlotAssignmentStrategyType slotAssignmentStrategyType;
    private BillAmountCalculationStrategyType billAmountCalculationStrategyType;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<ParkingFloor> getParkingFloors() {
        return parkingFloors;
    }

    public void setParkingFloors(List<ParkingFloor> parkingFloors) {
        this.parkingFloors = parkingFloors;
    }

    public List<Gate> getGates() {
        return gates;
    }

    public void setGates(List<Gate> gates) {
        this.gates = gates;
    }

    public List<VehicleType> getSupportedVehicleTypes() {
        return supportedVehicleTypes;
    }

    public void setSupportedVehicleTypes(List<VehicleType> supportedVehicleTypes) {
        this.supportedVehicleTypes = supportedVehicleTypes;
    }

    public ParkingLotStatus getParkingLotStatus() {
        return parkingLotStatus;
    }

    public void setParkingLotStatus(ParkingLotStatus parkingLotStatus) {
        this.parkingLotStatus = parkingLotStatus;
    }

    public SlotAssignmentStrategyType getSlotAssignmentStrategyType() {
        return slotAssignmentStrategyType;
    }

    public void setSlotAssignmentStrategyType(SlotAssignmentStrategyType slotAssignmentStrategyType) {
        this.slotAssignmentStrategyType = slotAssignmentStrategyType;
    }

    public BillAmountCalculationStrategyType getBillAmountCalculationStrategyType() {
        return billAmountCalculationStrategyType;
    }

    public void setBillAmountCalculationStrategyType(BillAmountCalculationStrategyType billAmountCalculationStrategyType) {
        this.billAmountCalculationStrategyType = billAmountCalculationStrategyType;
    }
}
