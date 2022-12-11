package com.dcelevator.app.models;

import java.util.UUID;
import java.util.concurrent.PriorityBlockingQueue;

public class Elevator implements Runnable {
    private String id;
    private ElevatorState state;
    private Direction direction;
    private int currentFloor;
    private int destinationFloor;
    private PriorityBlockingQueue<Request> requestsQueue;
    /*
     * in millisec, how long it takes to go up or down one floor
     * idea is that a moving elevator should pick up passengers
     * in intermediate floors
     * 
     * elevator from 0 -> 20 should pick passenger up from floors
     * 1-19 if elevator has not yet passed the given floor
     */
    private int SPEED_PER_REQUEST = 1000;

    public Elevator(String id) {
        // will be used for debugging
        this.id = id;
        this.state = ElevatorState.IDLE;
        this.direction = Direction.UP;
        this.requestsQueue = new PriorityBlockingQueue<Request>(2);
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

    private void moveElevatorToRequested(Request request) {
        while (this.currentFloor != request.getCurrentFloor()) {
            switch (Integer.compare(currentFloor, request.getCurrentFloor())) {
                case -1 -> {
                    // elevator is under the requested floor
                    this.direction = Direction.UP;
                    this.currentFloor += 1;
                }
                case 1 -> {
                    // elevator is above the requested floor
                    this.direction = Direction.DOWN;
                    this.currentFloor -= 1;
                }
            }
            System.out.printf("Moved elevator %s to pick up PAX: %s\n", this.id,
                    this.currentFloor);
        }
        request.setState(RequestState.CARRY);
    }

    private void moveElevatorToDestination(Request request) {
        while (this.currentFloor != request.getDestinationFloor()) {
            switch (request.getDirection()) {
                case UP -> {
                    this.direction = Direction.UP;
                    this.currentFloor += 1;
                    System.out.printf("Moved elevator %s to deliver PAX: %s\n", this.id,
                            this.currentFloor);
                }
                case DOWN -> {
                    this.direction = Direction.DOWN;
                    this.currentFloor -= 1;
                    System.out.printf("Moved elevator %s to deliver PAX: %s\n", this.id,
                            this.currentFloor);
                }
            }
        }
        request.setState(RequestState.DELIVERED);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Request request = this.requestsQueue.peek(); // need to keep request if not finished
                if (request != null) {
                    while (!request.isFinished()) {
                        switch (request.getState()) {
                            case PICKING -> {
                                Thread.sleep(SPEED_PER_REQUEST);
                                moveElevatorToRequested(request);
                            }
                            case CARRY -> {
                                Thread.sleep(SPEED_PER_REQUEST);
                                moveElevatorToDestination(request);
                            }
                            case DELIVERED -> {
                                System.out.printf("Finished Request %s", request);
                                request.setFinished(true);
                                this.requestsQueue.remove(request);
                                if (this.requestsQueue.isEmpty()) {
                                    System.out.printf("Elevator %s now idle.\n", this.id);
                                    this.state = ElevatorState.IDLE;
                                }
                            }
                            default -> throw new IllegalArgumentException("Unexpected value: " + request.getState());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addRequest(Request request) {
        this.requestsQueue.add(request);
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

    @Override
    public String toString() {
        return "Elevator [id=" + id + ", state=" + state + ", direction=" + direction + ", currentFloor=" + currentFloor
                + ", requestsQueue=" + requestsQueue + "]";
    }

    public int getDestinationFloor() {
        return this.destinationFloor;
    }

    public PriorityBlockingQueue<Request> getQueue() {
        return this.requestsQueue;
    }
}