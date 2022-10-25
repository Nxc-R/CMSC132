package listClasses;

import java.util.*;

//import myLinkedList.MyLinkedList.Node;


/**
 * Implements a generic sorted list using a provided Comparator. It extends
 * BasicLinkedList class.
 * 
 *  @author Dept of Computer Science, UMCP
 *  
 */

public class SortedLinkedList<T extends Comparable<T>> extends BasicLinkedList<T> {
	private Comparator<T> comparator;
	
	public SortedLinkedList(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}
	
	public SortedLinkedList<T> add(T element) {
		if (head == null) {
			Node nNode = new Node(element);
			head = nNode;
			tail = nNode;
			listSize++;
			return this;
		}
		Node curr = head;
		boolean inBefore = false;
		T data = curr.data;
		while(curr != null) {
			if (!(comparator.compare(head.data, element) < 0)) {
				data = head.data;
				inBefore = true;
				break;
			}
			if (comparator.compare(curr.data, element) < 0) {
				data = curr.data;
			} 
			else {
				break;
			}
			curr = curr.next;
		}
		if (inBefore) {
			insertElementBefore(data, element);
			listSize++;
		} else {
			insertElementAfter(data, element);
			listSize++;
		}
		return this;
	}
	
	public SortedLinkedList<T> remove(T targetData) {
		remove(targetData, comparator);
		return this;
	}
	
	public BasicLinkedList<T> addToEnd(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	public BasicLinkedList<T> addToFront(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	private void insertElementBefore(T targetElement, T toInsert) {
		Node prev = null, curr = head;

		while (curr != null) {
			if (comparator.compare(curr.data, targetElement) == 0) {
				Node newNode = new Node(toInsert);
				if (curr == head) {
					newNode.next = head;
					head = newNode;
				} else {
					prev.next = newNode;
					newNode.next = curr;
				}
				return;
			} else {
				prev = curr;
				curr = curr.next;
			}
		}
	}
	private void insertElementAfter(T targetElement, T toInsert) {
		Node curr = head;

		while (curr != null) {
			if (comparator.compare(curr.data, targetElement) == 0) {
				Node newNode = new Node(toInsert);
				newNode.next = curr.next;
				curr.next = newNode;
				return;
			}

			curr = curr.next;
		}
	}
}