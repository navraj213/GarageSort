package Garage;

/*
 * partCopy class to store part copy information
 */
public class partCopy {
    public String uniqueID;
    public String reserverName;
    public boolean isKeeping;

    // Constructor
    public partCopy(String uniqueID, String reserverName, boolean isKeeping) {
        this.uniqueID = uniqueID.toUpperCase();
        this.reserverName = reserverName.toUpperCase();
        this.isKeeping = isKeeping;
    }

    // Returns the brand name of the part copy
    public String getBrand() {
        String brand = "";
        for (int i = 0; i < this.uniqueID.length(); i++) {
            if (Character.isDigit(this.uniqueID.charAt(i))) {
                break;
            }
            brand += this.uniqueID.charAt(i);
        }
        return brand;
    }

    // Returns the model number of the part copy
    public String toString() {
        return "\033[31m" + this.uniqueID + " \033[32m" + this.reserverName + " \033[34m" + this.isKeeping + "\033[0m";
    }

    // Compares two part copies
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || partCopy.class != obj.getClass()) {
            return false;
        }
        partCopy p = (partCopy) obj;
        return this.uniqueID.equals(p.uniqueID) && this.reserverName.equals(p.reserverName) && this.isKeeping == p.isKeeping;
    }

}
