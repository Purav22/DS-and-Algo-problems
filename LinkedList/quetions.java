package LinkedList;


 class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }
public class quetions {
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null){
            return true;
        }

        ListNode mid = midNode(head);
        ListNode nhead = mid.next;
        mid.next = null;
        nhead = reverseList(nhead);
        ListNode c1 = head;
        ListNode c2 = head;
        boolean res = true;
        while(c1 != null && c2 != null){
            if(c1.val != c2.val){
                res = false;
                break;
            }
            c1 = c1.next;
            c2 = c2.next;
        }
        nhead = reverseList(nhead);
        mid.next = nhead;
        return res;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode forw = curr.next; // backup

            curr.next = prev; // link

            prev = curr; // move
            curr = forw;
        }

        return prev;
    }
    public ListNode midNode(ListNode head)
    {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
    public void reorderList(ListNode head) {
        if(head == null || head.next == null){
            return;
        }
        ListNode mid = midNode(head);
        ListNode nhead = mid.next;
        mid.next = null;
        nhead = reverseList(nhead);
        ListNode c1 = head;
       
        ListNode c2 = nhead;
        

        while(c1 != null && c2 != null){
            ListNode f1 = c1.next;
            ListNode f2 = c2.next;
            c1.next = c2;
            c2.next = f1;
            c1 = f1;
            c2 = f2;
    

        }
        
        
    }
    public void againReorderList(ListNode head) {
        if(head == null || head.next == null){
            return;
        }
        ListNode h1 = head;
        ListNode h2 = head.next;

        ListNode c1 = h1;
        ListNode c2 = h2;

        while(c2 != null && c2.next != null){
            ListNode f1 = c2.next;
            c1.next = f1;
            c2.next = f1.next;
            c1 = c1.next;
            c2 = c2.next;
        }

        c1.next = null;
        h2 = reverseList(h2);
        c1.next = h2;

        
        
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        
        if(l1 == null || l2 == null){
            return l1 != null ? l1 : l2;
        }
        ListNode dummy = new ListNode(-1);

        ListNode prev = dummy;
        ListNode c1 = l1;
        ListNode c2 = l2;

        while(c1 != null && c2 != null){
            if(c1.val <= c2.val){
                prev.next = c1;
                c1 = c1.next;

            }else{
                prev.next = c2;
                c2 = c2.next;
            }
            prev = prev.next;
        }

        prev.next = c1 != null ? c1 : c2;
        return dummy.next;
    }
    
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0){
            return null;
        }
        return mergeKLists(lists, 0, lists.length - 1);
    }

    public ListNode mergeKLists(ListNode[] lists, int si, int ei) {
        if (si == ei)
            return lists[si];

        int mid = (si + ei) / 2;
        ListNode list1 = mergeKLists(lists, si, mid);
        ListNode list2 = mergeKLists(lists, mid + 1, ei);

        return mergeTwoLists(list1, list2);
        
    }
    
}
