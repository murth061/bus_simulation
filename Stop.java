public class Stop{

  public Q1Gen<Rider> stopQueue;  // linked list queue
  public Q1Gen<Rider> expressQueue;
  public int stopNumber;
  public int expressQueueLength;
  public int regQueueLength;

  public Stop(int stopNumber){
    this.stopNumber =  stopNumber;
    this.expressQueueLength = 0;
    this.regQueueLength = 0;
    this.stopQueue = new Q1Gen<Rider>();
    this.expressQueue = new Q1Gen<Rider>();
  }
  // adds a rider to the corresponding queue, and increments our count of riders in that queue
  public void addRider(Rider rider){
    if(rider.checkExpress() == true){
      expressQueue.add(rider);
      expressQueueLength +=1;
    }
    else{
      stopQueue.add(rider);
      regQueueLength +=1;
    }
  }
  // returns the next express rider in the express queue
  public Rider getExpressRider(){
    expressQueueLength--;
    return expressQueue.remove();
  }
  // returns the next regular rider in the regular queue
  public Rider getRegRider(){
    regQueueLength--;
    return stopQueue.remove();
  }
  // other getters
  public int getStop(){
    return stopNumber;
  }

  public int getRegQueueLength(){
    return regQueueLength;
  }
  public int getExpressQueueLength(){
    return expressQueueLength;
  }


}
