import java.util.ArrayList;

public class Bus{

  private ArrayList<Rider> riderList;
  Rider[] dropOff; // might not need
  int busCapacity;
  int riderCount;
  String type; // either express or regular

  // input an int
  public Bus(String type){
    this.riderList = new ArrayList<Rider>();
    this.busCapacity = 50;
    this.type = type; // regular or express
    this.riderCount = 0;
  }

  public void addRider(Rider r){
    r.setBoardTime(Simulation.agenda.getCurrentTime());
    riderList.add(r);
    riderCount++;
  }

  // method to remove riders from the bus and return an int of how many we removed.
  // riders are "discarded" upon removal and after stats are collected
  public int removeRiders(int dropStop){
    int removeCount = 0;
    for(int i = 0; i <riderList.size();i++){
      if(riderList.get(i).getDestination() == dropStop){
        //System.out.println("removed a rider ");
        riderList.get(i).setArrivalTime(Simulation.agenda.getCurrentTime());
        // we did the stats above (line 32) right before we remove the rider so that we don't lost the data
        riderList.remove(i);
        riderCount--;
        removeCount++;
        i--;
      }
    }
    System.out.println("removed " + removeCount);
    return removeCount;
  }


  public String getType(){
    return type;
  }
  public int getRiderCount(){
    return riderCount;
  }
  public void increase(){
    riderCount++;
  }
  public void decrease(){
    riderCount--;
  }



}
