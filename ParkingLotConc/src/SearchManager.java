import java.util.concurrent.ConcurrentHashMap;

public class SearchManager {
    private final ConcurrentHashMap<String, ParkingResult> cache;

    public SearchManager() {
        this.cache = new ConcurrentHashMap<>();
    }

    public void index(ParkingResult parkingResult){
        if(!parkingResult.getSpotId().isBlank()) this.cache.put("s>"+parkingResult.getSpotId(), parkingResult);
        if(!parkingResult.getVehicleNumber().isBlank()) this.cache.put("v>"+parkingResult.getVehicleNumber(), parkingResult);
        if(!parkingResult.getTicketId().isBlank()) this.cache.put("t>"+parkingResult.getTicketId(), parkingResult);
    }

    public ParkingResult search(String spotId, String vehicleNumber, String ticketId){
        if(spotId != null && !spotId.isEmpty()) return this.cache.get("s>"+spotId);
        if(vehicleNumber != null && !vehicleNumber.isEmpty()) return this.cache.get("v>"+vehicleNumber);
        if(ticketId != null && !ticketId.isEmpty()) return this.cache.get("t>"+ticketId);
        return null;
    }
}
