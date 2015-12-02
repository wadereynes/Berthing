
package com.wade.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.wade.model.Port;
/**
 *
 * @author Gewa
 */
public class TestPort {
    
    Port port;
    
    @Before
    public void setUp() throws Exception {
        port = new Port();
    }
    
    @Test
    public void testAvailability() {
        assertTrue(port.isAvailable());
        port.setAvailable(false);
        assertFalse(port.isAvailable());
        port.setAvailable(true);
        assertTrue(port.isAvailable());
        port.setAvailable(true);
        assertTrue(port.isAvailable());
    }
    
    @Test
    public void testOccupiedTime() {
        assertTrue(port.isAvailable());
        port.setAvailable(false);
        assertFalse(port.isAvailable());
        
        port.setOccupiedTime(5);
        
        for (int i = 0; i < 5; i++) {
            port.incrementTime();
        }
        
        assertTrue(port.isAvailable());
    }
}
