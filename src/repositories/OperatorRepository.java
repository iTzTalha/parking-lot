package repositories;

import exceptions.LocationNotFoundException;
import models.Location;
import models.Operator;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class OperatorRepository {
    private final Map<Long, Operator> operatorMap;
    private final LocationRepository locationRepository;
    private static Long previousId = 0L;

    public OperatorRepository(LocationRepository locationRepository) throws LocationNotFoundException {
        operatorMap = new TreeMap<>();
        this.locationRepository = locationRepository;
        populateDummyOperators();
    }

    private void populateDummyOperators() throws LocationNotFoundException {
        Operator entryGateOperator = new Operator();
        entryGateOperator.setName("John");
        entryGateOperator.setEmpId(1);
        Optional<Location> entryGateOperatorLocationOptional = locationRepository.getLocationById(1L);
        if (entryGateOperatorLocationOptional.isEmpty()) {
            throw new LocationNotFoundException();
        }
        Location entryGateOperatorLocation = entryGateOperatorLocationOptional.get();
        entryGateOperator.setAddress(entryGateOperatorLocation);
        saveOperator(entryGateOperator);

        Operator exitGateOperator = new Operator();
        exitGateOperator.setName("Tom");
        exitGateOperator.setEmpId(2);
        Optional<Location> exitGateOperatorLocationOptional = locationRepository.getLocationById(2L);
        if (exitGateOperatorLocationOptional.isEmpty()) {
            throw new LocationNotFoundException();
        }
        Location exitGateOperatorLocation = exitGateOperatorLocationOptional.get();
        exitGateOperator.setAddress(exitGateOperatorLocation);
        saveOperator(exitGateOperator);
    }

    public Operator saveOperator(Operator operator) {
        previousId++;
        operator.setId(previousId);
        operator.setCreatedAt(new Date());
        operatorMap.put(previousId, operator);
        return operator;
    }

    public Optional<Operator> getOperatorById(Long operatorId) {
        if (operatorMap.containsKey(operatorId))
            return Optional.ofNullable(operatorMap.get(operatorId));
        return Optional.empty();
    }
}
