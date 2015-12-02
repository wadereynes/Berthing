
package com.wade.model;

/**
 * The Tanker class implements the ITanker interface and contains the
 * fields shared by all Tanker and the majority of the method implementation
 * required by the ITanker interface, however it still allows for expansion and
 * currently has two the two separate types of Tanker below it (Powered & Unpowered)
 * as well as the concrete subclasses (Commercial, Tug, Light).
 * 
 * @see ITanker
 * @author Gewa
 */
public abstract class Tanker implements ITanker {
    
    
    
    protected int deberthingTime;
    
    protected int berthingTime;
    
    private int waitingTime;
    
    
    protected boolean brokeDown;
    
    protected int maintenanceTime;
    private static int lastID = 0;
    private int ID;
    
    
    protected boolean isShipping;
    
    protected boolean storm;
    
    
    public Tanker(int deberthingTime, int berthingTime) {
        this.deberthingTime = deberthingTime;
        this.berthingTime = berthingTime;
        brokeDown = false;
        storm = false;
        maintenanceTime = 0;
        waitingTime = 0;
        this.ID = nextID();
    }
    
    
    
    public void step() {
        incrementWaitingTime();
        if (isBrokeDown()) {
            incrementMaintenanceTime();
            if (maintenanceTime >= 120) {
                brokeDown = false;
                maintenanceTime = 0;
            }
        }
    }
    
    
    
    public int getID() {
        return ID;
    }
    
    
    
    public int nextID() {
        return ++lastID;
    }
    
    
    
    public int getMaintenanceTime() {
        return maintenanceTime;
    }
    
    
    
    public int getDeberthingTime() {
        return deberthingTime;
    }
    
    
    
    public int getBerthingTime() {
        return berthingTime;
    }
    
    
    
    public int getWaitingTime() {
         return waitingTime;
    }
    
    
    
    public void incrementMaintenanceTime() {
        maintenanceTime++;
    }
    
    
    
    public void incrementWaitingTime() {
        waitingTime++;
    }
    
    
    
    public boolean isBrokeDown() {
        return brokeDown;
    }
    
    
    
    public void setBrokeDown(boolean brokeDown) {
        this.brokeDown = brokeDown;
    }
    
    
    
    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }
    
    
    
    public boolean isStorm() {
        return storm;
    }
    
    
    
    public void setIsShipping(boolean isShipping) {
        this.isShipping = isShipping;
    }
    
    
    
    public boolean isShipping() {
        return isShipping;
    }
    
    
    
    public int compareTo(ITanker a) {
        return Integer.compare(a.getID(), ID);
    }
}
