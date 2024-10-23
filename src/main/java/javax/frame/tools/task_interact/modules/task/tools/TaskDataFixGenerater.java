package javax.frame.tools.task_interact.modules.task.tools;

import javax.frame.tools.task_interact.modules.task.model.TaskItemObj;
import javax.frame.tools.task_interact.modules.task.model.TaskObj;
import javax.frame.tools.task_interact.modules.task.model.TmTaskRun;
import javax.frame.tools.task_interact.modules.task.utils.Tasks;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据修复处理（本地数据处理），任务数据项
 */
public interface TaskDataFixGenerater extends TaskGenerater {

    /**
     * 生成任务数据项
     *
     * @param run
     * @param taskObj
     * @return
     */
    @Override
    default List<TaskItemObj> generate(TmTaskRun run, TaskObj taskObj) {

        // 本地 serial
        List<String> local = this.getLocalSerial();

        // 最大 serial
        int max = Integer.parseInt(local.stream().max(String::compareTo).get());

        // 预期任务项集合
        List<TaskItemObj> list = Tasks.generateTaskObj(run, max).getDatas();

        // 遗漏缺失 serial 集合
        List<String> missing = list.stream()
                .map(f -> f.getSerial())
                .filter(f -> !local.contains(f))
                .collect(Collectors.toList());

        // 遗漏任务项集合
        List<TaskItemObj> missItems = list.stream()
                .filter((f) -> missing.contains(f.getSerial()))
                .collect(Collectors.toList());

        return missItems;
    }
}
