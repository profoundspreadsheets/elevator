package com.dcelevator.app;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.dcelevator.app.models.Elevator;
import com.dcelevator.app.models.ElevatorState;
import com.dcelevator.app.models.Request;

public class ElevatorSystem implements Runnable {
    public ElevatorSystem() {
    }

    private final List<Elevator> elevators = new ArrayList<>();
    private final BlockingQueue<Request> requestQueue = new ArrayBlockingQueue<>(1024);

    public void addElevator(Elevator elevator) {
        this.elevators.add(elevator);
    }

    public void addRequest(Request request) {
        requestQueue.add(request);
    }

    public Elevator getUsableElevatorForRequest(Request request) {
        if (getClosestMovingElevator(request) != null) {
            return getClosestMovingElevator(request);
        } else {
            return getClosestIdleElevator(request);
        }        
    }

    private Elevator getClosestMovingElevator(Request request) {
        return elevators.stream()
                .filter(elevator -> elevator.getState() == ElevatorState.INUSE)
                .filter(elevator -> elevator.getDirection() == request.getDirection())
                .min(Comparator
                        .comparingInt(elevator -> Math.abs(elevator.getCurrentFloor() - request.getCurrentFloor())))
                .orElse(null);
    }

    private Elevator getClosestIdleElevator(Request request) {
        return elevators.stream().filter(elevator -> elevator.getState() == ElevatorState.IDLE)
                .min(Comparator
                        .comparingInt(elevator -> Math.abs(elevator.getCurrentFloor() - request.getCurrentFloor())))
                .orElse(null);
    }

    @Override
    public void run() {
        // check if moving elevator can catch
        // check if idle elevator is available
        // move request into queue
        while (true) {
            try {
                Request request = this.requestQueue.take();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public BlockingQueue<Request> getQueue() {
        return this.requestQueue;
    }
}
