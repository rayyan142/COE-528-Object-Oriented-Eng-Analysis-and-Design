package coe528.lab1;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author r1faisal
 */
public class Flight {

    public int flightNumber, capacity, numberOfSeatsLeft;
    public String origin, destination, departureTime;
    public double originalPrice;

    public Flight(int flightNumber, int capacity, String origin, String destination, String departureTime, double originalPrice) {

        if (destination.equals(origin)) {
            throw new IllegalArgumentException("Origin and destination cannot be the same");
        }

        this.flightNumber = flightNumber;
        this.capacity = capacity;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.originalPrice = originalPrice;
        numberOfSeatsLeft = capacity;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getNumberOfSeatsLeft() {
        return numberOfSeatsLeft;
    }

    public void setNumberOfSeatsLeft(int numberOfSeatsLeft) {
        this.numberOfSeatsLeft = numberOfSeatsLeft;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public boolean bookASeat(int numberOfSeatsLeft) {
        if (numberOfSeatsLeft > 0) {
            numberOfSeatsLeft--;
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return ("Flight" + flightNumber + "," + origin + "to" + destination + "," + departureTime + ", Original Price:" + originalPrice);
    }
}
