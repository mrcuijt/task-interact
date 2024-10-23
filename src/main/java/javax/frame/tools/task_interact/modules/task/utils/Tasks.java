package javax.frame.tools.task_interact.modules.task.utils;

import javax.frame.tools.task_interact.modules.task.constants.TaskConstancts;
import javax.frame.tools.task_interact.modules.task.model.TaskItemObj;
import javax.frame.tools.task_interact.modules.task.model.TaskObj;
import javax.frame.tools.task_interact.modules.task.model.TmTaskRun;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tasks {

    private static int base = 100000;

    /**
     * 任务操作数据生成
     * 1、任务发布后根据任务类型，生成对应数据，完成对应操作
     *
     * @param tmTaskRun
     * @return
     */
    public static TaskObj generateTaskObj(TmTaskRun tmTaskRun) {
        return generateTaskObj(tmTaskRun, 0);
    }

    public static TaskObj generateTaskObj(TmTaskRun tmTaskRun, int max) {
        TaskObj taskObj = new TaskObj();
        taskObj.setAid(tmTaskRun.getTno());
        taskObj.setSuffix(tmTaskRun.getSuffix());
        taskObj.setMax(tmTaskRun.getExpectedNum());
        max = (max > 0) ? max : Integer.parseInt(taskObj.getMax());
        List<TaskItemObj> datas = new ArrayList<>();
        Stream.iterate(1, n -> n + 1)
                .limit(max)
                .map(n -> Tasks.initTaskItemObj(n, taskObj))
                .forEach(datas::add);
        taskObj.setDatas(datas);
        taskObj.setItems(Tasks.toJsJsonObj(datas));
        return taskObj;
    }

    public static TaskItemObj initTaskItemObj(int incer, TaskObj taskObj) {
        String serial = toSerial(incer);
        String serialName = toSerialName(serial, taskObj.getSuffix());
        String splitNum = toSplitNum(serial, taskObj.getAid());
        TaskItemObj taskItemObj = new TaskItemObj();
        taskItemObj.setSerial(serial);
        taskItemObj.setSerialName(serialName);
        taskItemObj.setSplitNum(splitNum);
        return taskItemObj;
    }

    public static String format(TaskItemObj taskItemObj) {
        String template = "{@k1:'@v1', @k2:'@v2', @k3:'@v3'}";
        return template.replace("@k1", TaskConstancts.PART_0_KEY)
                .replace("@k2", TaskConstancts.PART_1_KEY)
                .replace("@k3", TaskConstancts.PART_2_KEY)
                .replace("@v1", taskItemObj.getSerial())
                .replace("@v2", taskItemObj.getSerialName())
                .replace("@v3", taskItemObj.getSplitNum());
    }

    public static String toJsJsonObj(List<TaskItemObj> list) {
        String collect = list.stream().map(Tasks::format).collect(Collectors.joining(","));
        return "[" + collect + "]";
    }

    private static String toSplitNum(String serial, String aid) {
        return "10";
    }

    private static String toSerial(int i) {
        return String.valueOf(base + i).substring(1);
    }

    private static String toSerialName(String serial, String suffix) {
        return serial + "." + suffix;
    }
}
