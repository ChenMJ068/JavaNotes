package c.mj.notes.facade.req;

import lombok.Getter;
import lombok.Setter;

/**
 * dubbo 请求对象
 * @author chenMJ
 */
@Setter
@Getter
public class HelloReq {

    /**名称*/
    private String name;
    /**手机*/
    private String phone;

}
