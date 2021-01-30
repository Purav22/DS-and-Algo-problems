package LinkedList;
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class copyWithRandom {
    public Node copyRandomList(Node head) {
        if(head == null){
            return null;
        }
       createCopy(head);
        setRandom(head);
        
        return removeLL(head);
    }
    
   public void createCopy(Node head){
       
       Node curr = head;
       
       while(curr != null){
           Node f = curr.next;
           Node node = new Node(curr.val);
           
           node.next = f;
           curr.next = node;
           
           curr = f;
       }
   }
    
    public void setRandom(Node head){
        Node curr = head;
        while(curr != null){
            if(curr.random != null){
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }
    }
    
    public Node removeLL(Node head){
        Node dummy = new Node(-1);
        Node copyCurr = dummy;
        Node curr = head;
        
        while(curr != null){
            copyCurr.next = curr.next;
            curr.next = curr.next.next;
            
            curr = curr.next;
            copyCurr = copyCurr.next;
        }
        return dummy.next;
    }
}
