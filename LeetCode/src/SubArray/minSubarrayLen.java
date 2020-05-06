package SubArray;

public class minSubarrayLen {
	public int minSubArrayLen(int s, int[] nums) {
        int right=0;
        int left=0;
        int sum=0;
        int count=0;
        int min=Integer.MAX_VALUE;
        
            while(right<nums.length){
                
                while(sum<s&&right<nums.length){
                    sum+=nums[right++];
                }
                while(sum>=s){
                    min=Math.min(min,right-left);
                    sum-=nums[left++];
                }
            }
       
        min=(min==Integer.MAX_VALUE)?0:min;
        return min;
    }
}
