package javax.frame.tools.task_interact.modules.task.tools;

import javax.frame.tools.task_interact.modules.task.model.TaskItemObj;
import javax.frame.tools.task_interact.modules.task.model.TaskObj;
import javax.frame.tools.task_interact.modules.task.model.TmTaskRun;
import javax.frame.tools.task_interact.modules.task.utils.Tasks;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * 数据首次加载处理，任务数据项
 */
public interface TaskInitGenerater extends TaskGenerater {

    /**
     * 生成任务数据项
     *
     * @param run
     * @param taskObj
     * @return
     */
    @Override
    default List<TaskItemObj> generate(TmTaskRun run, TaskObj taskObj) {
        int max = Integer.parseInt(taskObj.getMax());
        List<TaskItemObj> datas = new ArrayList<>();
        Stream.iterate(1, n -> n + 1)
                .limit(max)
                .map(n -> Tasks.initTaskItemObj(n, taskObj))
                .forEach(datas::add);
        return datas;
    }
}
