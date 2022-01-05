package c.mj.note;

public class LeetCode14 {
    public static void main(String[] args) {
        String[] str = {"aaaa", "aaa", "aa"};
        LeetCode14 leetCode14 = new LeetCode14();

        System.out.println(leetCode14.longestCommonPrefix(str));
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 0; i < strs.length; i++) {
            prefix = lcp(prefix, strs[i]);
            if (prefix.length() == 0) {
                break;
            }
        }
        return prefix;
    }

    private String lcp(String str1, String str2) {
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }
}
