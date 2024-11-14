package javax.frame.tools.task_interact.modules.websocket.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class DanmukRollingData {

    String roomId;

    int roomPage;
}
