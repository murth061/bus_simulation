// I based rider stats off riders who had completed their journey,
//i.e i collected their arrival time, departure time, and instantiation time and
// used those to calculate the wait and transit times, right before I remove them
// from my rider array from that bus
// I did this so that the stats don't get messed up by a rider's data who hasn't finished
// the journey yet, since when the simulation ends lots of riders are mid transit/waiting still and don't
// have relevant data


public class Stats{

  public static double totalWait = 0;
  public static double totalTransit = 0;

  // variables for avg bus queue
  public static double avgBusCount = 0;
  public static double sum = 0;

  public static int maxQueue = 0;
  public static double maxWait = 0;


  // sum wait times
  // once every time a rider gets off
  public static void addWait(double w){
    totalWait += w;
  }
  // sum transit times
  // once every time a rider gets off
  public static void addTransit(double t){
    totalTransit += t;
  }
  // adding to the total bus passenger count and keeping track of n
  // instantiates once every bus event
  public static void busFull(int s){
    avgBusCount +=1;
    sum += s;
  }
  // check if the queue is longer than what we have
  public static void checkQueue(int len){
    if(len>maxQueue){
      maxQueue = len;
    }
  }
// check max wait case
  public static void checkWait(double v ){
    if(v>maxWait){
      maxWait = v;
    }
  }

  public static void printStats(){
    // average transit time
    int x = Simulation.totalArrivals;
    double avg = totalTransit/x;
    System.out.println("Average Transit Time: " + (avg/60) + " minutes");
    // average wait time
    avg = totalWait/x;
    System.out.println("Average Wait Time: " + (avg/60) + " minutes");
    // total people who got in line
    System.out.println("total people who got in line: " + Simulation.totalPeople);
    // how full each bus is on average
    System.out.println("How full each bus is on average: " + (sum/avgBusCount));
    //maximum queue length at a bus stop
    System.out.println("Max queue length at a stop: " + maxQueue);
    //maximum wait time
    System.out.println("Max wait time for a rider: " + (maxWait/60) + " minutes ");


  }



}
