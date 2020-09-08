public class practice {

    public static int permutationNotDuplicate(String s, String ans, boolean[] vis) {  // doesn't work

        if (s.length() == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!vis[ch - 'a']) {
                vis[ch - 'a'] = true;
                String str = s.substring(0,i) + s.substring(i+1);
                vis[ch - 'a'] = false;
                count += permutationNotDuplicate(str, ans + ch, vis);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(permutationNotDuplicate("aba", "", new boolean[26]));
    }
}