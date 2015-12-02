
package com.wade.test;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.wade.model.LightTanker;
/**
 *
 * @author Gewa
 */
public class TestLightTanker {
    
    Random rand = new Random(17);
    LightTanker l1;
    LightTanker l2;
    LightTanker l3;
    
    @Before
    public void setUp() throws Exception {
        l1 = new LightTanker(rand.nextInt(20) + 20);
        l2 = new LightTanker(rand.nextInt(20) + 20);
        l3 = new LightTanker(rand.nextInt(20) + 20);
    }
    
    @Test
    public void testWaitingTime() {
        assertEquals(0, l1.getWaitingTime()); 
        assertEquals(0, l2.getWaitingTime()); 
        assertEquals(0, l3.getWaitingTime()); 
        
        for (int i = 0; i < 10; i++) { 
            l1.step();
        }
        
        l2.setWaitingTime(10); 
        
        l3.setWaitingTime(5); 
        for (int i = 0; i < 5; i++) { 
            l3.step();
        }
        
        assertEquals(10, l1. getWaitingTime()); 
        assertEquals(10, l2.getWaitingTime());
        assertEquals(10, l3.getWaitingTime());
        
        l1.incrementWaitingTime();
        assertEquals(l1, l1.getWaitingTime()); 
        
        l2.incrementWaitingTime(); 
        l2.incrementWaitingTime(); 
        assertEquals(l2, l2.getWaitingTime()); 
    }
    
    @Test
    public void testMaintenanceTime() {
        assertEquals(l1.getMaintenanceTime(), 0); 
        assertEquals(l2.getMaintenanceTime(), 0); 
        assertEquals(l3.getMaintenanceTime(), 0); 
        
        l1.setBrokeDown(true);
        l1.step();
        assertEquals(l1.getMaintenanceTime(), 1); 
        
        l2.step(); l2.step();
        assertEquals(l2.getMaintenanceTime(), 0); 
        
        l3.setBrokeDown(true);
        assertEquals(l3.getMaintenanceTime(), 0); 
        for (int i = 0; i < 60; i++) {
            l3.step();
        }
        
        assertEquals(l3.getMaintenanceTime(), 60); 
        for (int i = 0; i < 60; i++) {
            l3.step();
        }
        
        assertEquals(l3.getMaintenanceTime(), 0); 
    }
    
    @Test
    public void testBerthingTime() {
        assertEquals(4, l1.getBerthingTime());
        assertEquals(4, l2.getBerthingTime());
        assertEquals(4, l3.getBerthingTime());
    }
    
    @Test
    public void testDeberthingTime() {
        assertEquals(6, l1.getDeberthingTime());
        assertEquals(6, l2.getDeberthingTime());
        assertEquals(6, l3.getDeberthingTime());
    }
    
    @Test
    public void testShippingBoolean() {
        assertFalse(l1.isShipping());
        assertFalse(l2.isShipping());
        assertFalse(l3.isShipping());
        
        l1.setIsShipping(true);
        assertTrue(l1.isShipping());
        
        l1.setIsShipping(false);
        assertFalse(l1.isShipping());
    }
    
    @Test
    public void testStormBoolean() {
        assertFalse(l1.isStorm());
        assertFalse(l2.isStorm());
        assertFalse(l3.isStorm());
        
        l1.setIsShipping(true);
        int fuelLevel = l1.getFuelLevel();
        for (int i = 0; i < fuelLevel; i++) {
            l1.step(); 
        }
        assertTrue(l1.isStorm()); 
    }
    
    @Test
    public void testCompareTo() {
        assertEquals(0, l1.compareTo(l1));
        assertEquals(1, l1.compareTo(l2));
        assertEquals(-1, l2.compareTo(l1));
        assertEquals(-1, l3.compareTo(l2));
        assertEquals(1, l2.compareTo(l3));
        assertEquals(1, l1.compareTo(l3));
        assertEquals(-1, l3.compareTo(l1));
    }
    
}
