package com.dcelevator.app;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.dcelevator.app.models.Elevator;
import com.dcelevator.app.models.ElevatorState;
import com.dcelevator.app.models.Request;


public class GetElevatorTest {
    
    @Test
    public void testElevatorSystem() {
        ElevatorSystem test = new ElevatorSystem();
        Elevator e0 = new Elevator("0");
        Elevator e1 = new Elevator("1");
        Elevator e2 = new Elevator("2");

        e0.setState(ElevatorState.INUSE);
        e1.setState(ElevatorState.IDLE);
        e2.setState(ElevatorState.IDLE);

        e2.setCurrentFloor(10);

        Request request0 = new Request("request 0", 3, 20); // must be e0 because e1 is INUSE, facing correct direction
        Request request1 = new Request("request 1", 30, 20); // must be e2 because e2 is closest

        test.addElevator(e0);
        test.addElevator(e1);
        test.addElevator(e2);

        assertEquals(e0, test.getUsableElevatorForRequest(request0));
        assertEquals(e2, test.getUsableElevatorForRequest(request1));
    }
    
    @Test
    public void getUsableElevatorForRequest() {
        ElevatorSystem test = new ElevatorSystem();
        Elevator e0 = new Elevator("0");
        Elevator e1 = new Elevator("1");
        Elevator e2 = new Elevator("2");

        e0.setState(ElevatorState.INUSE);
        e1.setState(ElevatorState.INUSE);
        e2.setState(ElevatorState.IDLE);
        e1.setCurrentFloor(10);
        e2.setCurrentFloor(10);

        Request request0 = new Request("request 0", 3, 20);
        Request request1 = new Request("request 0", 12, 20);
        Request request2 = new Request("request 1", 12, 0);

        test.addElevator(e0);
        test.addElevator(e1);
        test.addElevator(e2);

        assertEquals(e0, test.getUsableElevatorForRequest(request0));
        assertEquals(e1, test.getUsableElevatorForRequest(request1));
        assertEquals(e2, test.getUsableElevatorForRequest(request2));
    }
}
