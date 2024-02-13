package factories;

import enums.BillAmountCalculationStrategyType;
import strategies.BillAmountCalculationStrategy;
import strategies.TimeBasedBillAmountCalculationStrategy;

public class BillAmountCalculationStrategyFactory {
    public static BillAmountCalculationStrategy getBillAmountCalculationStrategyByType(BillAmountCalculationStrategyType billAmountCalculationStrategyType) {
        if (billAmountCalculationStrategyType == BillAmountCalculationStrategyType.TIME_BASED) {
            return new TimeBasedBillAmountCalculationStrategy();
        }
        return null;
    }
}
