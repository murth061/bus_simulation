
public class BusEvent implements Event{
  private Stop busStop;
  private int departures;
  private int stopTime;
  private int timeCount;
  private Stop nextStop;
  private Bus bus;


  public BusEvent(Stop busStop, Bus bus){
    this.bus = bus;
    this.busStop = busStop;
    this.departures = 0;
    this.stopTime = 0; // default, we adjust it in
    this.timeCount = 0; // how long it took to board/deboard riders
    this.nextStop = null;
  }

  public void run(){
    // stats
    Stats.busFull(bus.getRiderCount());
    Stats.checkQueue(busStop.getRegQueueLength() + busStop.getExpressQueueLength());
    Simulation.totalDep++;


    System.out.println("At bus stop " + busStop.getStop());
    // remove the riders and get how many departed as an int(to calculate how much time it took)
    departures = bus.removeRiders(busStop.getStop());
    Simulation.totalArrivals += departures;
    // now add riders to the bus
    if(bus.getType() == "express"){
      // (case1) add express riders
      stopTime = 975;
      // checks if the bus is full or if the queue is empty, and then adds an express rider 
      while(bus.getRiderCount() <= 50 && busStop.getExpressQueueLength() !=0){
        System.out.println("added express rider ");
        System.out.println(" regular - " + bus.getRiderCount());
        bus.addRider(busStop.getExpressRider());
        Simulation.totalBoards += 1;
        timeCount += 3;
        bus.increase();
      }
      timeCount += (departures * 2);
      if(timeCount > 15){
        stopTime -= 15;
        stopTime += timeCount;
      }
    }
    else if(bus.getType() == "regular"  ){
        // (case2) add regular riders
        stopTime = 255;
      while(bus.getRiderCount() <= 50 && busStop.getRegQueueLength() !=0){
        System.out.println(" regular - " + bus.getRiderCount());
        System.out.println("added regular rider ");
        System.out.println(" regular - " + bus.getRiderCount());
        bus.addRider(busStop.getRegRider());
        Simulation.totalBoards += 1;
        timeCount += 3;
        bus.increase();
      }
      timeCount += (departures*2);
      if(timeCount > 15){
        stopTime -= 15;
        stopTime += timeCount;
      }
    }

    // now we reschedule another Busevent for this bus using our stopTime calculated above (2 cases )

    int next =0; // set it to a default to avoid erros

    // express case 1
    if(bus.getType() == "express"){
      // figure out the next stop, use a switch statement since there are lots of particular cases
      switch(busStop.getStop()){
        case 0: next = 1;
                break;
        case 1: next = 4;
                break;
        case 4: next = 8;
                break;
        case 8: next = 12;
                break;
        case 12: next = 14;
                break;
        case 14: next = 15;
                break;
        case 15: next = 16;
                break;
        case 16: next = 20;
                break;
        case 20: next = 24;
                break;
        case 24: next = 28;
                break;
        case 28: next = 29;
                break;
        case 29: next = 0;
                break;
      }
      // match our integer value to the corresponding stop object

      for(Stop s : Simulation.stops){
        if(s.getStop() == next){
          nextStop = s;
        }
      }
      Simulation.agenda.add(new BusEvent(nextStop,bus),stopTime);
    }

  // regular case 2
    if(bus.getType() == "regular"){
    // set the next stop (only 1 unique case )
      if(busStop.getStop() == 29){
        next = 0;
      }
      else{
        next = busStop.getStop() + 1;
      }
      // match to corresponding stop object
      for(Stop s : Simulation.stops){
        if(s.getStop() == next){
          nextStop = s;
        }
      }
      Simulation.agenda.add(new BusEvent(nextStop,bus),stopTime);
    }
    }
  }
