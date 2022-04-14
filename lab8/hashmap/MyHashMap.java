package hashmap;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A hash table-backed Map implementation. Provides amortized constant time
 * access to elements via get(), remove(), and put() in the best case.
 * <p>
 * Assumes null keys will never be inserted, and does not resize down upon remove().
 *
 * @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    private static final int INITIALSIZE_DEFAULT = 16;
    private static final double LOADFACTOR_DEFAULT = 0.75;
    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int tableSize; // initial bucket size
    private double loadFactor;
    private int size; // track of the number of nodes in the map

    /**
     * Constructors
     */
    public MyHashMap() {
        this.tableSize = INITIALSIZE_DEFAULT;
        this.loadFactor = LOADFACTOR_DEFAULT;
        this.buckets = createTable(INITIALSIZE_DEFAULT);
        this.size = 0;
    }

    public MyHashMap(int initialSize) {
        this.tableSize = initialSize;
        this.loadFactor = LOADFACTOR_DEFAULT;
        this.buckets = createTable(initialSize);
        this.size = 0;
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad     maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.tableSize = initialSize;
        this.loadFactor = maxLoad;
        this.buckets = createTable(initialSize);
        this.size = 0;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     * <p>
     * The only requirements of a hash table bucket are that we can:
     * 1. Insert items (`add` method)
     * 2. Remove items (`remove` method)
     * 3. Iterate through items (`iterator` method)
     * <p>
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     * <p>
     * Override this method to use different data structures as
     * the underlying bucket type
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new ArrayList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

    @Override
    public void clear() {
        this.size = 0;
        this.tableSize = INITIALSIZE_DEFAULT;
        this.loadFactor = LOADFACTOR_DEFAULT;
        this.buckets = null;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    @Override
    public boolean containsKey(K key) {
        if (this.size == 0) {
            return false;
        }
        for (K k : keySet()) {
            if (key.equals(k)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        int keyHashCode = key.hashCode();
        int bucketIndex = Math.floorMod(keyHashCode, tableSize);
        if (this.size == 0 || buckets[bucketIndex] == null) {
            return null;
        }
        return buckets[bucketIndex].stream()
                .filter(node -> node.key.equals(key))
                .map(node -> node.value)
                .findFirst()
                .orElse(null);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void put(K key, V value) {
        int keyHashCode = key.hashCode(); // get the hashcode of the key using the build in method
        int bucketIndex = Math.floorMod(keyHashCode, tableSize);// calculate which bucket this key-value pair should go into
        if (buckets[bucketIndex] == null) {
            buckets[bucketIndex] = createBucket();
        } else if (containsKey(key)) {
            if (get(key).equals(value)) return;
            remove(key, get(key));
        }
        buckets[bucketIndex].add(createNode(key, value));
        this.size += 1;
        if ((double) this.size / this.tableSize >= loadFactor) { // if N/M larger than loadFactor
            resizeUp();
        }
    }

    // resize up hashtable
    private void resizeUp() {
        int tmpTableSize = tableSize * 2;
        this.buckets = reHashing(tmpTableSize);
    }

    // resize down hashtable
    /*
    private void resizeDown() {
        int tmpTableSize = tableSize / 2 + 1;
        if (tmpTableSize < INITIALSIZE_DEFAULT ) {
            this.buckets = reHashing(INITIALSIZE_DEFAULT);
            this.tableSize = INITIALSIZE_DEFAULT;
            return;
        }
        this.buckets = reHashing(tmpTableSize);
    }
    */

    private Collection<Node>[] reHashing(int tmpTableSize) {
        Collection<Node>[] newBuckets = createTable(tmpTableSize);
        for (K k : keySet()) {
            int k_hashcode = k.hashCode();
            int newBucketIndex = Math.floorMod(k_hashcode, tmpTableSize);
            if (newBuckets[newBucketIndex] == null) {
                newBuckets[newBucketIndex] = createBucket();
            }
            newBuckets[newBucketIndex].add(createNode(k, get(k)));
        }
        this.tableSize = tmpTableSize;
        return newBuckets;
    }

    @Override
    public Set<K> keySet() {
//        return Arrays.stream(buckets)
//                .flatMap(Collection::stream)
//                .filter(Objects::nonNull)
//                .map(node -> node.key)
//                .collect(Collectors.toCollection(HashSet::new));
        HashSet<K> keySet = new HashSet<>();
        for (Collection<Node> bucket : buckets) {
            if (bucket != null) {
                keySet.addAll(bucket.stream()
                        .map(e -> e.key)
                        .collect(Collectors.toCollection(HashSet::new)));
            }
        }
        return keySet;
    }

    private Set<Node> entrySet() {
        HashSet<Node> entrySet = new HashSet<>();
        for (Collection<Node> bucket : buckets) {
            if (bucket != null) {
                entrySet.addAll(bucket.stream()
                        .filter(Objects::nonNull)
                        .collect(Collectors.toCollection(HashSet::new)));
            }
        }
        return entrySet;
    }

    @Override
    public V remove(K key) {
        if (!containsKey(key)) return null;
        V value = get(key);
        remove(key, value);
        size -= 1;
        return value;
//        throw new UnsupportedOperationException("unsupported operation");
    }

    @Override
    public V remove(K key, V value) {
        if (!containsKey(key)) return null;
        int bucketIndex = Math.floorMod(key.hashCode(), tableSize);
        for (Node entry : entrySet()) {
            if (key.equals(entry.key)) {
                buckets[bucketIndex].remove(entry);
                if (buckets[bucketIndex] == null) {
                    buckets[bucketIndex] = createBucket();
                }
            }
        }
        size -= 1;
//        if (tableSize > 16 && (double) this.size / tableSize < 0.75) resizeDown();
        return value;
//        throw new UnsupportedOperationException("unsupported operation");
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

}
