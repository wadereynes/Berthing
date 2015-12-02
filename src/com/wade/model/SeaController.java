
package com.wade.model;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * The Sea Controller class manages the queues containing the active tanker;
 * it ensures that the tanker within the queues are sorted correctly based on the
 * strategy comparator that was passed through from the Harbor class.
 * 
 * @author Gewa
 */
public class SeaController {
    
    private Random gen = new Random(42);
    private PriorityQueue<ITanker> deberthing;
    private PriorityQueue<ITanker> berthing;
    private PriorityQueue<ITanker> brokenDown; 
    private Counter counter;
    
   
    
    public SeaController(Strategy strategy) {
        
        deberthing = new PriorityQueue<ITanker>(500, strategy);
        berthing = new PriorityQueue<ITanker>();
        brokenDown = new PriorityQueue<ITanker>();
        counter = new Counter();
    }
    
    
    
    public void step() {
        
        for (ITanker a : brokenDown.toArray(new ITanker[brokenDown.size()])) { 
            a.step();                                                          
            
            if (a.isBrokeDown() == false) { 
                berthing.add(a);            
                brokenDown.remove(a);       
            }
        }
        for (ITanker a: deberthing.toArray(new ITanker[deberthing.size()])) {
            a.step();
            
            if (a.isStorm()) {
               deberthing.remove(a);
               counter.incrementStorms();
               counter.incrementWaitingTime(a.getWaitingTime());
            }
        }
        for (ITanker a : berthing.toArray(new ITanker[berthing.size()])){
            a.step();
            
            if (a.isBrokeDown() == false) {
                if (a.isShipping() == false && gen.nextDouble() < 0.0001) { 
                    a.setBrokeDown(true);                                   
                    berthing.remove(a);                                     
                    brokenDown.add(a);                                      
                    counter.incrementBreakdowns();                          
                }
            }
        }
        counter.setGrounded(berthing.size() + brokenDown.size());
        counter.setShipping(deberthing.size());
    }
    
    
    
    public void finish() {
        for (ITanker a : deberthing) {
            counter.incrementWaitingTime(a.getWaitingTime());
        }
        for (ITanker a : berthing) {
            if (a instanceof Tug) {  
                counter.incrementWaitingTime(a.getWaitingTime());
                counter.incrementWaitingTime(a.getWaitingTime());
            }
            else {          
                counter.incrementWaitingTime(a.getWaitingTime());
            }
        }
        for (ITanker a : brokenDown) {
             counter.incrementWaitingTime(a.getWaitingTime());
        }
    }
    
    
    public PriorityQueue<ITanker> getDeberthing() {
         return deberthing;
    }
    
    
    
    public PriorityQueue<ITanker> getBerthing() {
        return berthing;
    }
    
    
    
    public PriorityQueue<ITanker> getBrokenDown() {
        return brokenDown;
    }
    
    
    
    public Counter getCounter() {
        return counter;
    }
    
    
    
    public void clear() {
        deberthing.clear();
        berthing.clear();
        brokenDown.clear();
        counter.clear();
    }
    
    
    
    public void setSeed(int seed) {
        gen.setSeed(seed);
    }
    
}
