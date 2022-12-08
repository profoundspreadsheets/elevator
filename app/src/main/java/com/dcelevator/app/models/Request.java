package com.dcelevator.app.models;

public class Request {
    @SuppressWarnings("unused")
    private String id;
    private int currentFloor; // from which floor was request entered
    private int destinationFloor;

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }
}
