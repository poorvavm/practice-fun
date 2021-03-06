package dsAlgo.lists;

public class SingleLinkedList
{
   Node head = null;
   
   public SingleLinkedList(){
      head = null;
   }
   
   public class Node
   {
      private String data;
      private Node next;      
      
      // Node class constructors
      public Node(){
         this.data = "";
         this.next = null;
      }
      
      public Node(String data){
         this.data = data;
         this.next = null;
      }
      
      public Node(String data, Node next){
         this.data = data;
         this.next = next;
      }
      
      // Getters for node class
      public String data(){
         return this.data;
      }
      
      public Node next(){
         return this.next;
      }

      // Setters for node class
      public void setData(String data){
         this.data = data; 
      }
      
      public void setNext(Node nextNode){
         this.next = nextNode;
      }
   }
   
   public void insert(Node newNode){
      if (newNode == null )
      {
         System.out.println("Can not add a null node");
         return;
      }
        
      if (this.head != null)
         newNode.next = head;
      head = newNode;
   }
   
   public void insertEOL(Node newNode){
      if (newNode == null)
      {
         System.out.println("\ncan not add a null Node to end of list");
         return;
      }
      
      if (head==null)
         head = newNode;
      else 
      {
         Node temp = head;
         while (temp.next != null)
            temp = temp.next;
         temp.next = newNode;
      }
   }
   
   public int insertAtN(int pos, Node newNode){
      if(newNode == null || pos <= 0){
         System.out.println("Invalid node or position");
         return -1;
      }
      
      if (pos == 1){
         newNode.next = head;
         head = newNode;
         return 1;
      }
      
      Node temp = head;
      int counter = 1;
      while(temp.next!=null && counter < pos-1){
         temp = temp.next;
         counter++;
      }
      
      if (counter < pos-1){
         System.out.println("insert position is greater than list length");
         return -1;
      }

      newNode.next = temp.next;
      temp.next = newNode;
      return pos;
   }
   
   // Insert the elements in sorted order
   public void sortedInsert(Node n){
      if (head == null){
         head=n;
         return;
      }
      
      Node higher = head;
      Node lower = null;
      while (higher!=null)
      {
         if (higher.data.compareTo(n.data)>0)
         {
            if (lower==null){
               n.next = head;
               head = n;
               return;
            }
            lower.next = n;
            n.next = higher;
            return;
         }
         
         lower=higher;
         higher=higher.next;
      }
      lower.next = n;
   }
   
   public Node removeFirst(){
      if (head == null)
      {   System.out.println("Empty List");
         return null;
      }
      Node temp = head;
      head = head.next;
      return temp;
   }
   
   public Node removeLast(){
      if (head == null)
      {  
         System.out.println("Empty List");
         return null;
      }
      
      if (head.next == null)
      {
         Node temp = head;
         head = null;
         return temp;
      }
      
      Node temp = head;
      while (temp.next.next != null)
         temp = temp.next;
      
      Node retNode = temp.next;
      temp.next = null;
      return retNode; 
   }
   
   public Node removeNth(int pos){
      Node temp = null;
      // Check for empty list and if position is wrong
      if (head == null || pos <= 0)
      {
         System.out.println("Empty List or invalid position");
         return temp;
      }
      temp=head;
      if (pos==1)
      {
         head = head.next;
         return temp;
      }
      int counter = 1;
      
      while(temp.next!=null && counter < pos-1){
         temp = temp.next;
         counter++;
      }
      
      if (counter < pos-1){
         System.out.println("insert position is greater than list length");
         return null;
      }
      Node retNode = temp.next;
      temp.next = temp.next.next;
      return retNode;
   }
   
   public int removeNode(Node remNode){
      if (head == null || remNode == null)
      {
         System.out.println("EMPTY LIST or Bad Node !!!!!!");
         return -1;
      }
      
      if (head == remNode){
         head = head.next;
         return 1;
      }
      
      int count = 1;
      Node temp = head;
      while(temp.next != null  && temp.next != remNode)
      {
         temp = temp.next;
         count++;
      }
      
      if (temp.next == null)
      {
         System.out.println("Node not found in the list");
         return -1;
      }
      
      temp.next = temp.next.next;
      return ++count;
   }
   
   public Node reverseList(){
      if (head == null || head.next==null)
         return head;
      
      if (head.next.next==null)
      {
         head.next.next = head;
         head=head.next;
         head.next.next =null;
      }
      
      Node n1= head;
      Node n2= head.next;
      Node n3= head.next.next;
      n1.next = null;
      
      while (n2.next!=null)
      {
         n2.next=n1;
         n1=n2;
         n2=n3;
         n3=n3.next;
      }
      
      n2.next=n1;
      head =n2;
      return head;
   }
   
   public void recReverseList(Node headRef){
      // to do
    }
      
   public int getLength(){
      if(head==null)
         return 0;
      
      int count = 1;
      Node temp = head;
      
      while(temp.next()!=null)
      {
         count++;
         temp=temp.next;
      }
      return count;
   }
   
   public Node getTail(){
      if (head == null || head.next==null)
         return head;
      
      Node tempNode = head;
      while (tempNode.next != null)
         tempNode = tempNode.next;
      return tempNode;
   }

   public Node getNthNode(int pos){
      if (head == null || pos <= 0 )
         return null;
      
      Node temp = head;
      int counter = 1;
      while (temp.next != null && counter < pos){
         temp= temp.next;
         counter++;
      }      
      if(temp.next==null)
         return null;
      
      return temp;
   }
   
   public Node getMiddle(){
      Node temp = head;
      Node middle = head;
      int counter=1;
      
      if (head == null || head.next==null)
         return head;
      
      while (temp!=null)
      {
         temp=temp.next;
         if (counter%2==0)
            middle = middle.next;
         counter++;
      }
      return middle;
   }
   
   public Node getNthFromEnd(int pos){
      Node temp = head;
      Node nthNode = head;
      int counter = 1;
      
      if (head == null || pos<=0)
         return null;
      
      while (temp.next!=null)
      {
         if (counter > pos)
            nthNode = nthNode.next;
         temp = temp.next;
         counter++;
      }
      
      return nthNode;
   }
   
   public int printLL(){
      if (this.head == null)
         return -1;
      
      int count = 1;
      Node temp = head;
      
      System.out.print("head\n  |-> ");
      while(temp.next()!=null)
      {
         System.out.print(temp.data + " -> ");
         count++;
         temp=temp.next;
      }
      System.out.print(temp.data);
      return count;
   }

   public boolean isEmpty(){
      if (this.head ==null)
         return true;
      return true;
   }

   public boolean isLoop(){
      if (head == null || head.next==null)
         return false;
      
      int counter =2;
      Node slow = head;
      Node fast = head.next;
      while (fast.next != null){
         if (slow.next==fast.next)
            return true;
         
         fast = fast.next;
         
         if (++counter %2 ==  0)
            slow = slow.next;
      }
      return false;
   }

   public void deleteList(){
      this.head = null;
   }
   
   public void selectionSort(){
 
   }
   
   
   public void insertSort(){
      // to do 
   }
   
   public void mergeSort(){
      // to do 
   }
   
   // Rotate the list elements towards right n places
   public void rotateRight(int n){
      if (this.head==null || this.head.next==null || n<=0)
         return;
      int counter =1;
      Node fast = head;
      Node slow = head;
      while (fast.next!=null)
      {
         fast= fast.next;
         if (counter > n)
            slow = slow.next;
         ++counter;
      }
      if (counter < n)
         return;
      
      fast.next = head;
      head = slow.next;
      slow.next = null;
   }
   
   // Rotate the list elements towards left n places
   public void rotateLeft(int n){
      if (this.head==null || this.head.next==null || n<=0)
         return;
      int counter =1;
      Node temp = head;
      Node nthNode = null;
      
      while (temp.next!=null)
      {
         if (counter == n)
            nthNode = temp;

         temp = temp.next;
         counter++;
      }
         
      
      temp.next = head;
      head = nthNode.next;
      nthNode.next=null;
      
   }
   
   // Split the linked list into two at nth position 
   // Return the head of new splited sublist
   public Node split(int n){
      if (this.head == null)
         return null;
      
      int counter = 1;
      Node temp = head;
      Node headTwo = null;
      
      while (temp.next != null && counter < n-1){
         temp = temp.next;
         counter++;
      }
      
      if (temp.next == null)
         return null;
      
      headTwo = temp.next;
      temp.next = null;
      
      return headTwo;
   }
   
   // Split the list into two one with odd and other with even position elements
   // return the head of another list
   public Node alternateSplit(){
      Node headTwo = null;
      if (this.head == null || this.head.next == null)
         return headTwo;
      
      Node tempOne = this.head;
      Node tempTwo = tempOne.next;
      headTwo = tempTwo;
      tempOne.next = tempTwo.next;
      tempOne = tempOne.next;
      
      while (tempOne!= null && tempOne.next!=null)
      {
         tempTwo.next = tempOne.next;
         tempTwo = tempTwo.next;
         tempOne.next = tempTwo.next;
         tempOne = tempOne.next;
      }
      tempTwo.next = null;
      return headTwo;      
   }
   
   // Append secList to the end of the first list
   public void append(Node secList){
      if (secList==null)
         return;
      
      if (this.head == null)
         this.head = secList;
      
      Node temp = this.head;
      while (temp.next!=null)
         temp=temp.next;
      
      temp.next = secList;
   }
   
   // Merge secList to original list with elements 
   // inserted at alternate positions
   public void shuffleMerge(Node secList){
      if (secList==null)
         return;
      if (this.head == null)
      {
         this.head = secList;
         secList = null;
         return;
      }
      
      Node t1 = this.head;
      Node t2 = secList;
      secList = null;
      
      while(t1!=null && t2!= null)
      {
         Node temp = t1.next;
         t1.next = t2;
         t2 = t2.next;
         t1.next.next = temp;
         t1 = temp;
      }
      if (t1 != null)
         t1.next = t2;
   }
   
   // remove all the duplicate elements in the list
   public void removeDuplicates(){
      
   }
}