package javax.frame.tools.task_interact.modules.task.tools;

import javax.frame.tools.task_interact.modules.task.enums.TaskGeneraterEnum;
import javax.frame.tools.task_interact.modules.task.model.TaskItemObj;
import javax.frame.tools.task_interact.modules.task.model.TaskObj;
import javax.frame.tools.task_interact.modules.task.model.TmTaskRun;
import javax.frame.tools.task_interact.modules.task.utils.Tasks;
import java.util.List;

public class TaskHolder {

    public static TaskGenerater getTaskGenerater(TaskGeneraterEnum e) {
        switch (e) {
            case TASK_INIT:
                return (TaskInitGenerater) () -> e;
            case TASK_DATA_FIX:
                return (TaskDataFixGenerater) () -> e;
            case TASK_INCREMENT:
                return (TaskIncrementGenerater) () -> e;
        }
        return null;
    }

    public static void main(String[] args) {

        TmTaskRun run = new TmTaskRun();
        run.setTno("100");
        run.setExpectedNum("10");
        run.setIncrement("20");
        run.setSuffix("webp");

        TaskObj taskObj = new TaskObj();
        taskObj.setAid(run.getTno());
        taskObj.setSuffix(run.getSuffix());
        taskObj.setMax(run.getExpectedNum());

        List<TaskItemObj> generate = TaskHolder
                .getTaskGenerater(TaskGeneraterEnum.TASK_INCREMENT)
                .generate(run, taskObj);
        System.out.println(generate);
        System.out.println(Tasks.toJsJsonObj(generate));
    }
}
