package javax.frame.tools.task_interact.modules.task.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class TmTaskConfig {

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
     * 本地后缀 SUFFIX_LOCAL
     */
    private String suffixLocal;

    /**
     * 远程后缀 SUFFIX_REMOTE
     */
    private String suffixRemote;

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
     * 任务状态;0-任务建立（红色），1-任务开启（蓝色），2-任务结束（绿色），3-任务中断（红色） STATE
     */
    private String state;

    /**
     * 数据状态;0-异常（红色），1-正常（绿色） DATA_STATE
     */
    private String dataState;

    /**
     * 预期状态;0-异常（红色），1-正常（绿色） EXPECTED_STATE
     */
    private String expectedState;

    /**
     * 批量状态;0-异常（红色），1-正常（绿色） BATCH_STATE
     */
    private String batchState;

    /**
     * 人工复核;0-异常（红色），1-正常（绿色） MANUAL_STATE
     */
    private String manualState;

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