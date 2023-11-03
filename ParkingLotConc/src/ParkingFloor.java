import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class ParkingFloor {
    private final int floorNumber;
    private final ParkingSpot[][] floor;
    private final HashMap<Integer, ConcurrentLinkedDeque<ParkingSpot>> freeSpots;
    private final ConcurrentHashMap<Integer, AtomicInteger> freeSpotsSize;

    public ParkingFloor(int floorNumber, String[][] parkingFloor) {
        this.floorNumber = floorNumber;
        this.freeSpots = new HashMap<>();
        this.freeSpotsSize = new ConcurrentHashMap<>();
        this.freeSpots.put(2, new ConcurrentLinkedDeque<>());
        this.freeSpots.put(4, new ConcurrentLinkedDeque<>());
        this.freeSpotsSize.put(2, new AtomicInteger(0));
        this.freeSpotsSize.put(4, new AtomicInteger(0));

        this.floor = new ParkingSpot[parkingFloor.length][parkingFloor[0].length];
        for (int row = 0; row < parkingFloor.length; row++) {
            for (int column = 0; column < parkingFloor[row].length; column++) {
                // int type = Integer.parseInt(String.valueOf(parkingFloor[row][column].charAt(0)));
                int type = parkingFloor[row][column].charAt(0) == '2' ? 2 : 4;
                boolean parkable = parkingFloor[row][column].charAt(2) == '1';
                String spotId = floorNumber + "-" + row + "-" + column;
                this.floor[row][column] = new ParkingSpot(type, spotId, parkable);
                this.freeSpots.get(type).add(this.floor[row][column]);
                this.freeSpotsSize.get(type).addAndGet(1);
            }
        }
    }

    public int getFreeSpots(int type) {
        return freeSpotsSize.get(type).get();
    }

    public ParkingResult park(int type, String vehicleNumber, String ticketNumber) {
        AtomicInteger size = this.freeSpotsSize.get(type);
        while (true) {
            int old = size.get();
            if (old <= 0) return new ParkingResult(404, null, vehicleNumber, ticketNumber);
            if (size.compareAndSet(old, old - 1)) {
                break;
            }
        }
        ParkingSpot freeSpot = this.freeSpots.get(type).poll();
        if (freeSpot != null && freeSpot.setVehicle(vehicleNumber, ticketNumber))
            return new ParkingResult(201, freeSpot.getSpotId(), vehicleNumber, ticketNumber);
        return new ParkingResult(404, null, vehicleNumber, ticketNumber);
    }

    public boolean remove(ParkingSpot spot) {
        if (!spot.isEmpty() && spot.removeVehicle()) {
            freeSpots.get(spot.getType()).add(spot);
            freeSpotsSize.get(spot.getType()).addAndGet(1);
            return true;
        }
        return false;
    }

    public ParkingSpot getSpot(int row, int column) {
        return floor[row][column];
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("floor:").append(floorNumber).append("[");
        for (ParkingSpot[] parkingSpots : floor) {
            res.append("[");
            for (ParkingSpot parkingSpot : parkingSpots) {
                res.append(parkingSpot.toString()).append(", ");
            }
            res.append(" ]");
        }
        res.append(" ]");
        return "ParkingFloor{" +
                "floorNumber=" + floorNumber +
                ", floor=" + res +
                ", free=" + getFreeSpots(2) + "  ][  " + getFreeSpots(4) +
                '}';
    }
}
