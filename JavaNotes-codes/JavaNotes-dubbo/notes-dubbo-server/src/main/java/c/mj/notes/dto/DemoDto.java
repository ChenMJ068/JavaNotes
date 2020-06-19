package c.mj.notes.dto;

import lombok.Data;

@Data
public class DemoDto {
    private Integer id;
    /**名称*/
    private String name;
    /**手机*/
    private String phone;

    private String age;

    private String sex;

    private String desc;
}
