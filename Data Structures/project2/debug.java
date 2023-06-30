

public class debug {
    public static void main (String args[]) {
        CircularSinglyLinkedList<String> test = new CircularSinglyLinkedList<String>();
        test.addToFront("A");
        test.removeFromBack();
        System.out.println(test.size());
    }
}
