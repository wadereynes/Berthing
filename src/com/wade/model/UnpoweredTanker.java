
package com.wade.model;

/**
 * Characteristics the shared behaviors if all Unpowered Tanker; this class, alongside the
 * Powered Tanker class, fulfills the remaining Inheritance from the ITanker interface.
 * 
 * @see Tanker
 *
 * @author Gewa
 */
public class UnpoweredTanker extends Tanker {
    
    public UnpoweredTanker(int deberthingTime, int berthingTime) {
        super(deberthingTime, berthingTime);
    }
    
    public void step() {
        super.step();
    }
    
    public int getFuelLevel() {
        return Integer.MAX_VALUE;
    }
}
