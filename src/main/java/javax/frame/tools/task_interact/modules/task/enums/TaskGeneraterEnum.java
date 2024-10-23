package javax.frame.tools.task_interact.modules.task.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum TaskGeneraterEnum {

    TASK_INIT("数据首次加载处理"),

    TASK_DATA_FIX("数据修复处理（本地数据处理）"),

    TASK_INCREMENT("数据增长处理（视当前情况：数据未达到预期，增长至预期；已达预期，按预定数增长）"),

    TASK_INCREMENT_ACTUAL("数据增长处理（实际）"),

    TASK_INCREMENT_EXPECTED("数据增长处理（预期）");

    TaskGeneraterEnum(String desc) {
        this.desc = desc;
    }

    String desc;
}
