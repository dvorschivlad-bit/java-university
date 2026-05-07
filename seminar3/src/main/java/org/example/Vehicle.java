package org.example.vehicle;

public abstract class Vehicle {

    private String id;
    private String brand;
    private double speed;
    private int mileage;
    private boolean rented;

    public Vehicle(String id, String brand, double speed) {
        this.id = id;
        this.brand = brand;
        this.speed = speed;
        this.mileage = 0;
        this.rented = false;
    }

    public String getId()           { return id; }
    public String getBrand()        { return brand; }
    public double getSpeed()        { return speed; }
    public int getMileage()         { return mileage; }
    public boolean isRented()       { return rented; }

    public void setSpeed(double speed)   { this.speed = speed; }
    public void setBrand(String brand)   { this.brand = brand; }

    public void rent() {
        if (rented) throw new IllegalStateException(brand + " is already rented.");
        rented = true;
    }

    public void returnVehicle(int drivenKm) {
        if (!rented) throw new IllegalStateException(brand + " was not rented.");
        if (drivenKm <= 0) throw new IllegalArgumentException("Driven km must be > 0.");
        mileage += drivenKm;
        rented = false;
    }

    public abstract void move();
    public abstract boolean needsService();
    public abstract double rentalPrice(int days);

    @Override
    public String toString() {
        return id + " | " + brand + " | " + speed + " km/h | " + mileage + " km | rented=" + rented;
    }
}