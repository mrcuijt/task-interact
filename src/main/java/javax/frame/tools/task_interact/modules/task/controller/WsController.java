package javax.frame.tools.task_interact.modules.task.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.frame.tools.task_interact.modules.task.utils.ResponseData;
import javax.frame.tools.task_interact.modules.websocket.manager.WsManager;

@Slf4j
@Controller
@CrossOrigin
@RequestMapping("/ws")
public class WsController {

    @ResponseBody
    @RequestMapping("wsLoad")
    public ResponseData wsLoad() {
        return ResponseData.success(WsManager.listWs());
    }
}
