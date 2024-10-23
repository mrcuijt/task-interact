package javax.frame.tools.task_interact.modules.task.mapper;

import javax.frame.tools.task_interact.modules.task.model.TmTaskConfigHisLog;

public interface TmTaskConfigHisLogMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     *
     * @mbg.generated
     */
    int insert(TmTaskConfigHisLog record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(TmTaskConfigHisLog record);

    /**
     *
     * @mbg.generated
     */
    TmTaskConfigHisLog selectByPrimaryKey(String id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TmTaskConfigHisLog record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TmTaskConfigHisLog record);
}