package javax.frame.tools.task_interact.modules.websocket.manager;

import javax.frame.tools.task_interact.modules.websocket.command.CommandEndpoint;
import javax.websocket.Session;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WsManager {

    private static List<Ws> wsList = new ArrayList<>();

    public static Ws addWs(String id, CommandEndpoint endpoint, Session session) {
        synchronized (wsList) {
            Ws ws = wsList.stream()
                    .filter(f -> id.equals(f.getId()))
                    .findFirst()
                    .orElse(null);
            if (ws == null) {
                ws = new Ws(id, endpoint, session);
                wsList.add(ws);
            }
            return ws;
        }
    }

    public static List<Ws> listWs() {
        synchronized (wsList) {
            return Arrays.asList(wsList.toArray(new Ws[]{}));
        }
    }
}
