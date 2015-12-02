
package com.wade.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.wade.model.Tug;
/**
 *
 * @author Gewa
 */
public class TestTug {
    
    Tug t1;
    Tug t2;
    Tug t3;
    
    @Before
    public void setUp() throws Exception {
         t1 = new Tug();
         t2 = new Tug();
         t3 = new Tug();
    }
    
    @Test
    public void testWaitingTime() {
        assertEquals(0, t1.getWaitingTime()); 
        assertEquals(0, t2.getWaitingTime()); 
        assertEquals(0, t3.getWaitingTime()); 
        
        for (int i = 0; i < 10; i++) { 
            t1.step();
        }
        
        t2.setWaitingTime(10); 
        
        t3.setWaitingTime(5); 
        for (int i = 0; i < 5; i++) { 
            t3.step();
        }
        
        assertEquals(10, t1.getWaitingTime()); 
        assertEquals(10, t2.getWaitingTime()); 
        assertEquals(10, t3.getWaitingTime()); 
        
        t1.incrementWaitingTime(); 
        assertEquals(11, t1.getWaitingTime()); 
        
        t2.incrementWaitingTime(); 
        t2.incrementWaitingTime(); 
        assertEquals(12, t2.getWaitingTime()); 
        
    }
    
    @Test
    public void testMaintenance() {
        assertEquals(t1.getMaintenanceTime(), 0); 
        assertEquals(t2.getMaintenanceTime(), 0); 
        assertEquals(t3.getMaintenanceTime(), 0); 
        
        t1.setBrokeDown(true);
        t2.step();
        assertEquals(t1.getMaintenanceTime(), 1); 
        
        t2.step(); t2.step();
        assertEquals(t2.getMaintenanceTime(), 0); 
        
        t3.setBrokeDown(true);
        assertEquals(t3.getMaintenanceTime(), 0); 
        for (int i = 0; i < 60; i++) {
            t3.step();
        }
        
        assertEquals(t3.getMaintenanceTime(), 60); 
        for (int i = 0; i < 60; i++) {
            t3.step();
        }
        assertEquals(t3.getMaintenanceTime(), 0); 
        
    }
    
    @Test
    public void testBethingTime() {
        assertEquals(6, t1.getBerthingTime());
        assertEquals(6, t2.getBerthingTime());
        assertEquals(6, t3.getBerthingTime());
    }
    
    @Test
    public void testDeberthingTime() {
        assertEquals(8, t1.getDeberthingTime());
        assertEquals(8, t2.getDeberthingTime());
        assertEquals(8, t3.getDeberthingTime());
    }
    
    @Test
    public void testShippingBoolean() {
        assertFalse(t1.isShipping());
        assertFalse(t2.isShipping());
        assertFalse(t3.isShipping());
        
        t1.setIsShipping(true);
        assertTrue(t1.isShipping());
        
        t1.setIsShipping(false);
        assertFalse(t1.isShipping());
    }
    
    @Test
    public void testStormBoolean() {
        assertFalse(t1.isStorm()); 
        assertFalse(t2.isStorm());
        assertFalse(t3.isStorm());
        
        t1.setIsShipping(true);
        
        for (int i = 0; i < 10000; i++) {
            t1.step();
        }
        assertFalse(t1.isStorm()); 
    }
    
    
    
    @Test
    public void testCompareTo() {
        assertEquals(0, t1.compareTo(t1));
        assertEquals(1, t1.compareTo(t2));
        assertEquals(-1, t2.compareTo(t1));
        assertEquals(-1, t3.compareTo(t2));
        assertEquals(1, t2.compareTo(t3));
        assertEquals(1, t1.compareTo(t3));
        assertEquals(-1, t3.compareTo(t1));
    }
}
