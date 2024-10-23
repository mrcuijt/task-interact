package javax.frame.tools.task_interact.modules.task.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.frame.tools.task_interact.modules.commons.exception.TaskException;
import javax.frame.tools.task_interact.modules.task.constants.MapperConstancts;
import javax.frame.tools.task_interact.modules.task.enums.TaskGeneraterEnum;
import javax.frame.tools.task_interact.modules.task.model.TmTaskConfig;
import javax.frame.tools.task_interact.modules.task.service.TmTaskService;
import javax.frame.tools.task_interact.modules.task.utils.JacksonUtil;
import javax.frame.tools.task_interact.modules.task.utils.LocalDateTimeUtil;
import javax.frame.tools.task_interact.modules.task.utils.ResponseData;
import java.util.Map;

@Slf4j
@Service
public class TmTaskServiceImpl implements TmTaskService {

    private static final String TM_TASK_RUN_MAPPER = MapperConstancts.TM_TASK_RUN_MAPPER;
    private static final String TM_TASK_CONFIG_MAPPER = MapperConstancts.TM_TASK_CONFIG_MAPPER;
    private static final String TM_TASK_CONFIG_HIS_LOG_MAPPER = MapperConstancts.TM_TASK_CONFIG_HIS_LOG_MAPPER;

    @Resource
    SqlSession sqlSession;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = TaskException.class)
    public ResponseData add(Map<String, Object> params) throws TaskException {
        try {
            TmTaskConfig taskConfig = JacksonUtil.parser(params, TmTaskConfig.class);
            taskConfig.setInputDate(LocalDateTimeUtil.getLocalDate());
            taskConfig.setInputTime(LocalDateTimeUtil.getLocalDateTime());
            taskConfig.setUpdateDate(LocalDateTimeUtil.getLocalDate());
            taskConfig.setUpdateTime(LocalDateTimeUtil.getLocalDateTime());
            sqlSession.insert(TM_TASK_CONFIG_MAPPER + "insert", taskConfig);
            TmTaskConfig result = sqlSession.selectOne(TM_TASK_CONFIG_MAPPER + "selectByPrimaryKey", taskConfig);
            return ResponseData.success(result);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new TaskException("任务添加失败！", e);
        }
    }

    @Override
    public ResponseData load(Map<String, Object> params) throws TaskException {
        try {
            TmTaskConfig queryParam = new TmTaskConfig()
                    .setTno(JacksonUtil.parser(params, TmTaskConfig.class).getTno());
            TmTaskConfig result = sqlSession.selectOne(TM_TASK_CONFIG_MAPPER + "selectByPrimaryKey", queryParam);
            return ResponseData.success(result);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            throw new TaskException("任务查询出错！", e);
        }
    }

    public static void main(String[] args) throws Exception {
        TmTaskConfig temp = new TmTaskConfig();
        temp.setTno("100");
        temp.setExpectedNum("20");
        temp.setIncrement("20");
        temp.setActualNum("");
        temp.setSuffixRemote("webp");
        temp.setType(TaskGeneraterEnum.TASK_INIT.name());
        temp.setInputDate(LocalDateTimeUtil.getLocalDate());
        temp.setInputTime(LocalDateTimeUtil.getLocalDateTime());
        temp.setUpdateDate(LocalDateTimeUtil.getLocalDate());
        temp.setUpdateTime(LocalDateTimeUtil.getLocalDateTime());
        String json = JacksonUtil.jsonString(temp);
        Map map = JacksonUtil.parser(temp, Map.class);
        System.out.println(json);
        System.out.println(map);
        TmTaskServiceImpl service = new TmTaskServiceImpl();
        service.add(map);
    }
}
