Suriya Murthy
CSCI 1933 
4/26/2019
Readme


Author: Suriya Murthy ; murth061@umn.edu 
Running Instructions: 
* Compile all files in zip folder
* The driver class is Simulation.java
* To adjust buses, it can be done in the driver 
   * Express buses can be adjusted on line 35 by adjusting the modulus function
   * Regular buses can be adjusted by uncommenting the commented out code 
* To adjust rider frequencies, it can be done in the RiderEvent Class on lines 29 and 24 by adjusting the parameters in the equation that determines rider frequencies
* I decided to leave in some lines of code that print out different states of the simulation so you can see if there are any issues or how the code is functioning, i.e what is being executed. 
Project Organization: 
Classes: 
* Simulation
   * The main program, uses a loop to create all the stops and then is easily customizable to use different combos of buses 
* Stats 
   * Contains stats gathering methods, and a method to print out half of the statistics(the easier ones to gather are printed out in Simulation)
* Bus
   * Our object class for the buses
   * Contains the removeRider and addRider methods 
* BusEvent
   * One is instantiated every time a bus arrives at a bus stop 
   * Has cases for an express bus arrival and a regular bus arrival 
* Rider
   * Contains the algorithm i developed to determine the arrival destination based off the boarding destination 
   * Also contains the methods setBoardTime and setArrivalTime
* RiderEvent
   * Instantiates a new rider 
* Stop 
   * One created for each stop 
   * 1 queue for express riders and 1 for regular riders




Classes I didn't write: 
*  Credit to Chris Dovolis and CSCI 1933 faculty for classes Q1Gen, QGen, Segment, NGen, Event, PQ, PQInterface
Data Structures: 
* A priority queue was used to schedule events with relevant times. This was necessary to keep track of timed events, which our simulation depends upon. It also has a convenient getter to access the current time, important in gathering stats. 
* I used an ArrayList to store stops since it is easy to iterate through it with a for loop and the given methods automatically resize an array if it’s modified. 
* A linked list queue was used at the stop class for riders, since it’s very easy to dequeue from that computationally. (Q1Gen) 
Bugs: 
* I have express riders only getting on express buses (if they want to go to an express l have a boolean variable that is set to true(line 79) - Rider class )
   * This could cause potential issues since it is not completely realistic, however the project outline was vague in how to implement this so I just have regular riders going for regular buses and vice versa with express. 
* I needed to adjust the rider arrival rate to (600,1200) or more to get close to an equilibrium, but my results can still be used to draw meaningful conclusions since we can look at trends in the numbers instead of the numbers themselves to determine how many buses to use