package p2.DataStructures.SortedList;

import java.util.spi.CurrencyNameProvider;

/**
 * Implementation of a Sorted List using a Singly Linked List structure
 * 
 * @author Fernando J. Bermudez - bermed28
 * @author Jorge Colon Velez - 843-18-8733
 * @version 3.0
 * @since 03/28/2023
 * @param <E> 
 */
public class SortedLinkedList<E extends Comparable<? super E>> extends AbstractSortedList<E> {

	@SuppressWarnings("unused")
	private static class Node<E> {

		private E value;
		private Node<E> next;

		public Node(E value, Node<E> next) {
			this.value = value;
			this.next = next;
		}

		public Node(E value) {
			this(value, null); // Delegate to other constructor
		}

		public Node() {
			this(null, null); // Delegate to other constructor
		}

		public E getValue() {
			return value;
		}

		public void setValue(E value) {
			this.value = value;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}

		public void clear() {
			value = null;
			next = null;
		}				
	} // End of Node class

	
	private Node<E> head; // First DATA node (This is NOT a dummy header node)
	
	public SortedLinkedList() {
		head = null;
		currentSize = 0;
	}

	@Override
	public void add(E e) {
		/* TODO ADD CODE HERE */
		/* Special case: Be careful when the new value is the smallest */
		Node<E> newNode = new Node<>(e);
		Node<E> curNode = head;
		Node<E> prevNode = null;
		
		// Iterates trough the list to find where to add the node
		while(curNode != null && e.compareTo(curNode.getValue()) > 0) {
			prevNode = curNode;
			curNode = curNode.getNext();
		}
		
		newNode.setNext(curNode);
		
		// If the node to add will be the first, assing it to be the head
		if(prevNode == null)
			head = newNode;
		else 
			prevNode.setNext(newNode);
		
		currentSize++;
	}

	@Override
	public boolean remove(E e) {
		/* TODO ADD CODE HERE */
		/* Special case: Be careful when the value is found at the head node */
		Node<E> rmNode = null;
		Node<E> curNode = head;
		
		// Find the node to be remove 
		while(curNode != null && !curNode.getValue().equals(e)) {
			rmNode = curNode;
			curNode = curNode.getNext();
		}
		
		// If the node to remove is not found, return false
		if(curNode == null)
			return false;
		
		// If the node to be remove is the head, assign it to the next node
		if(rmNode == null)
			head = curNode.getNext();
		else
			rmNode.setNext(curNode.getNext());
		
		curNode.clear();
		currentSize--;
		
		return true; //Dummy Return
	}

	@Override
	public E removeIndex(int index) {
		/* TODO ADD CODE HERE */
		/* Special case: Be careful when index = 0 */
		if(index < 0 || index >= currentSize)
			throw new IndexOutOfBoundsException();
		
		Node<E> rmNode = null;
		Node<E> curNode = head;
		
		for(int i = 0; i < index; i++) {
			rmNode = curNode;
			curNode = curNode.getNext();
		}
		
		// If the node to remove is the head, assign it to the next node
		if(rmNode == null)
			head = curNode.getNext();
		else
			rmNode.setNext(curNode.getNext());
		
		
		E value = curNode.getValue();
		curNode.clear();
		currentSize--;
		
		return value; //Dummy Return
	}

	@Override
	public int firstIndex(E e) {
		/* TODO ADD CODE HERE */
		int counter = 0;
		Node<E> curNode = head;
		
		// Iterates trough the list, when e is found returns
		while(curNode != null) {
			if(curNode.getValue().equals(e)) 
				return counter;
			
			curNode = curNode.getNext();
			counter++;
		}
		return -1;
	}

	@Override
	public E get(int index) {
		/* TODO ADD CODE HERE */
		if(index < 0 || index >= currentSize)
			throw new IndexOutOfBoundsException();
		
		Node<E> curNode = head;
		
		// Iterates trough the list until the node at index is found
		for(int i = 0; i < index; i++)
			curNode = curNode.getNext();
		
		// Returns the value of the node at index
		return curNode.getValue(); //Dummy Return
	}

	@SuppressWarnings("unchecked")
	@Override
	public Comparable<E>[] toArray() {
		int index = 0;
		Comparable[] theArray = new Comparable[size()]; // Cannot use Object here
		for(Node<E> curNode = this.head; index < size() && curNode  != null; curNode = curNode.getNext(), index++) {
			theArray[index] = curNode.getValue();
		}
		return theArray;
	}

}