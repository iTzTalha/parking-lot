package repositories;

import models.Bill;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class BillRepository {
    private final Map<Long, Bill> billMap;
    private Long previousId = 0L;

    public BillRepository() {
        billMap = new TreeMap<>();
    }

    public Bill saveBill(Bill bill) {
        previousId++;
        bill.setId(previousId);
        bill.setCreatedAt(new Date());
        billMap.put(previousId, bill);
        return bill;
    }
}
