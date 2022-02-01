package src;

import java.util.ArrayList;
import java.util.List;

class Node {
	boolean endoftheword;
	Node array[];
	int hash;

	public Node() {
		this.array = new Node[10];
	}
}

public class TrieTree {
	private Node root;

	public TrieTree() {
		root = new Node();
	}

	public void insert(String input, int key) {
		Node temp = this.root;
		for (int i = 0; i < input.length(); i++) {
			if (temp.array[Character.getNumericValue(input.charAt(i))] == null) {
				temp.array[Character.getNumericValue(input.charAt(i))] = new Node();
			}
			temp = temp.array[Character.getNumericValue(input.charAt(i))];
			if (i == input.length() - 1) {
				temp.endoftheword = true;
				temp.hash = key;
			}
		}
	}

	public boolean search(String input) {
		Node temp = this.root;
		for (int i = 0; i < input.length(); i++) {
			if (temp.array[Character.getNumericValue(input.charAt(i))] == null) {
				return false;
			}
			temp = temp.array[Character.getNumericValue(input.charAt(i))];
			if (i == input.length() - 1 && temp.endoftheword == true) {
				return true;
			}
		}
		return false;
	}

	public int searchhash(String input) {
		Node temp = this.root;
		for (int i = 0; i < input.length(); i++) {
			if (temp.array[Character.getNumericValue(input.charAt(i))] == null) {
				return -1;
			}
			temp = temp.array[Character.getNumericValue(input.charAt(i))];
			if (i == input.length() - 1 && temp.endoftheword == true) {
				return temp.hash;
			}
		}
		return -1;
	}

	boolean empty(Node node) {
		for (int i = 0; i < 10; i++) {
			if (node.array[i] != null) {
				return false;
			}
		}
		return true;
	}

	public void delet(String key) {
		if (!search(key)) {
			return;
		} 
		delet(root, key, 0);
	}

	private Node delet(Node root, String key, int index) {
		if (root == null) {
			return null;
		} 

		if (index == key.length()) {
			if (root.endoftheword) {
				root.endoftheword = false;
			} 
			if (empty(root)) { 
				root = null; 
			} 
			return root;
		}

		root.array[Character.getNumericValue(key.charAt(index))] = delet(root.array[Character.getNumericValue(key.charAt(index))], key, index + 1);
		if (empty(root) && root.endoftheword == false) {
			root = null;
		}
		return root;
	}
	boolean nochild(Node node) {
		for (int i = 0; i < 10; i++) {
			if (node.array[i] != null) {
				return false;
			}
		}
		return true;
	}
	List<String> suggest(Node temp, String key, List<String> list) {
		if (temp.endoftheword) {
			list.add(key);
		}
		if (nochild(temp)) {
			return null;
		}
		for (int i = 0; i < 10; i++) {
			if (temp.array[i] != null) {
				suggest(temp.array[i], key + Integer.toString(i), list);
			}
		}
		return list;
	}
	public List<String> allauto(String key) {
		return allauto(root, key);
	}

	private List<String> allauto(Node root, String key) {
		List<String> list = new ArrayList<String>();
		Node temp = root;
		for (int pointer = 0; pointer < key.length(); pointer++) {
			if (temp.array[Character.getNumericValue(key.charAt(pointer))] == null) {
				list.add("");
				return list;
			}
			temp = temp.array[Character.getNumericValue(key.charAt(pointer))];
		}
		if (temp.endoftheword && nochild(temp)) {
			list.add(key);
		}
		if (!nochild(temp)) {
			list = suggest(temp, key, list);
			return list;
		}
		return null;
	}
}

