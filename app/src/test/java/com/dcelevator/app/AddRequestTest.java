package com.dcelevator.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import com.dcelevator.app.models.Elevator;
import com.dcelevator.app.models.Request;

public class AddRequestTest {
    @Test
    public void testElevatorSystem() {
        ElevatorSystem test = new ElevatorSystem();
        Elevator e0 = new Elevator("0");
        Request request0 = new Request("request 0", 3, 20);

        Thread t0 = new Thread(e0);
        t0.start();
        test.addElevator(e0);

        test.addRequest(request0);
        
        assertEquals(1, test.getQueue().size());
        
    }
}
