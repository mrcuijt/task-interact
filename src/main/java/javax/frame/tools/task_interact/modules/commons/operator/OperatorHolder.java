package javax.frame.tools.task_interact.modules.commons.operator;

import javax.frame.tools.task_interact.modules.commons.enums.CommandEnum;
import javax.frame.tools.task_interact.modules.websocket.operator.*;

public class OperatorHolder {

    public static CommandOperator getOperator(CommandEnum command){
        switch (command){
            case AUTH:
                return (AuthCommandOperator)() -> command;
            case DANMUK_MSG:
                return (DanmukMsgCommandOperator)() -> command;
            case DANMUK_MSG_ROLLING:
                return (DanmukMsgRollingCommandOperator)() -> command;
            case DANMUK_MSG_RESET_PROGRESS:
                return (DanmukMsgResetProgressCommandOperator)() -> command;
            case AUTH_HEART:
                return (AuthHeartCommandOperator)() -> command;
            default:
                return null;
        }
    }

}
