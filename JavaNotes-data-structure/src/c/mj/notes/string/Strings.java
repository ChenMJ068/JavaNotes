package c.mj.notes.string;

import java.io.Serializable;

/**
 * 模拟java.lang.String
 * @author ChenMJ
 * @version Strings.class, v 0.1 2020/4/5 10:46 n-cz Exp$
 */
public final class Strings implements Comparable<Strings> , Serializable {

    /**
    字符数组，私有最终变量，只能赋值一次
     */
    private final char[] value;

    /**
    构造一个空串
     */
    public Strings() {
        this.value = new char[0];
    }

    /**
    由字符串常量构造字符串对象，并获取字符串中的字符串数组
     */
    public Strings(String original) {
        this.value = original.toCharArray();
    }

    /**
     * 从begin开始构造count个字符串对象
     * @param value 字符串数组
     * @param begin 字符开始位置
     * @param count 字符个数
     */
    public Strings(char[] value,int begin,int count) {
        this.value = new char[count];
        if(value != null){
            //System.arraycopy(value, begin, this.value, begin, count);
            for (int i = begin; i < begin + count; i++) {
                this.value[i] = value[i];
            }
        }
    }

    /**
     * 以value数组中字符构造串对象
     * @param value 字符串数组
     */
    public Strings(char[] value) {
        this(value,0,value.length);
    }

    public Strings (Strings strings){
        this(strings.value);
    }

    public int length(){
        return value.length;
    }

    public char charAt(int i){
        if (i<0||i>=value.length){
            throw new StringIndexOutOfBoundsException(i);
        }
        return value[i];
    }

    /**
     * 返回指定区间的字符串
     * @param begin 开始下标
     * @param end 结束下标
     * @return 目标字符串的子串
     */
    public Strings subString(int begin,int end){

        if(begin < 0){
            begin = 0;
        }
        if (end > this.value.length){
            end = this.value.length;
        }
        if (begin > end){
            throw  new StringIndexOutOfBoundsException(end-begin);
        }

        if (begin == 0 && end == this.value.length){
            return this;
        }
        char[] buffer = new char[end-begin];

        System.arraycopy(this.value, begin, buffer, 0, buffer.length);
        return new Strings(buffer);
    }

    /**
     * 返回从begin开始到字符串结尾的字符串
     * @param begin 开始位置
     * @return 从begin开始到字符串结尾的字符串
     */
    public Strings subString(int begin){
        return subString(begin,this.value.length);
    }

    /**
     * 字符串链接
     */
    public Strings concat(Strings strings){
        if (strings == null || strings.length() == 0){
            return this;
        }
        char[] buffer = new char[this.value.length+strings.length()];
        int i;
        for ( i = 0; i < this.value.length; i++) {
            buffer[i] = value[i];
        }
        for (int j = 0; j < strings.value.length; j++) {
            buffer[i+j] = strings.value[j];
        }
        return new Strings(buffer);
    }

    /** Brute-Force算法
     * @param pattern 待匹配的模板字符串
     * @param begin 开始位置
     * @return  返回配置位置，匹配失败时返回-1；匹配成功返回匹配的位置
     */
    public int indexOf(Strings pattern,int begin){
        if (pattern != null && pattern.length()>0 && this.length() >= pattern.length()){
            //定义i、j分别为目标字符串和模板字符串的字符下标
            int i = begin,j = 0;
            while (i<length()){
                //若当前两字符相等，则继续比较后续字符
                if (this.charAt(i) == pattern.charAt(j)){
                    i++;
                    j++;
                }else {
                    //否则i、j回溯，进行下一次匹配，目标串下标i退回到下一个待匹配子串首字符，模式串下标退回到0
                    i = i-j+1;
                    j=0;
                }
                //匹配成功时返回匹配到的字符串的下标序号
                if (j == pattern.length()){
                    return i-j;
                }
            }
        }
        return -1;
    }

    public static int indexOf(String target,Strings pattern,int begin){
        if (target != null && pattern != null && pattern.length()>0 && target.length() >= pattern.length()){
            int i = begin,j=0;

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
                next[j] = k;
            }else{
                k = next[k];
            }
        }
        return next;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if (obj instanceof Strings){
            Strings str = (Strings) obj;
            if (value.length == str.value.length){
                for (int i = 0;i < value.length;i++){
                    if (value[i] != str.length()){
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 返回两个字符串长度的差值
     */
    @Override
    public int compareTo(Strings strings) {
        for (int i = 0;i<value.length && i<strings.value.length;i++){
            if (value[i] != strings.value[i]){
                return value[i] - strings.value[i];
            }
        }
        return value.length-strings.value.length;
    }

    /**
     *  Brute-Force算法
     * 返回模板串pattern在当前串中的首次匹配位置
     * @param pattern 待匹配的模板字符串
     * @return 返回配置位置，匹配失败时返回-1
     */
    public int indexOf(Strings pattern){
        return this.indexOf(pattern,0);
    }

    /**
     * 替换第一次出现模板字符串的
     */
    public Strings replace(Strings pattern, Strings replacement){
        int i = indexOf(pattern,0);
        if (i == -1){
            return this;
        }
        return this.subString(0,i).concat(replacement).concat(subString(i+pattern.length()));
    }

    /**
     * 替换所有出现模板字符串的
     */
    public Strings replaceAll(Strings pattern, Strings replacement){
        Strings strings = new Strings(this);
        int i = indexOf(pattern,0);
        while (i != -1){
            strings = strings.subString(0,i).concat(replacement).concat(strings.subString(i+pattern.length()));
            i = strings.indexOf(pattern,i+replacement.length());
        }
        return strings;
    }

    /**
     * 删除当前字符串中第一次出现pattern模板字符串，返回删除后的新字符串
     */
    public Strings del(Strings pattern){
        int i = this.indexOf(pattern);
        if (i == -1){
            return this;
        }
        return this.subString(0,i).concat(this.subString(i+pattern.length()));
    }

    /**
     * 删除target字符串中所有出现pattern模板字符串，返回删除后的新字符串
     */
    public Strings delAll(Strings pattern){
        Strings strings = new Strings(this);
        int i = this.indexOf(pattern,0);
        while (i != -1){
            strings = strings.subString(0,i).concat(strings.subString(i+pattern.length()));
            i = strings.indexOf(pattern,i);
        }
        return strings;
    }

    @Override
    public String toString(){
        return new String(this.value);
    }
}
