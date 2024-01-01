package Garage;
import java.util.*;

/*
 * Modified version of the Binary Tree class from assignment 8
 */
public class BT {
    private BTnode root; //root node

    public BT() { //constructor
        root = null;
    }

    /* Function to check if tree is empty */
    public boolean isEmpty()    {
        return root == null;
    }

    /* 
     * Functions to insert data 
     * Modified to take in a String brand name and 
     * an arraylist of linked lists of part copies
    */
    public void insert(String brand, ArrayList<LinkedList<partCopy>> details)   {
        root = insert(root, brand, details);
    }

    /* Function to insert data recursively 
     * Modified to take in a String brand name and
     * an arraylist of linked lists of part copies.
     * 
     * It also inserts the data in alphabetical order 
     * in respect to the brand name.
    */
    private BTnode insert(BTnode node, String brand, ArrayList<LinkedList<partCopy>> details) {
        if (node == null)
            node = new BTnode(brand, details);
        else
        {
            int compareResult = brand.compareTo(node.getDataBrand());
            if (compareResult >= 0)
                node.right = insert(node.right, brand, details);
            else
                node.left = insert(node.left, brand, details);
        }
        return node;
    }

    /* Function to count number of nodes */
    public int countNodes()
    {
        return countNodes(root);
    }
    /* Function to count number of nodes recursively */
    private int countNodes(BTnode r)
    {
        if (r == null)
            return 0;
        else
        {
            int l = 1;
            l += countNodes(r.getLeft());
            l += countNodes(r.getRight());
            return l;
        }
    }

    /* Function to search for an element */
    public boolean doesExist(String targetVal)
    {
        if (countNodes() <= 0) {
            return false;
        }
        return search(targetVal) != null;
    }

    /* Function to search for an element 
     * Modified to take in a String brand name and
     * search for it.
    */
    public BTnode search(String targetVal)
    {
        return search(root, targetVal);
    }
    /* Function to search for an element recursively 
     * Only compares the brand names
    */
    private BTnode search(BTnode r, String targetVal)
    {
        if (r == null) {
            return null;
        }
        if (r.getDataBrand().equals(targetVal) && r.getDataDetails() != null) {
            return r;
        }
        BTnode leftResult = search(r.getLeft(), targetVal);
        if (leftResult != null) {
            return leftResult;
        }
        return search(r.getRight(), targetVal);
    }

    /* Function to print the binary tree */
    public void printTree() {
        printTree(root, 0);
    }
    /* Function to print the binary tree's brand elements */
    private void printTree(BTnode root2, int i) {
        if (root2 == null) {
            return;
        }
        printTree(root2.getRight(), i + 1);
        for (int j = 0; j < i; j++) {
            System.out.print("    ");
        }
        System.out.println(root2.getDataBrand());
        printTree(root2.getLeft(), i + 1);
    }
}
