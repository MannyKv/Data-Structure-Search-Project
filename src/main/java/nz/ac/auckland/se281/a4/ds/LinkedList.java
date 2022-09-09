package nz.ac.auckland.se281.a4.ds;

import java.util.NoSuchElementException;
//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//THIS CLASS ALSO HAS TO BE MADE 
//GENERIC
//*******************************

/**
 * The Linked List Class Has only one head pointer to the start node (head)
 * Nodes are indexed starting from 0. The list goes from 0 to size-1.
 *
 * @author Partha Roop
 */
public class LinkedList<T> {
	// the head of the linked list
	private Node<T> head;

	/**
	 * Constructor for LinkedList
	 */
	public LinkedList() {
		head = null;
	}

	/**
	 * This method returns a reference to a node whose position is at pos TODO:
	 * Complete this method
	 * 
	 * @param pos: an integer specifying the position of the node to be located
	 * @return Node: the reference to the Node at position pos
	 * @throws InvalidPositionException if position is less than 0 or greater than
	 *                                  size-1
	 * @throws NoSuchElementException   if the element does not exist in the
	 *                                  LinkedList
	 */
	private Node<T> locateNode(int pos) throws InvalidPositionException {

		throw new java.lang.UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * This method adds a node with specified data as the start node of the list
	 * TODO: Complete this method
	 *
	 * @param element a parameter, which is the value of the node to be prepended
	 */
	public void prepend(T element) {
		Node<T> n = new Node<T>(element);

		if (size() == 0) {
			head = n;
		} else {
			n.setNext(head);
			head = n;
		}
	}

	/**
	 * This method adds a node with specified data as the end node of the list TODO:
	 * Complete this method
	 *
	 * @param element a parameter, which is the value of the node to be appended
	 */

	// Note this method has been refactored using the helper methods
	// I will do this as a small ACP exercise in class
	public void append(T element) {
		Node<T> t = new Node<T>(element);
		if (head == null) {
			head = t;
		} else {
			Node<T> temp = head;

			while (temp.getNext() != null) {
				temp = temp.getNext();
			}
			temp.setNext(t);
		}
	}

	/**
	 * This method gets the value of a node at a given position TODO: Complete this
	 * method
	 *
	 * @param pos an integer, which is the position
	 * @return the value at the position pos
	 * @throws InvalidPositionException if position is less than 0 or greater than
	 *                                  size-1
	 */
	public T get(int pos) throws InvalidPositionException {
		if (head == null) {
			throw new InvalidPositionException();
		}
		int count = 0;
		Node<T> temp = head;
		while (count != pos) {
			count += 1;
			temp = temp.getNext();
		}
		if (temp == null) {
			throw new InvalidPositionException();
		} else {
			return temp.getValue();
		}
	}

	/**
	 * This method adds an node at a given position in the List TODO: Complete this
	 * method
	 * 
	 * @param pos:     an integer, which is the position
	 * @param element: the element to insert
	 * @throws InvalidPositionException if position is less than 0 or greater than
	 *                                  size-1
	 */
	public void insert(int pos, T element) throws InvalidPositionException {

		Node<T> temp = head;
		if (pos < 0 || size() < pos) {
			throw new InvalidPositionException();
		}
		if (pos == 0) {
			prepend(element);

		} else {
			Node<T> n = new Node<T>(element);
			int count = 0;
			Node<T> posB4Target = temp;
			while (count != pos) {
				if (count == pos - 1) {
					posB4Target = temp;
				}
				count += 1;
				temp = temp.getNext();
			}
			posB4Target.setNext(n);
			n.setNext(temp);
		}
	}

	/**
	 * This method removes an node at a given position TODO: Complete this method
	 *
	 * @param pos: an integer, which is the position
	 */
	public void remove(int pos) throws InvalidPositionException {
		if (head == null || size() - 1 < pos) {
			throw new InvalidPositionException();
		} else if (pos == 0) {
			Node<T> temp = head.getNext();
			Node<T> temp2 = head;
			temp2.setNext(null);
			head = temp;

		} else {
			int count = 0;
			Node<T> temp = head;
			Node<T> posB4Target = temp;
			Node<T> temp2;
			while (count != pos) {
				if (count == pos - 1) {
					posB4Target = temp;
				}
				count += 1;
				temp = temp.getNext();
			}
			temp2 = temp.getNext();
			temp.setNext(null);
			posB4Target.setNext(temp2);
		}
	}

	/**
	 * This method returns the size of the Linked list TODO: Complete this method
	 *
	 * @return the size of the list
	 */
	public int size() {
		if (head == null) {
			return 0;
		}
		int count = 1;
		Node<T> temp = head;
		while (temp.getNext() != null) {
			count += 1;
			temp = temp.getNext();
		}
		return count;
	}

	/**
	 * This method is used for printing the data in the list from head till the last
	 * node
	 *
	 */
	public void print() {
		Node<T> node = head;
		while (node != null) {
			System.out.println(node);
			node = node.getNext();
		}
	}
}