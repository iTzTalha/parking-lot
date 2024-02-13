package repositories;

import models.Location;

import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class LocationRepository {
    private final Map<Long, Location> locationMap;
    private static Long previousId = 0L;

    public LocationRepository() {
        locationMap = new TreeMap<>();
        populateDummyLocations();
    }

    private void populateDummyLocations() {
        Location entryGateOperatorLocation = new Location();
        saveLocation(entryGateOperatorLocation);

        Location exitGateOperatorLocation = new Location();
        saveLocation(exitGateOperatorLocation);

        Location parkingLotLocation = new Location();
        saveLocation(parkingLotLocation);
    }

    public Location saveLocation(Location location) {
        previousId++;
        location.setId(previousId);
        location.setCreatedAt(new Date());
        locationMap.put(previousId, location);
        return location;
    }

    public Optional<Location> getLocationById(Long locationId) {
        if (locationMap.containsKey(locationId)) {
            return Optional.ofNullable(locationMap.get(locationId));
        }
        return Optional.empty();
    }
}
