package c.mj.note.ioc.collections;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * create class Stu.java @version 1.0.0 by @author ChenMJ @date 2021-12-23 17:01:00
 */
public class Stu {
    private String[] courses;

    private List<String> list;

    private Map<String ,String> maps;

    private Set<String> sets;

    public void setCourses(String[] courses) {
        this.courses = courses;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setMaps(Map<String, String> maps) {
        this.maps = maps;
    }

    public void setSets(Set<String> sets) {
        this.sets = sets;
    }

    @Override
    public String toString() {
        return "Stu{" +
                "courses=" + Arrays.toString(courses) +
                ", List=" + list +
                ", maps=" + maps +
                ", sets=" + sets +
                '}';
    }
}
