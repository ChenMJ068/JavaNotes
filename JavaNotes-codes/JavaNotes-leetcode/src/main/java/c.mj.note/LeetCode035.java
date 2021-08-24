package c.mj.note;

public class LeetCode035 {
    public int searchInsert(int[] nums, int target) {
        if(nums.length == 0){
            return 0;
        }

        for(int i = 0;i<nums.length;i++){
            if(nums[0] == target){
                return 0;
            }
            if(nums[i] == target){
                return i-1;
            }
            if(nums[i] > target){
                return i;
            }
        }
        return nums.length+1;
    }
}
