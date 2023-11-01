public class Ticket {
    private String ticketNumber;
    private int vehicleType;
    private String vehicleNumber;
    
    public Ticket(String vehicleNumber, int vehicleType, String ticketNumber) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.ticketNumber = ticketNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public int getVehicleType() {
        return vehicleType;
    }

}
