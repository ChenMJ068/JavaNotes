package c.mj.note;

import com.sun.deploy.util.ArrayUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 */
public class LeetCode001 {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 4, 15};
        int[] a = twoSumByHash(nums, 6);
        for (int n : a) {
            System.out.println(n);
        }
    }

    //hash表的方式
    public static int[] twoSumByHash(int[] nums, int target) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(target - nums[i])) {
                return new int[]{hashMap.get(target - nums[i]), i};
            }
            hashMap.put(nums[i], i);
        }
        return new int[0];
    }

    //暴力破解方式
    public static int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }
}
