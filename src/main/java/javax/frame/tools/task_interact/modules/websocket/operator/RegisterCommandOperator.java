package javax.frame.tools.task_interact.modules.websocket.operator;

import javax.frame.tools.task_interact.modules.commons.operator.CommandOperator;
import javax.frame.tools.task_interact.modules.commons.utils.SpringUtil;

public interface RegisterCommandOperator extends CommandOperator {
    @Override
    default String process(String data) {
        //SpringUtil.getBean()
        return null;
    }
}
