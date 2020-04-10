# 串
目录：
<!-- TOC -->
- [串](#串)
- [1.串的表示和实现](#串的表示和实现)
    - [1.1 串的顺序存储结构](#串的顺序存储结构)
    - [1.2 串的链式存储结构](#串的链式存储结构)
    - [1.3 串的堆存储结构](#串的堆存储结构)
    - [1.4 JDK中的String](#JDK中的String)
- [2.串的模式匹配](#串的模式匹配)
    - [2.1 Brute-Force算法](#Brute-Force算法)
        - [2.1.1 Brute-Force算法描述与实现](#Brute-Force算法描述与实现)
        - [2.1.2 模式匹配应用](#模式匹配应用)
        - [2.1.3 Brute-Force算法分析](#Brute-Force算法分析)
    - [2.2 KMP算法](#KMP算法)
        - [2.2.1 目标串不回溯](#目标串不回溯)
        - [2.2.2 KMP算法描述](#KMP算法描述)
        - [2.2.3 KMP算法之求next数组](#KMP算法之求next数组)
        - [2.2.4 KMP算法实现](#KMP算法实现)
        - [2.2.5 KMP算法分析](#KMP算法分析)
<!-- /MarkdownTOC -->

## 1.串的表示和实现
java.lang.String是java程序员工作中必用的数据类型。字符串是由0或0个以上字符组成的有限序列，
从逻辑结构上来分析，字符串是一种特殊的线性表，特殊的原因是在线性表中的每个元素都是一个字符。字符串也有序号，长度等线性表的属性。  
定义字符串的抽象数据类型：
```java
public interface SString {
    /**字符串长度*/
    int length();
    /**返回第i个字符*/
    char charAt(int i);
    /**返回当前串与str串连续生成的新串*/
    SString concat(SString str);
    /**返回串中字符序号从begin至end-1的子串*/
    SString subString(int begin,int end);
    /**设置第i个字符为ch*/
    void setChar(int i,char ch);
    /**在第i个字符处插入str串*/
    SString insert(int i,SString str);
    /**删除从begin到end-1的子串*/
    SString delete(int begin,int end);
    /**返回目标串被配置的首次出现的位置*/
    int indexOf(SString pattern);
}
```
### 1.1 串的顺序存储结构
串的顺序存储结构采用字符数组将串中的字符序列依次连续存储在数组的相邻单元中。使用字符串数组有两种方案：数组容量等于或者大于串的长度。

数组容量等于串的长度时不易增加字符长度，通常用于存储串的常量；数组容量大于串长度时，便于增加字符，常用于存储串变量。

顺序存储的串具有随机存取功能，时间复杂度为O(1);插入和删除元素时需要移动元素，平均移动数据量时串长度的一半，当数组容量不够时，
需要重新申请一个更大的数组，并复制原数组中的所有元素。插入和删除操作的时间复杂度为O(n)。

### 1.2 串的链式存储结构
串的链式存储结构有单字符链表和块链表两种。单字符链表是每个结点的结点的数据域只包含一个字符的单链表，
块链表是每个结点的数据域包含若干字符的单链表。链式存储结构的串，存取指定位置字符的时间复杂度为O(n)。
单字符链表占用存储空间多，浪费空间；块链表效率低。

### 1.3 串的堆存储结构
串的顺序存储和链表存储各有利弊，在实际应用中常采用一种动态存储结构，称其为堆结构。定义一个很大的连续空间和相应的指针结构。  
指针用来指示串在堆中的位置；例如:

```
a='BEI'，b='JING'，c=''，d=S'HANGHAI'；
```
### 1.4 JDK中的String
java.lang中提供String、StringBuffer、StringBuilder方法。  
请参考博文：
[String,StringBuffer与StringBuilder的区别](https://blog.csdn.net/qq_36814756/article/details/84137257) 

## 2.串的模式匹配
串的模式匹配就是假定有目标匹配串target和模式串pattern，在目标target串中查找与模式串pattern相等的一个子串并确定该子串位置的操作。
### 2.1 Brute-Force算法

#### 2.1.1 Brute-Force算法描述与实现
Brute-Force算法匹配是回溯算法。  
假设目标串target="t<sub>0</sub>t<sub>1</sub>...t<sub>n-1</sub>",模式串pattern="p<sub>0</sub>p<sub>1</sub>...p<sub>m-1</sub>",0<m<=n,
Brute-Force算法每次匹配将从目标中从*t<sub>i</sub>(0<=i<=n-m)* 开始、长度为m的子串"t<sub>i</sub>t<sub>i+1</sub>...t<sub>i+m-1</sub>"与模式串进行比较，
如果相等则匹配成功，i即是模式串在目标中的匹配序号；否则一次匹配失败，继续比较目标串的下一个子串"t<sub>i+1</sub>t<sub>i+2</sub>...t<sub>i+m</sub>"，
如果匹配失败依次类推，每次都匹配模式串长度m个字符。即目标串中所有长度为m的子串都与模式串匹配过，这样保证不丢失任何匹配的可能。

![Brute-Force算法匹配描述](../../../docs/img/java/dataStructure/brute-Force算法描述.png)

在Strings.calss增加代码实现：
```java
public class Strings{
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
}
```
#### 2.1.2 模式匹配应用
在目标字符串target中进行替换或者删除操作时，由于不知道target字符串是否包含与模板字符串pattern匹配的子串以及子串的位置，必须先执行模式匹配算法，在做其他操作。

1.替换操作  
```java
public class Strings{
    
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
        return replacement;
    }
}
```
2.删除操作
```java
public class Strings{
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
}

```

#### 2.1.3 Brute-Force算法分析
从Strings.class的indexOf()方法上看，这个方法简单，但是时间效率不高。模式匹配操作花费的时间主要用于比较字符。
最好的情况下，第一次匹配成功，模板字符串刚好与目标字符串的"t<sub>0</sub>t<sub>1</sub>……t<sub>m-1</sub>"子串匹配成功。
此时模板字符串的长度m，时间复杂度为O(m)。最坏情况下，从头到尾一次都匹配不上，每次都比较了模板字符串的m次，所以时间复杂度为O(m*n)。
### 2.2 KMP算法
#### 2.2.1 目标串不回溯
Brute-Force算法目标字符串存在回溯，两个字符串逐个比较字符，若t<sub>i</sub> != p<sub>j</sub>(0<=i<n,0<=j<m),则下次匹配目标字符串回退
从t<sub>i-j+1</sub>开始与模式串p<sub>0</sub>比较。但是，目标字符串的回溯是不必要的，t<sub>i-j+1</sub>与p<sub>0</sub>的比较结果可由前一次匹配结果得到。
#### 2.2.2 KMP算法描述
KMP算法是一种无回溯的模式匹配算法，改进了Brute-Force算法，改进之处主要体现在以下两点：  
1.目标串不回溯。一旦比较不等，t<sub>i</sub>!=p<sub>j</sub>,下次匹配目标串继续从t<sub>i</sub>开始比较；  
2.模式串每次匹配从p<sub>k</sub>(0<=k<j)开始比较,对于每个p<sub>j</sub>，k的取值不同。因此，如何求得这个k，就是KMP算法的核心内容。

设目标串target="abdabcabbaabc",模式串pattern="abcd",其中n=target.length(),m=pattern.length(),0<m<=n,0<=i<n,0<=j<m,0<=k<j。
目标串target下标为"t<sub>0</sub>t<sub>1</sub>…t<sub>n-1</sub>",模式串pattern下标为"p<sub>0</sub>p<sub>1</sub>…p<sub>m-1</sub>"。  
根据目标串和模式串，KMP算法描述如下：  
1.依次比较t<sub>i</sub>与p<sub>j</sub>(0<=i<n,0<=j<m)。如果t<sub>i</sub>=p<sub>j</sub>，则继续比较t<sub>i+1</sub>与p<sub>j+1</sub>，
直到"t<sub>0</sub>t<sub>1</sub>…t<sub>n-1</sub>"与"p<sub>0</sub>p<sub>1</sub>…p<sub>m-1</sub>"匹配成功。  
2.如果t<sub>i</sub>!=p<sub>0</sub>,则继续比较t<sub>i+1</sub>与p<sub>0</sub>  
3.如果t<sub>i</sub>!=p<sub>j</sub>(0<j<m),有t<sub>i-j</sub>t<sub>i-j+1</sub>…t<sub>i-1</sub>"="p<sub>0</sub>p<sub>1</sub>…p<sub>j-1</sub>";
如果"p<sub>0</sub>p<sub>1</sub>…p<sub>j-1</sub>"串中存在相同的前缀子串"p<sub>0</sub>…p<sub>k-1</sub>(0<=k<j)"和后缀子串"p<sub>0</sub>…p<sub>k-1</sub>"，
即："p<sub>0</sub>…p<sub>k-1</sub>"="p<sub>j-k</sub>…p<sub>j-1</sub>",
"p<sub>0</sub>p<sub>1</sub>…p<sub>k-1</sub>"="p<sub>j-k</sub>…p<sub>j-1</sub>"="t<sub>i-k</sub>=p<sub>i-1</sub>",
则接着模式串只需从p<sub>k</sub>开始继续与t<sub>i</sub>比较。
到此，问题转化为对模式串中每一个字符p<sub>j</sub>,找出p<sub>0</sub>p<sub>1</sub>…p<sub>j-1</sub>"串中相同的最长前缀子串和后缀子串的长度k，k取值只与模式串有关，与目标串无关。

![KMP算法匹配描述](../../../docs/img/java/dataStructure/kmp算法描述.png)

#### 2.2.3 KMP算法之求next数组
next数组的值是代表着模式字符串的前缀与后缀相同的最大长度，next[j]值定义为:  
$$
next[j]=\begin{cases}-1&当j=0时\\k&当0<=k<j时且使"p_0…p_{k-1}"="p_{j-k}…p_{j-1}"\end{cases}\
$$



KMP算法充分利用前一次匹配的比较结果，由next[j]值逐个递推计算得到next[j+1]。  
1.约定next[0] = -1，-1表示下次匹配从t<sub>i+1</sub>与p<sub>0</sub>开始比较；有next[1] = 0。  
2.对模式串当前字符序号j(0<=j<m),有有next[j] = k,说明在"p<sub>0</sub>p<sub>1</sub>…p<sub>j-1</sub>"串中存在长度为k的相同的前缀子串和后缀子串，
即"p<sub>0</sub>p<sub>1</sub>…p<sub>j-1</sub>"="p<sub>j-k</sub>…p<sub>j-1</sub>",0<=k<j且k取最大值。  
3.对next[j+1]而言，在"p<sub>0</sub>p<sub>1</sub>…p<sub>j-1</sub>"串中，如果"p<sub>0</sub>…p<sub>k-1</sub>p<sub>k</sub>"="p<sub>j-k</sub>…p<sub>j-1</sub>p<sub>j</sub>",
则存在相同的前缀子串和后缀子串，而判断前缀子串"p<sub>0</sub>…p<sub>k-1</sub>p<sub>k</sub>"与后缀子串"p<sub>j-k</sub>…p<sub>j-1</sub>p<sub>j</sub>"是否相同又是一个模式匹配问题。
此时，已知"p<sub>0</sub>…p<sub>k-1</sub>p<sub>k</sub>"="p<sub>j-k</sub>…p<sub>j-1</sub>p<sub>j</sub>"，所以只需要比较p<sub>k</sub>与p<sub>j</sub>即可。
如果p<sub>k</sub>=p<sub>j</sub>，则next[j+1]=k+1=next[j]+1.意味着，增加一次字符比较，即可确定两个子串是否匹配。  
4.如果p<sub>k</sub>！=p<sub>j</sub>，在"p<sub>0</sub>p<sub>1</sub>…p<sub>j</sub>"串中继续寻找较短的相同前缀子串，较短前后子串长度为next[k]，则k=next[k]，
在比较p<sub>j</sub>与p<sub>k</sub>,继续执行第3第4步。

计算next数组的getNext()代码如下：
```java
public class Strings{
    private static int[] getNext(String pattern){
        int j = 0,k = -1;
        int[] next = new int[pattern.length()];
        next[0] = -1;
        while (j<pattern.length()-1){
            if (k == -1 || pattern.charAt(j) == pattern.charAt(k)){
                j++;
                k++;
                next[j] = k; //有待改进
            }else{
                k = next[k]; 
            }
        }
        return next;
    }
}
```
上面KMP算法描述中，当t<sub>i</sub>!=p<sub>j</sub>时，下次匹配模式串从p<sub>k=next[j]</sub>开始比较。此时，p<sub>k</sub>=p<sub>j</sub>，可知t<sub>i</sub>!=p<sub>k</sub>,
则下次匹配模式串从p<sub>next[k]</sub>开始比较。显然next[k]<next[j],next[j]越小，模式串向右移动的距离越远，比较次数也就越少。

所以，要改就求next数据，减少一些不必要的比较。假设next[j] = k,若p<sub>k</sub>=p<sub>j</sub>,则next[k]=next[j]。代码改动如下：
```java
public class Strings{

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
```
#### 2.2.4 KMP算法实现
```java
public class Strings{
    public static int indexOfByKMP(String target,String pattern,int begin){
            if (target != null && pattern != null && pattern.length()>0 && target.length() >= pattern.length()){
                int i = begin,j=0;
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
}
```
#### 2.2.5 KMP算法分析
kmp算法的最多比较次数为目标串的长度n与模式串的长度m之和，所以时间复杂度为O(n).


参考博文:  
[KMP的next数组求法详解](https://blog.csdn.net/yutong5818/article/details/81319120)  
[KMP 算法详解](https://zhuanlan.zhihu.com/p/83334559)  
[字符串匹配的KMP算法](http://www.ruanyifeng.com/blog/2013/05/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm.html)