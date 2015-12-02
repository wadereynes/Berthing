
package com.wade.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.wade.model.Counter;
/**
 *
 * @author Gewa
 */
public class TestCounter {
    
    Counter counter;
    
    @Before
    public void stepUp() throws Exception {
        counter = new Counter();
    }
    
    @Test
    public void testIncrements() {
        for (int i = 0; i < 10; i++) {
            counter.incrementStorms();
            counter.incrementDeberthings();
            counter.incrementBerthings();
        }
        
        assertEquals(10, counter.getStorms());
        assertEquals(10, counter.getDeberthings());
        assertEquals(10, counter.getBerthings());
        
        counter.incrementDeberthings();
        
        assertEquals(11, counter.getDeberthings());
    }
    
    @Test
    public void testMutators() {
        counter.setShipping(55);
        counter.setGrounded(60);
        
        assertEquals(55, counter.getShipping());
        assertEquals(60, counter.getGrounded());
    }
    
    public void testClear() {
        counter.setShipping(5);
        counter.setGrounded(15);
        counter.incrementStorms(); counter.incrementStorms();
        counter.incrementDeberthings();
        counter.incrementBerthings();
        
        counter.clear();
        
        assertEquals(0, counter.getStorms());
        assertEquals(0, counter.getShipping());
        assertEquals(0, counter.getGrounded());
        assertEquals(0, counter.getDeberthings());
        assertEquals(0, counter.getBerthings());
    }
}
