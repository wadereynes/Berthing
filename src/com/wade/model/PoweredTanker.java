
package com.wade.model;

/**
 * Characteristics the shared behaviors of all Powered Tanker; this class, alongside
 * Unpowered Tanker, fulfills the remaining inheritance from the ITanker interface.
 * @see Tanker
 * 
 * @author Gewa
 */
public class PoweredTanker extends Tanker{
    
    
    private int fuelLevel;
    
    
    public PoweredTanker(int deberthingTime, int berthingTime, int fuelLevel) {
        super(deberthingTime, berthingTime);
        this.fuelLevel = fuelLevel;
    }
    
    
    
    public void step() {
        super.step();
        fuelLevel--;
        if (isShipping && (fuelLevel - deberthingTime) < 0) { 
            storm = true; 
        }
    }
    
    
    public int getFuelLevel() {
        return fuelLevel;
    }
}
