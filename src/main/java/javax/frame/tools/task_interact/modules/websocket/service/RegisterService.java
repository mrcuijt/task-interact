package javax.frame.tools.task_interact.modules.websocket.service;

import javax.frame.tools.task_interact.modules.websocket.command.CommandEndpoint;
import javax.frame.tools.task_interact.modules.websocket.manager.Ws;
import javax.websocket.Session;
import java.util.List;

public interface RegisterService {

    Ws addWs(String id, CommandEndpoint endpoint, Session session);

    List<Ws> listWs();
}
