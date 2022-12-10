package com.dcelevator.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dcelevator.app.models.Direction;
import com.dcelevator.app.models.Elevator;
import com.dcelevator.app.models.ElevatorState;
import com.dcelevator.app.models.Request;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

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

        Request request0 = new Request("request 0", 3, 20);
        Request request1 = new Request("request 1", 30, 20);

        Thread t0 = new Thread(e0);
        Thread t1 = new Thread(e1);
        Thread t2 = new Thread(e2);
        t0.start();
        t1.start();
        t2.start();
        test.addElevator(e0);
        test.addElevator(e1);
        test.addElevator(e2);

        assertEquals(e1, test.getClosestIdleElevator(request0));
        assertEquals(e2, test.getClosestIdleElevator(request1));
    }

    @Test
    public void getRequestDirection() {
        Request requestUp = new Request("0", 1, 3);
        Request requestDown = new Request("1", 3, 1);
        assertEquals(Direction.UP, requestUp.getDirection());
        assertEquals(Direction.DOWN, requestDown.getDirection());
    }

    @Test
    public void getClosestElevator() {
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

        Thread t0 = new Thread(e0);
        Thread t1 = new Thread(e1);
        Thread t2 = new Thread(e2);
        t0.start();
        t1.start();
        t2.start();
        test.addElevator(e0);
        test.addElevator(e1);
        test.addElevator(e2);

        assertEquals(e0, test.getClosestMovingElevator(request0));
        assertEquals(e1, test.getClosestMovingElevator(request1));
        assertEquals(null, test.getClosestMovingElevator(request2));
    }
}
