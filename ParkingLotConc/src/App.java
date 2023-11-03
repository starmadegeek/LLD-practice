public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World! I'm Parking Lot.");
        String[][][] parking = {{
                {"4-1", "4-1", "2-1", "2-0"},
                {"2-1", "4-1", "2-1", "2-1"},
                {"4-0", "2-1", "4-0", "2-1"},
                {"4-1", "4-1", "4-1", "2-1"}}};

        ParkingLot parkingLot = new ParkingLotImpl();
        Helper helper = new Helper();
        parkingLot.init(helper, parking);

        // Park a 2-wheeler vehicle
        ParkingResult parkResult1 = parkingLot.park(2, "AB123", "T123");
        System.out.println("Parking Result 1: " + parkResult1);

        // Park a 4-wheeler vehicle
        ParkingResult parkResult2 = parkingLot.park(4, "CD456", "T456");
        System.out.println("Parking Result 2: " + parkResult2);

        // Search for a vehicle by spotId
        ParkingResult searchResult1 = parkingLot.searchVehicle(parkResult1.getSpotId(), "", "");
        System.out.println("Search Result 1: " + searchResult1);

        // Remove a vehicle by spotId
        int removeResult1 = parkingLot.removeVehicle(parkResult1.getSpotId(), "", "");
        System.out.println("Remove Result 1: " + removeResult1);

        // Get the count of free 2-wheeler spots on the first floor
        int freeSpotsCount = parkingLot.getFreeSpotsCount(0, 2);
        System.out.println("Free 2-Wheeler Spots on Floor 1: " + freeSpotsCount);

        // Remove a vehicle by vehicleNumber
        int removeResult2 = parkingLot.removeVehicle("", parkResult2.getVehicleNumber(), "");
        System.out.println("Remove Result 2: " + removeResult2);

        System.out.println(parkingLot);

    }
}