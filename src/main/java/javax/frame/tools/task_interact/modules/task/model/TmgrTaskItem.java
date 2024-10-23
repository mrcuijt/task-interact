package javax.frame.tools.task_interact.modules.task.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TmgrTaskItem {
    /**
     * 主键 ID
     */
    private String id;

    /**
     * 任务名 TASK_NAME
     */
    private String taskName;

    /**
     * 任务编号 TASK_NO
     */
    private String taskNo;

    /**
     * 项目编号 ITEM_NO
     */
    private String itemNo;

    /**
     * 请求响应状态码 HTTP_RESP_CODE
     */
    private String httpRespCode;

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