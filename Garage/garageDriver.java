package Garage;

import java.util.*;

public class garageDriver {
    public static HashSet<part> inventory = new HashSet<part>(); //Keeps track of all parts avaliable
    public static BT binaryTree = new BT(); //Organizes parts by brand and also keeps track of all part copies/borrowers of a part
    public static Queue<part> orders = new LinkedList<>(); //Keeps track of all parts that need to be ordered
    public static Hashtable<String, ArrayList<part>> locationTable = new Hashtable<String, ArrayList<part>>(); //Keeps track of all parts in a location

    /*
     * getOption() 
     * This method prints out the options for the user to select from and then
     * calls the appropriate method based on the user's selection
     */
    public static void getOption() {
        clearScreen(); 
        //Prints out the options for the user to select from
        System.out.println("\033[32m" + "Welcome to the Garage Inventory System\n" + "\033[0m");
        System.out.println("Please select an option:");
        System.out.println("\033[31m" + " 1. " + "\033[0m" + "Add a part");
        System.out.println("\033[31m" + " 2. " + "\033[0m" + "Remove a part");
        System.out.println("\033[31m" + " 3. " + "\033[0m" + "Update a part");
        System.out.println("\033[31m" + " 4. " + "\033[0m" + "Borrow a part");
        System.out.println("\033[31m" + " 5. " + "\033[0m" + "Return a part");
        System.out.println("\033[31m" + " 6. " + "\033[0m" + "List all parts");
        System.out.println("\033[31m" + " 7. " + "\033[0m" + "List all borrowed parts");
        System.out.println("\033[31m" + " 8. " + "\033[0m" + "List all history of a part");
        System.out.println("\033[31m" + " 9. " + "\033[0m" + "List all parts of a brand");
        System.out.println("\033[31m" + "10. " + "\033[0m" + "List all details of a part");
        System.out.println("\033[31m" + "11. " + "\033[0m" + "List parts that need to be ordered");
        System.out.println("\033[31m" + "12. " + "\033[0m" + "List parts in a location");
        System.out.println("\033[31m" + "13. " + "\033[0m" + "List parts in order of quantity");
        System.out.println("\033[31m" + "14. " + "\033[0m" + "Exit");

        //Gets the user's selection
        Scanner input = new Scanner(System.in);
        int option = -1;
        //Checks if the user's selection is valid
        while(true) {
            try {
                option = input.nextInt();
                if(option < 1 || option > 14) {
                    throw new InputMismatchException();
                }
                break;
            }
            catch (InputMismatchException e) {
                System.out.println("\033[31m" + "ENTER A VALID NUMBER!!!" + "\033[0m");
                input.nextLine();
                continue;
            }
        }

        clearScreen();
        //Calls the appropriate method based on the user's selection
        switch (option) {
            case 1:
                addPart();
                break;
            case 2:
                removePart();
                break;
            case 3:
                updatePart();
                break;
            case 4:
                borrowPart();
                break;
            case 5:
                returnPart();
                break;
            case 6:
                //Prints out all parts in the inventory
                System.out.println("\033[31m" + "BRAND" + "\033[0m" + "-" + "\033[33m" + "MODEL#" + "\033[0m" + " " + "DESCRIPTION"
                    + " |\033[36m " + "QUANTITY" + "\033[35m " + "LOCATION" + "\033[0m");
                System.out.println("\033[32m" + "All Parts:" + "\033[0m\n");
                for (part p : inventory) {
                    System.out.println(p);
                }
                break;
            case 7:
                listAllBorrowed();
                break;
            case 8:
                //Prints out the history of a part
                System.out.println("Enter the " + "\033[31m" + "brand" + "\033[0m" + " of the part: ");
                input.nextLine();
                String brand = input.nextLine().strip();

                System.out.println("Enter the " + "\033[31m" + "model number" + "\033[0m" + " of the part: ");
                int modelNumber = input.nextInt();

                part currPart = new part(brand, "", modelNumber, 0, "");

                if (!inventory.contains(currPart)) {
                    System.out.println("\033[31m" + "Part does not exist"+ "\033[0m");
                    break;
                }
                else {
                    for (part p : inventory) {
                        if (p.equals(currPart)) {
                            System.out.println("Part: " + p + "\033[32m" + "\n\nHistory of Part: " + "\033[0m");
                            p.printHistory();
                            break;
                        }
                    }
                }
                System.out.println();
                break;
            case 9:
                listAllPartsOfBrand();
                break;
            case 10:
                //Prints out all attributes of a part
                System.out.println("Enter the " + "\033[31m" + "brand" + "\033[0m" + " of the part: ");
                input.nextLine();
                String brand2 = input.nextLine().strip();

                System.out.println("Enter the " + "\033[31m" + "model number" + "\033[0m" + " of the part: ");
                int modelNumber2 = input.nextInt();

                part currPart2 = new part(brand2, "", modelNumber2, 0, "");

                if (!inventory.contains(currPart2)) {
                    System.out.println("\033[31m" + "Part does not exist"+ "\033[0m");
                    break;
                }
                else {
                    for (part p : inventory) {
                        if (p.equals(currPart2)) {
                            System.out.println("Part: " + p + "\033[0m");
                            break;
                        }
                    }
                }
                System.out.println();
                break;
            case 11:
                printQueue();
                break;
            case 12:
                //Prints out all parts in a location
                System.out.println("Enter the " + "\033[31m" + "location" + "\033[0m" + " of the part: ");
                input.nextLine();
                String location = input.nextLine().strip().toUpperCase();

                if(!locationTable.containsKey(location)) {
                    System.out.println("\033[31m" + "Location does not exist" + "\033[0m");
                    break;
                }
                else {
                    System.out.println("Parts in location: " + location);
                    for(part p : locationTable.get(location)) {
                        System.out.println(p);
                    }
                }
                break;
            case 13:
                //Prints out all parts in order of quantity
                ArrayList<part> sortedInventory = new ArrayList<part>(inventory);
                sortByQuantity(sortedInventory);
                System.out.println("\033[31m" + "BRAND" + "\033[0m" + "-" + "\033[33m" + "MODEL#" + "\033[0m" + " " + "DESCRIPTION"
                    + " |\033[36m " + "QUANTITY" + "\033[35m " + "LOCATION" + "\033[0m" + " (in terms of quantity)");
                System.out.println("\033[32m" + "All Parts:" + "\033[0m\n");
                for (part p : sortedInventory) {
                    System.out.println(p);
                }
                break;
            case 14:
                //Exits the program
                System.out.println("\033[32m" + "Thank you for using the Garage Inventory System\nCreated by Navraj Singh and Risham Singh" + "\033[0m");
                System.exit(0);
                break;
            default:
                break;
        }
        //Pauses the program until the user presses enter
        input.nextLine();
        System.out.println("Press enter to continue");
        input.nextLine();
    }

    /*
     * clearScreen() 
     * This method clears the screen
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /*
     * addPart() 
     * This method adds a part to the inventory
     */
    public static void addPart() {
        //Gets the details of the part from the user
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the " + "\033[31m" + "brand" + "\033[0m" + " of the part: ");
        String brand = input.nextLine().strip();

        System.out.println("Enter the " + "\033[31m" + "description" + "\033[0m" + " of the part: ");
        String description = input.nextLine();

        System.out.println("Enter the " + "\033[31m" + "model number" + "\033[0m" + " of the part: ");
        int modelNumber = input.nextInt();

        //Checks if the part already exists
        if (inventory.contains(new part(brand, description, modelNumber, 0, ""))) {
            System.out.println("\033[34m" + "Part already exists!" + "\033[0m" + "\nWould you like to add to the quantity? (y/n)");
            String option = input.next();
            //If the user wants to add to the quantity, then the quantity of the part is updated
            if (option.toLowerCase().equals("y")) {
                System.out.println("Enter the " + "\033[31m" + "quantity" + "\033[0m" + " of the part: ");
                int quantity = input.nextInt();
                for (part p : inventory) {
                    if (p.equals(new part(brand, description, modelNumber, 0, ""))) {
                        p.quantity += quantity;
                        System.out.println("\033[32m" + "Part Quantity Updated" + "\033[0m");
                        return;
                    }
                }
                
            }
            return;
        }

        System.out.println("Enter the " + "\033[31m" + "quantity" + "\033[0m" + " of the part: ");
        int quantity = input.nextInt();

        System.out.println("Enter the " + "\033[31m" + "location" + "\033[0m" + " of the part: ");
        input.nextLine();
        String location = input.nextLine().toUpperCase().strip();

        //Adds the part to the inventory
        part newPart = new part(brand, description, modelNumber, quantity, location);
        inventory.add(newPart);

        //Checks if the quantity is low, if it is then the part is added to the orders queue
        if(!orders.contains(newPart) && newPart.quantity <= 0) {
            orders.add(newPart);
        }

        //Adds the part's brand and the unique ID to the binary tree
        BTnode myBTNODE;
        if(!binaryTree.doesExist(newPart.brand)) {
            binaryTree.insert(newPart.brand, new ArrayList<LinkedList<partCopy>>());
        }
        myBTNODE = binaryTree.search(newPart.brand);
        ArrayList<LinkedList<partCopy>> details = (ArrayList<LinkedList<partCopy>>) myBTNODE.data[1];
        details.add(new LinkedList<partCopy>());
        LinkedList<partCopy> addCopy = new LinkedList<partCopy>();
        addCopy.add(new partCopy(newPart.uniqueID, "", false));
        details.set(details.size() - 1, addCopy);

        //Adds the part to the location table
        if(locationTable.containsKey(newPart.location)) {
            locationTable.get(newPart.location).add(newPart);
        }
        else {
            ArrayList<part> newLocation = new ArrayList<part>();
            newLocation.add(newPart);
            locationTable.put(location, newLocation);
        }

        System.out.println("\033[32m" + "Part Added" + "\033[0m");
    }

    /*
     * removePart() 
     * This method removes a part from the inventory
     */
    public static void removePart() {
        //Gets the details of the part from the user
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the " + "\033[31m" + "brand" + "\033[0m" + " of the part: ");
        String brand = input.nextLine().strip();

        System.out.println("Enter the " + "\033[31m" + "model number" + "\033[0m" + " of the part: ");
        int modelNumber = input.nextInt();

        part currPart = new part(brand, "", modelNumber, 0, "");

        //Checks if the part exists and if it does removes from inventory
        if (!inventory.contains(currPart)) {
            System.out.println("\033[31m" + "Part does not exist"+ "\033[0m");
        }
        else {
            for(part p : inventory) {
                if(p.equals(currPart)) {
                    //Removes the part from the binary tree
                    BTnode myBTNODE = binaryTree.search(brand.toUpperCase());
                    ArrayList<LinkedList<partCopy>> details = (ArrayList<LinkedList<partCopy>>) myBTNODE.data[1];
                    for(LinkedList<partCopy> partCopies : details) {
                        if(partCopies.getFirst().uniqueID.equals(currPart.uniqueID)) {
                            details.remove(partCopies);
                            break;
                        }
                    }

                    //Removes the part from the location table
                    if(locationTable.containsKey(p.location)) {
                        locationTable.get(p.location).remove(p);
                    }
                    break;
                }
            }
            inventory.remove(currPart);

            //Removes the part from the orders queue
            if(orders.contains(currPart)) {
                orders.remove(currPart);
            }

            System.out.println("\033[32m" + "Part removed" + "\033[0m");
        }
    }

    /*
     * updatePart() 
     * This method updates a part in the inventory
     */
    public static void updatePart() {
        //Gets the details of the part from the user
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the " + "\033[31m" + "brand" + "\033[0m" + " of the part: ");
        String brand = input.nextLine().strip();

        System.out.println("Enter the " + "\033[31m" + "model number" + "\033[0m" + " of the part: ");
        int modelNumber = input.nextInt();

        part currPart = new part(brand, "", modelNumber, 0, "");

        //Checks if the part exists and if it does updates the part
        if (!inventory.contains(currPart)) {
            System.out.println("\033[31m" + "Part does not exist"+ "\033[0m");
            return;
        }
        else {
            System.out.println("Enter new details: ");
            System.out.println("Enter the " + "\033[31m" + "description" + "\033[0m" + " of the part: ");
            input.nextLine();
            String description = input.nextLine();
            System.out.println("Enter the " + "\033[31m" + "quantity" + "\033[0m" + " of the part: ");
            int quantity = input.nextInt();
            System.out.println("Enter the " + "\033[31m" + "location" + "\033[0m" + " of the part: ");
            input.nextLine();
            String location = input.nextLine().toUpperCase().strip();

            for (part p : inventory) {
                if (p.equals(currPart)) {
                    //Removes the part from the location table and updates new location
                    if(locationTable.containsKey(p.location)) {
                        locationTable.get(p.location).remove(p);
                    }
                    p.description = description;
                    p.quantity = quantity;
                    //Checks if the quantity is low, if it is then the part is added to the orders queue
                    if(p.quantity <= 0 && !orders.contains(p)) {
                        orders.add(p);
                    }
                    else if(p.quantity > 0 && orders.contains(p)) {
                        orders.remove(p);
                    }
                    p.location = location;
                }
                if(locationTable.containsKey(p.location)) {
                    locationTable.get(p.location).add(p);
                }
                else {
                    ArrayList<part> newLocation = new ArrayList<part>();
                    newLocation.add(p);
                    locationTable.put(p.location, newLocation);
                }

                System.out.println("\033[32m" + "Part Updated" + "\033[0m");
                return;
            }
        }
    }

    /*
     * borrowPart() 
     * This method borrows a part from the inventory
     */
    public static void borrowPart() {
        //Gets the details of the part from the user
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the " + "\033[31m" + "brand" + "\033[0m" + " of the part: ");
        String brand = input.nextLine().strip();

        System.out.println("Enter the " + "\033[31m" + "model number" + "\033[0m" + " of the part: ");
        int modelNumber = input.nextInt();

        part currPart = new part(brand, "", modelNumber, 0, "");

        //Checks if the part exists and if it does borrows the part
        if (!inventory.contains(currPart)) {
            System.out.println("\033[31m" + "Part does not exist"+ "\033[0m");
            return;
        }
        else {
            String uniqueID = brand.toUpperCase() + modelNumber;
            System.out.println("Enter the " + "\033[31m" + "name" + "\033[0m" + " of the borrower: ");
            input.nextLine();
            String name = input.nextLine();
            System.out.println("Is the borrower keeping the item" + "\033[31m" + " (y/n):" + "\033[0m");
            String selection = input.next().toLowerCase();
            boolean isKeeping = selection.equals("y");

            for (part p : inventory) {
                if (p.equals(currPart)) {
                    //Checks if the part is out of stock
                    if ((p.quantity - p.numBorrowed) <= 0) {
                        System.out.println("\033[31m" + "Part is out of stock" + "\033[0m");
                        return;
                    }
                    else {
                        //Update part's quantity and history depending on if the part is being kept or not
                        if(isKeeping) {
                            p.quantity--;
                            p.addHistory(new String[] {"Kept", name});
                            System.out.println("\033[32m" + "Part Kept" + "\033[0m");
                        }
                        else {
                            p.numBorrowed++;
                            p.addHistory(new String[] { "Borrowed", name });
                            System.out.println("\033[32m" + "Part Borrowed" + "\033[0m");
                        }
                        if(p.quantity <= 0) {
                            if(!orders.contains(p)) {
                                orders.add(p);
                            }
                        }

                        //Add part's copy in the binary tree
                        BTnode myBTNODE = binaryTree.search(brand.toUpperCase());
                        ArrayList<LinkedList<partCopy>> details = (ArrayList<LinkedList<partCopy>>) myBTNODE.data[1];
                        for(LinkedList<partCopy> partCopies : details) {
                            if(partCopies.getFirst().uniqueID.equals(uniqueID)) {
                                partCopies.add(new partCopy(uniqueID, name, isKeeping));
                                break;
                            }
                        }

                        
                        return;
                    }
                }
            }
        }
    }

    /*
     * returnPart() 
     * This method returns a part to the inventory
     */
    public static void returnPart() {
        //Gets the details of the part from the user
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the " + "\033[31m" + "brand" + "\033[0m" + " of the part: ");
        String brand = input.nextLine().strip();

        System.out.println("Enter the " + "\033[31m" + "model number" + "\033[0m" + " of the part: ");
        int modelNumber = input.nextInt();

        part currPart = new part(brand, "", modelNumber, 0, "");

        //Checks if the part exists and if it does returns the part
        if (!inventory.contains(currPart)) {
            System.out.println("\033[31m" + "Part does not exist"+ "\033[0m");
            return;
        }
        else {
            String uniqueID = brand.toUpperCase() + modelNumber;
            System.out.println("Enter the " + "\033[31m" + "name" + "\033[0m" + " of the borrower: ");
            input.nextLine();
            String name = input.nextLine();

            for (part p : inventory) {
                if (p.equals(currPart)) {
                    if (p.numBorrowed <= 0) {
                        System.out.println("\033[31m" + "No instances of Part being borrowed" + "\033[0m");
                        return;
                    }
                    else {
                        //Attempt to locate and remove the part's copy in the binary tree
                        boolean actualyReturned = false;
                        partCopy myPartCopy = new partCopy(uniqueID, name, false);

                        BTnode myBTNODE = binaryTree.search(brand.toUpperCase());
                        ArrayList<LinkedList<partCopy>> details = (ArrayList<LinkedList<partCopy>>) myBTNODE.data[1];
                        for(LinkedList<partCopy> partCopies : details) {
                            if(partCopies.getFirst().uniqueID.equals(uniqueID)) {
                                for(partCopy partCopy : partCopies) {
                                    if(partCopy.equals(myPartCopy)) {
                                        actualyReturned = true;
                                        partCopies.remove(partCopy);
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        //Check if it was actually returned
                        if(!actualyReturned) {
                            System.out.println("\033[31m" + "Error part was not borrowed by " + name + "\033[0m");
                            return;
                        }
                        //If part was removed it will do the following
                        p.numBorrowed--;

                        p.addHistory(new String[] { "Returned", name });
                        System.out.println("\033[32m" + "Part Returned" + "\033[0m");
                        return;
                    }
                }
            }
        }
    }

    /*
     * listAllBorrowed()
     * This method lists all parts that are borrowed
     */
    public static void listAllBorrowed() {
        boolean didPrint = false; //Checks if there are any instances of any part being borrowed
        //Checking each part against the binary tree to see if any part is borrowed
        for(part p : inventory) {
            if(p.numBorrowed > 0) {
                partCopy mPartCopy = new partCopy(p.uniqueID, "", false);
                BTnode myBTNODE = binaryTree.search(p.brand.toUpperCase());
                ArrayList<LinkedList<partCopy>> details = (ArrayList<LinkedList<partCopy>>) myBTNODE.data[1];
                //Gets the part information from the binary tree
                for(LinkedList<partCopy> partCopies : details) {
                    //Prints out the list of part copies if the part is borrowed
                    if(partCopies.getFirst().equals(mPartCopy)){
                        System.out.println("Part: \033[31m" + mPartCopy.getBrand() + "\033[0m-\033[33m" + p.uniqueID.substring(mPartCopy.getBrand().length()) + "\033[0m is borrowed by:");
                        for(int i = 1; i < partCopies.size(); i++) {
                            System.out.println("\033[3m" + partCopies.get(i).reserverName + "\033[0m");
                        }
                    }
                }
                didPrint = true; //Since there is at least one part borrowed the boolean is true
            }
        }
        //Appropriate message if no parts are borrowed
        if(!didPrint) {
            System.out.println("\033[31m" + "No parts are borrowed yet" + "\033[0m");
        }
        System.out.println();
    }

    /*
     * listAllPartsOfBrand()
     * This method lists all parts of a brand
     */
    public static void listAllPartsOfBrand() {
        //Gets the brand from the user
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the " + "\033[31m" + "brand" + "\033[0m" + " of the part: ");
        String brand = input.nextLine().strip().toUpperCase();

        //Checks if the brand exists
        BTnode myBTNODE = binaryTree.search(brand);
        if(myBTNODE == null) {
            System.out.println("\033[31m" + "Brand does not exist" + "\033[0m");
            return;
        }
        //If brand exists, then it prints out all parts of the brand from the binary tree
        ArrayList<LinkedList<partCopy>> details = (ArrayList<LinkedList<partCopy>>) myBTNODE.data[1];
        System.out.println("Parts of brand: \033[31m" + brand + "\033[0m");
        for(LinkedList<partCopy> partCopies : details) {
            System.out.println("\033[31m" + brand + "\033[0m-\033[33m" + partCopies.getFirst().uniqueID.substring(brand.length()) + "\033[0m");
        }
        System.out.println();
    }

    /*
     * printQueue()
     * This method prints out all parts that need to be ordered
     */
    public static void printQueue() {
        System.out.println("Parts that need to be ordered: ");
        for(part p : orders) {
            System.out.println(p);
        }
    }

    /*
     * sortByQuantity()
     * This method sorts an arraylist of parts by quantity through bubble sort
     */
    public static void sortByQuantity(ArrayList<part> arr) {
        int n = arr.size();
        //Bubble sort
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr.get(j).quantity > arr.get(j + 1).quantity) {
                    part temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                }
            }
        }
    }

    /*
     * main()
     * This method runs the program through a forever loop
     */
    public static void main(String[] args) {
        while (true) {
            getOption();
        }
    }
}
