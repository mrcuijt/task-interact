package javax.frame.tools.task_interact.modules.websocket.commons;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.frame.tools.task_interact.modules.commons.utils.CmdUtil;

@Getter
@Setter
@Accessors(chain = true)
public class CmdRespData<T> {

    String cmd;

    T data;

    public static <T> CmdRespData<T> response(String cmd, T data) {
        return new CmdRespData<T>()
                .setCmd(cmd)
                .setData(data);
    }

    public static <T> CmdRespData<T> fail(T data) {
        return new CmdRespData<T>()
                .setCmd(CmdUtil.authFail())
                .setData(data);
    }
}
