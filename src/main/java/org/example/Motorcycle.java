package org.example.vehicle;

public class Motorcycle extends Vehicle {

    private boolean hasSidecar;

    public Motorcycle(String id, String brand, double speed, boolean hasSidecar) {
        super(id, brand, speed);
        this.hasSidecar = hasSidecar;
    }

    public boolean hasSidecar() { return hasSidecar; }

    @Override
    public void move() {
        System.out.println(getBrand() + " motorcycle is riding.");
    }

    @Override
    public boolean needsService() {
        return getMileage() >= 6000;
    }

    @Override
    public double rentalPrice(int days) {
        double price = 30 * days;
        if (hasSidecar) price += 15 * days;
        return price;
    }

    @Override
    public String toString() {
        return "Moto   [" + super.toString() + " | sidecar=" + hasSidecar + "]";
    }
}