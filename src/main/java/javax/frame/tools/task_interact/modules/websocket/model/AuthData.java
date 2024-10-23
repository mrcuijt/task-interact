package javax.frame.tools.task_interact.modules.websocket.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
public class AuthData {

    private String userId;

    private LocalDateTime reqTime;

}
