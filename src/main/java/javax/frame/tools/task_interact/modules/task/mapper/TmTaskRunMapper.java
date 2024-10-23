package javax.frame.tools.task_interact.modules.task.mapper;

import javax.frame.tools.task_interact.modules.task.model.TmTaskRun;

public interface TmTaskRunMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     *
     * @mbg.generated
     */
    int insert(TmTaskRun record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(TmTaskRun record);

    /**
     *
     * @mbg.generated
     */
    TmTaskRun selectByPrimaryKey(String id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TmTaskRun record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TmTaskRun record);
}