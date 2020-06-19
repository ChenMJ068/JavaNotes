package c.mj.notes.service.impl;

import c.mj.notes.dto.DemoDto;
import c.mj.notes.service.DemoService;
import org.springframework.stereotype.Service;

/**
 * @author chenMJ
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public DemoDto query(String name, String phone) {
        DemoDto demoDto = new DemoDto();
        demoDto.setId(10001);
        demoDto.setName(name);
        demoDto.setAge("23");
        demoDto.setPhone(phone);
        demoDto.setSex("男");
        demoDto.setDesc("开朗活泼，成熟稳重，浑身散发的男性的荷尔蒙");
        return demoDto;
    }
}
