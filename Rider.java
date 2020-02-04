public class Rider{
  private int pickupStop;
  private int dropoffStop;
  private String direction;
  private Boolean express;
  // stats trackers
  private double startTime;
  private double boardTime;
  private double arrivalTime;
  private double wait;
  private double transit;


// for randomly selecting an pickup stop
  public static int[] stopSelect = {0, 0, 1, 1, 29, 29, 14, 14, 15, 15, 16, 16,2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
    17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28};
  public static int[] westStops = {16,16,17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28,29,29,0,0}; //18
  public static int[] eastStops = {1,1,2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,14,14,15,15};//18
  public static int[] expressStops = {0,1,29,14,15,16,4,8,12,20,24,28};

  public Rider (int pickupStop){
    Simulation.totalPeople++;
    this.wait = 0;
    this.transit = 0;
    this.boardTime = 0;
    this.arrivalTime = 0;
    this.startTime = Simulation.agenda.getCurrentTime();
    this.express = false; // default case
    this.pickupStop = pickupStop;
    if(pickupStop < 15){ // they are going east
      // array indices accessing cases(need a slightly different formula to access the array each time)
      this.direction = "east";
      if(pickupStop ==14){ // only 1 possible stop
        this.dropoffStop = 15;
      }
      else if(pickupStop ==13){
        this.dropoffStop = eastStops[(int)(Math.random()*3)+14];
      }
      else if(pickupStop ==0){ // any stop works
        this.dropoffStop = eastStops[(int)(Math.random()*17)+0];
      }
      else if(pickupStop == 1){
        this.dropoffStop = eastStops[(int)(Math.random()*15)+2];
      }
      else{ // pickup is between [2,12] and we can apply 1 formula for all these cases
        this.dropoffStop = eastStops[(int)(Math.random()*(16-pickupStop))+pickupStop+1];
      }
    }
    if(pickupStop >= 15){               // they are going west
      this.direction = "west";
      if(pickupStop == 29){
        this.dropoffStop = 0;
      }
      else if(pickupStop ==28){
        this.dropoffStop = westStops[(int)(Math.random()*3)+14];
      }
      else if(pickupStop==15){
        this.dropoffStop = westStops[(int)(Math.random()*17)+0];
      }
      else if(pickupStop==16){
        this.dropoffStop = westStops[(int)(Math.random()*15)+2];
      }
      else{ //pickup is between [17,27] and we can apply 1 formula for all these cases
        // transform west stop to something we can use in our formula
        int temp = pickupStop - 15;
        this.dropoffStop = westStops[(int)(Math.random()*(16-temp))+temp+1];
      }
    }
    // figure out if they can use an express bus
    for(int i : expressStops){
      if(i == dropoffStop){
        express = true;
      }
    }
    /*
    System.out.println("pick up " + pickupStop);
    System.out.println("drop off " + dropoffStop);
    System.out.println(" ");
    */
  }

  public int getDestination(){
    return dropoffStop;
  }

  public int getStart(){
    return pickupStop;
  }

  public double getTime(){
    return boardTime;
  }
  public String getDirection(){
    return direction;
  }
  public Boolean checkExpress(){
    return express;
  }
  public void setBoardTime(double t){
    boardTime = t;
  }
  public void setArrivalTime(double v){
    arrivalTime = v;
    wait = boardTime - startTime;
    transit = arrivalTime - boardTime;
    Stats.addWait(wait);
    Stats.addTransit(transit);
    Stats.checkWait(wait);
    // now send this data to stats class before we remove the rider
  }

}
