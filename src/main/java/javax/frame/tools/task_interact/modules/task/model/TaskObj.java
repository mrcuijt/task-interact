package javax.frame.tools.task_interact.modules.task.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class TaskObj {

    private String aid;

    private String suffix;

    private String max;

    private String items;

    private List<TaskItemObj> datas;

}
