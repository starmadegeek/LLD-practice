public class ParkingSpot {
    private final int type;
    private final String spotId;
    private final boolean parkable;
    private String vehicleNumber;
    private String ticketNumber;

    public ParkingSpot(int type, String spotId, boolean parkable) {
        this.type = type;
        this.spotId = spotId;
        this.parkable = parkable;
        this.vehicleNumber = null;
        this.ticketNumber = null;
    }

    public int getType() {
        return type;
    }

    public String getSpotId() {
        return spotId;
    }

    public boolean isParkable() {
        return parkable;
    }

    public boolean isEmpty(){
        return vehicleNumber == null || ticketNumber == null;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public boolean setVehicle(String vehicleNumber, String ticketNumber){
        setTicketNumber(ticketNumber);
        setVehicleNumber(vehicleNumber);
        return true;
    }

    public boolean removeVehicle(){
        return setVehicle(null, null);
    }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "type=" + type +
                ", spotId='" + spotId + '\'' +
                ", parkable=" + parkable +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                '}';
    }
}
