package repositories;

import enums.GateStatus;
import enums.GateType;
import exceptions.OperatorNotFoundException;
import models.Gate;
import models.Operator;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class GateRepository {
    private final Map<Long, Gate> gateMap;
    private final OperatorRepository operatorRepository;
    private static Long previousId = 0L;

    public GateRepository(OperatorRepository operatorRepository) throws OperatorNotFoundException {
        gateMap = new TreeMap<>();
        this.operatorRepository = operatorRepository;
        populateDummyGates();
    }

    private void populateDummyGates() throws OperatorNotFoundException {
        Gate entryGate = new Gate();
        entryGate.setGateType(GateType.ENTRY);
        entryGate.setGateStatus(GateStatus.OPEN);
        Optional<Operator> entryGateOperatorOptional = operatorRepository.getOperatorById(1L);
        if (entryGateOperatorOptional.isEmpty()) {
            throw new OperatorNotFoundException();
        }
        Operator entryGateOperator = entryGateOperatorOptional.get();
        entryGate.setGateOperator(entryGateOperator);
        saveGate(entryGate);

        Gate exitGate = new Gate();
        exitGate.setGateType(GateType.EXIT);
        exitGate.setGateStatus(GateStatus.OPEN);
        Optional<Operator> exitGateOperatorOptional = operatorRepository.getOperatorById(2L);
        if (exitGateOperatorOptional.isEmpty()) {
            throw new OperatorNotFoundException();
        }
        Operator exitGateOperator = exitGateOperatorOptional.get();
        exitGate.setGateOperator(exitGateOperator);
        saveGate(exitGate);
    }

    public Gate saveGate(Gate gate) {
        previousId++;
        gate.setId(previousId);
        gate.setCreatedAt(new Date());
        gateMap.put(previousId, gate);
        return gate;
    }

    public Optional<Gate> getGateById(Long gateID) {
        //SELECT * FROM Gates where id = :gateId;
        if (gateMap.containsKey(gateID)) {
            return Optional.ofNullable(gateMap.get(gateID));
        }
        return Optional.empty();
    }
}
