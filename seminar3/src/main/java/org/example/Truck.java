package org.example.vehicle;

public class Truck extends Vehicle {

    private double loadCapacity;

    public Truck(String id, String brand, double speed, double loadCapacity) {
        super(id, brand, speed);
        this.loadCapacity = loadCapacity;
    }

    public double getLoadCapacity() { return loadCapacity; }

    @Override
    public void move() {
        System.out.println(getBrand() + " truck is hauling.");
    }

    @Override
    public boolean needsService() {
        return getMileage() >= 15000;
    }

    @Override
    public double rentalPrice(int days) {
        return (80 + 0.02 * loadCapacity) * days;
    }

    @Override
    public String toString() {
        return "Truck  [" + super.toString() + " | capacity=" + loadCapacity + "kg]";
    }
}