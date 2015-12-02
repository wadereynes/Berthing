
package com.wade.model;

/**
 * The Counter class is used to store and display information regarding the Simulations;
 * it is used for both the basic console interface as well as the short and long GUI reports.
 * 
 * @author Gewa
 */
public class Counter {
    
    private int totalShips; 
    private int commercialTanker;
    private int tugs;
    private int lightTanker;
    private int waitingTime; 
    private int breakdowns; 
    private int storms; 
    private int berthings; 
    private int deberthings; 
    private int grounded; 
    private int shipping; 
    
    
    
    public Counter() {
        clear();
    }
    
    
    
    public void incrementTotalShips() {
        totalShips++;
    }
    
    
    
    public void incrementWaitingTime(int increment) {
        waitingTime += increment;
    }
    
    
    
    public void incrementBreakdowns() {
        breakdowns++;
    }
    
    
    
    public void incrementStorms() {
        storms++;
    }
    
    
    
    public void incrementCommercialTanker() {
        commercialTanker++;
    }
    
    
    
    public void incrementTug() {
        tugs++;
    }
    
   
    
    public void incrementLightTanker() {
        lightTanker++;
    }
    
   
    
    public int getStorms() {
        return storms;
    }
    
    
    
    public void incrementBerthings() {
        berthings++;
    }
    
    
    
    public int getBerthings() {
        return berthings;
    }
    
    
    
    public void incrementDeberthings() {
        deberthings++;
    }
    
    
    
    public int getDeberthings() {
        return deberthings;
    }
    
    
    
    public void setShipping(int shipping) {
        this.shipping = shipping;
    }
    
    
    public int getShipping() {
         return shipping;
    }
    
   
    
    public void setGrounded(int grounded) {
        this.grounded = grounded;
    }
    
    
    
    public int getGrounded() {
        return grounded;
    }
    
    
    
    public void clear() {
        totalShips = 0;
        commercialTanker = 0;
        tugs = 0;
        lightTanker = 0;
        waitingTime = 0;
        berthings = 0;
        deberthings = 0;
        breakdowns = 0;
        storms = 0;
        grounded = 0;
        shipping = 0;
    }
    
   
    
    public String toString() {
        return (
                " Total Ships: " + totalShips + "\n" +
                " Commercial Tanker: " + commercialTanker + "\n" +
                " Tugs: " + tugs + "\n" +
                " LightTankers: " + lightTanker + "\n" +
                " Total Berthings: " + berthings + "\n" +
                " Total Deberthings: " + deberthings + "\n" +
                " Average Waiting Time: " + waitingTime/totalShips + "\n" +
                " Total Storms: " + storms + "\n" +
                " Total Breakdowns: " + breakdowns + "\n" +
                " Ships still sea: " + grounded + "\n" +
                " Ships still shipping: " + shipping + "\n"
                );
    }
    
  
    
    public String longReport() {
        return (
        "       |       Total Ships: " + totalShips + 
        "       |       Sea: " + grounded +
        "       |       Berthings: " + berthings +
        "       |       Shipping: " + shipping +
        "       |       Deberthings: " + deberthings +
        "       |       Storms: " + storms +
        "       |       Breakdowns: " + breakdowns
        ); 
    }
    
}
