package javax.frame.tools.task_interact.modules.websocket.operator;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;

import javax.frame.tools.task_interact.modules.commons.exception.TaskException;
import javax.frame.tools.task_interact.modules.commons.operator.CommandOperator;
import javax.frame.tools.task_interact.modules.commons.utils.CmdUtil;
import javax.frame.tools.task_interact.modules.task.utils.JacksonUtil;
import javax.frame.tools.task_interact.modules.websocket.commons.CmdRespData;
import java.util.Map;

public interface AuthHeartCommandOperator extends CommandOperator {

    @Override
    default String process(String data) {
        try {
            System.out.println("心跳保持：" + data);
            return JacksonUtil.jsonString(new CmdRespData()
                    .setCmd(CmdUtil.authSuccess())
                    .setData(JacksonUtil.parser(data, Map.class)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
