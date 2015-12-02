
package com.wade.model;

/**
 * The ITanker class defines the functionality that all Tankers share, including both
 * the currently implemented Powered and Unpowered Tanker classes.
 * 
 * @author Gewa
 */
public interface ITanker extends Comparable<ITanker>  {
    
    
    public abstract void step();
    
    
    
    public abstract int getFuelLevel();
    
    
    
    public abstract int getID();
    
   
    
    public abstract int getDeberthingTime();
    
    
    
    public abstract int getBerthingTime();
    
   
    
    public abstract int getMaintenanceTime();
    
    
    
    public abstract int getWaitingTime();
    
    
    
    public abstract void incrementMaintenanceTime();
    
    
    
    public abstract void incrementWaitingTime();
    
    
    
    public abstract boolean isBrokeDown();
    
    
    
    public abstract void setBrokeDown(boolean brokeDown);
    
    
    
    public abstract void setWaitingTime(int waitingTime);
    
    
    
    public abstract boolean isStorm();
    
    
    
    public abstract void setIsShipping(boolean isShipping);
    
    
    
    public abstract boolean isShipping();
    
    
    
}
