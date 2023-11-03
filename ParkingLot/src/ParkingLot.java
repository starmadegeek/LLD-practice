import java.util.HashMap;

public class ParkingLot {
    private HashMap<String, Slot> parking;

    public ParkingLot(String[][][] parking, Helper01 helper) {
        // Helper01 helper = new Helper01();
        for (int floor = 0; floor < parking.length; floor++) {
            for (int row = 0; row < parking[floor].length; row++) {
                for (int column = 0; column < parking[floor][row].length; column++) {
                    String park = parking[floor][row][column];
                    String slotId = helper.getSpotId(floor, row, column);
                    int vehicleType = park.charAt(0);
                    this.parking
                        .put(slotId, new Slot(slotId, park.charAt(2) == '1', vehicleType, null));
                }
            }
        }
    }

    public Slot findSpot(int vehicleType, String vehicleNumber, String ticketId) {
        for(String slot: parking.keySet()){
            Slot spot = parking.get(slot);
            if(spot.isParkalbe() && spot.getVehicleType() == vehicleType && spot.getParked() == null){
                Ticket ticket = new Ticket(vehicleNumber, vehicleType, ticketId);
                spot.setParked(ticket);
                return spot;
            }
        }
        return null;
    }

    public int getFreeSpotsCount(int floor, int vehicleType, Helper01 helper01) {
        int count = 0;
        for (Slot slot : parking.values()) {
            if(slot.getFloor(helper01) == floor && !slot.isEmpty() && slot.getVehicleType() == vehicleType)
                count = count + 1;
        }
        return 0;
    }

    public boolean removeVehicleBySpotId(String spotId, Helper01 helper) {
        Slot slot = searchVehicleBySpotId(spotId, helper);
        boolean ret = false;
        if(slot != null) {
            slot.setParked(null);
            ret = true;
        }
        return ret;
    }

    public boolean removeVehicleByVehicleNumber(String vehicleNumber, Helper01 helper) {
        Slot slot = searchVehicleByVehicleNumber(vehicleNumber, helper);
        boolean ret = false;
        if(slot != null) {
            slot.setParked(null);
            ret = true;
        }
        return ret;
    }

    public boolean removeVehicleByTicketNumber(String ticketId, Helper01 helper) {
        Slot slot = searchVehicleByTicketNumber(ticketId, helper);
        boolean ret = false;
        if(slot != null) {
            slot.setParked(null);
            ret = true;
        }
        return ret;
    }

    public Slot searchVehicleBySpotId(String spotId, Helper01 helper) {
        if(parking.containsKey(spotId)){
            if(parking.get(spotId).getParked() != null) return parking.get(spotId);
        }
        return null;
    }

    public Slot searchVehicleByVehicleNumber(String vehicleNumber, Helper01 helper) {
        for (Slot slot : parking.values()) {
            if(slot.getParked() != null && slot.getParked().vehicleNumber().equals(vehicleNumber)) return slot;
        }
        return null;
    }

    public Slot searchVehicleByTicketNumber(String ticketId, Helper01 helper) {
        for (Slot slot : parking.values()) {
            if(slot.getParked() != null && slot.getParked().ticketNumber().equals(ticketId)) return slot;
        }
        return null;
    }
    
}
