# 1. Design a Parking Lot

Solution - YouTube video explanation     
Solution - Blog with complete code     
Solution - Blog with a different Custom list explanation     

Write code for low level design (object oriented design) of a parking lot system.
The parking lot has two kinds of parking spaces: type = 2, for 2 wheeler vehicles and type = 4, for 4 wheeler vehicles.

There are multiple floors in the parking lot. On each floor, vehicles are parked in parking spots arranged in rows and columns.
For simplicity, lets assume that each floor will have same number of rows and each row will have same number of columns.

Some of the parking spots are inactive. You can't park your vehicle in those spots.

Your code will be tested in a multithreaded environment.
So make sure you take care of thread safety and synchronization.

Implement the below methods in Solution class:

init(Helper helper, String [][][] parking) :
- helper has methods like, helper.print("") and helper.println("") which you can use for printing logs
- parking[i][j][k] : parking spot on i-th floor, j-th row and k-th column.
- each item in parking is of the following type.
"4-1" : an active 4 wheeler parking spot,
"2-1" : an active 2 wheeler parking spot,
"4-0" and "2-0" are inactive parking spots, you can't park there.

park(int vehicleType, String vehicleNumber, String ticketId);
returns ParkingResult: (status,spotId, vehicleNumber, ticketId)
- This function assigns an empty parking spot to vehicle and maps vehicleNumber and ticketId to the assigned spotId
- spotId is floor+"-"+row+"-"+column
e.g. parking[2][8][15] = parking spot at 2nd floor , 8th row and 15th column (0 based index),
its spotId will be: "2-8-15"

removeVehicle(String spotId, String vehicleNumber, String ticketId)
Unparks or removes vehicle from parking spot. Exactly one of spotId, vehicleNumber and ticketId will be non-blank. vehicleId and ticketId are from the ones assigned in park() above

ParkingResult searchVehicle( String spotId, String vehicleNumber, String ticketId)
search the vehicle parked in one of park() method calls. Exactly one of spotId, vehicleNumber and ticketId will be non-blank.

int getFreeSpotsCount(int floor, int vehicleType)
At any point of time get the number of free spots of vehicle type (2 or 4 wheeler). 0>= floor < parking from init() method.


Constraints:
- type = 2 for two-wheeler vehicle, type = 4 for 4 wheeler vehicle
- 1<=floors<=5, 1<=rows<=10,000, 1<=columns<=10,000, 1<=rows*columns<=10,000

Input Example
parking = [[
["4-1","4-1","2-1","2-0"],
["2-1","4-1","2-1","2-1"],
["4-0","2-1","4-0","2-1"],
["4-1","4-1","4-1","2-1"]]]
Above input has 1 floor.
It has 4 rows and 4 columns on floor 0.
Total 7 active 2-wheeler vehicles and 6 active 4-wheeler vehicles are there.

e.g park(4, "bh234", "tkt4534") will return below ParkingResult object
   {
      status: 201,
      spotId: "0-3-1",
      vehicleNumber: "bh234",
      ticketId:"tkt4534"
   }
It assigns spot id 0-3-1 i.e parking spot from 0th floor, 2nd row and 4th column is assigned.
The spotId can be different depending on how you have implemented your solution.

- search("","bh234","") or search("","","tkt4534") at this point should return the above ParkingResult object with spotId = "0-3-1"
we can use vehicleNumber: "bh234" or ticketId:"tkt4534" to find the parking spot id where vehicle is parked.

- getFreeSpotsCount(0, 4) : will return 5.

- removeVehicle("0-3-1","","") or removeVehicle("","bh234","") or removeVehicle("","","tkt4534") :
should unpark the parked vehicle and getFreeSpotsCount(0, 4) after unparking will return 6.


parking = [[
["2-1","4-1","2-1","4-1"],
["2-1","2-1","4-1","4-1"]
],
[
["2-1","2-1","4-1","2-1"],
["4-1","4-1","2-1","2-1"]
]] 
Above input has 2 floors.
Each floor has 2 rows and 4 columns.
ParkingFloor 0 has 4 active 2-wheeler and 4 active 4-wheeler vehicle parking spots.
ParkingFloor 1 has 5 active 2-wheeler and 3 active 4-wheeler vehicle parking spots.

Note :
- put all import statements at the top before class Solution, else it will give compiler error
- any other class outside class Solution must be non public

