import java.util.Arrays;

public class ParkingLotImpl implements ParkingLot {
    private Helper helper;
    private ParkingFloor[] floors;
    private SearchManager searchManager;

    public ParkingLotImpl() {
    }

    /**
     * - use helper.print() and helper.println() for logging
     * normal System.out.println() logs won't appear
     * - parking[2][8][15] = parking spot at 2nd floor, 8th row and 15th column (zero-based index),
     * its spotId will be: "2-8-15"
     */
    @Override
    public void init(Helper helper, String[][][] parking) {
        this.helper = helper;
        this.floors = new ParkingFloor[parking.length];
        for (int i = 0; i < parking.length; i++) {
            this.floors[i] = new ParkingFloor(i, parking[i]);
        }
        this.searchManager = new SearchManager();
        helper.println("parking lot initialized");
    }

    /**
     * ParkingResult status 201 for success, 404 for error
     * vehicleType = 2 or 4 for 2-wheeler or 4-wheeler vehicle
     */
    @Override
    public ParkingResult park(int vehicleType, String vehicleNumber, String ticketId) {
        // use spotId = helper.getSpotId(floor, row, column); to build spotId
        // or just do spotId = ""+floor+"-"+row+"-"+column
        if ((vehicleType != 2 && vehicleType != 4) || vehicleNumber.isBlank() || ticketId.isBlank())
            return new ParkingResult(404, "", vehicleNumber, ticketId);
        for (ParkingFloor floor : floors) {
            ParkingResult result = floor.park(vehicleType, vehicleNumber, ticketId);
            if (result != null && result.getStatus() == 201) {
                searchManager.index(result);
                return result;
            }
        }
        return new ParkingResult(404, "", vehicleNumber, ticketId);
    }

    /**
     * - returns 201 success, 404: vehicle not found or any other error,
     * - exactly one of spotId, vehicleNumber or ticketId will be non-empty
     */
    @Override
    public int removeVehicle(String spotId, String vehicleNumber, String ticketId) {
        // extracting floor, row, column of a parking spot where vehicle is parked
        // Integer []location=helper.getSpotLocation(spotId);
        // int floor= location[0], row=location[1],column=location[2];
        // write code below to unpark the vehicle
        ParkingResult result = searchVehicle(spotId, vehicleNumber, ticketId);
        if (result != null && result.getStatus() == 201) {
            Integer[] location = helper.getSpotLocation(result.getSpotId());
            ParkingSpot spot = this.floors[location[0]].getSpot(location[1], location[2]);
            if (this.floors[location[0]].remove(spot)) {
                searchManager.index(new ParkingResult(404, spot.getSpotId(), "", ""));
                helper.print("vehicle removed");
                return 201;
            }
            ;
        }
        helper.println("Error in vehicle remove");
        return 404;
    }

    /**
     * status = 200: success, 404: not found
     * exactly one of spotId, vehicleNumber or ticketId will be non-empty
     */
    @Override
    public ParkingResult searchVehicle(String spotId, String vehicleNumber, String ticketId) {
        return searchManager.search(spotId, vehicleNumber, ticketId);
    }

    // floor is 0-index based, i.e.  0<=floor<parking.length
    @Override
    public int getFreeSpotsCount(int floor, int vehicleType) {
        return floors[floor].getFreeSpots(vehicleType);
    }

    @Override
    public String toString() {
        return "ParkingLot{" +
                "floors=" + Arrays.toString(floors) +
                '}';
    }
}