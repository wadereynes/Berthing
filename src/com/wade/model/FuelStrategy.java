
package com.wade.model;

/**
 * The FuelStrategy class is used to sort the Incoming Tanker queue in the
 * SeaController class as well as deciding how the Tanker should be scheduled; the
 * schedule method will be called from the Harbor. schedule method.
 * The class will sort the Deberthing Tanker queue based on the fuel level of the Tanker,
 * which could be seed as the safest option, however it may cause an increased number of storms
 * due to the fact that it allows Tanker to berth if they can do so without endandgering the head of the
 * deberthing queue, which is perhaps dangerous for the 2nd/3rd Tanker in the Deberthing queue.
 * 
 * @Strategy
 * @author Gewa
 */
public class FuelStrategy extends Strategy { 
    
  
    
    public int compare (Object o1, Object o2) {
        Tanker a1 = (Tanker) o1;
        Tanker a2 = (Tanker) o2;
            return Integer.compare(a1.getFuelLevel(), a2.getFuelLevel());
    }
    
   
    public int schedule(ITanker Deberthing, ITanker Berthing) { 
        if (Deberthing == null) { 
            if (Berthing != null) { 
                return 2; 
            }
        } 
        else if (Berthing != null && Berthing.getWaitingTime() > Deberthing.getWaitingTime() && 
                    Berthing.getBerthingTime() < Deberthing.getFuelLevel() - Deberthing.getDeberthingTime()) { 
            return 2;
        }
        else {
            return 1;
        }
        return 0; 
    }
    
}
