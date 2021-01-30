package LinkedList;

import java.util.Scanner;

public class segregateEvenOdd {
    public static ListNode solve(ListNode head){
        ListNode odd = new ListNode(-1);
        ListNode even = new ListNode(-1);
        
        ListNode curr = head;
        ListNode temp1 = even;
        ListNode temp2 = odd;
        
        while(curr != null){
            if(curr.val % 2 != 0){
                temp2.next = curr;
                temp2 = temp2.next;
            }else{
                temp1.next = curr;
                temp1 = temp1.next;
            }
            curr = curr.next;
        }
        
        temp1.next = odd.next;
        temp2.next = null;
        
        return even.next;
    }
	public static void main (String[] args) {
		//code
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		while(T-- > 0){
		    int n = scan.nextInt();
		    ListNode l1 = new ListNode(-1);
		    
		    ListNode temp = l1;
		    for(int i = 0; i < n; i++){
		        temp.next = new ListNode(scan.nextInt());
		        temp = temp.next;
		    }
		    ListNode head = solve(l1.next);
		    ListNode t = head;
		    while(t != null){
		        System.out.print(t.val + " ");
		        t = t.next;
		    }
		    System.out.println();
		}
	}
}
