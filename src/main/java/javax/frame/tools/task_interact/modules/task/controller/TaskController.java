package javax.frame.tools.task_interact.modules.task.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.frame.tools.task_interact.modules.commons.exception.TaskException;
import javax.frame.tools.task_interact.modules.task.service.TaskService;
import javax.frame.tools.task_interact.modules.task.service.TmTaskService;
import javax.frame.tools.task_interact.modules.task.utils.RequestData;
import javax.frame.tools.task_interact.modules.task.utils.ResponseData;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@CrossOrigin
@RequestMapping("task")
public class TaskController {

    @Resource
    TaskService taskService;

    @Resource
    TmTaskService tmTaskService;

    @ResponseBody
    @RequestMapping("publish")
    public ResponseData publish() {
        taskService.publish();
        return ResponseData.success("");
    }

    @ResponseBody
    @RequestMapping("get")
    public ResponseData get() {
        String id = "1808044819030597634";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return taskService.get(params);
    }

    @ResponseBody
    @RequestMapping("notifyStatus")
    public ResponseData notifyStatus(@RequestBody RequestData requestData){
        return taskService.notifyStatus(requestData.getParams());
    }

    @ResponseBody
    @RequestMapping("add")
    public ResponseData add(@RequestBody RequestData requestData){
        try {
            return tmTaskService.add(requestData.getParams());
        } catch (TaskException e) {
            return ResponseData.fail(null).setMsg(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("load")
    public ResponseData load(@RequestBody RequestData requestData){
        try {
            return tmTaskService.load(requestData.getParams());
        } catch (TaskException e) {
            return ResponseData.fail(null).setMsg(e.getMessage());
        }
    }
}
