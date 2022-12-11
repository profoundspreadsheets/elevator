package com.dcelevator.app.models;

import java.util.UUID;
import java.util.concurrent.PriorityBlockingQueue;

public class Elevator implements Runnable {
    private String id;
    private ElevatorState state;
    private Direction direction;
    private int currentFloor;
    private int destinationFloor;
    // TODO sort after destination Floor ?
    private PriorityBlockingQueue<Request> requestsQueue;
    /*
     * in millisec, how long it takes to go up or down one floor
     * idea is that a moving elevator should pick up passengers
     * in intermediate floors
     * 
     * elevator from 0 -> 20 should pick passenger up from floors
     * 1-19 if elevator has not yet passed the given floor
     */
    @SuppressWarnings("unused")
    private int SPEED_PER_REQUEST = 500;

    public Elevator(String id) {
        // will be used for debugging
        this.id = id;
        this.state = ElevatorState.IDLE;
        this.direction = Direction.UP;
        this.requestsQueue = new PriorityBlockingQueue<Request>(1024);
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

    @Override
    public void run() {
        while (true) {
            try {
                Request request = this.requestsQueue.peek(); // need to keep request if not finished
                if (request != null) {
                    while (!request.isFinished()) {
                        System.out.println("Working on Request " + request.toString());
                        Thread.sleep(SPEED_PER_REQUEST);
                        this.currentFloor += 1;
                        System.out.println("Moving elevator to " + this.currentFloor);
                        if (this.currentFloor == request.getDestinationFloor()) {
                            System.out.println("Finished Request " + request);
                            request.setFinished(true);
                            this.requestsQueue.remove(request);
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

    public int getDestinationFloor() {
        return this.destinationFloor;
    }

    public synchronized PriorityBlockingQueue<Request> getQueue() {
        return this.requestsQueue;
    }

    
}