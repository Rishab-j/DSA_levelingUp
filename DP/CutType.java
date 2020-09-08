import java.util.Arrays;

public class CutType {

    public static void display(final int[] dp) {
        for (final int ele : dp)
            System.out.print(ele + " ");
        System.out.println();
    }

    public static void display2D(final int[][] dp) {
        for (final int[] ar : dp) {
            display(ar);
        }
        System.out.println();
    }

    public static int MCM_Rec(final int[] arr, final int si, final int ei, final int[][] dp) {
        if (si + 1 == ei)
            return dp[si][ei] = 0;
        if (dp[si][ei] != 0)
            return dp[si][ei];

        int myAns = (int) 1e8;
        for (int cut = si + 1; cut < ei; cut++) {
            final int leftRes = MCM_Rec(arr, si, cut, dp);
            final int rightRes = MCM_Rec(arr, cut, ei, dp);

            final int recRes = leftRes + arr[si] * arr[cut] * arr[ei] + rightRes;
            if (recRes < myAns)
                myAns = recRes;
        }

        return dp[si][ei] = myAns;
    }

    public static int MCM_DP(final int[] arr, int si, int ei, final int[][] dp) {

        for (int gap = 1; gap < arr.length; gap++) {
            for (si = 0, ei = gap; ei < arr.length; si++, ei++) {
                if (si + 1 == ei) {
                    dp[si][ei] = 0;
                    continue;
                }
                int myAns = (int) 1e8;
                for (int cut = si + 1; cut < ei; cut++) {
                    final int leftRes = dp[si][cut];// MCM_Rec(arr,si,cut,dp);
                    final int rightRes = dp[cut][ei];// MCM_Rec(arr,cut,ei,dp);

                    final int recRes = leftRes + arr[si] * arr[cut] * arr[ei] + rightRes;
                    if (recRes < myAns)
                        myAns = recRes;
                }

                dp[si][ei] = myAns;
            }
        }

        return dp[0][arr.length - 1];
    }

    public static int MCM_Rec_02(final int[] arr, final int si, final int ei, final int[][] dp, final String[][] sdp) {
        if (si + 1 == ei) {
            sdp[si][ei] = ((char) (si + 'A') + "");
            return dp[si][ei] = 0;
        }

        if (dp[si][ei] != 0)
            return dp[si][ei];

        dp[si][ei] = (int) 1e8;
        for (int cut = si + 1; cut < ei; cut++) {
            final int leftRes = MCM_Rec_02(arr, si, cut, dp, sdp);
            final int rightRes = MCM_Rec_02(arr, cut, ei, dp, sdp);

            final int recRes = leftRes + arr[si] * arr[cut] * arr[ei] + rightRes;
            if (recRes < dp[si][ei]) {
                sdp[si][ei] = "(" + sdp[si][cut] + sdp[cut][ei] + ")";
                dp[si][ei] = recRes;
            }
        }
        return dp[si][ei];
    }

    public static void MCM() {
        final int[] arr = { 10, 20, 30, 40, 30 };
        final int n = arr.length;

        final int[][] dp = new int[n][n];
        final String[][] sdp = new String[n][n];

        // System.out.println(MCM_Rec(arr,0,n-1,dp));
        System.out.println(MCM_DP(arr, 0, n - 1, dp));
        // System.out.println(MCM_Rec_02(arr,0,n-1,dp,sdp));

        System.out.println(sdp[0][n - 1]);
        display2D(dp);
        for (final String[] s : sdp) {
            for (final String e : s)
                System.out.print(e + " ");
            System.out.println();
        }
    }

    // OBST.======================================================

    public static int summation(int si, final int ei, final int[] freq) {
        int sum = 0;
        while (si <= ei)
            sum += freq[si++];
        return sum;
    }

    public static int OBST_Rec(final int[] freq, final int si, final int ei, final int[][] dp, final int[] prefixSum) {
        if (dp[si][ei] != 0)
            return dp[si][ei];

        int myAns = (int) 1e8;
        for (int cut = si; cut <= ei; cut++) {
            final int leftRes = cut == si ? 0 : OBST_Rec(freq, si, cut - 1, dp, prefixSum);
            final int rightRes = cut == ei ? 0 : OBST_Rec(freq, cut + 1, ei, dp, prefixSum);

            // int recRes = leftRes + summation(si, ei, freq) + rightRes;
            final int recRes = leftRes + (prefixSum[ei] - (si == 0 ? 0 : prefixSum[si - 1])) + rightRes;

            if (recRes < myAns)
                myAns = recRes;
        }

        return dp[si][ei] = myAns;
    }

    public static int maxCoins(final int[] nums, final int si, final int ei, final int[][] dp) {
        if (dp[si][ei] != 0)
            return dp[si][ei];

        final int lval = si == 0 ? 1 : nums[si - 1];
        final int rval = ei == nums.length - 1 ? 1 : nums[ei + 1];

        int maxAns = 0;
        for (int cut = si; cut <= ei; cut++) {
            final int leftRes = cut == si ? 0 : maxCoins(nums, si, cut - 1, dp);
            final int rightRes = cut == ei ? 0 : maxCoins(nums, cut + 1, ei, dp);

            final int myAns = leftRes + lval * nums[cut] * rval + rightRes;
            maxAns = Math.max(maxAns, myAns);
        }

        return dp[si][ei] = maxAns;
    }

    public static int maxCoinsDP(final int[] nums, int si, int ei, final int[][] dp) {

        for (int gap = 1; gap < nums.length; gap++) {
            for (si = 0, ei = gap; ei < nums.length; si++, ei++) {
                final int lval = si == 0 ? 1 : nums[si - 1];
                final int rval = ei == nums.length - 1 ? 1 : nums[ei + 1];

                int maxAns = 0;
                for (int cut = si; cut <= ei; cut++) {
                    final int leftRes = cut == si ? 0 : dp[si][cut - 1];// maxCoins(nums,si,cut - 1,dp);
                    final int rightRes = cut == ei ? 0 : dp[cut + 1][ei];// maxCoins(nums,cut + 1, ei, dp);

                    final int myAns = leftRes + lval * nums[cut] * rval + rightRes;
                    maxAns = Math.max(maxAns, myAns);
                }

                dp[si][ei] = maxAns;
            }
        }

        return dp[0][nums.length - 1];

    }

    public static class pair {

        int min = (int) 1e8;
        int max = (int) -1e8;
        String minStr = "";
        String maxStr = "";

        pair(final int min, final int max, final String minStr, final String maxStr) {
            this.min = min;
            this.max = max;

            this.minStr = minStr;
            this.maxStr = maxStr;
        }

        pair() {
        }

        @Override
        public String toString() {
            return "(" + min + ", " + max + ")";
        }
    }

    public static int evaluateExpression(final int a, final int b, final char oper) {
        if (oper == '+')
            return a + b;
        else
            return a * b;

    }

    public static pair minMaxEval(final String str, final int si, final int ei, final pair[][] dp) {
        if (si == ei) {
            final char ch = str.charAt(si);
            final int val = ch - '0';
            return new pair(val, val, val + "", val + "");
        }

        if (dp[si][ei] != null)
            return dp[si][ei];

        final pair Ans = new pair();
        for (int cut = si + 1; cut < ei; cut += 2) {

            // left and right ki call
            final pair leftRes = minMaxEval(str, si, cut - 1, dp);
            final pair rightRes = minMaxEval(str, cut + 1, ei, dp);

            // evaluation of left and right
            final int minVal = evaluateExpression(leftRes.min, rightRes.min, str.charAt(cut));
            final int maxVal = evaluateExpression(leftRes.max, rightRes.max, str.charAt(cut));

            // comparison
            if (minVal < Ans.min) {
                Ans.min = minVal;
                Ans.minStr = "(" + leftRes.minStr + str.charAt(cut) + rightRes.minStr + ")";
            }

            if (maxVal > Ans.max) {
                Ans.max = maxVal;
                Ans.maxStr = "(" + leftRes.maxStr + str.charAt(cut) + rightRes.maxStr + ")";
            }
        }

        return dp[si][ei] = Ans;

    }

    public static int minPlaindromicCut(final String str, final int si, final int ei, final int[][] dp, final boolean[][] palindromicSubstring) {
        if (palindromicSubstring[si][ei])
            return 0;
        if (dp[si][ei] != -1)
            return dp[si][ei];
        int minCut = (int) 1e8;

        for (int cut = si; cut < ei; cut++) {
            final int leftRes = minPlaindromicCut(str, si, cut, dp, palindromicSubstring);
            final int rightRes = minPlaindromicCut(str, cut + 1, ei, dp, palindromicSubstring);

            final int myAns = leftRes + 1 + rightRes;
            minCut = Math.min(minCut, myAns);
        }

        return dp[si][ei] = minCut;
    }

    public static int minPlaindromicCut_bestMethod(final String str, final int si, final int ei, final int[] dp,
            final boolean[][] palindromicSubstring) {

        if (palindromicSubstring[si][ei])
            return 0;
        if (dp[si] != -1)
            return dp[si];

        int minCut = (int) 1e8;
        for (int cut = si; cut < ei; cut++) {
            if (palindromicSubstring[si][cut]) {
                minCut = Math.min(minCut, minPlaindromicCut_bestMethod(str, cut + 1, ei, dp, palindromicSubstring) + 1);
            }
        }

        for (final int ele : dp)
            System.out.print(ele + " , ");
        System.out.println();

        return dp[si] = minCut;
    }

    // public static int minPlaindromicCut_bestMethod_DP(String str, int si, int ei,
    // int[] dp,
    // boolean[][] palindromicSubstring) {

    // for (si = ei - 1; si >= 0; si--) {

    // if (palindromicSubstring[si][ei])
    // {dp[si]=0; continue; }

    // int minCut = (int) 1e8;
    // for (int cut = si; cut < ei; cut++) {
    // if (palindromicSubstring[si][cut]) {
    // minCut = Math.min(minCut,
    // dp[cut+1]+ 1);
    // }
    // }
    // dp[si] = minCut;
    // }
    // return dp[cut];
    // }

    public static int minCut(final String str) {
        final int n = str.length();
        final int[][] dp = new int[n][n];
        final int[] dp1 = new int[n];
        Arrays.fill(dp1, -1);
        final boolean[][] isPalindrome = new boolean[n][n];

        for (final int[] d : dp)
            Arrays.fill(d, -1);

        for (int gap = 0; gap < n; gap++) {
            for (int si = 0, ei = gap; ei < n; si++, ei++) {
                if (gap == 0)
                    isPalindrome[si][ei] = true;
                else if (str.charAt(si) == str.charAt(ei) && gap == 1)
                    isPalindrome[si][ei] = true;
                else
                    isPalindrome[si][ei] = (str.charAt(si) == str.charAt(ei)) && isPalindrome[si + 1][ei - 1];
            }
        }

        // return minPlaindromicCut(str, 0, n - 1, dp, isPalindrome);
        System.out.println();
        return minPlaindromicCut_bestMethod(str, 0, n - 1, dp1, isPalindrome);
    }

    public static int minTriangulation(final int[] arr, final int si, final int ei, final int[][] dp) {
        if (ei - si < 2)
            return 0;
        if (dp[si][ei] != 0)
            return dp[si][ei];

        int minAns = (int) 1e8;
        for (int cut = si + 1; cut < ei; cut++) {
            final int leftAns = minTriangulation(arr, si, cut, dp);
            final int rightAns = minTriangulation(arr, cut, ei, dp);

            final int myAns = leftAns + arr[si] * arr[cut] * arr[ei] + rightAns;
            minAns = Math.min(minAns, myAns);
        }

        return dp[si][ei] = minAns;
    }

    //***************************************************************************************** */

    // THIS IS NOT A CUTTYPE QUESTION

    public static int wildCardMatching(final String str1, final String str2, final int n, final int m, final int[][] dp) {
        if (n == 0 || m == 0) {
            if (n == 0 && m == 0)
                return dp[n][m] = 1;
            else if (m == 0)
                return dp[n][m] = 0;
            else {
                return dp[n][m] = (str2.charAt(m - 1) == '*' && (m - 1 == 0)) ? 1 : 0;
            }
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        if (str1.charAt(n - 1) == str2.charAt(m - 1))
            return dp[n][m] = wildCardMatching(str1, str2, n - 1, m - 1, dp);
        else if (str2.charAt(m - 1) == '?')
            return dp[n][m] = wildCardMatching(str1, str2, n - 1, m - 1, dp);
        else if (str2.charAt(m - 1) == '*') {
            final boolean emptyMatching = wildCardMatching(str1, str2, n, m - 1, dp) == 1;
            final boolean sequenceMatching = wildCardMatching(str1, str2, n - 1, m, dp) == 1;

            return dp[n][m] = (emptyMatching || sequenceMatching) ? 1 : 0;
        } else
            return dp[n][m] = 0;
    }

    public static String removeStar(final String p) {
        final StringBuilder str = new StringBuilder();
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*') {
                str.append('*');
                while (i < p.length() && p.charAt(i) == '*') {
                    i++;
                }
                i--;
            } else {
                str.append(p.charAt(i));
            }
        }
        return str.toString();
    }

    public static boolean isMatch(final String s, String p) {

        p = removeStar(p);
        final int[][] dp = new int[s.length() + 1][p.length() + 1];
        for (final int[] d : dp)
            Arrays.fill(d, -1);
        return wildCardMatching(s, p, s.length(), p.length(), dp) == 1;

    }

    public int minScoreTriangulation(final int[] A) {
        final int n = A.length;
        final int[][] dp = new int[n][n];
        return minTriangulation(A, 0, n - 1, dp);
    }

    public static void minMaxEval() {
        final String str = "1+2*3+4*5";
        final int n = str.length();
        final pair[][] dp = new pair[n][n];

        final pair ans = minMaxEval(str, 0, n - 1, dp);
        System.out.println(ans.minStr + " -> " + ans.min);
        System.out.println(ans.maxStr + " -> " + ans.max);

        // for(pair[] d : dp){
        // for(pair e : d){
        // System.out.print(e + " ");
        // }
        // System.out.println();
        // }
    }

    public static int maxCoins(final int[] nums) {
        final int n = nums.length;
        if (n == 0)
            return 0;
        final int[][] dp = new int[n][n];

        return maxCoins(nums, 0, n - 1, dp);
    }

    public static void OBST() {
        final int[] keys = { 10, 12, 20 };
        final int[] freq = { 34, 8, 50 };

        final int[] prf = new int[freq.length];

        for (int i = 0; i < prf.length; i++) {
            if (i == 0)
                prf[i] = freq[i];
            else {
                prf[i] = prf[i - 1] + freq[i];
            }
        }

        for (int i = 0; i < prf.length; i++) {
            System.out.println(prf[i]);
        }

        final int ans = OBST_Rec(freq, 0, 2, new int[3][3], prf);

        System.out.println(ans);
    }

    public static void main(final String[] args) {
        // MCM();
        // minMaxEval();
        // OBST();
        System.out.println(minCut("abbbacccdef"));
    }

}