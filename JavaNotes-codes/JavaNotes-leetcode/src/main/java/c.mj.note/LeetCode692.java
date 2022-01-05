package c.mj.note;

import java.util.*;

public class LeetCode692 {
    public List<String> topKFrequent(String[] words, int key) {
        Map<String, Integer> count = new HashMap<>();
        for (String word : words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }

        List<String> rec = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            rec.add(entry.getKey());
        }

        Collections.sort(rec, (o1, o2) -> count.get(o1).equals(count.get(o2)) ? o1.compareTo(o2) : count.get(o2) - count.get(o1));
        return rec.subList(0, key);
    }
}
