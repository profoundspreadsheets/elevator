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
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testElevatorSystem() {
        ElevatorSystem test = new ElevatorSystem();
        
        Elevator e0 = new Elevator("0");
        e0.setState(ElevatorState.INUSE);
        Elevator e1 = new Elevator("1");
        e1.setState(ElevatorState.IDLE);

        Thread t0 = new Thread(e0);
        Thread t1 = new Thread(e1);
        t0.start();
        t1.start();

        test.addElevator(e0);
        test.addElevator(e1);

        assertEquals(e1, test.getIdleElevator());
    }

    @Test
    public void testRequestDirection() {
        Request requestUp = new Request("0", 1, 3);
        Request requestDown = new Request("1", 3, 1);        
        assertEquals(Direction.UP, requestUp.getDirection());
        assertEquals(Direction.DOWN, requestDown.getDirection());
    }
}



