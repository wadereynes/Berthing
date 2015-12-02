
package com.wade.test;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.wade.model.CommercialTanker;
import com.wade.model.LightTanker;
import com.wade.model.WaitingTimeStrategy;
/**
 *
 * @author Gewa
 */
public class TestWaitingTimeStrategy {
    
    
    Random rand = new Random(17);
    WaitingTimeStrategy strategy;
    LightTanker l1;
    CommercialTanker c1;
    CommercialTanker cn;
    
    @Before
    public void setUp() throws Exception {
        strategy = new WaitingTimeStrategy();
        
        c1 = new CommercialTanker(rand.nextInt(40) + 40);
        l1 = new LightTanker(rand.nextInt(20) + 20);
        cn = null;
    }
    
    @Test
    public void testCompare() {
        c1.setWaitingTime(5);
        l1.setWaitingTime(3);
        
        assertEquals(1, strategy.compare(c1, l1)); 
        assertEquals(-1, strategy.compare(l1, c1)); 
        
        l1.setWaitingTime(7);
        
        assertEquals(-1, strategy.compare(c1, l1)); 
        assertEquals(1, strategy.compare(l1, c1)); 
    }
    
    @Test
    public void testSchedule() {
        assertEquals(1, strategy.schedule(c1, l1));
        assertEquals(1, strategy.schedule(l1, c1)); 
        assertEquals(1, strategy.schedule(l1, cn)); 
        assertEquals(1, strategy.schedule(c1, cn)); 
        
        assertEquals(0, strategy.schedule(cn, cn));
        
        assertEquals(2, strategy.schedule(cn, c1)); 
        assertEquals(2, strategy.schedule(cn, l1));
    }
}
