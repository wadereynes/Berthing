
package com.wade.model;

/**
 * The Harbor class is used to model an Harbor; it has a Port that planes can deberthing/berthing from,
 * it has a Facility where Tanker are stored(/created in this Simulation) and an Sea Controller which holds/
 * does the more autonomous management of the Tanker queues.
 * 
 * The Harbor class manages the front-end actions of the Tanker, including how to schedule them, as well as
 * when they would berthing/deberthing; it makes used of the @Strategy class in order to decide how to do this.
 * takes a Strategy as to how to handle the Deberthing/Berthing Tanker.
 * 
 * @author Gewa
 */
public class Harbor {
    
    private SeaController SC; 
    private Port port;        
    private Facility facility;
    private Strategy strategy;
    
    
    
    public Harbor() {
        strategy = new WaitingTimeStrategy();
        SC = new SeaController(strategy);
        port = new Port();
        facility = new Facility();
    }
    
   
    
    public void schedule() {
        generateTanker();
        SC.step();
        
        if (port.isAvailable()) {
            ITanker Deberthing = SC.getDeberthing().peek(); 
            ITanker Berthing = SC.getBerthing().peek();     
            
            switch(strategy.schedule(Deberthing, Berthing)) { 
                case 0: // do nothing
                    break;
                case 1: sea(Deberthing);
                    break;
                case 2: berthing(Berthing);
                    break;
            }
        }
        else {
            port.incrementTime();
        }
    }
    
    
    private void generateTanker() {
        
        ITanker tanker = facility.generateTanker();
        if (tanker != null) { 
            SC.getCounter().incrementTotalShips();
            
            if (tanker instanceof CommercialTanker) {
                SC.getCounter().incrementCommercialTanker(); 
            }
            else if (tanker instanceof Tug) {
                SC.getCounter().incrementTug(); 
            }
            else if (tanker instanceof LightTanker) {
                SC.getCounter().incrementLightTanker(); 
            }
            else {
                // could add new ship here
            }
            
            if (tanker.isShipping()) {
                SC.getDeberthing().add(tanker);
            }
            else {
                SC.getBerthing().add(tanker);  
            }
        }
        
    }
    
    
    
    private void berthing (ITanker a) {
        SC.getCounter().incrementBerthings();
        SC.getCounter().incrementWaitingTime(a.getWaitingTime());
        port.setAvailable(false);
        port.setOccupiedTime(a.getBerthingTime());
        SC.getBerthing().remove(a);
        if (a instanceof Tug) {
            ITanker light = facility.generateLightTanker();
            light.setWaitingTime(a.getWaitingTime());
            SC.getDeberthing().add(light);
            SC.getCounter().incrementTotalShips();
        }
    } 
    
    
    private void sea(ITanker a) {
        SC.getCounter().incrementDeberthings();
        port.setAvailable(false);
        port.setOccupiedTime(a.getDeberthingTime());
        SC.getDeberthing().remove(a);
    }
    
    
    
    public SeaController getSC() {
        return SC;
    }
    
    public Facility getFacility() {
        return facility;
    }
    
    
    
    public void setStrategy(int strategyNumber) {
        switch(strategyNumber) {
            case 0: strategy = new WaitingTimeStrategy();
                break;
            case 1: strategy = new FuelStrategy();
                break;
            case 2: // new strategy can be implemented here and so on.
                break;
        }
    }
    
    
    
    public void reset() {
        SC.clear();
        port.reset();
    }
}
