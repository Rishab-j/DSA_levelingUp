import java.util.ArrayList;
import java.util.HashSet;
import java.util.Arrays;
import java.util.List;

public class questions {

    // Leetcode 46

    List<List<Integer>> res1 = new ArrayList<>();
    List<Integer> smallAns1 = new ArrayList<>();

    public void permute_(int[] arr, int count, boolean[] vis) {
        if (count == arr.length) {
            ArrayList<Integer> smallRes = new ArrayList<>(smallAns1);
            res1.add(smallRes);
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (!vis[i]) {
                vis[i] = true;
                smallAns1.add(arr[i]);
                permute_(arr, count + 1, vis);
                smallAns1.remove(smallAns1.size() - 1);
                vis[i] = false;
            }
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        boolean[] vis = new boolean[nums.length];
        permute_(nums, 0, vis);
        return res1;
    }

    // Leetcode 47

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> smallAns = new ArrayList<>();

    public void permuteUnique(int[] nums, int count, boolean[] vis) {
        if (count == nums.length) {
            ArrayList<Integer> smallRes = new ArrayList<>(smallAns);
            res.add(smallRes);
            return;
        }

        HashSet<Integer> isUsed = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (!vis[i] && !isUsed.contains(nums[i])) {

                isUsed.add(nums[i]);
                vis[i] = true;
                smallAns.add(nums[i]);

                permuteUnique(nums, count + 1, vis);

                smallAns.remove(smallAns.size() - 1);
                vis[i] = false;
            }
        }
    }

    public void permuteUnique_02(int[] nums, int count, boolean[] vis) {
        if (count == nums.length) {
            ArrayList<Integer> smallRes = new ArrayList<>(smallAns);
            res.add(smallRes);
            return;
        }

        int prev = (int) 1e8;
        for (int i = 0; i < nums.length; i++) {
            if (!vis[i] && prev != nums[i]) {

                vis[i] = true;
                smallAns.add(nums[i]);

                permuteUnique_02(nums, count + 1, vis);

                smallAns.remove(smallAns.size() - 1);
                vis[i] = false;

                prev = nums[i];
            }
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        boolean[] vis = new boolean[nums.length];
        permuteUnique(nums, 0, vis);
        return res;
    }

    // Leetcode 980

    public static int[][] dir = { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };

    public static int floodFill(int sr, int sc, int[][] grid, int f) {

        if (grid[sr][sc] == 2)
            return f == 1 ? 1 : 0;

        int count = 0;

        int temp = grid[sr][sc];
        grid[sr][sc] = -1;

        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] != -1) {
                count += floodFill(r, c, grid, f - 1);
            }
        }
        grid[sr][sc] = temp;
        return count;
    }

    public int uniquePathsIII(int[][] grid) {

        int sr = 0;
        int sc = 0;
        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                if (grid[i][j] != -1)
                    count++;

                if (grid[i][j] == 1) {
                    sr = i;
                    sc = j;
                }
            }
        }

        return floodFill(sr, sc, grid, count);

    }
}