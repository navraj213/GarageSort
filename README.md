# GarageSort
A Java Software garage inventory manager designed to efficiently organize tools and parts, employing data structures.

### Problem Statement
We need to tidy up our garage by arranging tools and parts more efficiently. Our goal is to avoid having duplicate items, stop ordering things we already have, and make it easy to locate everything. Currently, we end up with extra copies of items we don't need, leading to unnecessary spending. Plus, the lack of organization makes it challenging to find things, resulting in unintentional purchases. We want to save money by cutting unnecessary expenses and have a clear system for finding items in our garage.

The software will keep track of the tools/parts in an organized manner and have functions to keep track of the quantity, search and retrieve the location of these tools, and even highlight certain things that need to be ordered if they're low on supply. 

### Flow Chart
![Blank board - Page 1](https://github.com/navraj213/GarageSort/assets/85139834/8bbeff08-c4a9-4371-a385-79559e5bdb18)

## Data Structures Used
### Linked List
Garage.BTNode - Line 10: public Object[] data;
			        [String brand, ArrayList<LinkedList<partCopy>> details]
ArrayList<LinkedList<partCopy>> object which represents the different types of parts that brand has and who holds those certain copies inside of the linked list. For example, if there was a BMW part #, 101, 202, 303, 404 and Nav borrows parts 101 and 404. The data would look like the following,
[“BMW”, <	<BMW101, Nav>,
<BMW202>,
<BMW303>,
<BMW404, Nav>> ]

### Stack
Garage.part - Line 17: public Stack<String[]> history;
We used a stack to keep track of the part’s history. The top element would be the recent activity of the part, and the last element would be the first activity of the part. 

### Queue 
Garage.garageDriver - Line 8: public static Queue<part> orders = new LinkedList<>();
We used a queue to place the orders of low-quantity parts. A queue would be best for this operation because low-quantity parts would get added to the queue and additional parts would go on top of the queue. The first part would get ordered first and the following. 

### Hash Table/Map
Garage.garageDriver - Line 6: public static HashSet<part> inventory = new HashSet<part>();
We used a HashSet to keep track of all the parts in our inventory. We used a hashset so we don’t accidentally put duplicate parts, and this ensures quick lookup. 

Garage.garageDriver - Line 9: public static Hashtable<String, ArrayList<part>> locationTable = 
new Hashtable<String, ArrayList<part>>();
A Hashtable keeps track of all parts in a location. It stores a key and a value. The Key is a String location and the value is an ArrayList of all the parts available in that location. 

### Bubble Sort
Garage.garageDriver - Line 595-607: 
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
A regular bubble sort that sorts an ArrayList of part Objects with respect to their quantity. The lowest quantity is on the top and the highest quantity is on the bottom. 

### Binary Tree
Garage.garageDriver - Line 7: public static BT binaryTree = new BT();
The binary tree organizes the brands of our inventory and keeps track of all the borrowers of the parts. For example, if we add a Toyota branded air filter it either finds a node that represents the Toyota brand, and if it doesn’t exist it will then create a new node to store all Toyota branded items. Then inside the Toyota node, it will add a copy of the part representing the partCopy objects. 

## Screenshots
Option Screen
![Screenshot 2023-12-31 at 8 06 26 PM](https://github.com/navraj213/GarageSort/assets/85139834/a06ad7f6-7090-41f6-b348-45ffbd77abe2)

### Option 1
![Screenshot 2023-12-31 at 8 06 44 PM](https://github.com/navraj213/GarageSort/assets/85139834/643c31f2-237e-4a8c-b081-a204f905828c)


### Option 2
![Screenshot 2023-12-31 at 8 07 01 PM](https://github.com/navraj213/GarageSort/assets/85139834/9373160a-8289-4875-bde5-4e0a941b8687)


### Option 3
![Screenshot 2023-12-31 at 8 07 20 PM](https://github.com/navraj213/GarageSort/assets/85139834/9a350668-5f9e-481e-a8de-4ff6ee7cd864)


### Option 4
![Screenshot 2023-12-31 at 8 07 34 PM](https://github.com/navraj213/GarageSort/assets/85139834/032dd1e6-e051-4402-8467-a87eaf5545d2)


### Option 5
![Screenshot 2023-12-31 at 8 07 48 PM](https://github.com/navraj213/GarageSort/assets/85139834/9e3bd031-1eee-4c98-be95-276b489607a8)


### Option 6
![Screenshot 2023-12-31 at 8 08 03 PM](https://github.com/navraj213/GarageSort/assets/85139834/c677053b-dbdf-4589-a03c-119dba1f7537)


### Option 7
![Screenshot 2023-12-31 at 8 08 15 PM](https://github.com/navraj213/GarageSort/assets/85139834/77d97903-f9f3-47da-be80-71457266d996)


### Option 8
![Screenshot 2023-12-31 at 8 08 30 PM](https://github.com/navraj213/GarageSort/assets/85139834/d0ff0efa-e6ad-4b9d-b034-40916641144e)


### Option 9
![Screenshot 2023-12-31 at 8 08 46 PM](https://github.com/navraj213/GarageSort/assets/85139834/9335fd02-8515-4fd5-b311-fdd7787c4c94)


### Option 10
![Screenshot 2023-12-31 at 8 08 57 PM](https://github.com/navraj213/GarageSort/assets/85139834/8b1a845d-740c-4675-9bc0-a2bd55d341dc)


### Option 11
![Screenshot 2023-12-31 at 8 09 11 PM](https://github.com/navraj213/GarageSort/assets/85139834/bc88afb5-7276-4063-ae1d-0d8370f553df)


### Option 12
![Screenshot 2023-12-31 at 8 09 33 PM](https://github.com/navraj213/GarageSort/assets/85139834/91ad5827-c016-4fac-b790-c9b94d0aad38)


### Option 13
![Screenshot 2023-12-31 at 8 09 48 PM](https://github.com/navraj213/GarageSort/assets/85139834/11f79547-b186-4726-ac34-36f66f9b9f1d)


### Option 14
![Screenshot 2023-12-31 at 8 10 03 PM](https://github.com/navraj213/GarageSort/assets/85139834/aaca0892-2277-4b70-814b-3128fef6cedc)
