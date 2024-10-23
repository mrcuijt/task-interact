package javax.frame.tools.task_interact.modules.task.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TmgrTask {
    /**
     * 主键 ID
     */
    private String id;

    /**
     * 任务名 TASK_NAME
     */
    private String taskName;

    /**
     * 任务类型 TASK_TYPE
     */
    private String taskType;

    /**
     * 任务编号 TASK_NO
     */
    private String taskNo;

    /**
     * 预期记录数 EXPECTED_NUM
     */
    private String expectedNum;

    /**
     * 实际记录数 ACTUAL_NUM
     */
    private String actualNum;

    /**
     * 任务结束说明 OVER_REASON
     */
    private String overReason;

    /**
     * 任务是否启用 USE_FLAG
     */
    private String useFlag;

    /**
     * 插入日期 INPUT_DATE
     */
    private LocalDateTime inputDate;

    /**
     * 插入时间 INPUT_TIME
     */
    private LocalDateTime inputTime;

    /**
     * 更新日期 UPDATE_DATE
     */
    private LocalDateTime updateDate;

    /**
     * 更新时间 UPDATE_TIME
     */
    private LocalDateTime updateTime;
}