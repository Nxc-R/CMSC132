package listClasses;

import java.util.*;


//import jdk.tools.jaotc.binformat.HeaderContainer;

//import myLinkedList.MyLinkedList.Node;


public class BasicLinkedList<T extends Comparable<T>> implements Iterable<T> {
	
	/* Node definition */
	protected class Node {
		protected T data;
		protected Node next;

		protected Node(T data) {
			this.data = data;
			next = null;
		}
	}

	/* We have both head and tail */
	protected Node head, tail;
	
	/* size */
	protected int listSize;
	
	public BasicLinkedList() {
		head = null;
		tail = null;
		listSize = 0;
	}
	
	public TreeMap<T, Integer> getCount() {
		TreeMap<T, Integer> treeMap = new TreeMap<T, Integer>();
		Node curr = head, curr2;
		int instances = 1;
		while (curr != null) {
			for(curr2 = curr.next; curr2 != null; curr2 = curr2.next) {
				if (curr.data.compareTo(curr2.data) == 0) {
					instances++;
					System.out.println(instances);
				}
			}
			treeMap.put(curr.data, instances);
			instances = 1;
			curr = curr.next;
		}
		return treeMap;
	}
	public BasicLinkedList(BasicLinkedList<T> list) {
		if (list.head == null) {
			head = null;
		}
		
		head = new Node(list.head.data);
		Node tmp = head, tmp1 = list.head;
		while(tmp1.next != null) {
			tmp1 = tmp1.next;
			tmp.next = new Node(tmp1.data);
			tmp = tmp.next;
		}
	}
	public int getSize() {
		return listSize;
	}
	
	public BasicLinkedList<T> addToEnd(T data) {
		Node newNode = new Node(data);
		if (listSize == 0) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
			tail.next = null;
		}
		listSize++;
		return this;
	}
	
	public BasicLinkedList<T> addToFront(T data) {
		Node newNode = new Node(data);
		if (head == null) {
			head = newNode;
			tail = newNode;
		} else {
			newNode.next = head;
			head = newNode;
		}
		listSize++;
		return this;
	}
	
	public T getFirst() {
		return head.data;
	}
	
	public T getLast() {
		return tail.data;
	}
	
	public T retrieveFirstElement() {
		if (listSize == 0) {
			return null;
		}
		T returnVal = head.data;
		Node temp = head;
		head = head.next;
		temp.next = null;
		listSize--;
		return returnVal;
	}
	
	public T retrieveLastElement() {
		if (listSize == 0) {
			return null;
		}  
		T returnVal = tail.data;
		Node curr = head;
		while(curr.next != tail) {
			curr = curr.next;
		}
		tail = curr;
		tail.next = null;
		listSize--;
		return returnVal; 
	}
	
	public BasicLinkedList<T> remove(T targetData, Comparator<T> comparator) {
		if(head == null) {
			return this;
		}
		Node temp = head;
		if (head != null && comparator.compare(targetData, head.data) == 0) {
			head = head.next;
			listSize--;
		}
		while (temp.next != null && temp.next != tail) {
			if (comparator.compare(targetData, temp.next.data) == 0) {
				temp.next = temp.next.next;
				listSize--;
			} else {
				temp = temp.next;
			}
		}
		if (comparator.compare(targetData, tail.data) == 0) {
			while(temp.next != tail) {
				temp = temp.next;
			}
			tail = temp;
			tail.next = null;
			listSize--;
		}
		return this;
	}
	
	public Iterator<T> iterator() {
		Iterator<T> iterate = new Iterator<T>() {
			Node curr = head;
			
			public boolean hasNext() {
				return curr != null;
			}
			
			public T next() {
				if (hasNext()) {
					Node temp = curr;
					curr = curr.next;
					return temp.data; 
				} else {
					return null;
				}
			}
			
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
		return iterate;
	}
	
	public ArrayList<T> getReverseArrayList() {
		BasicLinkedList<T> tmpList = new BasicLinkedList<T>(this);
		ArrayList<T> returnList = new ArrayList<T>();
		getReverseList(tmpList.head, tmpList);
		while (tmpList.head != null) {
			returnList.add(tmpList.head.data);
			tmpList.head = tmpList.head.next;
		}
		return returnList;
	}
	
	public BasicLinkedList<T> getReverseList() {
		BasicLinkedList<T> returnList = new BasicLinkedList<T>(this);
		getReverseList(returnList.head, returnList);
		return returnList;
	}
	public String toString() {
		String result = "\" ";
		Node curr = head;

		while (curr != null) {
			result += curr.data + " ";

			curr = curr.next;
		}

		return result + "\"";
	}
	private void getReverseList(Node headAux, BasicLinkedList<T> list) {
		if (headAux == null) {
			return;
		}
		
		if (headAux.next == null) {
			list.head = headAux;
			return;
		} 
		getReverseList(headAux.next, list);
		headAux.next.next = headAux;
		headAux.next = null;
	}
}