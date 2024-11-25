// -----------------------------------------------------
// Assignment 3
// Question 3
// Written by: Nicholas Chamoun (40264135) & Kaila Quimson (40240746)
// -----------------------------------------------------

public class CellList{


}







/* Instructions:

III) The CellList class has the following:
(a) An inner class called CellNode. This class has the following:
	i. Two private attributes: an object of CellPhone and a pointer to a CellNode object;
	ii. A default constructor, which assigns both attributes to null;
	iii. A parameterized constructor that accepts two parameters, a CellPhone object and a CellNode object,
then initializes the attributes accordingly;
	iv. A copy constructor;
	v. A clone() method;
	vi. Other mutator and accessor methods.
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
