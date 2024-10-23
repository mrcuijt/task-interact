package javax.frame.tools.task_interact.modules.websocket.mapper;

import javax.frame.tools.task_interact.modules.websocket.model.AuthLog;

public interface AuthLogMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     *
     * @mbg.generated
     */
    int insert(AuthLog record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(AuthLog record);

    /**
     *
     * @mbg.generated
     */
    AuthLog selectByPrimaryKey(String id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(AuthLog record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(AuthLog record);
}