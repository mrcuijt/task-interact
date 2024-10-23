package javax.frame.tools.task_interact.modules.task.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableIndexView {
    /**
     * 主键 ID
     */
    private Long id;

    /**
     * 表名 TB_NAME
     */
    private String tbName;

    /**
     * 建表明细 TB_CREATE
     */
    private String tbCreate;

    /**
     * 建表索引 TB_INDEX
     */
    private String tbIndex;
}