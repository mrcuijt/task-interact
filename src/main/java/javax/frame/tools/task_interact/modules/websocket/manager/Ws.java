package javax.frame.tools.task_interact.modules.websocket.manager;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.frame.tools.task_interact.modules.websocket.command.CommandEndpoint;
import javax.websocket.Session;

@Getter
@Setter
@Accessors(chain = true)
public class Ws {

    public Ws(String id, CommandEndpoint endpoint, Session session) {
        this.endpoint = endpoint;
        this.session = session;
        this.id = id;
    }

    /**
     * 端点
     */
    @JsonIgnore
    private CommandEndpoint endpoint;

    /**
     * 会话
     */
    @JsonIgnore
    private Session session;

    /**
     * 标识
     */
    private String id;

    /**
     * 备注
     */
    private String remark;

    /**
     * 类型
     */
    private String type;

    /**
     * 是否可用
     */
    private boolean available;

}
