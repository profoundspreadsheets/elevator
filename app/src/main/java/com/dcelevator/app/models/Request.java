package com.dcelevator.app.models;

public class Request {
    @SuppressWarnings("unused")
    private final String id;
    private final int currentFloor; // from which floor was request entered
    private final int destinationFloor;
    private final Direction direction;

    public Request (String id, int currentFloor, int destinationFloor) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
        if (currentFloor > destinationFloor) {
            this.direction = Direction.DOWN;
        }
        else {
            this.direction = Direction.UP;
        }
    }
    
    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public String getId() {
        return id;
    }

    public Direction getDirection() {
        return direction;
    }
}
