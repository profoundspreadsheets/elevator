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

    public void addElevator(String id) {
        Elevator elevator = new Elevator(id);
        new Thread(elevator).start();
        this.elevators.add(elevator);
    }

    public void addElevator(Elevator elevator) {
        new Thread(elevator).start();
        this.elevators.add(elevator);
    }

    public void addRequest(Request request) {
        requestQueue.add(request);
    }

    public Elevator getUsableElevatorForRequest(Request request) {
        
        // check if moving elevator can catch
        // check if idle elevator is available
        // move request into queue

        if (getClosestMovingElevator(request) != null) {
            Elevator elevator = getClosestMovingElevator(request);
            System.out.printf("Closest %s for Request %s", elevator, request);
            return elevator;
        }

        if (getClosestIdleElevator(request) != null) {
            Elevator elevator = getClosestIdleElevator(request);
            System.out.printf("Closest %s for Request %s", elevator, request);
            return elevator;
        }

        System.out.printf("FOUND NO SUITABLE ELEVATOR");
        this.requestQueue.add(request);
        return null;
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

    private void workRequest(Request request) {
        Elevator elevator = getUsableElevatorForRequest(request);
        elevator.getQueue().offer(request);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                Request request = this.requestQueue.take();
                System.out.printf("Working on Request %s", request);
                this.workRequest(request);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public BlockingQueue<Request> getQueue() {
        return this.requestQueue;
    }
}
