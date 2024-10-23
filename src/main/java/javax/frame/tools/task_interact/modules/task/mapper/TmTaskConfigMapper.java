package javax.frame.tools.task_interact.modules.task.mapper;

import javax.frame.tools.task_interact.modules.task.model.TmTaskConfig;

public interface TmTaskConfigMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String tno);

    /**
     *
     * @mbg.generated
     */
    int insert(TmTaskConfig record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(TmTaskConfig record);

    /**
     *
     * @mbg.generated
     */
    TmTaskConfig selectByPrimaryKey(String tno);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TmTaskConfig record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TmTaskConfig record);
}