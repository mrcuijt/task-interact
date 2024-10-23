package javax.frame.tools.task_interact.modules.websocket.service;

import javax.frame.tools.task_interact.modules.commons.exception.TaskException;
import javax.frame.tools.task_interact.modules.websocket.model.AuthLog;

public interface AuthService {

    void add(AuthLog authLog) throws TaskException;

}
