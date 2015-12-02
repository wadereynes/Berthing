
package com.wade.model;

/**
 * The concrete subclass defines the characteristics of LightTanker; it defines the deberthing time,
 * the berthing time and the length of time  it can ship for (it`s fuel level) in order to 
 * successfully inherit from the PoweredTanker class.
 * 
 * @author Gewa
 */
public class LightTanker extends PoweredTanker {
    
    
    private static final int LIGHT_BERTHING = 4; 
    // The amount of time it takes to deberth
    private static final int LIGHT_DEBERTHING = 6; 
    
    
    
    public LightTanker(int fuelLevel) {
        super(LIGHT_DEBERTHING, LIGHT_BERTHING, fuelLevel);
    }
    
}
