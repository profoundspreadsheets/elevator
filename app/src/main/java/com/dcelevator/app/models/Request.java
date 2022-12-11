package com.dcelevator.app.models;

import java.util.UUID;

public class Request implements Comparable<Request> {
    private final String id;
    private final int currentFloor; // from which floor was request entered
    private final int destinationFloor;
    private final Direction direction;
    private boolean finished;
    private RequestState state;

    public Request(int currentFloor, int destinationFloor) {
        this.id = UUID.randomUUID().toString();
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
        this.finished = false;
        this.state = RequestState.PICKING;
        if (currentFloor == destinationFloor) {
            throw new IllegalArgumentException(String.format("Request must contain different floors", this.toString()));
        } else if (currentFloor > destinationFloor) {
            this.direction = Direction.DOWN;
        } else {
            this.direction = Direction.UP;
        }
    }

    public Request(String id, int currentFloor, int destinationFloor) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
        this.finished = false;
        this.state = RequestState.PICKING;
        if (currentFloor == destinationFloor) {
            throw new IllegalArgumentException(String.format("Request must contain different floors", this.toString()));
        } else if (currentFloor > destinationFloor) {
            this.direction = Direction.DOWN;
        } else {
            this.direction = Direction.UP;
        }
    }

    @Override
    public String toString() {
        return "[id=" + id + ", requested on: " + currentFloor + ", destination: " + destinationFloor
                + ", direction=" + direction + "]\n";
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void setState(RequestState requestState) {
        this.state = requestState;
    }

    public RequestState getState() {
        return this.state;
    }

    public boolean isFinished() {
        return this.finished;
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

    @Override
    public int compareTo(Request arg0) {
        switch (this.direction) {
            case DOWN:
                return (-1) * Integer.compare(this.destinationFloor, arg0.destinationFloor);
            case UP:
                return Integer.compare(this.destinationFloor, arg0.destinationFloor);
        }
        return 0;
    }
}
