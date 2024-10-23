package javax.frame.tools.task_interact.modules.websocket.operator;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.h2.entity.Danmuk;

import javax.frame.tools.task_interact.modules.commons.enums.CommandEnum;
import javax.frame.tools.task_interact.modules.commons.operator.CommandOperator;
import javax.frame.tools.task_interact.modules.commons.utils.SpringUtil;
import javax.frame.tools.task_interact.modules.task.utils.JacksonUtil;
import javax.frame.tools.task_interact.modules.websocket.commons.CmdRespData;
import javax.frame.tools.task_interact.modules.websocket.service.impl.DanmukServiceImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DanmukMsgCommandOperator extends CommandOperator {

    DanmukServiceImpl danmukService = SpringUtil.getBean(DanmukServiceImpl.class);

    @Override
    default String process(String data) {
        try {
            Map parser = JacksonUtil.parser(data, Map.class);
            String roomId = (String)parser.get("roomId");
            List<Danmuk> danmuks = danmukService.load(roomId);
            List<Map<String, Object>> list = new ArrayList<>();
            danmuks.stream().forEach( f -> {
                Map<String, Object> message = new HashMap<>();
                message.put("id", f.getId());
                message.put("danmuk", f.getDanmuk());
                message.put("danmukTime", f.getDanmukTime());
                message.put("danmukUserName", f.getDanmukUserName());
                message.put("danmukUserId", f.getDanmukUserId());
                list.add(message);
            });
            return JacksonUtil.jsonString(new CmdRespData()
                    .setCmd(command().name())
                    .setData(list));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
