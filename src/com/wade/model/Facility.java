
package com.wade.model;
import java.util.Random;

/**
 * The Facility class is used to control the creation of Tanker; it is created in the Harbor class
 * and has it`s methods called from the Harbor in order to potentially generate Tanker at every step.
 * The variables are not static/final due to the fact that they are required to be changeable, mostly
 * through the user interaction provided with the GUI.
 * 
 * @see Harbor#generateTanker()
 * 
 * @author Gewa
 */
public class Facility {
    
    private double COMMERCIAL_CREATION_PROBABILITY = 0.1;
    private double TUG_CREATION_PROBABILITY = 0.002;
    private double LIGHT_CREATION_PROBABILITY = 0.005;
    private Random gen = new Random(42);
    
    /**
     * Creates a new <code>Facility<code>.
     */
    
    Facility() {
        
    }
    
   
    
    public ITanker generateTanker() {
       
        if (COMMERCIAL_CREATION_PROBABILITY < 0.0 || TUG_CREATION_PROBABILITY < 0.0 || LIGHT_CREATION_PROBABILITY < 0.0 ||
                   COMMERCIAL_CREATION_PROBABILITY + TUG_CREATION_PROBABILITY + LIGHT_CREATION_PROBABILITY > 1.0)
            throw new IllegalArgumentException("Incorrect vehicle creation probabilities.");
        ITanker tanker;
        if (gen.nextDouble() <= TUG_CREATION_PROBABILITY) {
            tanker = generateTug(); 
        }
        else if (gen.nextDouble() <= LIGHT_CREATION_PROBABILITY + TUG_CREATION_PROBABILITY) {
            tanker = generateLightTanker();
        }
        else if (gen.nextDouble() <= COMMERCIAL_CREATION_PROBABILITY) {
            tanker = generateCommercialTanker(); 
        }
        else {
            tanker = null; 
        }
        
        if (tanker != null && gen.nextInt(100) < 50 && !(tanker instanceof Tug)) {
            tanker.setIsShipping(true);
        }
        
        return tanker; 
    }   
    
    
    public ITanker generateCommercialTanker() {
        @SuppressWarnings("unused") 
        int debug = gen.nextInt(40) + 40; 
        ITanker tanker = (ITanker) new CommercialTanker(gen.nextInt(40) + 40);
        return tanker;
    }
    
  
    
    public ITanker generateTug() {
        ITanker tanker = (ITanker) new Tug();
        return tanker;
    }
    
   
    
    public ITanker generateLightTanker() {
        ITanker tanker = (ITanker) new LightTanker(gen.nextInt(20) + 20);
        return tanker;
    }
   
    
    public void setProbabilities(double commercial, double tug, double light) {
        COMMERCIAL_CREATION_PROBABILITY = commercial;
        TUG_CREATION_PROBABILITY = tug;
        LIGHT_CREATION_PROBABILITY = light;
    }
    
    
    
    public void setSeed(int seed) {
        gen.setSeed(seed);
    }
}   
