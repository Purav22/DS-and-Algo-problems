package LinkedList;

public class temp {
    public static void main(String[] args) throws Exception {
        linkedlist l1 = new linkedlist();
        l1.addFirst(10);
        l1.addLast(12);
        l1.addAt(13, 1);
        l1.display();
        System.out.println( l1.removeAt(1));
        System.out.println(l1.removeFirst());
        l1.display();
        
    }
    
}
