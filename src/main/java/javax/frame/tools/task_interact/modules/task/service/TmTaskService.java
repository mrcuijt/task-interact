package javax.frame.tools.task_interact.modules.task.service;

import javax.frame.tools.task_interact.modules.commons.exception.TaskException;
import javax.frame.tools.task_interact.modules.task.utils.ResponseData;
import java.util.Map;

public interface TmTaskService {

    ResponseData add(Map<String, Object> params) throws TaskException;

    ResponseData load(Map<String, Object> params) throws TaskException;

}
