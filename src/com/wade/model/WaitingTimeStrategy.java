
package com.wade.model;

/**
 * The WaitingTimeStrategy class is used to sort the Deberthing Tanker queue in the
 * SeaController class as well as deciding how the Tanker should be scheduled; the
 * schedule method will be called from the Harbor. schedule method.
 * The class will sort the Deberthing Tanker queue based on the waiting time of the Tanker,
 * which could be seen as their Natural Ordering.
 * 
 * @see Strategy
 * 
 * @author Gewa
 */
public class WaitingTimeStrategy extends Strategy {
    
    
    
    public int compare(Object o1, Object o2) {
         
        Tanker a1 = (Tanker) o1;
        Tanker a2 = (Tanker) o2;
        
            return Integer.compare(a1.getWaitingTime(), a2.getWaitingTime());
    }
    
    
    
    public int schedule(ITanker Deberthing, ITanker Berthing) {
        
        if (Deberthing != null) {
            return 1;
        }
        else if (Berthing != null) {
            return 2;
        }
        else {
            return 0;
        }
    }
}
