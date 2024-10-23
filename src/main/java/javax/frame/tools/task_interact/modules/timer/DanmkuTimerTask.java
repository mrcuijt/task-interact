package javax.frame.tools.task_interact.modules.timer;

import lombok.extern.slf4j.Slf4j;

import javax.frame.tools.task_interact.modules.commons.enums.CommandEnum;
import javax.frame.tools.task_interact.modules.commons.utils.CmdUtil;
import javax.frame.tools.task_interact.modules.task.utils.JacksonUtil;
import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

@Slf4j
public class DanmkuTimerTask extends TimerTask {

    private Session session;

    private String data;

    public DanmkuTimerTask(Session session, String data) {
        this.session = session;
        this.data = data;
    }

    @Override
    public void run() {
        try {
            log.info(Thread.currentThread().getName() + ": is running");
            String process = CmdUtil.op(CommandEnum.DANMUK_MSG.name()).process(data);
            session.getBasicRemote().sendText(process);
        } catch (IOException e) {
            log.info("弹幕消息通知运行出错：" + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

}
