public class Slot {
    private String slotId;
    private boolean parkalbe;
    private int vehicleType;
    private Ticket parked;
    
    public Slot(String slotId, boolean parkalbe, int vehicleType, Ticket parked) {
        this.slotId = slotId;
        this.parkalbe = parkalbe;
        this.vehicleType = vehicleType;
        this.parked = parked;
    }

    public String getSlotId() {
        return slotId;
    }

    public boolean isParkalbe() {
        return parkalbe;
    }

    public Ticket getParked() {
        return parked;
    }
    
    public boolean isEmpty(){
        return parked == null;
    }

    public int getVehicleType() {
        return vehicleType;
    }

    public void setParked(Ticket parked) {
        this.parked = parked;
    }

    public int getFloor(Helper01 helper01) {
        return helper01.getSpotLocation(slotId)[0];
    }

}
