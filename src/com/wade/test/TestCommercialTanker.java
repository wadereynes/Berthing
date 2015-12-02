
package com.wade.test;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.wade.model.CommercialTanker;
/**
 *
 * @author Gewa
 */
public class TestCommercialTanker {
    
    CommercialTanker c1;
    CommercialTanker c2;
    CommercialTanker c3;
    
    @Before
    public void setUp() throws Exception {
        Random rand = new Random(17);
        c1 = new CommercialTanker(rand.nextInt(40) + 40); 
        c2 = new CommercialTanker(rand.nextInt(40) + 40); 
        c3 = new CommercialTanker(rand.nextInt(40) + 40); 
    }
    
    @Test
    public void tastWaitingTime() {
        assertEquals(0, c1.getWaitingTime()); 
        assertEquals(0, c2.getWaitingTime()); 
        assertEquals(0, c3.getWaitingTime()); 
        
        for (int i = 0; i < 10; i++) {
            c1.step();
        }
        
        c2.setWaitingTime(10); 
        
        c3.setWaitingTime(5); 
        for (int i = 0; i < 5; i++) { 
            c3.step();
        }
        
        assertEquals(10, c1.getWaitingTime()); 
        assertEquals(10, c2.getWaitingTime()); 
        assertEquals(10, c3.getWaitingTime()); 
        
        c1.incrementWaitingTime(); 
        assertEquals(11, c1.getWaitingTime()); 
        
        c2.incrementWaitingTime(); 
        c2.incrementWaitingTime(); 
        assertEquals(12, c2.getWaitingTime()); 
    }
    
    @Test
    public void testMaintenanceTime() {
        assertEquals(c1.getMaintenanceTime(), 0); 
        assertEquals(c2.getMaintenanceTime(), 0); 
        assertEquals(c3.getMaintenanceTime(), 0); 
        
        c1.setBrokeDown(true);
        c1.step();
        assertEquals(c1.getMaintenanceTime(), 1); 
       
        c2.step(); c2.step();
        assertEquals(c2.getMaintenanceTime(), 0); 
        
        c3.setBrokeDown(true);
        assertEquals(c3.getMaintenanceTime(), 0); 
        for (int i = 0; i < 60; i++) {
            c3.step();
        }
        assertEquals(c3.getMaintenanceTime(), 60);
        for (int i = 0; i < 60; i++) {
            c3.step();
        }
        
        assertEquals(c3.getMaintenanceTime(), 0); 
    }
    
    @Test
    public void testBerthingTime() {
        assertEquals(4, c1.getBerthingTime());
        assertEquals(4, c2.getBerthingTime());
        assertEquals(4, c3.getBerthingTime());
    }
    
    @Test
    public void testDeberthingTime() {
        assertEquals(6, c1.getDeberthingTime());
        assertEquals(6, c2.getDeberthingTime());
        assertEquals(6, c3.getDeberthingTime());
    }
    
    @Test
    public void testShippingBoolean() {
        assertFalse(c1.isShipping());
        assertFalse(c2.isShipping());
        assertFalse(c3.isShipping());
        
        c1.setIsShipping(true);
        assertTrue(c1.isShipping());
        
        c1.setIsShipping(false);
        assertFalse(c1.isShipping());
    }
    
    @Test
    public void testStormBoolean() {
        assertFalse(c1.isStorm());
        assertFalse(c2.isStorm());
        assertFalse(c3.isStorm());
        
        c1.setIsShipping(true);
        int fuelLevel = c1.getFuelLevel();
        for (int i = 0; i < fuelLevel; i++) {
            c1.step(); 
        }
        assertTrue(c1.isStorm()); 
    }
    
    @Test
    public void testCompareTo() {
        assertEquals(0, c1.compareTo(c1));
        assertEquals(1, c1.compareTo(c2));
        assertEquals(-1, c2.compareTo(c1));
        assertEquals(-1, c3.compareTo(c2));
        assertEquals(1, c2.compareTo(c3));
        assertEquals(1, c1.compareTo(c3));
        assertEquals(-1, c3.compareTo(c1));
    }
}
