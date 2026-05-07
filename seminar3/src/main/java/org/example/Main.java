package org.example;

import org.example.garage.Garage;
import org.example.vehicle.*;

public class Main {

    public static void main(String[] args) {

        System.out.println(" 3. Polymorphism ");
        Vehicle[] vehicles = {
                new Car("C1", "Toyota", 180, 4),
                new Motorcycle("M1", "Honda", 150, false),
                new Truck("T1", "Volvo", 120, 5000)
        };
        for (Vehicle v : vehicles) v.move();

        System.out.println("\n 4. equals() on Car ");
        Car car1 = new Car("C2", "Toyota", 180, 4);
        Car car2 = new Car("C3", "Toyota", 200, 4);
        Car car3 = new Car("C4", "BMW",    180, 2);
        System.out.println("car1 equals car2: " + car1.equals(car2));
        System.out.println("car1 equals car3: " + car1.equals(car3));

        System.out.println("\n 8. Garage Demo ");
        Garage garage = new Garage(10);

        garage.add(new Car       ("C10", "Toyota",   180, 4));
        garage.add(new Car       ("C11", "BMW",       210, 2));
        garage.add(new Motorcycle("M10", "Honda",     150, false));
        garage.add(new Motorcycle("M11", "Harley",    140, true));
        garage.add(new Truck     ("T10", "Volvo",     120, 8000));

        System.out.println("\n All available before renting ");
        garage.printAvailable();

        System.out.println("\n Renting C10 and M11 ");
        garage.rentById("C10");
        garage.rentById("M11");

        System.out.println("\n Available after renting ");
        garage.printAvailable();

        System.out.println("\n Rental estimates ");
        garage.printRentalEstimate("C10", 5);
        garage.printRentalEstimate("M11", 3);
        garage.printRentalEstimate("T10", 7);

        System.out.println("\n Returning C10 after 12000 km ");
        garage.returnById("C10", 12000);

        System.out.println("\n Needs service ");
        garage.printNeedsService();
    }
}