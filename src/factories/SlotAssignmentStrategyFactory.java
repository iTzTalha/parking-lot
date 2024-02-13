package factories;

import enums.SlotAssignmentStrategyType;
import strategies.RandomSlotAssignmentStrategy;
import strategies.SlotAssignmentStrategy;

public class SlotAssignmentStrategyFactory {
    public static SlotAssignmentStrategy getSlotAssignmentStrategyByType(SlotAssignmentStrategyType slotAssignmentStrategyType) {
        if (slotAssignmentStrategyType == SlotAssignmentStrategyType.RANDOM) {
            return new RandomSlotAssignmentStrategy();
        }
        return null;
    }
}
