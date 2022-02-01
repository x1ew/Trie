package src;

public class LinkedList {
	Node head;// head of list

	static class Node {
		Student data;
		Node prev, next;

		Node(Student data) {
			this.data = data;
		}// constructor
	}

	public void push(Student data) {
		Node node = new Node(data);

		node.next = head;
		node.prev = null;

		if (head != null)
			head.prev = node;

		head = node;
	}// add new node to the beginning of the list
	void append(Student data) {

		Node node = new Node(data);

		Node last = head;

		node.next = null;

		if (head == null) {
			node.prev = null;
			head = node;
			return;
		} // add new node to the end of the list

		while (last.next != null)
			last = last.next;

		last.next = node;

		node.prev = last;
	}

	public Node getLast() {
		Node node = this.head;
		while (node.next != null)
			node = node.next;
		return node;
	}

	// public void printlist() {
	// 	Node node = this.head;
	// 	Node last = null;
	// 	while (node != null) {
	// 		System.out.print(node.data + " ");
	// 		last = node;
	// 		node = node.next;
	// 	}
	// }

	void remove(String key) {
		Node temp = head, prev = null;
		if (temp != null && temp.data.getNumber().equals(key)) {
			head = temp.next; // Changed head
			return;
		}
		while (temp != null && !temp.data.getNumber().equals(key)) {
			prev = temp;
			temp = temp.next;
		}
		if (temp == null)
			return;
		prev.next = temp.next;
	}

	public Student search(String key) {
		Node current = head;
		while (current != null) {
			if (current.data.getNumber().equals(key))
				return current.data;
			current = current.next;
		}
		return null;
	}
}
