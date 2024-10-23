package javax.frame.tools.task_interact.modules.task.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TmTaskRun {
    /**
     * 标识 ID
     */
    private String id;

    /**
     * 编号 TNO
     */
    private String tno;

    /**
     * 名称 NAME
     */
    private String name;

    /**
     * 类型 TYPE
     */
    private String type;

    /**
     * 后缀 SUFFIX
     */
    private String suffix;

    /**
     * 预期数 EXPECTED_NUM
     */
    private String expectedNum;

    /**
     * 增量数 INCREMENT
     */
    private String increment;

    /**
     * 实际数 ACTUAL_NUM
     */
    private String actualNum;

    /**
     * 运行状态;0-异常（红色），1-正常（绿色） RUN_STATE
     */
    private String runState;

    /**
     * 中断状态;0-中断（红色），1-正常（绿色） INTERRUPT_STATE
     */
    private String interruptState;

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