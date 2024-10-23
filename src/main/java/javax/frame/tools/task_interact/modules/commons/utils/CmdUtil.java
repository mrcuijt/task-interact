package javax.frame.tools.task_interact.modules.commons.utils;

import com.fasterxml.jackson.core.JsonProcessingException;

import javax.frame.tools.task_interact.modules.commons.enums.CommandEnum;
import javax.frame.tools.task_interact.modules.commons.operator.CommandOperator;
import javax.frame.tools.task_interact.modules.commons.operator.OperatorHolder;
import javax.frame.tools.task_interact.modules.task.utils.JacksonUtil;
import javax.frame.tools.task_interact.modules.websocket.commons.CmdReqData;
import javax.frame.tools.task_interact.modules.websocket.commons.CmdRespData;
import javax.websocket.Session;

public class CmdUtil {

    public static CommandEnum of(String name) {
        return CommandEnum.valueOf(name);
    }

    public static CommandOperator op(String name) {
        return OperatorHolder.getOperator(of(name));
    }

    public static String fail() {
        return CommandEnum.FAIL.name();
    }

    public static String authFail() {
        return CommandEnum.AUTH_FAIL.name();
    }

    public static String authSuccess() {
        return CommandEnum.AUTH_SUCCESS.name();
    }

    public static CommandEnum getCmd(String data){
        try {
            CmdReqData reqData = JacksonUtil.parser(data, CmdReqData.class);
            String cmd = reqData.getCmd();
            return CmdUtil.of(cmd);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return CommandEnum.FAIL;
    }

    public static String process(String data, Session session) {
        return null;
    }

    public static String process(String data) {
        String fail = null;
        try {
            fail = JacksonUtil.jsonString(CmdRespData.fail(data));
            CmdReqData reqData = JacksonUtil.parser(data, CmdReqData.class);
            String cmd = reqData.getCmd();
            String process = CmdUtil.op(cmd).process(JacksonUtil.jsonString(reqData.getData()));
            return process;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return fail;
    }
}
