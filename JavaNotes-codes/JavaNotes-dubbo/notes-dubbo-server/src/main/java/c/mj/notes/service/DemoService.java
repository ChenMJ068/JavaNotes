package c.mj.notes.service;

import c.mj.notes.dto.DemoDto;

public interface DemoService {
    DemoDto query(String name,String phone);
}
