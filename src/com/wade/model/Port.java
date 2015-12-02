
package com.wade.model;

/**
 * The Port class is used to accommodate Tanker deberth and berth;
 * it is simple yet critical to the Simulation. It is used in the Harbor class,
 * where the Simulation can manipulate it`s methods in order to see whether the
 * Port is available for a ship to deberth or berth. The functionality is relatively
 * simple, using two Integers and a boolean to set the availability of the Port.
 * 
 * @see Harbor
 * @author Gewa
 */
public class Port { 
    
    private boolean isAvailable;
    private int occupiedTime;
    private int clearPort; 
    
    
    
    public Port() {
        reset();
    }
    
    
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    
    
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    
   
    
    public void setOccupiedTime(int occupiedTime) {
        this.occupiedTime = occupiedTime;
    }
    
    
    
    public void incrementTime () {
        clearPort++;
        if (clearPort >= occupiedTime) {
            clearPort = 0;
            occupiedTime = 0;
            isAvailable = true;
        }
    }
    
    public void reset() {
        isAvailable = true;
        clearPort = 0;
        occupiedTime = 0;
    }
    
}
