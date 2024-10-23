package javax.frame.tools.task_interact.modules.websocket.service;

import org.example.h2.entity.Danmuk;

import javax.frame.tools.task_interact.modules.websocket.model.ResetData;
import java.util.List;

public interface DanmukService {

    ResetData reset(ResetData resetData);

    List<Danmuk> load(String roomId);
}
