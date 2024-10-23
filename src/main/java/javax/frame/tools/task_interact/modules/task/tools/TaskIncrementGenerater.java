package javax.frame.tools.task_interact.modules.task.tools;

import javax.frame.tools.task_interact.modules.task.model.TaskItemObj;
import javax.frame.tools.task_interact.modules.task.model.TaskObj;
import javax.frame.tools.task_interact.modules.task.model.TmTaskRun;
import javax.frame.tools.task_interact.modules.task.utils.Tasks;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * 数据增长处理（视当前情况：数据未达到预期，增长至预期；已达预期，按预定数增长）
 */
public interface TaskIncrementGenerater extends TaskGenerater {

    /**
     * 生成任务数据项
     *
     * @param run
     * @param taskObj
     * @return
     */
    @Override
    default List<TaskItemObj> generate(TmTaskRun run, TaskObj taskObj) {
        List<String> localSerial = this.getLocalSerial();
        int cur = Integer.parseInt(localSerial.stream().max(String::compareTo).orElse("1"));
        int max = Integer.parseInt(run.getExpectedNum());
        int increment = Integer.parseInt(run.getIncrement());
        List<TaskItemObj> datas = new ArrayList<>();
        // 数据未达到预期，增长至预期
        if (cur < max) {
            Stream.iterate(cur + 1, n -> n + 1)
                    .limit(max)
                    .map(n -> Tasks.initTaskItemObj(n, taskObj))
                    .forEach(datas::add);
        }
        // 已达预期，按预定数增长
        else {
            Stream.iterate(cur + 1, n -> n + 1)
                    .limit(increment)
                    .map(n -> Tasks.initTaskItemObj(n, taskObj))
                    .forEach(datas::add);
        }
        return datas;
    }
}
