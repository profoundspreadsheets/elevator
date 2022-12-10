package com.dcelevator.app;

import java.util.ArrayList;
import java.util.Comparator;
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

    public Elevator getClosestMovingElevator(Request request) {
        return elevators.stream()
                .filter(elevator -> elevator.getState() == ElevatorState.INUSE)
                .filter(elevator -> elevator.getDirection() == request.getDirection())
                .min(Comparator
                        .comparingInt(elevator -> Math.abs(elevator.getCurrentFloor() - request.getCurrentFloor())))
                .orElse(null);
    }

    public Elevator getClosestIdleElevator(Request request) {
        return elevators.stream().filter(elevator -> elevator.getState() == ElevatorState.IDLE)
                .min(Comparator
                        .comparingInt(elevator -> Math.abs(elevator.getCurrentFloor() - request.getCurrentFloor())))
                .orElse(null);
    }
}
