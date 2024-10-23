package javax.frame.tools.task_interact.modules.websocket.operator;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.h2.entity.Danmuk;

import javax.frame.tools.task_interact.modules.commons.enums.CommandEnum;
import javax.frame.tools.task_interact.modules.commons.operator.CommandOperator;
import javax.frame.tools.task_interact.modules.commons.utils.SpringUtil;
import javax.frame.tools.task_interact.modules.task.utils.JacksonUtil;
import javax.frame.tools.task_interact.modules.websocket.commons.CmdRespData;
import javax.frame.tools.task_interact.modules.websocket.model.ResetData;
import javax.frame.tools.task_interact.modules.websocket.service.impl.DanmukServiceImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DanmukMsgResetProgressCommandOperator extends CommandOperator {

    DanmukServiceImpl danmukService = SpringUtil.getBean(DanmukServiceImpl.class);

    @Override
    default String process(String data) {
        try {
            ResetData resetData = JacksonUtil.parser(data, ResetData.class);
            ResetData reset = danmukService.reset(resetData);
            return JacksonUtil.jsonString(new CmdRespData()
                    .setCmd(command().name())
                    .setData(reset));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
