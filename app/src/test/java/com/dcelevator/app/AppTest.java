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
    public void getRequestDirection() {
        Request requestUp = new Request("0", 1, 3);
        Request requestDown = new Request("1", 3, 1);
        assertEquals(Direction.UP, requestUp.getDirection());
        assertEquals(Direction.DOWN, requestDown.getDirection());
    }
}
