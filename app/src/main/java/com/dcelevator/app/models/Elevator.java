package com.dcelevator.app.models;

import java.util.UUID;

enum ElevatorState {
    IDLE, INUSE, BLOCKED
}

enum Direction {
    UP, DOWN
}

public class Elevator {
    private String id;
    private ElevatorState state;
    private Direction direction;
    private int currentFloor;
    private int destinationFloor;
    /*
     * in millisec, how long it takes to go up or down one floor
     * idea is that a moving elevator should pick up passengers
     * in intermediate floors
     * 
     * elevator from 0 -> 20 should pick passenger up from floors
     * 1-19 if elevator has not yet passed the given floor
     */
    private int SPEED_PER_FLOOR = 5000; 

    public Elevator(String id) {
        // will be used for debugging
        this.id = id;
        this.state = ElevatorState.IDLE;
        this.direction = Direction.UP;
        this.currentFloor = 0;
        this.destinationFloor = 0;
    }
    
    public Elevator() {
        this.id = UUID.randomUUID().toString();
        this.state = ElevatorState.IDLE;
        this.direction = Direction.UP;
        this.currentFloor = 0;
        this.destinationFloor = 0;
    }
}