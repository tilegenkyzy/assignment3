public class MyHashTable<K, V> {
    private static final int INITIAL_CAPACITY = 11;
    private HashNode<K, V>[] chainArray;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashTable() {
        chainArray = new HashNode[INITIAL_CAPACITY];
        size = 0;
    }

    public static class HashNode<K, V> {
        K key;
        V value;
        HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public HashNode<K, V> getNext() {
            return next;
        }
    }

    private int getBucketIndex(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode % chainArray.length);
    }

    public void put(K key, V value) {
        int index = getBucketIndex(key);
        HashNode<K, V> head = chainArray[index];

        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        size++;
        head = chainArray[index];
        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = head;
        chainArray[index] = newNode;
    }

    public V get(K key) {
        int index = getBucketIndex(key);
        HashNode<K, V> head = chainArray[index];

        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }

        return null;
    }

    public V remove(K key) {
        int index = getBucketIndex(key);
        HashNode<K, V> head = chainArray[index];
        HashNode<K, V> prev = null;

        while (head != null) {
            if (head.key.equals(key)) {
                break;
            }
            prev = head;
            head = head.next;
        }

        if (head == null) return null;

        size--;
        if (prev != null) {
            prev.next = head.next;
        } else {
            chainArray[index] = head.next;
        }

        return head.value;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
