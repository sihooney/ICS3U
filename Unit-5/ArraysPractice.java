import java.util.*;

// https://codingbat.com/java/Array-3

public class ArraysPractice {
    public static void main(String[] args) {

    }

    // https://codingbat.com/prob/p193817
    public static int countClumps(int[] nums) {
        boolean inClump = false;
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                if (!inClump) {
                    count++;
                    inClump = true;
                }
            } else {
                inClump = false;
            }
        }
        return count;
    }


    // https://codingbat.com/prob/p196409
    public static int maxMirror(int[] nums) {
        int[] reversed = new int[nums.length];
        for (int k = 0; k < reversed.length; k++) {
            reversed[k] = nums[nums.length - k - 1];
        }

        // Longest common substring problem using dynamic programming
        int[][] dp = new int[nums.length + 1][nums.length + 1];
        int result = 0;
        for (int i = 0; i <= nums.length; i++) {
            for (int j = 0; j <= nums.length; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (nums[i - 1] == reversed[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    result = Math.max(result, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return result;
    }

    // https://codingbat.com/prob/p104090
    public static int[] seriesUp(int n) {
        int[] res = new int[n * (n + 1) / 2];
        int group = 1;
        int arrIdx = 0;
        while (group <= n) {
            for (int i = arrIdx, curVal = 1; i < arrIdx + n; i++, curVal++) {
                res[i] = curVal;
            }
            arrIdx += group;
            group++;
        }
        return res;
    }

    // https://codingbat.com/prob/p155405
    public static int[] squareUp(int n) {
        int[] res = new int[n * n];
        for (int i = 0; i < res.length; i++) {
            int group = i / n + 1;
            int pos = i % n;
            if (n - pos <= group) {
                res[i] = n - pos;
            } else {
                res[i] = 0;
            }
        }
        return res;
    }

    // https://codingbat.com/prob/p125819
    public static int[] fix45(int[] nums) {
        if (nums.length == 0) {
            return nums;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        if (nums[0] == 5) {
            queue.add(0);
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 5 && nums[i - 1] != 4) {
                queue.add(i);
            }
        }
        for (int j = 0; j < nums.length - 1; j++) {
            if (nums[j] == 4 && nums[j + 1] != 5) {
                if (!queue.isEmpty()) {
                    nums[queue.poll()] = nums[j + 1];
                    nums[j + 1] = 5;
                }
            }
        }
        return nums;
    }

    // https://codingbat.com/prob/p159339
    public static int[] fix34(int[] nums) {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 3) {
                queue.add(i);
            }
        }
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] == 4) {
                if (!queue.isEmpty()) {
                    nums[j] = nums[queue.peek() + 1];
                    nums[queue.poll() + 1] = 4;
                }
            }
        }
        return nums;
    }

    // https://codingbat.com/prob/p189576
    public static int maxSpan(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> leftmost = new HashMap<>();
        int largest = 1;
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            if (!leftmost.containsKey(val)) {
                leftmost.put(val, i);
            } else {
                largest = Math.max(largest, i - leftmost.get(val) + 1);
            }
        }
        return largest;
    }

    // https://codingbat.com/prob/p134022
    public static boolean linearIn(int[] outer, int[] inner) {
        int i = 0;
        for (int k : outer) {
            if (i == inner.length) {
                return true;
            } else if (k == inner[i]) {
                i++;
            }
        }
        return i == inner.length;
    }

    // https://codingbat.com/prob/p158767
    public static boolean canBalance(int[] nums) {
        int total = 0;
        for (int i : nums) {
            total += i;
        }
        int curSum = 0;
        for (int j : nums) {
            curSum += j;
            if (curSum * 2 == total) {
                return true;
            }
        }
        return false;
    }
}
