package nz.ac.auckland.se281.a4.ds;

import java.util.EmptyStackException;
//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//*******************************

public class NodesStackAndQueue<T> {

	private Node<T> head; // You should use this variable in your methods
	private Node<T> newTail;

	public NodesStackAndQueue() {
		head = null;
	}

	/**
	 * Checks if the stack / queue is empty
	 * 
	 * @return true if the stack / queue is empty
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Push operation refers to inserting an element in the Top of the stack. TODO:
	 * Complete this method
	 * 
	 * @param element the element to be "pushed"
	 */
	public void push(T element) {
		Node<T> n = new Node<T>(element);

		if (isEmpty()) {
			head = n;
		} else {
			Node<T> temp = head;
			while (temp.getNext() != null) {
				temp = temp.getNext();
			}
			temp.setNext(n);
		}

	}

	/**
	 * pop an element from the top of the stack (removes and returns the tope
	 * element) TODO: Complete this method (Note: You may have to change the return
	 * type)
	 * 
	 * @return object of the top element
	 * @throws EmptyStackException if the stack is empty
	 */
	public T pop() throws EmptyStackException {
		Node<T> temp = head;
		if (isEmpty()) {
			throw new EmptyStackException();
		} else {
			newTail = temp; // newTail is top of stack or start of the queue
			while (temp.getNext() != null) { // once temp is the last element, it wont update newTail
				newTail = temp;
				temp = temp.getNext();

			}
			newTail.setNext(null); // new tail is the new top/start therefore set its next to null
			if (temp == head) {
				head = null;
			}
			return temp.getValue();
		}
	}

	/**
	 * get the element from the top of the stack without removing it TODO: Complete
	 * this method (Note: You may have to change the return type)
	 *
	 * @return the value of the top element
	 * @throws EmptyStackException if the stack is empty
	 */
	public T peek() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		Node<T> temp = head;
		while (temp.getNext() != null) { // while temp is not the top/start of the stack
			temp = temp.getNext();
		}
		return temp.getValue(); // return the top of the stack or start of queue
	}

	/**
	 * append an element at the end of the queue TODO: Complete this method
	 *
	 * @param element the element to be appended
	 */
	public void append(T element) {
		Node<T> n = new Node<T>(element);
		if (isEmpty()) {
			head = n;
		} else {
			n.setNext(head);
			head = n;
		}
	}
}
