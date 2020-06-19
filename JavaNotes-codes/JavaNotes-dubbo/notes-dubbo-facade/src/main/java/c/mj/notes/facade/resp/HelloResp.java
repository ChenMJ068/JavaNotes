package c.mj.notes.facade.resp;

import lombok.Getter;
import lombok.Setter;

/**
 * dubbo 返回对象
 * @author chenMJ
 */
@Getter
@Setter
public class HelloResp {

    private Integer id;
    /**名称*/
    private String name;
    /**手机*/
    private String phone;

    private String age;

    private String sex;

    private String desc;
}
