import java.util.ArrayList;

public class RiderEvent implements Event{
  static double[] arrivalPercents = {.75, .75, .5, .5, .5, .2, .2, .2, .2, 0, 0,
  -.2, -.2, -.2, -.5, -.5, -.5, -.75, -.75};
  static int[] stopSelect = {0, 0, 1, 1, 29, 29, 14, 14, 15, 15, 16, 16,2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
    17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28};
  static int[] downTown = {0,1,29,14,15,16};
  private  Stop stop;
  private int stopNum;



  public RiderEvent(Stop stop){
    this.stop = stop;
    this.stopNum =  stop.getStop();
  }

  public void run(){
    double nextTime;
      // if stop is a downtown stop, do time that way(faster rider arrivals)

    if(stopNum == 0 || stopNum == 1 || stopNum == 29 || stopNum == 14 || stopNum == 15 || stopNum == 16){
      nextTime = (60 + arrivalPercents[(int)(Math.random()*19)]*60);
    }

      //if stop is non downtown do time that way(less frequent rider arrivals)
    else{
      nextTime = (120 + arrivalPercents[(int)(Math.random()*19)]*120);
    }
    //create new rider
    Simulation.agenda.add(new RiderEvent(stop),nextTime);
    Rider rider = new Rider(stopSelect[(int)(Math.random()*35)+0]);
    // figure out which stop to add the rider to now
    stop.addRider(rider);
    Simulation.totalLine++;
    //System.out.println("Rider Event at Bus stop  " + stop.getStop()+ " Express: " + stop.getExpressQueueLength() + " Regular: " + stop.getRegQueueLength()) ;
    // add a new rider event to the agenda

    // add rider to queue
  }


}
