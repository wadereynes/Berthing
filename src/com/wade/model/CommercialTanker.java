
package com.wade.model;

/**
 * This concrete subclass defines the characteristics of CommercialTanker; it defines the debert time,
 * the berthing time and the length of time it can fly for (it`s fuel level) in order to 
 * successfully inherit from the PoweredTanker class.
 * 
 * @author Gewa
 */
public class CommercialTanker extends PoweredTanker {
    

    private static final int COMMERCIAL_DEBERTHING = 4; 
    // The amount of time it takes to land
    private static final int COMMERCIAL_BERTHING = 6; 
    
    /**
     * Creating a new CommercialTanker, calling the constructor of the PoweredTanker class
     * and filling in the values required to instantiate the object.
     * 
     * @see PoweredTanker
     */
    
    public CommercialTanker(int fuelLevel) {
        super(COMMERCIAL_DEBERTHING, COMMERCIAL_BERTHING, fuelLevel);
    }
    
}
