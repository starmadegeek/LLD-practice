
// ****** It's better to write code in your local code editor and paste it back here *********

import java.util.*;

import javafx.scene.effect.Light.Spot;
public class Solution implements Q001ParkingLotInterface {
    private Helper01 helper;
    private String[][][] parking;
    private ParkingLot parkingLot;
    private int floors=0;
    public Solution(){}

    /**
     - use helper.print() and helper.println() for logging
       normal System.out.println() logs won't appear
     - parking[2][8][15] = parking spot at 2nd floor , 8th row and 15th column (0 based index),
       its spotId will be: "2-8-15"
     */
    public void init(Helper01 helper, String[][][] parking) {
        this.helper=helper;
        this.parking=parking;
        this.floors=parking.length;
        // add more initializations code here as you require
        parkingLot = new ParkingLot(parking, helper);
        
        // helper.println("parking lot initialized");
    }

    /**
     * ParkingResult status 201 for success, 404 for error
     * vehicleType = 2 or 4 for 2-wheeler or 4-wheeler vehicle
     */
    public ParkingResult park(int vehicleType, String vehicleNumber, String ticketId){
        // use spotId=  helper.getSpotId(floor, row, column); to build spotId
        // or just do spotId = ""+floor+"-"+row+"-"+column
        Slot slot = parkingLot.findSpot(vehicleType, vehicleNumber, ticketId);
        if(slot != null)
            return new ParkingResult(201, slot.getSlotId(), vehicleNumber, ticketId);
        else
            return new ParkingResult(404, "", vehicleNumber, ticketId);
        // return new ParkingResult(201, "0-2-16", vehicleNumber, ticketId);
    }

    /**
     * - returns 201 success, 404 : vehicle not found or any other error,
     * - exactly one of spotId, vehicleNumber or ticketId will be non-empty
     */
    public int removeVehicle(String spotId, String vehicleNumber, String ticketId){
        // extracting floor, row, column of parking spot where vehicle is parked
        //Integer []location=helper.getSpotLocation(spotId);
        // int floor= location[0], row=location[1],column=location[2];
        // write code below to unpark the vehicle
        boolean removed = false;
        if(spotId != null && spotId != ""){
            removed =  parkingLot.removeVehicleBySpotId(spotId, helper);
        }
        else if(vehicleNumber != null && vehicleNumber != "") {
            removed = parkingLot.removeVehicleByVehicleNumber(vehicleNumber, helper);
        }
        else if(ticketId != null && ticketId != null)
            removed = parkingLot.removeVehicleByTicketNumber(ticketId, helper);

        // helper.print("vehicle removed");
        // return 201;
        return removed ? 201 : 404;
    }

    /** status = 200 : success, 404 : not found
     * exactly one of spotId, vehicleNumber or ticketId will be non-empty
     */
    public ParkingResult searchVehicle(String spotId, String vehicleNumber, String ticketId){
        Slot spot = null;
        if(spotId != null && spotId != ""){
            spot =  parkingLot.searchVehicleBySpotId(spotId, helper);
        }
        else if(vehicleNumber != null && vehicleNumber != "") {
            spot = parkingLot.searchVehicleByVehicleNumber(vehicleNumber, helper);
        }
        else if(ticketId != null && ticketId != null)
            spot = parkingLot.searchVehicleByTicketNumber(ticketId, helper);

        if(spot == null) 
            return new ParkingResult(404, spotId, vehicleNumber, ticketId);
        else
            return new ParkingResult(200, spot.getSlotId(), spot.getParked().getVehicleNumber(), spot.getParked().getTicketNumber());
    }

    // floor is 0-index based, i.e.  0<=floor<parking.length
    public int getFreeSpotsCount(int floor, int vehicleType){
        return parkingLot.getFreeSpotsCount(floor, vehicleType, helper);
    }
}

// uncomment below code in case you are using your local ide and
// comment it back again back when you are pasting completed solution in the online CodeZym editor
// this will help avoid unwanted compilation errors and get method autocomplete in your local code editor.

class ParkingResult{
    private int status; private String spotId, vehicleNumber, ticketId;
    ParkingResult(int status, String spotId, String vehicleNumber, String ticketId){
    this.status=status;this.spotId=spotId;this.vehicleNumber=vehicleNumber;this.ticketId=ticketId;}
    public int getStatus(){return status;}
    public String getSpotId(){return spotId;}
    public String getVehicleNumber(){return vehicleNumber;}
    public String getTicketId(){return ticketId;}
}

interface Q001ParkingLotInterface {
    void init(Helper01 helper, String [][][] parking);
    ParkingResult park(int vehicleType, String vehicleNumber, String ticketId);
    int removeVehicle(String spotId, String vehicleNumber, String ticketId);
    ParkingResult searchVehicle(String spotId, String vehicleNumber, String ticketId);
    int getFreeSpotsCount(int floor, int vehicleType);
}

class Helper01{
    void print(String s){System.out.print(s);} void println(String s){print(s+"\n");}
    String getSpotId(int floor, int row, int column) {return ""+floor + "-" + row + "-" + column;}
    Integer[] getSpotLocation(String spotId) {Integer[] location = {-1, -1, -1};String[] ss = spotId.split("-");
        for (int i = 0; i < 3 && i < ss.length; i++) {location[i] = Integer.parseInt(ss[i]);}return location;}
}
