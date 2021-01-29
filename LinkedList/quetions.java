package LinkedList;

import java.util.Stack;

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
            //count++;
            curr.next = prev; // link

            prev = curr; // move
            curr = forw;
        }

        return prev;
    }
    public static ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode forw = curr.next; // backup
            count++;
            curr.next = prev; // link

            prev = curr; // move
            curr = forw;
        }

        return prev;
    }
    static int count = 0;
    public static int[] nextLargerNodes(ListNode head) {
        Stack<ListNode> st = new Stack<>();
        ListNode h = reverseList1(head);
        // ListNode temp = h;
        // while(temp != null){
        //     System.out.println(temp.val);
        //     temp = temp.next;
        // }
        int ans[] = new int[count];
        //System.out.println(count);
        int i = ans.length - 1;
        //System.out.println(h.val);
        //System.out.println(i);
        st.push(h);
        ans[i] = 0;
        i--;
        h = h.next;
        while(h != null){
            //System.out.println(h.val);
            while(st.size() > 0 && st.peek().val < h.val){
                
                st.pop();
            }
            
            if(st.size() == 0){
                ans[i] = 0;
            }else{
                ans[i] = st.peek().val;
                //System.out.println(i + " " + ans[i]);
            }
            
            st.push(h);
            //if(st.size() > 0)System.out.println(st.peek().val);
            i--;
            h = h.next;
        }
        return ans;
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // ListNode dummy = new ListNode(0);
        // dummy.next = head;
        // int length  = 0;
        // ListNode first = head;
        // while (first != null) {
        //     length++;
        //     first = first.next;
        // }
        // length -= n;
        // first = dummy;
        // while (length > 0) {
        //     length--;
        //     first = first.next;
        // }
        // first.next = first.next.next;
        // return dummy.next;
        
   
        if (head == null)
            return head;

        ListNode c1 = head;
        ListNode c2 = head;

        while (n-- > 0)
            c2 = c2.next;

        if (c2 == null)
        {
            ListNode temp = head;
            head = head.next;
            temp.next = null;
            return head;
        }

        while (c2.next != null)
        {
            c2 = c2.next;
            c1 = c1.next;
        }

        ListNode temp = c1.next;
        c1.next = c1.next.next;
        temp.next = null;

        return head;
    
    }
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        ListNode head = l1;
        l1.next = new ListNode(1,null);
        l1 = l1.next;
        l1.next = new ListNode(5,null);
        int ans[] = nextLargerNodes(head);
        for(int num : ans)
            System.out.println(num);
        
    }
    
}
