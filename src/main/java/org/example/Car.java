package org.example.vehicle;

public class Car extends Vehicle {

    private int doors;

    public Car(String id, String brand, double speed, int doors) {
        super(id, brand, speed);
        this.doors = doors;
    }

    public int getDoors() { return doors; }

    @Override
    public void move() {
        System.out.println(getBrand() + " car is driving.");
    }

    @Override
    public boolean needsService() {
        return getMileage() >= 10000;
    }

    @Override
    public double rentalPrice(int days) {
        double price = 50 * days;
        if (doors >= 4) price *= 1.10;
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car other = (Car) o;
        return getBrand().equals(other.getBrand()) && doors == other.doors;
    }

    @Override
    public String toString() {
        return "Car    [" + super.toString() + " | doors=" + doors + "]";
    }
}