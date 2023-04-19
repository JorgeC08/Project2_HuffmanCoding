package p2.DataStructures.SortedList;

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
	
	/**
	 * Adds a new element to the list while maintaining its order
	 *
	 * @param e The element to be added
	 */
	@Override
	public void add(E e) {
		/* TODO ADD CODE HERE */
		/* Special case: Be careful when the new value is the smallest */
		Node<E> newNode = new Node<>(e);

		// The list is empty
		if (head == null) {
			head = newNode;
		}
		// The new value is the smallest, insert before head
		else if (head.getValue().compareTo(e) >= 0) {
			newNode.setNext(head);
			head = newNode;
		}
		// Insert in the middle or at the end of the list
		else {
			Node<E> curNode = head;

			// Finding the node before the position where the new node would be inserted
			while (curNode.getNext() != null && e.compareTo(curNode.getNext().getValue()) >= 0) {
				curNode = curNode.getNext();
			}

			// Insert the new node after curNode
			newNode.setNext(curNode.getNext());
			curNode.setNext(newNode);
		}

		currentSize++;
	}

	@Override
	/**
	 * Removes the first occurrence of the element e from the list if found.
	 * If not, the list stays the same
	 *
	 * @param e the element to be removed 
	 * @return true if e is found, false otherwise
	 */
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
		
		// Removing the node
		curNode.clear();
		currentSize--;
		
		return true; 
	}

	@Override

	/**
	 * Removes the element at the specified position in the list and returns
	 * the value of the removed element.
	 *
	 * @param index the position of the element to be removed
	 * @return the removed element
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= currentSize)
	 */
	public E removeIndex(int index) {
		/* TODO ADD CODE HERE */
		/* Special case: Be careful when index = 0 */
		if(index < 0 || index >= currentSize)
			throw new IndexOutOfBoundsException();
		
		Node<E> rmNode = null;
		Node<E> curNode = head;
		
		// Iterates through the list until the position at index
		for(int i = 0; i < index; i++) {
			rmNode = curNode;
			curNode = curNode.getNext();
		}
		
		// If the node to remove is the head, assign it to the next node
		if(rmNode == null)
			head = curNode.getNext();
		else
			rmNode.setNext(curNode.getNext());
		
		// Saving the value to return it before it gets removed
		E value = curNode.getValue();
		// Removing the node
		curNode.clear();
		currentSize--;
		
		return value; 
	}

	@Override
	/**
	 * Returns the index of the first occurrence of the specified element in this list,
	 * or -1 if this list does not contain the element.
	 *
	 * @param e the element to search for
	 * @return the index of the first occurrence of the specified element in this list,
	 *         or -1 if this list does not contain the element
	 */
	public int firstIndex(E e) {
		/* TODO ADD CODE HERE */
		int counter = 0;
		Node<E> curNode = head;
		
		// Iterates trough the list, when e is found returns where it was
		while(curNode != null) {
			if(curNode.getValue().equals(e)) 
				return counter;
			
			curNode = curNode.getNext();
			counter++;
		}
		// If not found return -1
		return -1;
	}
	/**
	 * Returns the element at the specified position in this list.
	 *
	 * @param index the position of the element to return
	 * @return the element at the specified position in this list
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= currentSize)
	 */
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
		return curNode.getValue();
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