package org.example.garage;

import org.example.vehicle.Vehicle;

public class Garage {

    private Vehicle[] fleet;
    private int size;

    public Garage(int capacity) {
        fleet = new Vehicle[capacity];
        size = 0;
    }

    public void add(Vehicle v) {
        if (size == fleet.length) {
            System.out.println("Garage is full.");
            return;
        }
        if (findById(v.getId()) != null) {
            System.out.println("Vehicle with id " + v.getId() + " already exists.");
            return;
        }
        fleet[size++] = v;
    }

    public Vehicle findById(String id) {
        for (int i = 0; i < size; i++)
            if (fleet[i].getId().equals(id)) return fleet[i];
        return null;
    }

    public void rentById(String id) {
        Vehicle v = findById(id);
        if (v == null) { System.out.println("Vehicle " + id + " not found."); return; }
        v.rent();
        System.out.println(v.getBrand() + " rented successfully.");
    }

    public void returnById(String id, int drivenKm) {
        Vehicle v = findById(id);
        if (v == null) { System.out.println("Vehicle " + id + " not found."); return; }
        v.returnVehicle(drivenKm);
        System.out.println(v.getBrand() + " returned. Total mileage: " + v.getMileage() + " km.");
    }

    public void printAvailable() {
        System.out.println("--- Available vehicles ---");
        for (int i = 0; i < size; i++)
            if (!fleet[i].isRented()) System.out.println(fleet[i]);
    }

    public void printNeedsService() {
        System.out.println("--- Needs service ---");
        for (int i = 0; i < size; i++)
            if (fleet[i].needsService()) System.out.println(fleet[i]);
    }

    public void printRentalEstimate(String id, int days) {
        Vehicle v = findById(id);
        if (v == null) { System.out.println("Vehicle " + id + " not found."); return; }
        System.out.printf("Rental estimate for %s (%d days): %.2f EUR%n", v.getBrand(), days, v.rentalPrice(days));
    }
}