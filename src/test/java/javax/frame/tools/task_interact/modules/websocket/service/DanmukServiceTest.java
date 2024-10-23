package javax.frame.tools.task_interact.modules.websocket.service;

import org.example.h2.entity.Danmuk;
import org.junit.Test;

import javax.frame.tools.task_interact.modules.websocket.service.impl.DanmukServiceImpl;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DanmukServiceTest {

    DanmukService danmukService = new DanmukServiceImpl();

    @Test
    public void demo() {
        String roomId = "21696950";
        roomId = "21987615";
        for (int i = 0; i < 3; i++) {
            List<Danmuk> load = danmukService.load(roomId);
            System.out.println(load.size());
            System.out.println();
            load.stream().forEach(System.out::println);
        }
    }

    @Test
    public void demo2() {
        String roomId = "21696950";
        roomId = "21987615";
        roomId = "7401280";
        roomId = "5279";
        List<Danmuk> load = null;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("message.txt"))) {
            do {
                try {
                    load = danmukService.load(roomId);
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
                load.stream().forEach(f -> {
                    try {
                        String danmukType = f.getDanmukType();
                        String danmukUserName = f.getDanmukUserName();
                        String danmukUserId = f.getDanmukUserId();
                        String id = f.getId();
                        String danmuk = f.getDanmuk();
                        bw.write(String.format("%s\t%s\t%s\t%s\t%s", id, danmukType, danmuk, danmukUserName, danmukUserId));
                        bw.newLine();
                        bw.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } while (load.size() > 0 && !load.get(0).getId().equals("1L"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
