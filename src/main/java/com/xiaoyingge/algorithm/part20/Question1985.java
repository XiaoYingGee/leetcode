package com.xiaoyingge.algorithm.part20;

/**
 * @author XiaoYingGee
 * @date 2022/4/9 12:38
 */
public class Question1985 {

    public String kthLargestNumber(String[] nums, int k) {
        String max = "";
        for (String num : nums) {
            if (max.length() < num.length()) {
                max = num;
            } else if (max.length() == num.length()) {
                max = max.compareTo(num) < 0 ? num : max;
            }
        }
        int length = max.length();

        int start = 0;
        while (start != length) {
            int[] count = new int[10];
            for (String num : nums) {
                if (num.length() <= start) {
                    count[0]++;
                } else {
                    int digit = getDigit(num, start);
                    count[digit]++;
                }
            }
            // 生成前缀和数组
            for (int i = 1; i < count.length; i++) {
                count[i] = count[i] + count[i - 1];
            }

            // 从后向前排序
            String[] help = new String[nums.length];
            for (int i = nums.length - 1; i >= 0; i--) {
                String num = nums[i];
                int digit = getDigit(num, start);
                help[count[digit] - 1] = num;
                count[digit]--;
            }
            for (int i = 0; i < help.length; i++) {
                nums[i] = help[i];
            }
            start++;
        }
        return nums[nums.length - k];
    }

    private int getDigit(String num, int start) {
        if (num.length() - 1 - start < 0) {
            return 0;
        }
        char c = num.toCharArray()[num.length() - 1 - start];
        return c - '0';
    }

    public static void main(String[] args) {
        String[] nums = new String[]{"233", "96"};
        String s = new Question1985().kthLargestNumber(nums, 1);
        System.out.println(s);
    }
}
