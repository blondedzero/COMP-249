// -----------------------------------------------------
// Assignment 3
// Question 2
// Written by: Nicholas Chamoun (40264135) & Kaila Quimson (40240746)
// -----------------------------------------------------

import java.util.NoSuchElementException;

public class CellList{

    //private inner CellNode class
    class CellNode {
        
        // CellNode attributes
        private CellPhone phone;    // CellPhone object
        private CellNode node;      // pointer to a CellNode object

       // CellNode default constructor
        public CellNode(){
        this.phone = null;
        this.node = null;
        }

        // CellNode parameterized constructor
        public CellNode(CellPhone phone, CellNode node) {
            this.phone = phone;
            this.node = node;
        }

        // CellNode copy constructor
        public CellNode(CellNode copy) {
            this.phone = new CellPhone(copy.phone, copy.phone.getSerialNum());
            this.node = copy.node;
        }

        // CellNode clone method
        @Override
        public CellNode clone() {
            return new CellNode(this);
        }

        // CellPhone accessor
        public CellPhone getPhone() {
            return phone;
        }

        //CellNode accessor & mutator
        public CellNode getNode() {
            return node;
        }
        public void setNode(CellNode node) {
            this.node = node;
        }
    }

    // CellList attributes
    private CellNode head;  // points to first node in list
    private int size;       // # of nodes in list

    // CellList default constructor
    public CellList() { // creates an empty list
		
        head = null;
        size = 0;
		
    }

    // CellList copy constructor
        public CellList(CellList copy) {
			
            this.head = null; // initialized empty list
			this.size = 0; // initilize  to 0
            
			CellNode original = copy.head;
			
			// Loops through the copy list until it reaches nothing
			while (original != null) {

				addToStart(original.getPhone()); // It add the phone from the original node up to the front of the new list
				original = original.getNode(); // moves to the following node 

			}
        }

	// ---- METHODS ----
	// addToStart method
    public void addToStart(CellPhone phone) {
        head = new CellNode(phone, head);
        size++; // increment size
    }

    // replaceAtIndex method
    public void replaceAtIndex(CellPhone phone, int index) {

        //validates index
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Error: Invalid index!");
        }
        // Goes through list to find at index
        CellNode current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.node;
        }
        current.node = new CellNode(phone, current.node);
    }

	// insertAtIndex method
    public void insertAtIndex(CellPhone phone, int index) {

        if (index < 0 || index >= size) throw new NoSuchElementException("Error: Invalid index!");
        if (index == 0) {
            addToStart(phone);
        }
        replaceAtIndex(phone, index);
    }

	// deleteFromIndex method
     public void deleteFromIndex(int index) {
        if (index < 0 || index >= size) throw new NoSuchElementException("Error: Invalid index!");

        if (index == 0) {
            deleteFromStart(); // method defined later
            
        }
        CellNode current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNode();
        }
        CellNode nodeDelete = current.node;
        current.node = nodeDelete.node;
        size--;
    }
	
	
    // deleteFromStart method
    public void deleteFromStart() {
        if (head != null) {
            head = head.node;
            size--; // decrement size
        }
    }

    // contains method 
    public boolean contains(long serialNum) {   
        return (find(serialNum) != null); // method defined later
    }

    // find method
    public CellNode find(long serialNum) {
        CellNode current = head;
        int i = 0;
        while (current != null) {
            i++;
            if (current.phone.getSerialNum() == serialNum) {
                System.out.println("Found after " + i + " iterations.");
                return current;
            }
            current = current.node;
        }
        System.out.println("Not found after " + i + " iterations.");
        return null;
    }

    // showContents method
    public void showContents() {
        System.out.println("The current size of the list is " + size + ". Here are the contents of the list");
        System.out.println("====================================================================");
        CellNode current = head;
        while (current != null) {
            System.out.print("[" + current.phone + "] ---> ");
            current = current.node;
        }
        System.out.println("X"); // end of list's contents
    }
    
    // CellList equals method 
    public boolean equals(CellList o) {
        
        if (this.size != o.size) {
            return false;
        }

        CellNode c1 = this.head;
        CellNode c2 = o.head;

        // loops until it reached an null
        while (c1 != null) {
            if (!c1.phone.equals(c2.phone)) { // confirms that c1 is not the same as c2 thus returns false
                return false;
            }
            c1 = c1.node; // moves c1 to the next node in list
            c2 = c2.node; // moves c2 to the next node in list
        }
        return true;
    }


} 

/* Instructions:

III) The CellList class has the following:
(b) A private attribute called head, which should point to the first node in this list object;
(c) A private attribute called size, which always indicates the current size of the list (how many nodes are in thelist);
(d) A default constructor, which creates an empty list;
(e) A copy constructor, which accepts a CellList object and creates a copy of it;
(f) A method called addToStart(), which accepts one parameter, an object from CellPhone class. The method then creates a node with that passed object and inserts this node at the head of the list;
(g) A method called insertAtIndex(), which accepts two parameters, an object from CellPhone class, and an
integer representing an index. If the index is not valid (a valid index must have a value between 0 and size-1),
the method must throw a NoSuchElementException and terminate the program. If the index is valid, then the method creates a node with the passed CellPhone object and inserts this node at the given index. The method must properly handle all special cases;
(h) A method called deleteFromIndex(), which accepts one integer parameter representing an index. Again, if the index is not valid, the method must throw a NoSuchElementException and terminate the program. Otherwise;
the node pointed by that index is deleted from the list. The method must properly handle all special cases;
(i) A method called deleteFromStart(), which deletes the first node in the list (the one pointed by head). All
special cases must be properly handled.
(j) A method called replaceAtIndex(), which accepts two parameters, an object from CellPhone class, and an integer representing an index. If the index is not valid, the method simply returns; otherwise the object in the node
at the passed index is to be replaced by the passed object;
(k) A method called find(), which accepts one parameter of type long representing a serial number. The method then searches the list for a node with a cell phone with that serial number. If such an object is found, then the method returns a pointer to that node where the object is found; otherwise, the method returns null. The method must keep track of how many iterations were made before the search finally finds the phone or concludes that it
is not in the list;
(l) A method called contains(), which accepts one parameter of type long representing a serial number. The method returns true if an object with that serial number is in the list; otherwise, the method returns false;
(m) A method called showContents(), which displays the contents of the list, in a similar fashion to what is shown below:

The current size of the list is 23. Here are the contents of the list
[1119000: SonyEricsson 347.94$ 2009] ---> [2389076: BlackBerry 564.22$ 2010] ---> [5909887: Apple 726.99$ 2022] --->
[9873330: Nokia 677.9$ 2010] ---> [5555902: SonyEricsson 177.11$ 2007] ---> [8006832: Motorola 423.22$ 2008] --->
[6987612: НТС 577.25$ 2009] ---> [2999900: Siemens 457.28$ 2006] ---> [7333403: BenQ 659.0$ 2009] --->
[5890779: Motorola 457.28$ 2007] ---> [8888902: Samsung 810.35$ 2020] ---> [5000882: Apple 977.27$ 2016] --->
[1119002: Motorola 457.28$ 2008] ---> [9675654: Nokia 388.0$ 2009] ---> [6699001: Lenovo 237.29$ 2012] --->
[2887460: Siemens 457.28$ 2009] ---> [7559090: Pansonic 290.9$ 2005] ---> [2887685: Motorola 569.28$ 2012] --->
[89076: Sharp 564.22$ 2009] ---> [1989000: Nokia 237.24$ 2006] ---> [4900088: LG 232.99$ 2022] --->
[2787985: Acer 572.2$ 2013] --> [3890909: Samsung 857.28$ 2021] ---> X


(n) A method called equals(), which accepts one parameter of type CellList. The method returns true if the two
lists contain similar objects; otherwise the method returns false. Recall that two CellPhone objects are equal
if they have the same values with the exception of the serial number, which can, and actually is expected to be,
different.

*/
