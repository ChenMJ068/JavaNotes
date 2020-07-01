package c.mj.notes.demo.facade.impl;

import c.mj.notes.demo.DemoFacade;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @author ChenMJ
 * @version DemoFacadeImpl.class, v 0.1 2020/6/29 14:46  Exp$
 */
@Service
@Component
public class DemoFacadeImpl implements DemoFacade {
    @Override
    public String say(String str) {
        if (StringUtils.isEquals("dubbo",str)){
            return "Yes,I'm dubbo!";
        }else{
            return "Hi,My name is Dubbo";
        }
    }
}
