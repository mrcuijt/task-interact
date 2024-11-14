package javax.frame.tools.task_interact.modules.websocket.service.impl;

import org.example.h2.entity.Danmuk;
import org.example.h2.enums.DanmukEnum;
import org.example.h2.util.DanmukUtil;
import org.springframework.stereotype.Service;

import javax.frame.tools.task_interact.modules.websocket.model.DanmukRollingData;
import javax.frame.tools.task_interact.modules.websocket.service.DanmukRollingService;
import javax.frame.tools.task_interact.modules.websocket.service.DanmukService;
import java.io.File;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class DanmukRollingServiceImpl implements DanmukRollingService {

    String basePath = "D:/tools/upload/upload";

    // 缓存溢出数据，优先从缓存中获取
    Map<String, List<Danmuk>> cache = new HashMap<>();

    // 数据获取计数
    Map<String, AtomicInteger> counter = new HashMap<>();

    // 数据获取页数
    Map<String, AtomicInteger> page = new HashMap<>();

    Danmuk danmuk = new Danmuk().setDanmuk("无消息").setId("1L");

    @Override
    public List<Danmuk> rolling(DanmukRollingData rollingData) {
        String roomId = rollingData.getRoomId();
        int roomPage = rollingData.getRoomPage();
        if (!counter.containsKey(roomId)) {
            counter.put(roomId, new AtomicInteger(1));
            page.put(roomId, new AtomicInteger(roomPage));
        }
        List<Danmuk> danmuks = loadMessage(roomId, roomPage, new File(basePath, roomId));
        if (danmuks.size() == 0){
            danmuks.add(danmuk);
        }
        return danmuks;
    }

    public List<Danmuk> loadMessage(String roomId, int roomPage, File dir) {
        int limit = 20;
        int curPage = 1;
        boolean next = page.get(roomId).get() + 1 == roomPage;
        if (!next) {
            cache.put(roomId, new ArrayList<>());
            counter.put(roomId, new AtomicInteger(1));
            page.put(roomId, new AtomicInteger(roomPage));
        }
        List<Danmuk> danmuks = new ArrayList<>();
        while (true) {
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

            if (collect.size() == counter.get(roomId).get()) {
                return new ArrayList<>();
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
            if (next || !next && curPage == roomPage) {
                danmuks.addAll(messages);
                page.get(roomId).set(roomPage);
                break;
            }
            curPage++;
        }
        return danmuks;
    }
}
