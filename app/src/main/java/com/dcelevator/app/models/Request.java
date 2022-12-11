package com.dcelevator.app.models;

public class Request implements Comparable<Request> {
    @SuppressWarnings("unused")
    private final String id;
    private final int currentFloor; // from which floor was request entered
    private final int destinationFloor;
    private final Direction direction;
    private boolean finished;

    public Request(String id, int currentFloor, int destinationFloor) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
        this.finished = false;
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
        return "Request [id=" + id + ", requested on: " + currentFloor + ", destination: " + destinationFloor
                + ", direction=" + direction + "]";
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
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
        // TODO Auto-generated method stub
        switch (this.direction) {
            case DOWN:
                return (-1) * Integer.compare(this.destinationFloor, arg0.destinationFloor);
            case UP:
                return Integer.compare(this.destinationFloor, arg0.destinationFloor);
        }
        return 0;
    }
}
