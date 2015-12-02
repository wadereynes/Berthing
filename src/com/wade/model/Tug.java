
package com.wade.model;

/**
 * This concrete subclass defines the characteristics of Tugs; it defines the deberthing time and
 * the berthing time in order to successfully inherit from the UnpoweredTanker class.
 * 
 * @author Gewa
 */
public class Tug extends UnpoweredTanker {
    
  
    private static final int Tug_Berthing = 6; 
   
    private static final int Tug_Deberthing = 8;
    
    
    
    public Tug() {
        super(Tug_Deberthing, Tug_Berthing);
    }
}
