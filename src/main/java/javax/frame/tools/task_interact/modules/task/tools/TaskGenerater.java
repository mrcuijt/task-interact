package javax.frame.tools.task_interact.modules.task.tools;

import javax.frame.tools.task_interact.modules.task.enums.TaskGeneraterEnum;
import javax.frame.tools.task_interact.modules.task.model.TaskItemObj;
import javax.frame.tools.task_interact.modules.task.model.TaskObj;
import javax.frame.tools.task_interact.modules.task.model.TmTaskRun;
import javax.frame.tools.task_interact.modules.task.utils.RegexUtil;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface TaskGenerater {

    TaskGeneraterEnum taskGeneraterEnum();

    /**
     * 获取数据目录
     *
     * @return
     */
    default String getDir() {
        return "D:/tools/upload/upload";
    }

    /**
     * 获取本地 serial 集合
     * @return
     */
    default List<String> getLocalSerial() {

        String basePath = this.getDir();

        Optional<File[]> optional = Optional.of(new File(basePath).listFiles());
        if (!optional.isPresent()) {
            return Collections.emptyList();
        }

        List<String> local = Arrays.stream(optional.get())
                .map(f -> f.getName())
                .map(f -> f.substring(0, f.lastIndexOf(".")))
                .filter(f -> RegexUtil.match("^0\\d+", f))
                .collect(Collectors.toList());
        return local;
    }

    /**
     * 生成任务数据项
     *
     * @param run
     * @param taskObj
     * @return
     */
    List<TaskItemObj> generate(TmTaskRun run, TaskObj taskObj);
}
