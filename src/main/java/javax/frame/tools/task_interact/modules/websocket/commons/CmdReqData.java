package javax.frame.tools.task_interact.modules.websocket.commons;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmdReqData<T> {

    String cmd;

    T data;
}
