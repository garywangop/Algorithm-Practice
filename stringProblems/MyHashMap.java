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
	
}
