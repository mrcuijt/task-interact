package javax.frame.tools.task_interact.modules.commons.enums;

import lombok.Getter;

@Getter
public enum CommandEnum {

    AUTH("认证"),

    AUTH_HEART("心跳保持"),

    AUTH_SUCCESS("认证通过"),

    AUTH_FAIL("认证失败"),

    FAIL("失败"),

    REGISTER("注册登记"),

    DANMUK_MSG("弹幕消息"),

    DANMUK_NOTIFY("弹幕通知"),

    DANMUK_MSG_RESET_PROGRESS("弹幕消息进度重置"),


    ;

    CommandEnum(String desc) {
        this.desc = desc;
    }

    String desc;

}
