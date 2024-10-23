package javax.frame.tools.task_interact.modules.websocket.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.frame.tools.task_interact.modules.commons.exception.TaskException;
import javax.frame.tools.task_interact.modules.task.constants.MapperConstancts;
import javax.frame.tools.task_interact.modules.websocket.model.AuthLog;
import javax.frame.tools.task_interact.modules.websocket.service.AuthService;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    final String AUTH_LOG_MAPPER = MapperConstancts.AUTH_LOG_MAPPER;

    @Resource
    SqlSession sqlSession;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void add(AuthLog authLog) throws TaskException {
        try {
            sqlSession.insert(AUTH_LOG_MAPPER + "insert", authLog);
        } catch (Exception e) {
            log.error("授权日志记录出错：" + e.getMessage(), e);
            throw new TaskException(e);
        }
    }

}
