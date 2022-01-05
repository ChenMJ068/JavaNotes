package c.mj.notes.string.impl;

import c.mj.notes.string.Strings;

/**
 * @author ChenMJ
 * @version StringsMain.class, v 0.1 2020/4/6 13:22 n-cz Exp$
 */
public class StringsMain {

    public static void main(String[] args) {
        Strings strings = new Strings("abcdabcddefgabcghabijabcmkdabgdcabc");
        System.out.println("method concat:" + strings.concat(new Strings("测试")).toString());

        System.out.println("method replace:" + strings.replace(new Strings("abc"), new Strings("ABC")).toString());

        System.out.println("method replaceAll:" + strings.replaceAll(new Strings("abc"), new Strings("ABC")).toString());

        System.out.println("method del:" + strings.del(new Strings("abc")).toString());

        System.out.println("method delAll:" + strings.delAll(new Strings("abc")).toString());
    }
}
