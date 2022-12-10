package com.dcelevator.app;

import java.util.ArrayList;
import java.util.List;

import com.dcelevator.app.models.Elevator;
import com.dcelevator.app.models.ElevatorState;
import com.dcelevator.app.models.Request;

// check if moving elevator can catch
// check if idle elevator is available
// move request into queue

public class ElevatorSystem {
    public ElevatorSystem() {
    }

    private final List<Elevator> elevators = new ArrayList<>();
    private final List<Request> requestQueue = new ArrayList<>();

    public void addElevator(Elevator elevator) {
        this.elevators.add(elevator);
    }

    public void addRequest(Request request) {
        
    }

    public Elevator getMovingAbleToPickUpElevator(Request request) {
        for (Elevator elevator : elevators) {
            if (elevator.getState() == ElevatorState.INUSE) {
                
                
                
                return elevator;
            }
        }
        return null;
    }
    
    public Elevator getIdleElevator() {
        return elevators.stream().filter(x -> x.getState() == ElevatorState.IDLE).findFirst().get();
    }
}
