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
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int a = size(headA, 0);
        int b = size(headB, 0);
        
        if(a > b){
            int diff = a - b;
            for(int i = 0; i< diff; i++){
                headA= headA.next;
            }
            
        }else if(b > a){
            int diff = b - a;
            for(int i = 0; i< diff; i++){
                headB = headB.next;
            }
        }
        while( headA!= null ){
            if(headA == headB){
                return headA;
            }
            headA= headA.next;
            headB= headB.next;
        }
        return null;
        
        
    }
    public int size(ListNode head,int count){
        if(head == null){
            return count;
        }
        int ans = size(head.next, count + 1);
        return ans;
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
// 25 Leetcode
    ListNode th = null; // temp head
    ListNode tt = null; // temp tail

    public void addFirstNode(ListNode node){
        if(th == null){
            th = node;
            tt = node;
        }
        else{
            node.next = th;
            th = node;
        }
    }
    public int lengthOfLL(ListNode node)
    {
        if (node == null)
            return 0;
    
        int len = 0;
        while (node != null)
        {
            node = node.next;
            len++;
        }
    
        return len;
    }
    
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null || k == 1){
            return head;
        }

        ListNode oh = null; // oh = original head
        ListNode ot = null; // ot = temp tail

        int len = lengthOfLL(head);
        ListNode curr = head;
    
        while (len >= k)
        {
            int tempK = k;
            while (tempK-- > 0)
            {
                ListNode forw = curr.next;
                curr.next = null;
                addFirstNode(curr);
                curr = forw;
            }
    
            if (oh == null)
            {
                oh = th;
                ot = tt;
            }
            else
            {
                ot.next = th;
                ot = tt;
            }
    
            th = null;
            tt = null;
            len -= k;
        }
    
        ot.next = curr;
        return oh;

    }
    //1669
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode prev = null;
        if(a != 0){
            prev = getNode(list1, a - 1);
        }else 
            prev = null;
        ListNode tailNew = getNode(list2, size(list2, 0) - 1);
        ListNode forw = getNode(list1, b + 1);
        ListNode oldPrev = getNode(list1, b);

        if(prev != null) prev.next = list2;
        else prev = list2;
        tailNew.next = forw;
        oldPrev.next = null;
        return list1;
    }

    public ListNode getNode(ListNode head, int idx){
        int i = 0;
        ListNode temp = head;
        while(idx != i){
            temp = temp.next;
            i++;
        }
        return temp;
    }


    //1721
    public ListNode swapNodes(ListNode head, int k) {
        Stack<Integer> stack=new Stack<>();
        ListNode temp=head;
        ListNode temp1=head;
        int count=0;
        while(temp != null)
        {
            stack.push(temp.val);
            count++;
            temp=temp.next;
        }
        int c=0;
        while(temp1 != null)
        {
            if((c+1)==k || c==(count-k))
            {
                temp1.val=stack.pop();
            }
            else
            {
                stack.pop();
            }
            temp1=temp1.next;
            c++;
        }
        return head;
    } 
}
