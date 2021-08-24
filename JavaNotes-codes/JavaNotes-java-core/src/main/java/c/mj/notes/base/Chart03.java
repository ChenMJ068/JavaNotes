package c.mj.notes.base;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Chart03 {
    public static void main(String[] args) {
        System.out.println(strStr("",""));
    }

    public static int strStr(String target, String pattern) {

        if (target == "" || pattern == ""){
            return 0;
        }

        if (pattern.length()>0 && target.length() >= pattern.length()){
            int i = 0,j=0;
            int[] next = getNext(pattern);
            while (i<target.length()){
                if (j == -1 || target.charAt(i) == pattern.charAt(j)){
                    i++;
                    j++;
                }else{
                    j = next[j];
                }
                if (j == pattern.length()){
                    return i-j;
                }
            }
        }
        return -1;

    }
    private static int[] getNext(String pattern){
        int j = 0,k = -1;
        int[] next = new int[pattern.length()];
        next[0] = -1;
        while (j<pattern.length()-1){
            if (k == -1 || pattern.charAt(j) == pattern.charAt(k)){
                j++;
                k++;
                //改进之后
                if (pattern.charAt(j) != pattern.charAt(k)){
                    next[j] = k;
                }else {
                    next[j] = next[k];
                }
            }else{
                k = next[k];
            }
        }
        return next;
    }
}



