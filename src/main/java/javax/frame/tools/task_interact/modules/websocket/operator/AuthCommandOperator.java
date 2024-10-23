package javax.frame.tools.task_interact.modules.websocket.operator;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.frame.tools.task_interact.modules.commons.exception.TaskException;
import javax.frame.tools.task_interact.modules.commons.operator.CommandOperator;
import javax.frame.tools.task_interact.modules.commons.utils.CmdUtil;
import javax.frame.tools.task_interact.modules.commons.utils.SpringUtil;
import javax.frame.tools.task_interact.modules.task.utils.JacksonUtil;
import javax.frame.tools.task_interact.modules.task.utils.LocalDateTimeUtil;
import javax.frame.tools.task_interact.modules.websocket.commons.CmdRespData;
import javax.frame.tools.task_interact.modules.websocket.model.AuthData;
import javax.frame.tools.task_interact.modules.websocket.model.AuthLog;
import javax.frame.tools.task_interact.modules.websocket.service.AuthService;

public interface AuthCommandOperator extends CommandOperator {

    AuthService authService = SpringUtil.getBean(AuthService.class);

    @Override
    default String process(String data) {
        try {
            AuthData authData = JacksonUtil.parser(data, AuthData.class);
            AuthLog authLog = new AuthLog();
            authLog.setId(IdWorker.getIdStr());
            authLog.setUserId(authData.getUserId());
            authLog.setReqTime(authData.getReqTime());
            authLog.setReqData(data);
            authLog.setReqResult(CmdUtil.authSuccess());
            authLog.setProcessTime(LocalDateTimeUtil.getLocalDateTime());
            authLog.setInputDate(LocalDateTimeUtil.getLocalDate());
            authLog.setInputTime(LocalDateTimeUtil.getLocalDateTime());
            authLog.setUpdateDate(LocalDateTimeUtil.getLocalDate());
            authLog.setUpdateTime(LocalDateTimeUtil.getLocalDateTime());
            authService.add(authLog);
            return JacksonUtil.jsonString(new CmdRespData()
                    .setCmd(CmdUtil.authSuccess())
                    .setData(authData));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (TaskException e) {
            e.printStackTrace();
        }
        return null;
    }
}
