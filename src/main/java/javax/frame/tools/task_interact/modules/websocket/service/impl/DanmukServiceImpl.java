package javax.frame.tools.task_interact.modules.websocket.service.impl;

import com.sun.org.glassfish.external.statistics.annotations.Reset;
import org.example.h2.entity.Danmuk;
import org.example.h2.enums.DanmukEnum;
import org.example.h2.util.DanmukUtil;
import org.springframework.stereotype.Service;

import javax.frame.tools.task_interact.modules.websocket.model.ResetData;
import javax.frame.tools.task_interact.modules.websocket.service.DanmukService;
import java.io.File;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class DanmukServiceImpl implements DanmukService {

    //String basePath = "C:/workspace-example/websocket/target/websocket-1.0-SNAPSHOT/20221105";
    String basePath = "D:/tools/upload/upload";

    // 缓存溢出数据，优先从缓存中获取
    Map<String, List<Danmuk>> cache = new HashMap<>();

    // 数据获取计数
    Map<String, AtomicInteger> counter = new HashMap<>();

    Danmuk danmuk = new Danmuk().setDanmuk("无消息").setId("1L");

    @Override
    public ResetData reset(ResetData resetData) {
        String roomId = resetData.getRoomId();
        int initialValue = resetData.getBase() > 0 ? resetData.getBase() : 0;
        if (cache.keySet().contains(roomId)) {
            List<Danmuk> list = new ArrayList<>();
            AtomicInteger ai = new AtomicInteger(initialValue);
            cache.put(roomId, list);
            counter.put(roomId, ai);
        }
        return new ResetData().setRoomId(roomId).setBase(initialValue);
    }

    // 获取消息
    // 每次20条
    // 多出内容缓存至下次获取
    // 无消息时返回，已无最新消息
    @Override
    public List<Danmuk> load(String roomId) {
        List<Danmuk> messages = new ArrayList<>();
        File dir = new File(basePath, roomId);
        if (dir.exists() && dir.isDirectory()) {
            if (!cache.keySet().contains(roomId)) {
                List<Danmuk> list = new ArrayList<>();
                AtomicInteger ai = new AtomicInteger(0);
                cache.put(roomId, list);
                counter.put(roomId, ai);
            }
            messages.addAll(loadMessage(roomId, dir));
        }
        if (messages.isEmpty()) {
            messages.add(danmuk);
        }
        return messages;
    }

    public List<Danmuk> loadMessage(String roomId, File dir) {
        int limit = 20;
        List<Danmuk> messages = new ArrayList<>();
        List<String> collect = Arrays.stream(dir.listFiles())
                .filter(f -> f.getName().endsWith("json"))
                .map(f -> f.getName())
                .sorted()
                .collect(Collectors.toList());

        if (cache.get(roomId).size() > 0) {
            if (cache.get(roomId).size() <= limit) {
                messages.addAll(cache.get(roomId));
                cache.get(roomId).clear();
            } else {
                messages.addAll(cache.get(roomId).stream().limit(limit).collect(Collectors.toList()));
                cache.get(roomId).removeAll(messages);
            }
        }

        if (messages.size() < limit) {
            int cur = counter.get(roomId).get();
            for (int i = cur; i < collect.size(); i++) {
                String name = collect.get(i);
                counter.get(roomId).getAndIncrement();
                File file = new File(dir, name);
                List<Danmuk> danmuk = DanmukUtil.getDanmuk(Arrays.asList(file));
                List<Danmuk> danmukMsg = danmuk.stream()
                        .filter(f -> f.getDanmukType().equals(DanmukEnum.DANMU_MSG.name()))
                        .collect(Collectors.toList());

                if (messages.size() + danmukMsg.size() > limit) {
                    int need = limit - messages.size();
                    List<Danmuk> needmsg = danmukMsg.stream()
                            .limit(need)
                            .collect(Collectors.toList());
                    messages.addAll(needmsg);
                    danmukMsg.removeAll(needmsg);
                    cache.get(roomId).addAll(danmukMsg);
                    break;
                } else {
                    messages.addAll(danmukMsg);
                }
            }
        }

        return messages;
    }
}
