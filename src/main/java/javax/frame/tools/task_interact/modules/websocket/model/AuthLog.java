package javax.frame.tools.task_interact.modules.websocket.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthLog {
    /**
     * 标识 ID
     */
    private String id;

    /**
     * 用户Id USER_ID
     */
    private String userId;

    /**
     * 请求时间 REQ_TIME
     */
    private LocalDateTime reqTime;

    /**
     * 请求数据 REQ_DATA
     */
    private String reqData;

    /**
     * 请求结果 REQ_RESULT
     */
    private String reqResult;

    /**
     * 处理时间 PROCESS_TIME
     */
    private LocalDateTime processTime;

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