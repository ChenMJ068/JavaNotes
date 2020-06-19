package c.mj.notes.facade.impl;

import c.mj.notes.dto.DemoDto;
import c.mj.notes.facade.HelloFacade;
import c.mj.notes.facade.req.HelloReq;
import c.mj.notes.facade.resp.HelloResp;
import c.mj.notes.facade.resp.ResultBase;
import c.mj.notes.service.DemoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author chenMJ
 */
public class HelloFacadeImpl implements HelloFacade {

    @Autowired
    DemoService demoService;

    @Override
    public ResultBase<HelloResp> query(HelloReq helloReq) {
        ResultBase<HelloResp> resultBase = new  ResultBase<HelloResp>();
        DemoDto demo = demoService.query(helloReq.getName(),helloReq.getPhone());
        HelloResp helloResp = new HelloResp();
        BeanUtils.copyProperties(demo,helloResp);

        resultBase.setCode("0000");
        resultBase.setMsg("成功");
        resultBase.setDate(helloResp);
        return resultBase;
    }
}
