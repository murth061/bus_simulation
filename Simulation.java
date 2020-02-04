// Credit to Chris Dovolis and CSCI 1933 faculty for classes Q1Gen, QGen, Segment, NGen, Event, PQ, PQInterface


import java.util.ArrayList;
public class Simulation{

  public static PQ agenda = new PQ();


  public static int totalPeople = 0; // how many riders we instantiate
  public static int totalDep = 0; // total departures of buses
  public static int totalLine = 0; // people who were in line at the stops
  public static int totalArrivals = 0; // total destination arrivals
  public static int[] stopSelect = {0, 0, 1, 1, 29, 29, 14, 14, 15, 15, 16, 16,2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
    17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28};
  public static ArrayList<Stop> stops = new ArrayList<Stop>();
  public static int[] expressStops = {0,1,29,14,15,16,4,8,12,20,24,28};
  public static int totalBoards = 0; // how many people boarded
  public static int expBusCount = 0;
  public static int regBusCount = 0;



  public static void main(String[] args){
    System.out.println("SIMULATION  ");
    // create stops and a riderevent for each stop and add to agenda using a loop
    for(int i = 0; i <30;i++){
      Stop newStop = new Stop(i);
      stops.add(newStop);
      agenda.add(new RiderEvent(newStop),0);
    }
    // create regular  buses and a bus event for each bus (adjustable parameters )
    // express bus at each express stop
    for(int i =0; i < 12;i++){
      if(i % 1 ==0){ // we modify this modulus function to determine how many express buses to create (easier than re rewriting code )
        expBusCount++;
        Bus bus = new Bus("express");
        agenda.add(new BusEvent(stops.get(i),bus),0);
      }
    }
    // creating some regular buses
    Bus bus1 = new Bus("regular");
    agenda.add(new BusEvent(stops.get(3),bus1),0);
    regBusCount++;
    Bus bus2 = new Bus("regular");
    agenda.add(new BusEvent(stops.get(9),bus2),0);
    regBusCount++;
    Bus bus3 = new Bus("regular");
    agenda.add(new BusEvent(stops.get(12),bus3),0);
    regBusCount++;
    Bus bus4 = new Bus("regular");
    agenda.add(new BusEvent(stops.get(17),bus4),0);
    regBusCount++;
    Bus bus5 = new Bus("regular");
    agenda.add(new BusEvent(stops.get(29),bus5),0);
    regBusCount++;
    Bus bus6 = new Bus("regular");
    agenda.add(new BusEvent(stops.get(19),bus5),0);
    regBusCount++;


    Bus bus7 = new Bus("regular");
    agenda.add(new BusEvent(stops.get(7),bus5),0);
    regBusCount++;
    Bus bus8 = new Bus("regular");
    agenda.add(new BusEvent(stops.get(26),bus5),0);
    regBusCount++;
    Bus bus9 = new Bus("regular");
    agenda.add(new BusEvent(stops.get(23),bus5),0);
    regBusCount++;
    Bus bus10 = new Bus("regular");
    agenda.add(new BusEvent(stops.get(18),bus5),0);
    regBusCount++;
    Bus bus11 = new Bus("regular");
    agenda.add(new BusEvent(stops.get(20),bus5),0);
    regBusCount++;
    Bus bus12 = new Bus("regular");
    agenda.add(new BusEvent(stops.get(23),bus5),0);
    regBusCount++;






    while(agenda.getCurrentTime() <= 10000){
      agenda.remove().run();
    }
    System.out.println("Simulation done ");
    System.out.println();
    System.out.println("Stats: ");
    System.out.println("Traffic Type: less frequent intervals ");
    System.out.println("Regular Buses: " + regBusCount);
    System.out.println("Express Buses: " + expBusCount );
    System.out.println("total bus departures from a bus stop: " + totalDep);
    System.out.println("total rider arrivals at a destination: " + totalArrivals);
    System.out.println("total people who boarded a bus: " + totalBoards);
    Stats.printStats();
  }

}
