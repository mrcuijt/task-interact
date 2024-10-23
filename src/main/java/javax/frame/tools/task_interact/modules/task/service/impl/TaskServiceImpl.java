package javax.frame.tools.task_interact.modules.task.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.frame.tools.task_interact.modules.task.model.TmgrTask;
import javax.frame.tools.task_interact.modules.task.model.TmgrTaskItem;
import javax.frame.tools.task_interact.modules.task.service.TaskService;
import javax.frame.tools.task_interact.modules.task.utils.LocalDateTimeUtil;
import javax.frame.tools.task_interact.modules.task.utils.ResponseData;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService {

    @Resource
    SqlSession sqlSession;

    public static final String TMGR_TASK_MAPPER = "javax.frame.tools.task_interact.modules.task.mapper.TmgrTaskMapper.";

    public static final String TMGR_TASK_ITEM_MAPPER = "javax.frame.tools.task_interact.modules.task.mapper.TmgrTaskItemMapper.";

    @Override
    public ResponseData publish() {
        TmgrTask tmgrTask = new TmgrTask();
        tmgrTask.setId(IdWorker.getIdStr());
        tmgrTask.setTaskNo("00001");
        tmgrTask.setExpectedNum("800");
        tmgrTask.setTaskName("任务1");
        tmgrTask.setUseFlag("Y");
        tmgrTask.setInputDate(LocalDateTimeUtil.getLocalDate());
        tmgrTask.setInputTime(LocalDateTimeUtil.getLocalDateTime());
        sqlSession.insert(TMGR_TASK_MAPPER + "insert", tmgrTask);
        return null;
    }

    @Override
    public ResponseData publish(Map<String, Object> params) {
        return null;
    }

    @Override
    public ResponseData get(Map<String, Object> params) {
        TmgrTask tmgrTask = new TmgrTask();
        Object o = params.get("id");
        tmgrTask.setId((String)o);
        TmgrTask selectOne = sqlSession.selectOne(TMGR_TASK_MAPPER + "selectByPrimaryKey", tmgrTask);
        return ResponseData.success(selectOne);
    }

    @Override
    public ResponseData notifyStatus(Map<String, Object> params) {
        TmgrTaskItem tmgrTaskItem = new TmgrTaskItem();
        tmgrTaskItem.setId(IdWorker.getIdStr());
        tmgrTaskItem.setTaskName((String)params.get("taskName"));
        tmgrTaskItem.setTaskNo((String)params.get("taskNo"));
        tmgrTaskItem.setItemNo((String)params.get("itemNo"));
        tmgrTaskItem.setHttpRespCode((String)params.get("httpRespCode"));
        tmgrTaskItem.setInputDate(LocalDateTimeUtil.getLocalDate());
        tmgrTaskItem.setInputTime(LocalDateTimeUtil.getLocalDateTime());
        sqlSession.insert(TMGR_TASK_ITEM_MAPPER + "insert", tmgrTaskItem);
        TmgrTaskItem selectOne = sqlSession.selectOne(TMGR_TASK_ITEM_MAPPER + "selectByPrimaryKey", tmgrTaskItem);
        return ResponseData.success(selectOne);
    }
}
