package c.mj.notes.demo.client;

import c.mj.notes.demo.DemoFacade;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ChenMJ
 * @version DemoController.class, v 0.1 2020/6/29 14:51  Exp$
 */
@RestController
public class DemoController {

    @Reference
    DemoFacade demoFacade;

    @RequestMapping(value = "/say")
    public String say() {
        String demo = demoFacade.say("dubbo");
        System.out.println(demoFacade.say("Hello,What you name?"));
        return demo;
    }
}
