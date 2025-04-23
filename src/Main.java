import java.lang.reflect.Field;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // part1.2
        System.out.println("===== Hash Table Test =====");
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>();
        Random rand = new Random();

        for (int i = 0; i < 10000; i++) {
            MyTestingClass key = new MyTestingClass(rand.nextInt(100000), "Name" + rand.nextInt(10000));
            Student value = new Student("Student" + i, 18 + rand.nextInt(5));
            table.put(key, value);
        }

        for (int i = 0; i < 11; i++) {
            int count = 0;
            MyHashTable.HashNode<MyTestingClass, Student> node =
                    (MyHashTable.HashNode<MyTestingClass, Student>) getChain(table, i);

            while (node != null) {
                count++;
                node = node.getNext();
            }

            System.out.println("Bucket " + i + ": " + count + " elements");
        }

        // part 2.2
        System.out.println("\n===== BST Test =====");
        BST<Integer, String> bst = new BST<>();
        bst.put(50, "Apple");
        bst.put(30, "Banana");
        bst.put(70, "Cherry");
        bst.put(20, "Date");
        bst.put(40, "Elderberry");
        bst.put(60, "Fig");
        bst.put(80, "Grape");

        System.out.println("BST Size: " + bst.size());

        System.out.println("Value for key 40: " + bst.get(40)); // Elderberry

        bst.delete(30);
        System.out.println("Deleted key 30. New size: " + bst.size());

        System.out.println("In-order Traversal:");
        for (BST<Integer, String>.Entry entry : bst) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }

    static Object getChain(MyHashTable<?, ?> table, int index) {
        try {
            Field field = table.getClass().getDeclaredField("chainArray");
            field.setAccessible(true);
            Object[] arr = (Object[]) field.get(table);
            return arr[index];
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
