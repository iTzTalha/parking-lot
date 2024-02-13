package factories;

import enums.SlotAssignmentStrategyType;
import repositories.ParkingLotRepository;
import strategies.RandomSlotAssignmentStrategy;
import strategies.SlotAssignmentStrategy;

public class SlotAssignmentStrategyFactory {
    public static SlotAssignmentStrategy getSlotAssignmentStrategyByType(SlotAssignmentStrategyType slotAssignmentStrategyType) {
        if (slotAssignmentStrategyType == SlotAssignmentStrategyType.RANDOM) {
            return new RandomSlotAssignmentStrategy(new ParkingLotRepository());
        }
        return null;
    }
}
