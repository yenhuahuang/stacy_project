package Sum;

//
//public class ListNode {
//	      int val;
//	      ListNode next;
//	      ListNode(int x) { val = x; }
//	  }
	
class addTwoNumber {
	static class ListNode{
		int val;
	      ListNode next;
	      ListNode(int x) { val = x; }
	}
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode lnew = new ListNode(0);
        ListNode lnew2=lnew;
        int formorethanten=0;
        while(l1!=null||l2!=null){
            int l1_int=(l1!=null)?l1.val:0;
            int l2_int=(l2!=null)?l2.val:0;
            int sum=l1_int+l2_int+formorethanten;
            if(l1!=null){
                l1=l1.next;
            }
            if(l2!=null){
                l2=l2.next;
            }
            
            formorethanten=sum/10;
            lnew2.next=new ListNode(sum%10);
            lnew2=lnew2.next;
            }  
        if(formorethanten>0){
            lnew2.next=new ListNode(1);
            
        }
           return lnew.next;
        }
}
