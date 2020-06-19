package c.mj.notes.facade.resp;

import lombok.Data;

/**
 * 返回消息基类
 * @param <T>
 * @author chenMJ
 */

@Data
public class ResultBase<T> {
    private String code;
    private String msg;
    private T date;
}
