package stringProblems;

import java.util.Arrays;

public class MyHashMap<K, V> {
	/*
	 * K is String, V is Integer for a simpler version
	 * Supported operations:
	 * size()
	 * isEmpty()
	 * clear()
	 * put(K key, V value
	 * get(K key)
	 * containsKey(K key)
	 * containsValue(V value) // check if the desired value is in the map. O(n)
	 * remove(K key) 
	*/
	public static class Node<K, V> {
		final K key;
		V value;
		Node<K, V> next;

		Node(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}
	}
	
	// static final variable are global constants
	public static final int DEFAULT_CAPACITY = 16;
	public static final float DEFAULT_LOAD_FACTOR = 0.75f;
	
	private Node<K, V>[] array;
	private int size; // how many key-value pairs are actually stored in the Hashmap
	private float loadFactor; // determine when to rehash
	
	public MyHashMap() {
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
	}
	
	public MyHashMap(int cap, float loadFactor) {
		if (cap <= 0) {
			throw new IllegalArgumentException("cap can not be <= 0");
		}
		this.array = (Node<K, V>[]) (new Node[cap]); // No generic arrays in Java
		this.size = 0;
		this.loadFactor = loadFactor;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		Arrays.fill(this.array, null);
		size = 0;
	}
	
	// Non-gevative
	private int hash(K key) {
		// 1. null key
		if (key == null) {
			return 0;
		}
		// 2.3. hashCode()
		/*
		int code = key.hashCode();
		return code >= 0 ? code : -code;
		int range = [-2^31, 2^31 - 1];
		-Integer.MIN_VALUE = Integer.MIN_VALUE; -> overflow
		*/
		// Guarantee non-negative
		// Reason: java's % return remainder rather than modulus. The remainder can be negative
		// -1 to 3 remainder: -1
		// -1 to 3 modulo: 2
		return key.hashCode() & 0x7FFFFFFF;
	}
	
	private int getIndex(K key) {
		return hash(key) % array.length;
	}
	
	private boolean equalsValue(V v1, V v2) {
		// v1, v2 all possibly to be null.
		if (v1 == null && v2 == null) {
			return true;
		}
		if (v1 == null || v2 == null) {
			return false;
		}
		return v1.equals(v2);
	}
	
	// T = O(n)
	// Traverse the whole array, and traverse each of the linked list in the array.
	public boolean containsValue(V value) {
		// special case.
		if (isEmpty()) {
			return false;
		}
		for (Node<K, V> node : array) {
			while (node != null) {
				// Check if the value equals()
				// value, node.getValue() all possible to be null
				if (equalsValue(node.value, value)) {
					return true;
				}
				node = node.next;
			}
		}
		return false;
	}
	
	private boolean equalsKey(K k1, K k2) {
		// k1, k2 all possibly to be null.
		if (k1 == null && k2 == null) {
			return true;
		}
		if (k1 == null || k2 == null) {
			return false;
		}
		return k1.equals(k2);
	}
	
	public boolean containsKey(K key) {
		// get the index of the key
		int index = getIndex(key);
		Node<K, V> node = array[index];
		while (node != null) {
			// Check if the key equals()
			// key, node.key() all possible to be null		
			if (equalsKey(node.key, key)) {
				return true;
			}
			node = node.next;
		}
		return false;
	}
	
	// If key does not exists in the HashMap, return null.
	public V get(K key) {
		int index = getIndex(key);
		Node<K, V> node = array[index];
		while (node != null) {
			// check if the key equals()
			// key, node.key() all possible to be null.
			if (equalsKey(node.key, key)) {
				return node.value;
			}
			node = node.next;
		}
		return null;
	}
	
	// Insert/update
	// If the key already exists, return the old corresponding value.
	// if the key not exists, return null.
	public V put (K key, V value) {
		int index = getIndex(key);
		Node<K, V> head = array[index];
		Node<K, V> node = head;
		while (node != null) {
			// Check if the key equals()
			// key, node.key() all possible to be null
			if (equalsKey(node.key, key)) {
				V res = node.value;
				node.value = value;
				return res;
			}
			node = node.next;
		}
		// Append the new node before the head and update the new head insert operation.
		Node<K, V> newNode = new Node(key, value);
		newNode.next = head;
		array[index] = newNode; // new head is here.
		size++;
		if (needRehashing()) {
			rehashing();
		}
		return null;
	}
	
	private boolean needRehashing() {
		// float loadFactor;
		float ratio = (size + 0.0f) / array.length;
		return ratio >= loadFactor;
	}
	
	private void rehashing() {
		// new double sized array.
		// for each node in the old array,
		// do the put() operation to the new larger array.
	}
	
	// If the key exists, remove the <key, value> from the HashMap, return the value
	// If the key not exists, return null.
	public V remove(K key) {
		// get index
		int index = getIndex(key);
		Node<K, V> node = array[index];
		// delete operation on the linked list.
		Node<K, V> prev = node;
		while (node != null) {
			if (equalsKey(node.key, key)) {
				size--;
				prev.next = node.next;
				return node.value;
			}
			prev = node;
			node = node.next;
		}
		return null;
	}
}





















