import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;

public class questions {
    class Node{
        int data ;
        Node next;
        Node(int d)
        {
            data = d;
            next = null;
	    }
    }
    
    // Given Only A Pointer To A Node To Be Deleted In A Singly Linked List How Do You Delete It
    // https://practice.geeksforgeeks.org/problems/delete-without-head-pointer/1
    void deleteNode(Node node)
    {
        
            node.data=node.next.data;
            node.next=node.next.next;
          
    }


    // Find First Non Repeating Character Stream Characters
    // https://practice.geeksforgeeks.org/problems/first-non-repeating-character-in-a-stream1216/1
    public String FirstNonRepeating(String A)
    {
        // code here
        int arr[] = new int[26];
        Queue<Character> q = new ArrayDeque<>();
        StringBuilder br = new StringBuilder();
        for(int i = 0; i < A.length(); i++){
          
            arr[A.charAt(i) - 'a'] = arr[A.charAt(i) - 'a'] + 1;
            q.add(A.charAt(i));
            
           while(!q.isEmpty()){
               if(arr[q.peek() - 'a'] > 1){
                   q.remove();
               }else{
                   br.append(q.peek());
                   break;
               }
           }
           if(q.isEmpty()){
               br.append('#');
           }
        }
        return br.toString();
    }


    // Nth Node From The End Of A Linked List
    // https://practice.geeksforgeeks.org/problems/nth-node-from-end-of-linked-list/1
    int getNthFromLast(Node head, int n)
    {
    		int count = 1;
    		Node temp = head;
    		Node ans = null;
    		
    		while(temp != null && count - n != 0){
    		    temp = temp.next;
    		    count++;
    		}
    		if(temp != null)
    		    ans = head;
    		while(temp != null && temp.next != null){
    		    temp = temp.next;
    		    ans = ans.next;
    		}
    		if(ans == null)
    		    return -1;
    		return ans.data;
    }



    // Detect And Remove Loop In A Linked List
    // https://practice.geeksforgeeks.org/problems/remove-loop-in-linked-list/1
    public static void removeLoop(Node head){
        // code here
        // remove the loop without losing any nodes
        Node fast = head;
        Node slow = head;
        Node prev = null;
        
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            
            if(fast == slow){
                break;
            }
        }
        
        if(fast == null || fast.next == null) return;
        
        slow = head;
        if(slow == fast){
            while(slow.next != fast){
                slow = slow.next;
                
            }
            slow.next = null;
        }else
        while(slow != fast){
            prev = fast;
            fast = fast.next;
            slow = slow.next;
        }
        if(prev != null)
        prev.next = null;
    }



    // Write A Function To Get The Intersection Point Of Two Linked Lists
    // https://practice.geeksforgeeks.org/problems/intersection-point-in-y-shapped-linked-lists/1
    int intersectPoint(Node headA, Node headB)
	{
         // code here
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
                return headA.data;
            }
            headA= headA.next;
            headB= headB.next;
        }
        return -1;
        
	}
	 public int size(Node head,int count){
        if(head == null){
            return count;
        }
        int ans =size(head.next, count + 1);
        return ans;
    }


    // Remove Duplicates From An Unsorted Linked List
    // https://practice.geeksforgeeks.org/problems/remove-duplicates-from-an-unsorted-linked-list/1
    public Node removeDuplicates(Node head) 
    {
         // Your code here
        HashSet<Integer> set = new HashSet<>();
        Node curr = head;
        Node dummy = new Node(-1);
        Node temp = dummy;
        
        
        while(curr != null){
           if(set.contains(curr.data)){
               curr = curr.next;
           }else{
               set.add(curr.data);
               temp.next = new Node(curr.data);
               temp = temp.next;
               curr = curr.next;
           }
        }
        return dummy.next;
    }


    // Merge Sort For Linked List
    // https://practice.geeksforgeeks.org/problems/sort-a-linked-list/1
    public static Node getMid(Node head){
        if(head == null)
        return null;
        
        Node fast = head;
        Node slow = head;
        
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    static Node mergeSort(Node head)
    {
        // add your code here
        if(head == null || head.next == null){
            return head;
        }
        
        Node mid = getMid(head);
        Node nHead = mid.next;
        mid.next = null;
        
        return mergeList(mergeSort(head), mergeSort(nHead));
    }
    
    
    public static Node mergeList(Node l1, Node l2){
        if(l1 == null || l2 == null){
            return l1 != null ? l1 : l2;
        }
        
        
        Node dummy = new Node(-1);
        Node prev = dummy;
        Node c1 = l1;
        Node c2 = l2;
        
        while(c1 != null && c2 != null){
            if(c1.data <= c2.data){
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


    // Delete Nodes Which Have A Greater Value On Right Side
    // https://practice.geeksforgeeks.org/problems/delete-nodes-having-greater-value-on-right/1
    Node compute(Node head)
    {
        head=reverseLL(head);
        int maxi=-100000;
        Node temp=head;
        Node rhead=new Node(-1);
        Node rtemp=rhead;
        while(temp!=null)
        {
            maxi=Math.max(temp.data,maxi);
            if(temp.data>=maxi)
            {
                rtemp.next=new Node(temp.data);
                rtemp=rtemp.next;
            }
            temp=temp.next;
        }
            rhead=reverseLL(rhead.next);
            return rhead;
    }
    public static Node reverseLL(Node head)
    {
        Node temp1=head;
        Node temp2=null;
        Node temp3=null;
        while(temp1!=null)
        {
            temp3=temp2;
            temp2=temp1;
            temp1=temp1.next;
            temp2.next=temp3;
        }
        return temp2;

    }


    // Segregate Even And Odd Elements In A Linked List
    // https://practice.geeksforgeeks.org/problems/segregate-even-and-odd-nodes-in-a-linked-list5035/1
    Node divide(int N, Node head){
        // code here
        Node odd = new Node(-1);
        Node even = new Node(-1);
        
        Node curr = head;
        Node o = odd;
        Node e = even;
        
        while(curr != null){
            if(curr.data % 2 == 1){
                o.next = curr;
                o = o.next;
            }else{
                e.next = curr;
                e = e.next;
                
            }
            curr = curr.next;
        }
        if(even.next != null){
            head = even.next;
            e.next = odd.next;
            o.next = null;
        }else{
            head = odd.next;
            o.next = null;
        }
        return head;
    }


    // Sum Of Two Linked Lists
    // https://practice.geeksforgeeks.org/problems/add-two-numbers-represented-by-linked-lists/1
    static Node addTwoLists(Node l1, Node l2){
        // code here
        // return head of sum list
          Node temp1 = l1, temp2 = l2;
        Stack<Integer> st1 = new Stack<>();
        Stack<Integer> st2 = new Stack<>();
        
        while(temp1 != null){
            st1.push(temp1.data);
            temp1 = temp1.next;
        }
        while(temp2 != null){
            st2.push(temp2.data);
            temp2 = temp2.next;
        }
        
        Node dummy = new Node(-1);
        
        Node curr = dummy.next;
        int carry = 0;
        while(st1.size() > 0 && st2.size() > 0){
            int num = st1.pop() + st2.pop() + carry;
            carry = num / 10;
            Node add = new Node(num % 10);
            curr = addFirst(curr, add);
            
        }
        while(st1.size() > 0){
            int num = st1.pop() + carry;
            carry = num / 10;
            Node add = new Node(num % 10);
            curr = addFirst(curr, add);
        }
        while(st2.size() > 0){
            int num = st2.pop() + carry;
            carry = num / 10;
            Node add = new Node(num % 10);
            curr = addFirst(curr, add);
        }
        
        if(carry > 0){
            Node add = new Node(carry);
            curr = addFirst(curr, add);
        }
        return curr;
        
    }
    public static Node addFirst(Node head, Node node){
        if(head == null){
            head = node;
            return head;
        }
        
        node.next = head;
        head = node;
        return head;
        
    }


    // Find A Triplet From Three Linked Lists With Sum Equal To A Given Number
    // https://www.geeksforgeeks.org/find-a-triplet-from-three-linked-lists-with-sum-equal-to-a-given-number/
    boolean isSumSorted(LinkedList la, LinkedList lb, LinkedList lc,int givenNumber){
        Node a = la.head;

        // Traverse all nodes of la
        while (a != null)
        {
            Node b = lb.head;
            Node c = lc.head;

            // for every node in la pick 2 nodes from lb and lc
            while (b != null && c!=null)
            {
                int sum = a.data + b.data + c.data;
                if (sum == givenNumber)
                {
                    System.out.println("Triplet found " + a.data +
                                    " " + b.data + " " + c.data);
                    return true;
                }

            // If sum is smaller then look for greater value of b
                else if (sum < givenNumber)
                b = b.next;

                else
                c = c.next;
            }
            a = a.next;
            }
            System.out.println("No Triplet found");
            return false;
        }


    // Flattening A Linked List
    // https://practice.geeksforgeeks.org/problems/flattening-a-linked-list/1
    public  Node getMid(Node head){
        if(head == null)
        return null;
        
        Node fast = head;
        Node slow = head;
        
        while(fast.bottom != null && fast.bottom.bottom != null){
            slow = slow.bottom;
            fast = fast.bottom.bottom;
        }
        return slow;
    }
     Node mergeSort(Node head)
    {
        // add your code here
        if(head == null || head.bottom == null){
            return head;
        }
        
        Node mid = getMid(head);
        Node nHead = mid.bottom;
        mid.bottom = null;
        
        return mergeList(mergeSort(head), mergeSort(nHead));
    }
    
    
    public  Node mergeList(Node l1, Node l2){
        if(l1 == null || l2 == null){
            return l1 != null ? l1 : l2;
        }
        
        
        Node dummy = new Node(-1);
        Node prev = dummy;
        Node c1 = l1;
        Node c2 = l2;
        
        while(c1 != null && c2 != null){
            if(c1.data <= c2.data){
                prev.bottom = c1;
                c1 = c1.bottom;
            }else{
                prev.bottom = c2;
                c2 = c2.bottom;
            }
            prev = prev.bottom;
        }
        prev.bottom = c1 != null ? c1 : c2;
        return dummy.bottom;
    }
    Node flatten(Node root)
    {
	// Your code here
	    Node curr = root;
	    while(curr != null){
	        Node forw = curr.next;
	        Node temp = curr;
	        Node prev = null;
	        while(temp != null){
	            temp.next = temp.bottom;
	            prev = temp;
	            temp = temp.bottom;
	        }
	        if(prev != null)
	        prev.bottom = forw;
	        curr = forw;
	        
	    }
	    root.next = null;
	    return mergeSort(root);
    }


    // Sort A Linked List Of 0S 1S Or 2S
    // https://practice.geeksforgeeks.org/problems/given-a-linked-list-of-0s-1s-and-2s-sort-it/1
    static Node segregate(Node head)
    {
        // add your code here
        return mergeSort(head);
    }
     
    public static Node getMid(Node head){
        if(head == null)
        return null;
        
        Node fast = head;
        Node slow = head;
        
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    static Node mergeSort(Node head)
    {
        // add your code here
        if(head == null || head.next == null){
            return head;
        }
        
        Node mid = getMid(head);
        Node nHead = mid.next;
        mid.next = null;
        
        return mergeList(mergeSort(head), mergeSort(nHead));
    }
    
    
    public static Node mergeList(Node l1, Node l2){
        if(l1 == null || l2 == null){
            return l1 != null ? l1 : l2;
        }
        
        
        Node dummy = new Node(-1);
        Node prev = dummy;
        Node c1 = l1;
        Node c2 = l2;
        
        while(c1 != null && c2 != null){
            if(c1.data <= c2.data){
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


    // Flatten A Linked List With Next And Child Pointers
    // https://www.geeksforgeeks.org/flatten-a-linked-list-with-next-and-child-pointers/


    class LinkedList {
        
        static Node head;
        
        class Node {
            
            int data;
            Node next, child;
            
            Node(int d) {
                data = d;
                next = child = null;
            }
	    }

	// A utility function to create a linked list with n nodes. The data
	// of nodes is taken from arr[]. All child pointers are set as NULL
	Node createList(int arr[], int n) {
		Node node = null;
		Node p = null;
		
		int i;
		for (i = 0; i < n; ++i) {
			if (node == null) {
				node = p = new Node(arr[i]);
			} else {
				p.next = new Node(arr[i]);
				p = p.next;
			}
			p.next = p.child = null;
		}
		return node;
	}

	// A utility function to print all nodes of a linked list
	void printList(Node node) {
		while (node != null) {
			System.out.print(node.data + " ");
			node = node.next;
		}
		System.out.println("");
	}
	
	Node createList() {
		int arr1[] = new int[]{10, 5, 12, 7, 11};
		int arr2[] = new int[]{4, 20, 13};
		int arr3[] = new int[]{17, 6};
		int arr4[] = new int[]{9, 8};
		int arr5[] = new int[]{19, 15};
		int arr6[] = new int[]{2};
		int arr7[] = new int[]{16};
		int arr8[] = new int[]{3};

		/* create 8 linked lists */
		Node head1 = createList(arr1, arr1.length);
		Node head2 = createList(arr2, arr2.length);
		Node head3 = createList(arr3, arr3.length);
		Node head4 = createList(arr4, arr4.length);
		Node head5 = createList(arr5, arr5.length);
		Node head6 = createList(arr6, arr6.length);
		Node head7 = createList(arr7, arr7.length);
		Node head8 = createList(arr8, arr8.length);

		/* modify child pointers to create the list shown above */
		head1.child = head2;
		head1.next.next.next.child = head3;
		head3.child = head4;
		head4.child = head5;
		head2.next.child = head6;
		head2.next.next.child = head7;
		head7.child = head8;

		/* Return head pointer of first linked list. Note that all nodes are
		reachable from head1 */
		return head1;
	}

	/* The main function that flattens a multilevel linked list */
	void flattenList(Node node) {
		
		/*Base case*/
		if (node == null) {
			return;
		}
		
		Node tmp = null;

		/* Find tail node of first level linked list */
		Node tail = node;
		while (tail.next != null) {
			tail = tail.next;
		}

		// One by one traverse through all nodes of first level
		// linked list till we reach the tail node
		Node cur = node;
		while (cur != tail) {

			// If current node has a child
			if (cur.child != null) {

				// then append the child at the end of current list
				tail.next = cur.child;

				// and update the tail to new last node
				tmp = cur.child;
				while (tmp.next != null) {
					tmp = tmp.next;
				}
				tail = tmp;
			}

			// Change current node
			cur = cur.next;
		}
	}
	
	
}



    // Quicksort On Singly Linked List
    // https://practice.geeksforgeeks.org/problems/quick-sort-on-linked-list/1





    
    // A Linked List With Next And Arbit Pointer
    // https://practice.geeksforgeeks.org/problems/clone-a-linked-list-with-next-and-random-pointer/1
    Node copyList(Node head) {
        // your code here
        if(head == null)
        return head;
        addNode(head);
        setRandom(head);
        return removeLL(head);
    }
     public void setRandom(Node head){
        Node curr = head;
        while(curr != null){
            if(curr.arb != null){
                curr.next.arb = curr.arb.next;
            }
            curr = curr.next.next;
        }
    }
    void addNode(Node head){
        Node curr = head;
        
        
        
        while(curr != null){
            Node forw = curr.next;
            Node temp = new Node(curr.data);
            
            temp.next = forw;
            curr.next = temp;
            curr = forw;
            
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


    // Point To Next Higher Value Node In A Linked List With An Arbitrary Pointer
    // https://www.geeksforgeeks.org/point-to-next-higher-value-node-in-a-linked-list-with-an-arbitrary-pointer/
    static class Node
    {
        int data;
        Node next, arbit;
 
        Node(int data)
        {
            this.data = data;
            next = null;
            arbit = null;
        }
    }
    void printList(Node node, Node anode)
    {
        System.out.println("Traversal using Next Pointer");
        while (node != null)
        {
            System.out.print(node.data + " ");
            node = node.next;
        }
 
        System.out.println("\nTraversal using Arbit Pointer");
        while (anode != null)
        {
            System.out.print(anode.data + " ");
            anode = anode.arbit;
        }
    }
 
    // This function populates arbit pointer in every node to the
    // next higher value. And returns pointer to the node with
    // minimum value
    private Node populateArbit(Node start)
    {
 
        Node temp = start;
 
        // Copy next pointers to arbit pointers
        while (temp != null)
        {
            temp.arbit = temp.next;
            temp = temp.next;
        }
 
        // Do merge sort for arbitrary pointers and
        // return head of arbitrary pointer linked list
        return MergeSort(start);
    }
 
    /* sorts the linked list formed by arbit pointers
    (does not change next pointer or data) */
    private Node MergeSort(Node start)
    {
         
        /* Base case -- length 0 or 1 */
        if (start == null || start.arbit == null)
        {
            return start;
        }
 
        /* Split head into 'middle' and 'nextofmiddle' sublists */
        Node middle = getMiddle(start);
        Node nextofmiddle = middle.arbit;
 
        middle.arbit = null;
 
        /* Recursively sort the sublists */
        Node left = MergeSort(start);
        Node right = MergeSort(nextofmiddle);
 
        /* answer = merge the two sorted lists together */
        Node sortedlist = SortedMerge(left, right);
 
        return sortedlist;
    }
 
    // Utility function to get the middle of the linked list
    private Node getMiddle(Node source)
    {
        // Base case
        if (source == null)
            return source;
        Node fastptr = source.arbit;
        Node slowptr = source;
 
        // Move fastptr by two and slow ptr by one
        // Finally slowptr will point to middle node
        while (fastptr != null)
        {
            fastptr = fastptr.arbit;
            if (fastptr != null)
            {
                slowptr = slowptr.arbit;
                fastptr = fastptr.arbit;
            }
        }
        return slowptr;
    }
 
    private Node SortedMerge(Node a, Node b)
    {
        Node result = null;
 
        /* Base cases */
        if (a == null)
            return b;
        else if (b == null)
            return a;
 
        /* Pick either a or b, and recur */
        if (a.data <= b.data)
        {
            result = a;
            result.arbit = SortedMerge(a.arbit, b);
        }
        else
        {
            result = b;
            result.arbit = SortedMerge(a, b.arbit);
        }
 
        return result;
    }


    // Rearrange A Given Linked List In Place
    // https://practice.geeksforgeeks.org/problems/reorder-list/1
    public Node midNode(Node head)
    {
        if (head == null || head.next == null)
            return head;

        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
    public Node reverseList(Node head) {
        if (head == null || head.next == null)
            return head;

        Node prev = null;
        Node curr = head;

        while (curr != null) {
            Node forw = curr.next; // backup
            //count++;
            curr.next = prev; // link

            prev = curr; // move
            curr = forw;
        }

        return prev;
    }
    Node reorderlist(Node head) {
        // Your code here
         if(head == null || head.next == null){
            return head;
        }
        Node mid = midNode(head);
        Node nhead = mid.next;
        mid.next = null;
        nhead = reverseList(nhead);
        Node c1 = head;
       
        Node c2 = nhead;
        

        while(c1 != null && c2 != null){
            Node f1 = c1.next;
            Node f2 = c2.next;
            c1.next = c2;
            c2.next = f1;
            c1 = f1;
            c2 = f2;
        }
        return head;
    }


    // Select A Random Node From A Singly Linked List
    // https://www.geeksforgeeks.org/select-a-random-node-from-a-singly-linked-list/
    void printrandom(Node node) {
  
        // If list is empty
        if (node == null) {
            return;
        }
  
        // Use a different seed value so that we don't get
        // same result each time we run this program
        Math.abs(UUID.randomUUID().getMostSignificantBits());
  
        // Initialize result as first node
        int result = node.data;
  
        // Iterate from the (k+1)th element to nth element
        Node current = node;
        int n;
        for (n = 2; current != null; n++) {
  
            // change result with probability 1/n
            if (Math.random() % n == 0) {
                result = current.data;
            }
  
            // Move to next node
            current = current.next;
        }
  
        System.out.println("Randomly selected key is " + result);
    }


    // Linked List In Zig Zag Fashion
    // https://www.geeksforgeeks.org/linked-list-in-zig-zag-fashion/
    static void zigZagList(Node head)
    {
        // If flag is true, then
        // next node should be greater
        // in the desired output.
        boolean flag = true;
 
        // Traverse linked list starting from head.
        Node current = head;
        while (current != null && current.next != null) {
            if (flag == true) /* "<" relation expected */
            {
                /* If we have a situation like A > B > C
            where A, B and C are consecutive Nodes
            in list we get A > B < C by swapping B
            and C */
                if (current.data > current.next.data) {
                    temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                }
            }
            else /* ">" relation expected */
            {
                /* If we have a situation like A < B < C where
            A, B and C are consecutive Nodes in list we
            get A < C > B by swapping B and C */
                if (current.data < current.next.data) {
                    temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                }
            }
 
            current = current.next;
 
            /* flip flag for reverse checking */
            flag = !(flag);
        }
    }


    // Sort Linked List Already Sorted Absolute Values
    // https://practice.geeksforgeeks.org/problems/absolute-list-sorting/1
    public Node mergeList(Node h1, Node h2){
        if(h1 == null || h2 == null){
            return h1 == null ? h2 : h1;
        }
        
        Node dummy = new Node(-1);
        Node curr = dummy;
        Node c1 = h1;
        Node c2 = h2;
        
        while(c1 != null && c2 != null){
            if(c1.data >= c2.data){
                curr.next = c2;
                c2 = c2.next;
            }else{
                curr.next = c1;
                c1 = c1.next;
            }
            curr = curr.next;
        }
        
        curr.next = c1 == null ? c2 : c1;
        
        return dummy.next;
    }
	Node sortedList(Node head)
	{
		// Your code here
		
		if(head == null || head.next == null){
		    return head;
		}
		
		Node mid = getMid(head);
		Node nHead = mid.next;
		mid.next = null;
		
		return mergeList(sortedList(head), sortedList(nHead));
	}
	
	Node getMid(Node head){
	    if(head == null){
	        return null;
	    }
	    Node fast = head;
	    Node slow = head;
	    
	    while(fast.next != null && fast.next.next != null){
	    
	        fast = fast.next.next;
	        slow = slow.next;
	    }
	    return slow;
	}


    // Merge K Sorted Linked Lists
    // https://practice.geeksforgeeks.org/problems/merge-k-sorted-linked-lists/1
    public Node solve(Node[] a, int si, int ei){
        if(si == ei){
            return a[si];
        }
        
        int mid = (ei + si) / 2;
        Node l1 = solve(a, si, mid);
        Node l2 = solve(a, mid + 1, ei);
        
        
        return mergeTwoLists(l1, l2);
    }
    public Node mergeTwoLists(Node l1, Node l2) {
        
        if(l1 == null || l2 == null){
            return l1 != null ? l1 : l2;
        }
        Node dummy = new Node(-1);

        Node prev = dummy;
        Node c1 = l1;
        Node c2 = l2;

        while(c1 != null && c2 != null){
            if(c1.data <= c2.data){
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
    Node mergeKList(Node[]a,int N)
    {
        //Add your code here.
        
        if(a.length == 0){
            return null;
        }
        
        return solve(a, 0, N - 1);
    }


    // Flatten A Multi Level Linked List Set 2 Depth Wise
    // https://www.geeksforgeeks.org/flatten-a-multi-level-linked-list-set-2-depth-wise/
    // class Node
    // {
    //     int data;
    //     Node next,down;
    //     Node(int data)
    //     {
    //         this.data=data;
    //         next=null;
    //         down=null;
    //     }
    // }
    static Node last;
 
    // Flattens a multi-level linked list depth wise
    public static Node flattenList(Node node)
    {
        if(node==null)
            return null;
   
        // To keep track of last visited node
        // (NOTE: This is static) 
        last = node;
   
        // Store next pointer
        Node next = node.next;
   
        // If down list exists, process it first
        // Add down list as next of current node
        if(node.down!=null)
            node.next = flattenList(node.down);
   
        // If next exists, add it after the next
        // of last added node
        if(next!=null)
            last.next = flattenList(next);
   
        return node;
    }
 
    // Utility method to print a linked list
    public static void printFlattenNodes(Node head)
    {
        Node curr=head;
        while(curr!=null)
        {
            System.out.print(curr.data+" ");
            curr = curr.next;
        }
         
    }

    // Subtract Two Numbers Represented As Linked Lists
    // https://practice.geeksforgeeks.org/problems/subtraction-in-linked-list/1



    // Find Pair Given Sum Sorted Singly Linked Without Extra Space
    // https://www.geeksforgeeks.org/find-pair-given-sum-sorted-singly-linked-without-extra-space/
    // C++ program to find pair with given sum in a singly
    // linked list in O(n) time and no extra space.
    // #include<bits/stdc++.h>
    // using namespace std;

    // /* Link list node */
    // struct Node
    // {
    //     int data;

    //     /* also constains XOR of next and
    //     previous node after conversion*/
    //     struct Node* next;
    // };

    // /* Given a reference (pointer to pointer) to the head
    //     of a list and an int, push a new node on the front
    //     of the list. */
    // void insert(struct Node** head_ref, int new_data)
    // {
    //     /* allocate node */
    //     struct Node* new_node =
    //         (struct Node*) malloc(sizeof(struct Node));

    //     /* put in the data */
    //     new_node->data = new_data;

    //     /* link the old list off the new node */
    //     new_node->next = (*head_ref);

    //     /* move the head to point to the new node */
    //     (*head_ref) = new_node;
    // }

    // /* returns XORed value of the node addresses */
    // struct Node* XOR (struct Node *a, struct Node *b)
    // {
    //     return (struct Node*) ((uintptr_t) (a) ^ (uintptr_t) (b));
    // }

    // // Utility function to convert singly linked list
    // // into XOR doubly linked list
    // void convert(struct Node *head)
    // {
    //     // first we store address of next node in it
    //     // then take XOR of next node and previous node
    //     // and store it in next pointer
    //     struct Node *next_node;

    //     // prev node stores the address of previously
    //     // visited node
    //     struct Node *prev = NULL;

    //     // traverse list and store xor of address of
    //     // next_node and prev node in next pointer of node
    //     while (head != NULL)
    //     {
    //         // address of next node
    //         next_node = head->next;

    //         // xor of next_node and prev node
    //         head->next = XOR(next_node, prev);

    //         // update previous node
    //         prev = head;

    //         // move head forward
    //         head = next_node;
    //     }
    // }

    // // function to Find pair whose sum is equal to
    // // given value x
    // void pairSum(struct Node *head, int x)
    // {
    //     // initialize first
    //     struct Node *first = head;

    //     // next_node and prev node to calculate xor again
    //     // and find next and prev node while moving forward
    //     // and backward direction from both the corners
    //     struct Node *next_node = NULL, *prev = NULL;

    //     // traverse list to initialize second pointer
    //     // here we need to move in forward direction so to
    //     // calculate next address we have to take xor
    //     // with prev pointer because (a^b)^b = a
    //     struct Node *second = head;
    //     while (second->next != prev)
    //     {
    //         struct Node *temp = second;
    //         second = XOR(second->next, prev);
    //         prev = temp;
    //     }

    //     // now traverse from both the corners
    //     next_node = NULL;
    //     prev = NULL;

    //     // here if we want to move forward then we must
    //     // know the prev address to calculate next node
    //     // and if we want to move backward then we must
    //     // know the next_node address to calculate prev node
    //     bool flag = false;
    //     while (first != NULL && second != NULL &&
    //             first != second && first != next_node)
    //     {
    //         if ((first->data + second->data)==x)
    //         {
    //             cout << "(" << first->data << ","
    //                 << second->data << ")" << endl;

    //             flag = true;

    //             // move first in forward
    //             struct Node *temp = first;
    //             first = XOR(first->next,prev);
    //             prev = temp;

    //             // move second in backward
    //             temp = second;
    //             second = XOR(second->next, next_node);
    //             next_node = temp;
    //         }
    //         else
    //         {
    //             if ((first->data + second->data) < x)
    //             {
    //                 // move first in forward
    //                 struct Node *temp = first;
    //                 first = XOR(first->next,prev);
    //                 prev = temp;
    //             }
    //             else
    //             {
    //                 // move second in backward
    //                 struct Node *temp = second;
    //                 second = XOR(second->next, next_node);
    //                 next_node = temp;
    //             }
    //         }
    //     }
    //     if (flag == false)
    //         cout << "No pair found" << endl;
    // }

    // // Driver program to run the case
    // int main()
    // {
    //     /* Start with the empty list */
    //     struct Node* head = NULL;
    //     int x = 17;

    //     /* Use insert() to construct below list
    //     3-->6-->7-->8-->9-->10-->11 */
    //     insert(&head, 11);
    //     insert(&head, 10);
    //     insert(&head, 9);
    //     insert(&head, 8);
    //     insert(&head, 7);
    //     insert(&head, 6);
    //     insert(&head, 3);

    //     // convert singly linked list into XOR doubly
    //     // linked list
    //     convert(head);
    //     pairSum(head,x);
    //     return 0;
    // }



    // Multiply Two Numbers Represented Linked Lists
    // https://practice.geeksforgeeks.org/problems/multiply-two-linked-lists/1
    public long multiplyTwoLists(Node l1,Node l2){
        String m1 = "";
        String m2 = "";
        while(l1!=null)
        {
            m1 = m1 + String.valueOf(l1.data);
            l1 = l1.next;
        }
        while(l2!=null)
        {
            m2 = m2 + String.valueOf(l2.data);
            l2 = l2.next;
        }
        java.math.BigInteger m11 = new java.math.BigInteger(m1);
        java.math.BigInteger m22 = new java.math.BigInteger(m2);
        java.math.BigInteger mul = m11.multiply(m22);
        java.math.BigInteger modd = new java.math.BigInteger("1000000007");
        java.math.BigInteger res = mul.mod(modd);
        Long rs = res.longValue();
        return rs;
   }



    // Merge Two Sorted Lists Place
    // https://practice.geeksforgeeks.org/problems/merge-two-sorted-linked-lists/1
    Node sortedMerge(Node headA, Node headB) {
        // This is a "method-only" submission. 
        // You only need to complete this method
        
        Node dummy = new Node(-1);
        Node curr = dummy;
        Node c1 = headA;
        Node c2 = headB;
        
        
        while(c1 != null && c2 != null){
            if(c1.data >= c2.data){
                curr.next = c2;
                c2 = c2.next;
            }else{
                curr.next = c1;
                c1 = c1.next;
            }
            curr = curr.next;
        }
        curr.next = c1 == null ? c2 : c1;
        return dummy.next;
      } 



    // Rotate Linked List Block Wise
    // https://www.geeksforgeeks.org/rotate-linked-list-block-wise/
    static Node rotateHelper(Node blockHead,Node blockTail,int d,int k){
        if (d == 0)
            return blockHead;

        // Rotate Clockwise
        if (d > 0)
        {
            Node temp = blockHead;
            for (int i = 1; temp.next.next!=null && i < k - 1; i++)
                temp = temp.next;
            blockTail.next = blockHead;
            tail = temp;
            return rotateHelper(blockTail, temp,
                    d - 1,  k);
        }

        // Rotate anti-Clockwise
        if (d < 0){
            blockTail.next = blockHead;
            tail = blockHead;
            return rotateHelper(blockHead.next,blockHead, d + 1,  k);
        }
        return blockHead;
    }

    // Function to rotate the linked list block wise
    static Node rotateByBlocks(Node head,int k, int d){
        // If length is 0 or 1 return head
        if (head == null || head.next == null)
            return head;

        // if degree of rotation is 0, return head
        if (d == 0)
            return head;

        Node temp = head;
        tail = null;

        // Traverse upto last element of this block
        int i;
        for (i = 1; temp.next != null && i < k; i++)
            temp = temp.next;

        // storing the first node of next block
        Node nextBlock = temp.next;

        // If nodes of this block are less than k.
        // Rotate this block also
        if (i < k)
            head = rotateHelper(head, temp, d % k,i);
        else
            head = rotateHelper(head, temp, d % k,k);

        // Append the new head of next block to
        // the tail of this block
        tail.next = rotateByBlocks(nextBlock, k,d % k);

        // return head of updated Linked List
        return head;
    }


    // Josephus Circle Using Circular Linked List
    // https://www.geeksforgeeks.org/josephus-circle-using-circular-linked-list/
    static void getJosephusPosition(int m, int n)
    {
        // Create a circular linked list of
        // size N.
        Node head = new Node(1);
        Node prev = head;
        for(int i = 2; i <= n; i++)
        {
            prev.next = new Node(i);
            prev = prev.next;
        }
         
        // Connect last node to first
        prev.next = head;
         
        /* while only one node is left in the
        linked list*/
        Node ptr1 = head, ptr2 = head;
         
        while(ptr1.next != ptr1)
        {
             
            // Find m-th node
            int count = 1;
            while(count != m)
            {
                ptr2 = ptr1;
                ptr1 = ptr1.next;
                count++;
            }
             
            /* Remove the m-th node */
            ptr2.next = ptr1.next;
            ptr1 = ptr2.next;
        }
        System.out.println ("Last person left standing " +
                 "(Josephus Position) is " + ptr1.data);
    }


    // Count Triplets Sorted Doubly Linked List Whose Sum Equal Given Value X
    // https://www.geeksforgeeks.org/count-triplets-sorted-doubly-linked-list-whose-sum-equal-given-value-x/
    static int countTriplets(Node head, int x)
    {
            Node ptr1, ptr2, ptr3;
            int count = 0;
 
            // generate all possible triplets
            for (ptr1 = head; ptr1 != null; ptr1 = ptr1.next)
                for (ptr2 = ptr1.next; ptr2 != null; ptr2 = ptr2.next)
                    for (ptr3 = ptr2.next; ptr3 != null; ptr3 = ptr3.next)
 
                        // if elements in the current triplet sum up to 'x'
                        if ((ptr1.data + ptr2.data + ptr3.data) == x)
                             
                            // increment count
                            count++;
 
            // required count of triplets
            return count;
    }


    // Sort Biotonic Doubly Linked List
    // https://www.geeksforgeeks.org/sort-biotonic-doubly-linked-list/
    static class Node 
    {
        int data;
        Node next;
        Node prev;
    }
      
    // Function to reverse a Doubly Linked List
    static Node reverse( Node head_ref)
    {
        Node temp = null;
        Node current = head_ref;
      
        // swap next and prev for all nodes
        // of doubly linked list
        while (current != null)
        {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev;
        }
      
        // Before changing head, check for the cases 
        // like empty list and list with only one node
        if (temp != null)
            head_ref = temp.prev;
            return head_ref;
    }
      
    // Function to merge two sorted doubly linked lists
    static Node merge(Node first, Node second)
    {
        // If first linked list is empty
        if (first == null)
            return second;
      
        // If second linked list is empty
        if (second == null)
            return first;
      
        // Pick the smaller value
        if (first.data < second.data)
        {
            first.next = merge(first.next, second);
            first.next.prev = first;
            first.prev = null;
            return first;
        } 
        else 
        {
            second.next = merge(first, second.next);
            second.next.prev = second;
            second.prev = null;
            return second;
        }
    }
      
    // function to sort a biotonic doubly linked list
    static Node sort(Node head)
    {
        // if list is empty or if it contains 
        // a single node only
        if (head == null || head.next == null)
            return head;
      
        Node current = head.next;
      
        while (current != null) 
        {
      
            // if true, then 'current' is the first node
            // which is smaller than its previous node
            if (current.data < current.prev.data)
                break;
      
            // move to the next node
            current = current.next;
        }
      
        // if true, then list is already sorted
        if (current == null)
            return head;
      
        // spilt into two lists, one starting with 'head'
        // and other starting with 'current'
        current.prev.next = null;
        current.prev = null;
      
        // reverse the list starting with 'current'
        current = reverse(current);
      
        // merge the two lists and return the
        // final merged doubly linked list
        return merge(head, current);
    }


}
