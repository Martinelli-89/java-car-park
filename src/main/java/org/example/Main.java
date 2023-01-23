package org.example;

import CarPark.CarPark;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        CarPark startCarPark = new CarPark(1, 3, 1);
        Scanner scanner = new Scanner(System.in);
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";
        boolean exit = false;
        System.out.println("Welcome to our Java Cark Park.");
        while(!exit) {
            System.out.println("Please select an option");
            System.out.println("1 - Check if car park is empty;");
            System.out.println("2 - Check if car park is full;");
            System.out.println("3 - Check available spaces for motorcycles;");
            System.out.println("4 - Check available spaces for cars;");
            System.out.println("5 - Check available spaces for vans;");
            System.out.println("6 - Check all available spaces");
            System.out.println("7 - Park motorcycle;");
            System.out.println("8 - Park car;");
            System.out.println("9 - Park van");
            System.out.println("10 - Unpark your vehicle;");
            System.out.println("11 - Exit");
            try {
                int entry = scanner.nextInt();
                if(entry < 1 || entry > 11) {
                    System.out.println(ANSI_RED + "Please enter a number between 1 and 11" + ANSI_RESET);
                } else {
                    switch(entry) {
                        case(1):
                            System.out.println(startCarPark.isCarParkEmpty());
                            break;
                        case(2): {
                            System.out.println(startCarPark.isCarParkFull());
                            break;
                        }
                        case(3): {
                            System.out.println("We have " + startCarPark.checkMotorcycleAvailableSpace() + " motorcycle spaces available.");
                            break;
                        }
                        case(4): {
                            System.out.println("We have " + startCarPark.checkCarAvailableSpace() + " car spaces available.");
                            break;
                        }
                        case(5): {
                            System.out.println("We have " + startCarPark.checkVanAvailableSpace() + " van spaces available.");
                            break;
                        }
                        case(6): {
                            startCarPark.checkCarParkAvailability();
                            break;
                        }
                        case(7): {
                            startCarPark.parkMotorcycle();
                            break;
                        }
                        case(8) : {
                            startCarPark.parkCar();
                            break;
                        }
                        case(9) : {
                            startCarPark.parkVan();
                            break;
                        }
                        case(10) : {
                            startCarPark.unparkVehicle();
                            break;
                        }
                        case (11) : {
                            System.out.println("Thanks for choosing our Java Car Park!");
                            exit = true ;
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(ANSI_RED + "Please enter a valid number;" + ANSI_RESET);
            }

        }
    }
}