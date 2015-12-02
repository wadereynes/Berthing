
package com.wade.test;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.wade.model.CommercialTanker;
import com.wade.model.FuelStrategy;
import com.wade.model.LightTanker;
/**
 *
 * @author Gewa
 */
public class TestFuelStrategy {
    
    Random rand = new Random(17);
    FuelStrategy strategy;
    LightTanker l1;
    CommercialTanker c1;
    CommercialTanker cn;
    
    @Before
    public void setUp() throws Exception {
        strategy = new FuelStrategy();
        
        c1 = new CommercialTanker(rand.nextInt(40) + 40);
        l1 = new LightTanker(rand.nextInt(20) + 20);
        cn = null;
    }
    
    @Test
    public void testCompare() {
        assertEquals(1, strategy.compare(c1, 11));
        assertEquals(-1, strategy.compare(l1, c1));
    }
    
    @Test
    public void testSchedule() {
        while(l1.getFuelLevel() > 13) {
            l1.step();
        }
        
        l1.setWaitingTime(0);
        c1.setWaitingTime(1); 
        assertEquals(2, strategy.schedule(l1, c1)); 
        assertEquals(1, strategy.schedule(c1, l1)); 
        
        l1.setWaitingTime(2);
        assertEquals(1, strategy.schedule(l1, c1));
        assertEquals(2, strategy.schedule(c1, l1)); 
        
        assertEquals(2, strategy.schedule(cn, c1)); 
        assertEquals(0, strategy.schedule(cn, cn));
        assertEquals(1, strategy.schedule(c1, cn)); 
    }
}
