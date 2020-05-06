package Sum;

class twoSum{
public static int[] twoSum(int[] nums, int target) {
    int returnint[]=new int[2];
    for(int i=0; i<nums.length;i++){
        for(int j=i+1;j<nums.length;j++){
            if(nums[j]==target-nums[i]){
                returnint[0]=i;
                returnint[1]=j;
                return returnint;
            }
        }
    }
    return new int[]{0,0};
}
}
