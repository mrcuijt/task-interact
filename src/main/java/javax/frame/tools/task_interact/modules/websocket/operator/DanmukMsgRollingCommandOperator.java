package javax.frame.tools.task_interact.modules.websocket.operator;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.h2.entity.Danmuk;

import javax.frame.tools.task_interact.modules.commons.enums.CommandEnum;
import javax.frame.tools.task_interact.modules.commons.operator.CommandOperator;
import javax.frame.tools.task_interact.modules.commons.utils.SpringUtil;
import javax.frame.tools.task_interact.modules.task.utils.JacksonUtil;
import javax.frame.tools.task_interact.modules.websocket.commons.CmdRespData;
import javax.frame.tools.task_interact.modules.websocket.model.DanmukRollingData;
import javax.frame.tools.task_interact.modules.websocket.service.DanmukRollingService;
import java.util.List;

public interface DanmukMsgRollingCommandOperator extends CommandOperator {

    DanmukRollingService danmukService = SpringUtil.getBean(DanmukRollingService.class);

    @Override
    default String process(String data) {
        try {
            DanmukRollingData rollingData = JacksonUtil.parser(data, DanmukRollingData.class);
            List<Danmuk> danmuks = danmukService.rolling(rollingData);
            return JacksonUtil.jsonString(new CmdRespData()
                    .setCmd(CommandEnum.DANMUK_MSG.name())
                    .setData(danmuks));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

