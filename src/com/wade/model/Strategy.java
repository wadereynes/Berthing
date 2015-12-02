
package com.wade.model;
import java.util.Comparator;

/**
 * The Strategy class is used to sort the Deberthing Tanker queue in the SeaController
 * class, as well as deciding how the Tanker should be scheduled.
 * 
 * The concrete subclasses complete the method implementation, allowing for an endless
 * number of ways with which to sort the Deberthing queue and to schedule the Tanker;
 * it allows for easy future expansion.
 * 
 * @author Gewa
 */
public abstract class Strategy implements Comparator<Object> {
    
    
    
    public abstract int compare(Object o1, Object o2);
    
   
    
    
    public abstract int schedule(ITanker Deberthing, ITanker Berthing);
    
}
