package com.dcelevator.app;

import java.util.Random;

import com.dcelevator.app.models.Request;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ElevatorSystem test = new ElevatorSystem();
        

        test.addElevator("0");
        test.addElevator("1");
        test.addElevator("2");
        test.addElevator("3");
        test.addElevator("4");

        Random rng = new Random();
        rng.setSeed(50);

        System.out.println("============================ START ============================");

        for (int i = 0; i <= 30; i++) {
            final int currentFloor = rng.nextInt(20);
            final int targetFloor = rng.nextInt(20);

            Request request = new Request(String.valueOf(i), currentFloor, targetFloor);
            
            test.addRequest(request);
        }        
        System.out.println(test.getQueue());
        Thread systemThread = new Thread(test);
        systemThread.start();
    }
}
