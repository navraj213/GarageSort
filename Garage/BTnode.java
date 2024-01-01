package Garage;
import java.util.*;

/*
 * Modified version of the Binary Tree class from assignment 8
 */
public class BTnode
{
    BTnode left, right; //left and right nodes
    public Object[] data; //data stored in node [String brand, ArrayList<LinkedList<partCopy>> details]

    /* Constructor 
     * Modified to take in a String brand name and
     * an arraylist of linked lists of part copies.
    */
    public BTnode(String brand, ArrayList<LinkedList<partCopy>> details)
    {
        left = null;
        right = null;
        data = new Object[]{brand, details};
    }

    /* Function to set left node */
    public void setLeft(BTnode n)
    {
        left = n;
    }
    /* Function to set right node */
    public void setRight(BTnode n)
    {
        right = n;
    }
    /* Function to get left node */
    public BTnode getLeft()
    {
        return left;
    }
    /* Function to get right node */
    public BTnode getRight()
    {
        return right;
    }

    /* Function to set data to node 
     * Modified to take in a String brand name and
     * an arraylist of linked lists of part copies.
    */
    public void setData(String brand, ArrayList<LinkedList<partCopy>> details)
    {
        data = new Object[]{brand, details};
    }

    /* Function to get data from node 
     * Modified to return the element at index 0 of the data array (the brand name).
    */
    public String getDataBrand()
    {
        if (data[0] == null) {
            return "";
        }
        return (String) data[0];
    }
    /* Function to get the element at index 1 of the data array (ArrayList of part copies) */
    public ArrayList<LinkedList<partCopy>> getDataDetails()
    {
        return (ArrayList<LinkedList<partCopy>>) data[1];
    }
}