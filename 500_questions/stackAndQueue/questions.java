import java.util.PriorityQueue;
import java.util.Stack;
import java.util.Comparator;

public class questions {
    class TwoStack
    {
    
        int size;
        int top1,top2;
        int arr[] = new int[100];
    
        TwoStack()
        {
            size = 100;
            top1 = -1;
            top2 = size;
        }
    }

    // Implement Two Stacks In An Array
    // https://practice.geeksforgeeks.org/problems/implement-two-stacks-in-an-array/1
    void push1(int x, TwoStack sq)
    {
        if(sq.top1 < sq.top2 - 1){
            sq.top1++;
            sq.arr[sq.top1] = x;
        }
    }

    //Function to push an integer into the stack2.
    void push2(int x, TwoStack sq)
    {
        if(sq.top1 < sq.top2 - 1){
            sq.top2--;
            sq.arr[sq.top2] = x;
        }

    }

    //Function to remove an element from top of the stack1.
    int pop1(TwoStack sq)
    {
        if(sq.top1 != -1){
            int ans = sq.arr[sq.top1];
            sq.top1--;
            return ans;
        }
        return -1;
    }

    //Function to remove an element from top of the stack2.
    int pop2(TwoStack sq)
    {
        if(sq.top2 != sq.size){
            int ans = sq.arr[sq.top2];
            sq.top2++;
            return ans;
        }
        return -1;
    }



    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *



    // Design A Stack With Find Middle Operation
    // https://www.geeksforgeeks.org/design-a-stack-with-find-middle-operation/

    class DLLNode {
        DLLNode prev;
        int data;
        DLLNode next;
        DLLNode(int d) { data = d; }
    }
 
    /* Representation of the stack data structure that
       supports findMiddle() in O(1) time.  The Stack is
       implemented using Doubly Linked List. It maintains
       pointer to head node, pointer to middle node and
       count of nodes */
    class myStack {
        DLLNode head;
        DLLNode mid;
        int count;
    }
 
    /* Function to create the stack data structure */
    myStack createMyStack()
    {
        myStack ms = new myStack();
        ms.count = 0;
        return ms;
    }
 
    /* Function to push an element to the stack */
    void push(myStack ms, int new_data)
    {
 
        /* allocate DLLNode and put in data */
        DLLNode new_DLLNode = new DLLNode(new_data);
 
        /* Since we are adding at the beginning,
          prev is always NULL */
        new_DLLNode.prev = null;
 
        /* link the old list off the new DLLNode */
        new_DLLNode.next = ms.head;
 
        /* Increment count of items in stack */
        ms.count += 1;
 
        /* Change mid pointer in two cases
           1) Linked List is empty
           2) Number of nodes in linked list is odd */
        if (ms.count == 1) {
            ms.mid = new_DLLNode;
        }
        else {
            ms.head.prev = new_DLLNode;
 
            if ((ms.count % 2)
                != 0) // Update mid if ms->count is odd
                ms.mid = ms.mid.prev;
        }
 
        /* move head to point to the new DLLNode */
        ms.head = new_DLLNode;
    }
 
    /* Function to pop an element from stack */
    int pop(myStack ms)
    {
        /* Stack underflow */
        if (ms.count == 0) {
            System.out.println("Stack is empty");
            return -1;
        }
 
        DLLNode head = ms.head;
        int item = head.data;
        ms.head = head.next;
 
        // If linked list doesn't become empty, update prev
        // of new head as NULL
        if (ms.head != null)
            ms.head.prev = null;
 
        ms.count -= 1;
 
        // update the mid pointer when we have even number
        // of elements in the stack, i,e move down the mid
        // pointer.
        if (ms.count % 2 == 0)
            ms.mid = ms.mid.next;
 
        return item;
    }
 
    // Function for finding middle of the stack
    int findMiddle(myStack ms)
    {
        if (ms.count == 0) {
            System.out.println("Stack is empty now");
            return -1;
        }
        return ms.mid.data;
    }



    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *


    
    // Efficiently Implement K Stacks Single Array
    // https://www.geeksforgeeks.org/efficiently-implement-k-stacks-single-array/
    static class KStack 
    {
        int arr[];   // Array of size n to store actual content to be stored in stacks
        int top[];   // Array of size k to store indexes of top elements of stacks
        int next[];  // Array of size n to store next entry in all stacks
                     // and free list
        int n, k;
        int free; // To store beginning index of free list
  
        //constructor to create k stacks in an array of size n
        KStack(int k1, int n1) 
        {
            // Initialize n and k, and allocate memory for all arrays
            k = k1;
            n = n1;
            arr = new int[n];
            top = new int[k];
            next = new int[n];
  
            // Initialize all stacks as empty
            for (int i = 0; i < k; i++)
                top[i] = -1;
  
            // Initialize all spaces as free
            free = 0;
            for (int i = 0; i < n - 1; i++)
                next[i] = i + 1;
            next[n - 1] = -1; // -1 is used to indicate end of free list
        }
  
        // A utility function to check if there is space available
        boolean isFull() 
        {
            return (free == -1);
        }
  
        // To push an item in stack number 'sn' where sn is from 0 to k-1
        void push(int item, int sn) 
        {
            // Overflow check
            if (isFull()) 
            {
                System.out.println("Stack Overflow");
                return;
            }
  
            int i = free; // Store index of first free slot
  
            // Update index of free slot to index of next slot in free list
            free = next[i];
  
            // Update next of top and then top for stack number 'sn'
            next[i] = top[sn];
            top[sn] = i;
  
            // Put the item in array
            arr[i] = item;
        }
  
        // To pop an from stack number 'sn' where sn is from 0 to k-1
        int pop(int sn) 
        {
            // Underflow check
            if (isEmpty(sn)) 
            {
                System.out.println("Stack Underflow");
                return Integer.MAX_VALUE;
            }
  
            // Find index of top item in stack number 'sn'
            int i = top[sn];
  
            top[sn] = next[i]; // Change top to store next of previous top
  
            // Attach the previous top to the beginning of free list
            next[i] = free;
            free = i;
  
            // Return the previous top item
            return arr[i];
        }
  
        // To check whether stack number 'sn' is empty or not
        boolean isEmpty(int sn) 
        {
            return (top[sn] == -1);
        }


    }






    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *




    // Implement Stack Using Priority Queue Or Heap
    // https://www.geeksforgeeks.org/implement-stack-using-priority-queue-or-heap/
    public class StacksUsingPriorityQueue {

        class pair{
        
        int val;
        int key;
        
        public pair(int val, int key) {
        this.val = val;
        this.key = key;
        }
        
        public int getVal() {
        return val;
        }
        
        public void setVal(int val) {
        this.val = val;
        }
        
        public int getKey() {
        return key;
        }
        
        public void setKey(int key) {
        this.key = key;
        }
        
        @Override
        public String toString() {
        return "pair{" +
        "val=" + val +
        ", key=" + key +
        '}';
        }
        }
        //  class KeyComparator implements Comparator<pair> {
        
        // // Overriding compare()method of Comparator
        // // for descending order of cgpa
        // public int compare(pair p1, pair p2) {
        // if (p1.key < p2.key)
        // return 1;
        // else if (p1.key>p2.key)
        // return -1;
        // return 0;
        // }
        
        // }
        
         class Stack {
        
        PriorityQueue<pair> stack;
        int order;
        
        public Stack(){
        stack=new PriorityQueue<>((a,b)->{
            return a.key-b.key;
        });
        order=0;
        }
        public void push(int val){
        pair newpair= new pair(val,order++);
        stack.add( newpair);
        }
        
        public void pop(){
        stack.remove( );
        order--;
        }
        
        public int peek(){
        return stack.peek().val;
        }
        
        public void print(){
        Object[] array= stack.toArray();
        for(Object val:array){
        System.out.println(val.toString());
        }
        }
        
        }
        
        }   

        
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *





    // Stack Set 2 Infix To Postfix
    // https://practice.geeksforgeeks.org/problems/infix-to-postfix-1587115620/1

    public static String infixToPostfix(String exp) 
	{
		// Your code here
		 String result = new String("");
         
        // initializing empty stack
        Stack<Character> stack = new Stack<>();
         
        for (int i = 0; i<exp.length(); ++i)
        {
            char c = exp.charAt(i);
             
            // If the scanned character is an
            // operand, add it to output.
            if (Character.isLetterOrDigit(c))
                result += c;
              
            // If the scanned character is an '(',
            // push it to the stack.
            else if (c == '(')
                stack.push(c);
             
            //  If the scanned character is an ')',
            // pop and output from the stack
            // until an '(' is encountered.
            else if (c == ')')
            {
                while (!stack.isEmpty() &&
                        stack.peek() != '(')
                    result += stack.pop();
                 
                    stack.pop();
            }
            else // an operator is encountered
            {
                while (!stack.isEmpty() && Prec(c)
                         <= Prec(stack.peek())){
                   
                    result += stack.pop();
             }
                stack.push(c);
            }
      
        }
      
        // pop all the operators from the stack
        while (!stack.isEmpty()){
            if(stack.peek() == '(')
                return "Invalid Expression";
            result += stack.pop();
         }
        return result;
	} 
	static int Prec(char ch)
    {
        switch (ch)
        {
        case '+':
        case '-':
            return 1;
      
        case '*':
        case '/':
            return 2;
      
        case '^':
            return 3;
        }
        return -1;
    }
    


    
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *


    // The Celebrity Problem
    // https://practice.geeksforgeeks.org/problems/the-celebrity-problem/1
    boolean knows(int M[][],int a, int b)  
    { 
        boolean res = (M[a][b] == 1) ?  
                                     true :  
                                     false; 
        return res; 
    } 
  
    int celebrity(int M[][], int n)
    {
        
        int a = 0; 
        int b = n - 1; 
          

        while (a < b)  
        { 
            if (knows(M,a, b)) 
                a++; 
            else
                b--; 
        } 
  
        
        for (int i = 0; i < n; i++)  
        { 
            
            if (i != a && (knows(M,a, i) ||  
                           !knows(M,i, a))) 
                return -1; 
        } 
        return a; 
    } 



    
    
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *


    // Iterative Tower Of Hanoi

    
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *


    // Design And Implement Special Stack Data Structure

    
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *


    // Find The Maximum Of Minimums For Every Window Size In A Given Array

    
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *

    

    // Length Of The Longest Valid Substring
    // https://practice.geeksforgeeks.org/problems/valid-substring0624/1

    static void solve(String str){
        Stack<Integer> stack = new Stack<Integer>();
        int n = str.length();
        int max = 0;
        stack.push(-1);
    
        for(int i =0; i<n; i++){
          char c = str.charAt(i);
          if(c == '(' ){
            stack.push(i);
          }
          else{
            stack.pop();
            if(stack.isEmpty()){
              stack.push(i);
            }
            else{
              max = Math.max(max,i-stack.peek());
            }
          }
        }
        System.out.println(max);
      }
    
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *


    // Find Expression Duplicate Parenthesis Not
    // https://www.geeksforgeeks.org/find-expression-duplicate-parenthesis-not/

    static boolean findDuplicateparenthesis(String s) {
        // create a stack of characters 
        Stack<Character> Stack = new Stack<>();
  
        // Iterate through the given expression 
        char[] str = s.toCharArray();
        for (char ch : str) {
            // if current character is close parenthesis ')' 
            if (ch == ')') {
                // pop character from the stack 
                char top = Stack.peek();
                Stack.pop();
  
                // stores the number of characters between a 
                // closing and opening parenthesis 
                // if this count is less than or equal to 1 
                // then the brackets are redundant else not 
                int elementsInside = 0;
                while (top != '(') {
                    elementsInside++;
                    top = Stack.peek();
                    Stack.pop();
                }
                if (elementsInside < 1) {
                    return true;
                }
            } // push open parenthesis '(', operators and 
            // operands to stack 
            else {
                Stack.push(ch);
            }
        }
  
        // No duplicates found 
        return false;
    }


    
    
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *


    // Lru Cache Implementation

    
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *


    // Efficiently Implement K Queues Single Array

    
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *


    // Find A Tour That Visits All Stations

    
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *


    // Minimum Time Required So That All Oranges Become Rotten

    
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *


    // Sum Minimum Maximum Elements Subarrays Size K

    
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *


    // Stack Permutations Check If An Array Is Stack Permutation Of Other

    
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *


    // Count Natural Numbers Whose Permutation Greater Number
    
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *


    // Sort A Stack Using Recursion

    
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *


    // Stack Set 4 Evaluation Postfix Expression

    
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *
    // *


    // Queue Based Approach For First Non Repeating Character In A Stream
}
