package javax.frame.tools.task_interact.modules.websocket.service.impl;

import org.springframework.stereotype.Service;

import javax.frame.tools.task_interact.modules.websocket.command.CommandEndpoint;
import javax.frame.tools.task_interact.modules.websocket.manager.Ws;
import javax.frame.tools.task_interact.modules.websocket.manager.WsManager;
import javax.frame.tools.task_interact.modules.websocket.service.RegisterService;
import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {

    List<Ws> wsList = new ArrayList<>();

    @Override
    public Ws addWs(String id, CommandEndpoint endpoint, Session session) {
        Ws ws = WsManager.addWs(id, endpoint, session);
        return ws;
    }

    @Override
    public List<Ws> listWs() {
        return null;
    }
}
