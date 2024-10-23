package javax.frame.tools.task_interact.modules.task.service;

import javax.frame.tools.task_interact.modules.task.utils.ResponseData;
import java.util.Map;

public interface TaskService {

    ResponseData publish();

    ResponseData publish(Map<String, Object> params);

    ResponseData get(Map<String, Object> params);

    ResponseData notifyStatus(Map<String, Object> params);
}
