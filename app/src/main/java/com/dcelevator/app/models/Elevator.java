package com.dcelevator.app.models;

import java.util.UUID;

enum Direction {
    UP, DOWN
}

public class Elevator implements Runnable {
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
    @SuppressWarnings("unused")
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

    public void setState(ElevatorState state) {
        this.state = state;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setCurrentFloor(int floor) {
        this.currentFloor = floor;
    }

    public void setDestinationFloor(int floor) {
        this.destinationFloor = floor;
    }

    public String getId() {
        return this.id;
    }

    public ElevatorState getState() {
        return this.state;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public int getCurrentFloor() {
        return this.currentFloor;
    }

    public int getDestinationFloor() {
        return this.destinationFloor;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
    }
}