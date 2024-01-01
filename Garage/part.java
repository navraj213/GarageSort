package Garage;

import java.util.*;

/*
 * part class to store part information
 */
public class part implements Cloneable {
    //User inputted information
    public String brand; 
    public String description;
    public int modelNumber;
    public int quantity;
    public String location;

    //System generated information
    public Stack<String[]> history; // Tracks information [status, user] in a stack (LIFO)
    public int numBorrowed; // Tracks number of times part has been borrowed
    public String uniqueID; // Creates a unique ID for each part (brand + modelNumber)

    //Constructor
    public part(String brand, String description, int modelNumber, int quantity, String location) {
        this.brand = brand.toUpperCase();
        this.description = description;
        this.modelNumber = modelNumber;
        this.quantity = quantity;
        if(this.quantity < 0) {
            this.quantity = 0;
        }
        this.location = location.toUpperCase().strip();

        this.uniqueID = (brand + modelNumber).toUpperCase();
        this.history = new Stack<String[]>();
        this.numBorrowed = 0;
    }

    // Adds status to history stack
    public void addHistory(String[] history) {
        this.history.add(history);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        part p = (part) obj;
        return this.uniqueID.equals(p.uniqueID);
    }

    /*
     * Generates a hashcode for the part based off of the uniqueID
     */
    @Override
    public int hashCode() {
        int result = this.uniqueID.hashCode();
        result = 31 * result + this.modelNumber;
        return result;
    }

    @Override
    public part clone() throws CloneNotSupportedException {
        return (part)super.clone();
    }

    /*
     * Returns a string representation of the part
     */
    @Override
    public String toString() {
        return "\033[31m" + this.brand + "\033[0m" + "-" + "\033[33m" + this.modelNumber + "\033[0m" + " " + this.description.toUpperCase()
                + " |\033[36m " + this.quantity + "\033[35m " + this.location + "\033[0m";
    }

    /*
     * Prints the history of the part and color codes it based on the status
     */
    public void printHistory() {
        for (String[] history : this.history) {
            String status = history[0].toLowerCase();
            switch (status) {
                case "borrowed":
                    System.out.println("\033[33m" + history[0].toUpperCase() + "\033[0m" + " by " + history[1]);
                    break;
                case "kept":
                    System.out.println("\033[31m" + history[0].toUpperCase() + "\033[0m" + " by " + history[1]);
                    break;
                case "returned":
                    System.out.println("\033[32m" + history[0].toUpperCase() + "\033[0m" + " by " + history[1]);
                    break;
                default:
                    break;
            }
        }
    }

    public int compareTo(part p2) {
        return this.quantity - p2.quantity;
    }
    
}