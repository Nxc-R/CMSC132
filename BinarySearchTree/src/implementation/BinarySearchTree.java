package implementation;

import java.util.Comparator;
import java.util.TreeSet;

public class BinarySearchTree<K, V> {
	/*
	 * You may not modify the Node class and may not add any instance nor static
	 * variables to the BinarySearchTree class.
	 */
	private class Node {
		private K key;
		private V value;
		private Node left, right;

		private Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	private Node root;
	private int treeSize, maxEntries;
	private Comparator<K> comparator;

	public BinarySearchTree(Comparator<K> comparator, int maxEntries) {
		this.comparator = comparator;
		this.maxEntries = maxEntries;
		treeSize = 0;
		root = null;
	}

	public BinarySearchTree<K, V> add(K key, V value) throws TreeIsFullException {
		if (key == null || value == null) {
			throw new IllegalArgumentException("Invalid parameters");
		}
		if (treeSize == maxEntries) {
			throw new TreeIsFullException("Tree is full");
		}
		if (root == null) {
			root = new Node(key, value);
			treeSize++;
			return this;
		} else {
			return addAux(key, value, root);
		}
	}
	
	private BinarySearchTree<K, V> addAux(K key, V value, Node rootAux) {
		int comparison = comparator.compare(key,rootAux.key);
		if (comparison == 0) {
			rootAux.value = value;
			return this;
		} else if (comparison < 0) {
			if (rootAux.left == null) {
				rootAux.left = new Node(key, value);
				treeSize++;
				return this;
			} else {
				return addAux(key, value, rootAux.left);
			}
		} else {
			if (rootAux.right == null) {
				rootAux.right = new Node(key, value);
				treeSize++;
				return this;
			} else {
				return addAux(key, value, rootAux.right);
			}
		}
	}

	public String toString() {
		if (root == null) {
			return "EMPTY TREE";
		}
		return toStringAux(root);
	}
	
	private String toStringAux(Node rootAux) {
		return rootAux == null ? ""
				: toStringAux(rootAux.left) + "{" + rootAux.key + ":" + rootAux.value + "}" + toStringAux(rootAux.right);
	}

	public boolean isEmpty() {
		return root == null;
	}

	public int size() {
		return treeSize;
	}
	public Node getRoot() {
		return root;
	}
	public boolean isFull() {
		return treeSize == maxEntries;
	}

	public KeyValuePair<K, V> getMinimumKeyValue() throws TreeIsEmptyException {
		if (isEmpty()) {
			throw new TreeIsEmptyException("Empty Tree");
		}
		Node n = getMinimumKeyValueAux(root);
		KeyValuePair<K, V> pair = new KeyValuePair<>(n.key, n.value);
		return pair;
	}
	private Node getMinimumKeyValueAux(Node rootAux) {
		if (rootAux.left == null) {
			return rootAux;
		}
		return getMinimumKeyValueAux(rootAux.left);
	}

	public KeyValuePair<K, V> getMaximumKeyValue() throws TreeIsEmptyException {
		if (isEmpty()) {
			throw new TreeIsEmptyException("Empty Tree");
		}
		Node n = getMaximumKeyValueAux(root);
		KeyValuePair<K, V> pair = new KeyValuePair<>(n.key, n.value);
		return pair;
	}
	
	private Node getMaximumKeyValueAux(Node rootAux) {
		if (rootAux.right == null) {
			return rootAux;	
		}
		return getMaximumKeyValueAux(rootAux.right);
	}
	public KeyValuePair<K, V> find(K key) {
		Node n = find(key, root);
		if (n == null) {
			return null;
		}
		KeyValuePair<K, V> pair = new KeyValuePair<>(n.key, n.value);
		return pair;
	}
	private Node find(K key, Node rootAux) {
		if (rootAux == null) {
			return null;
		} else {
			int comparison = comparator.compare(key,rootAux.key);
			if (comparison == 0) {
				return rootAux;
			} else if (comparison < 0) {
				return find(key, rootAux.left);
			} else {
				return find(key, rootAux.right);
			}
		}
	}
	public BinarySearchTree<K, V> delete(K key) throws TreeIsEmptyException {
		if (key == null) {
			throw new IllegalArgumentException("Invalid parameters");
		}
		if (isEmpty()) {
			throw new TreeIsEmptyException("Empty Tree");
		}
		deleteAux(key, root);
		return this;
	}
	private Node deleteAux(K key, Node rootAux) {
		if (rootAux == null) {
			return rootAux;
		} else {
			int comparison = comparator.compare(key,rootAux.key);
			if (comparison == 0) {
				if (rootAux.right == null && rootAux.left == null) {
					rootAux = null;
					treeSize--;
				} else if (rootAux.right == null ){ 
					rootAux = getMaximumKeyValueAux(rootAux.left);
					rootAux.left = deleteAux(rootAux.key, rootAux.left);
					treeSize--;
				} else { 
					rootAux = getMinimumKeyValueAux(rootAux.right);
					rootAux.right = deleteAux(rootAux.key, rootAux.right);
					treeSize--;
				}
			} else if (comparison < 0) {
				rootAux.left = deleteAux(key, rootAux.left);
			} else {
				rootAux.right = deleteAux(key, rootAux.right);
			}
		}
		return rootAux;
	}

	public void processInorder(Callback<K, V> callback) {
		if (callback == null) {
			throw new IllegalArgumentException("Invalid Parameter");
		}
		processInorderAux(root, callback);
	}
	
	private void processInorderAux(Node node, Callback<K, V> callback) {
		if (node == null) {
			return;
		}
		processInorderAux(node.left, callback);
		callback.process(node.key, node.value);
		processInorderAux(node.right, callback);
	}

	public BinarySearchTree<K, V> subTree(K lowerLimit, K upperLimit) {
		Node temp = null;
		BinarySearchTree<K, V> subTree = new BinarySearchTree<K, V>(comparator, maxEntries);
		subTree.root = subTreeAux(root, temp, lowerLimit, upperLimit);
		return subTree;
	}
	private Node subTreeAux(Node node, Node temp, K lowerLimit, K upperLimit) {
		if (node == null) {
			return null;
		}
		int compLower = comparator.compare(lowerLimit, node.key), 
				compUpper = comparator.compare(upperLimit, node.key);
			if (compLower > 0) {
				return subTreeAux(node.right, temp, lowerLimit, upperLimit);
			}
			if (compUpper < 0) {
				 return subTreeAux(node.left, temp, lowerLimit, upperLimit);
			}
			if (compLower <= 0 && compUpper >= 0) {
				temp = new Node(node.key, node.value);
				temp.left = subTreeAux(node.left, temp,  lowerLimit, upperLimit);
				temp.right = subTreeAux(node.right, temp,  lowerLimit, upperLimit);
			}
			return temp;
	}
	public TreeSet<V> getLeavesValues() {
		TreeSet<V> tree = new TreeSet<V>();
		getLeavlesValuesAux(tree, root);
		return tree;
	}
	
	private void getLeavlesValuesAux(TreeSet<V> tree, Node node) {
		if (node == null) {
			return;
		}
		if (node.left == null && node.right == null) {
			tree.add(node.value);
		} else {
			getLeavlesValuesAux(tree, node.left);
			getLeavlesValuesAux(tree, node.right);
		}
	}
	
	
}
