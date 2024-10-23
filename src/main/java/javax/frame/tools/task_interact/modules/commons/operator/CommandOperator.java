package javax.frame.tools.task_interact.modules.commons.operator;

import javax.frame.tools.task_interact.modules.commons.enums.CommandEnum;

public interface CommandOperator {

    CommandEnum command();

    String process(String data);

}
