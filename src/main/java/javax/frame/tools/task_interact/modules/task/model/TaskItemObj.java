package javax.frame.tools.task_interact.modules.task.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class TaskItemObj {

    private String serial;

    private String serialName;

    private String splitNum;

}
