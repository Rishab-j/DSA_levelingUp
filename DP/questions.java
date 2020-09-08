import java.util.*;

public class questions {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int t = scn.nextInt();
        while (t-- > 0) {
            int[] arr = new int[scn.nextInt()];
            for (int i = 0; i < arr.length; i++)
                arr[i] = scn.nextInt();
            System.out.println(LBS_sum(arr));
        }
    }

    // Left to Right
    public static int LIS_LR(int[] arr, int[] dp) {
        int max_ = 0;
        for (int i = 0; i < arr.length; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max_ = Math.max(max_, dp[i]);
        }
        return max_;
    }

    // Right to Left
    public static int LIS_RL(int[] arr, int[] dp) {
        int max_ = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            dp[i] = 1;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max_ = Math.max(max_, dp[i]);
        }
        return max_;
    }

    // https://www.geeksforgeeks.org/longest-bitonic-subsequence-dp-15/
    public static int LBS_(int[] arr) {
        int n = arr.length;
        int[] LIS = new int[n];
        int[] LDS = new int[n];

        LIS_LR(arr, LIS);
        LIS_RL(arr, LDS);

        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            int len = LIS[i] + LDS[i] - 1;
            maxLen = Math.max(maxLen, len);
        }

        return maxLen;
    }

    // Maximum Sum Bitonic subsequnece
    // Left to Right
    public static int LIS_LR_sum(int[] arr, int[] dp) {
        int max_ = 0;
        for (int i = 0; i < arr.length; i++) {
            dp[i] = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
                }
            }
            max_ = Math.max(max_, dp[i]);
        }
        return max_;
    }

    // Right to Left
    public static int LIS_RL_sum(int[] arr, int[] dp) {
        int max_ = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            dp[i] = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
                }
            }
            max_ = Math.max(max_, dp[i]);
        }
        return max_;
    }

    public static int LBS_sum(int[] arr) {
        int n = arr.length;
        int[] LIS = new int[n];
        int[] LDS = new int[n];

        LIS_LR(arr, LIS);
        LIS_RL(arr, LDS);

        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            int len = LIS[i] + LDS[i] - arr[i];
            maxLen = Math.max(maxLen, len);
        }

        return maxLen;
    }

    // leetcode 940
    public static int distinctSubseqII(String str) {

        int mod = (int) 1e9 + 7;
        str = '$' + str;
        int n = str.length();
        long[] dp = new long[n];

        int[] loc = new int[26];
        Arrays.fill(loc, -1);

        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = (2 * dp[i - 1] % mod) % mod;
            int idx = str.charAt(i) - 'a';

            if (loc[idx] != -1) {
                dp[i] = (dp[i] % mod - dp[loc[idx] - 1] % mod + mod) % mod;
            }

            loc[idx] = i;
        }

        dp[n - 1]--;
        return (int) dp[n - 1] % mod;

    }

    // Leetcode 416
    public boolean canPartition(int[] nums) {

        int sum = 0;
        for (int ele : nums) {
            sum += ele;
        }

        if (sum % 2 != 0)
            return false;

        boolean[][] dp = new boolean[nums.length + 1][sum / 2 + 1];
        return targetSum_01_DP(nums, sum / 2, nums.length, dp);
    }

    public static boolean targetSum_01_DP(int[] coins, int tar, int n, boolean[][] dp) {
        int N = n;
        int Tar = tar;

        for (n = 0; n <= N; n++) {
            for (tar = 0; tar <= Tar; tar++) {
                if (tar == 0 || n == 0) {
                    dp[n][tar] = (tar == 0) ? true : false;
                    continue;
                }

                dp[n][tar] = false;
                if (tar - coins[n - 1] >= 0)
                    dp[n][tar] = dp[n][tar] || dp[n - 1][tar - coins[n - 1]];
                dp[n][tar] = dp[n][tar] || dp[n - 1][tar];
            }
        }

        return dp[N][Tar];
    }

    public static int findTargetSumWays_memo(int[] nums, int n, int sum, int tar, int[][] dp) {
        if (n == 0)
            return dp[n][sum] = ((tar == (0 + sum)) ? 1 : 0);

        if (dp[n][sum] != -1)
            return dp[n][sum];

        int include = findTargetSumWays_memo(nums, n - 1, sum - nums[n - 1], tar, dp); // positive call
        int exclude = findTargetSumWays_memo(nums, n - 1, sum + nums[n - 1], tar, dp); // nrgative call

        return dp[n][sum] = include + exclude;
    }

    public static int findTargetSumWays(int[] nums, int s) {
        if (nums.length == 0)
            return 0;

        int n = nums.length;
        int sum = 0;

        for (int i : nums)
            sum += i;
        if (s > sum || s < -sum)
            return 0;

        int[][] dp = new int[n + 1][2 * sum + 1];
        for (int[] a : dp)
            Arrays.fill(a, -1);

        return findTargetSumWays_memo(nums, n, sum, s + sum, dp);
    }
}