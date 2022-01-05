package c.mj.notes.string;

/**
 * 定义字符串的一些基本操作
 *
 * @author ChenMJ
 * @version SString.class, v 0.1 2020/4/4 20:44 n-cz Exp$
 */
public interface SString {
    /**
     * 字符串长度
     */
    int length();

    /**
     * 返回第i个字符
     */
    char charAt(int i);

    /**
     * 返回当前串与str串连续生成的新串
     */
    SString concat(SString str);

    /**
     * 返回串中字符序号从begin至end-1的子串
     */
    SString subString(int begin, int end);

    /**
     * 设置第i个字符为ch
     */
    void setChar(int i, char ch);

    /**
     * 在第i个字符处插入str串
     */
    SString insert(int i, SString str);

    /**
     * 删除从begin到end-1的子串
     */
    SString delete(int begin, int end);

    /**
     * 返回目标串被配置的首次出现的位置
     */
    int indexOf(SString pattern);
}
