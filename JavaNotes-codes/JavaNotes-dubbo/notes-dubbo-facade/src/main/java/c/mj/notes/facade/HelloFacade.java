package c.mj.notes.facade;

import c.mj.notes.facade.req.HelloReq;
import c.mj.notes.facade.resp.ResultBase;
import c.mj.notes.facade.resp.HelloResp;

public interface HelloFacade {

    public ResultBase<HelloResp> query(HelloReq helloReq);
}
