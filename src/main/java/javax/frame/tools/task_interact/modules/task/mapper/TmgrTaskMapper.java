package javax.frame.tools.task_interact.modules.task.mapper;

import javax.frame.tools.task_interact.modules.task.model.TmgrTask;

public interface TmgrTaskMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     *
     * @mbg.generated
     */
    int insert(TmgrTask record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(TmgrTask record);

    /**
     *
     * @mbg.generated
     */
    TmgrTask selectByPrimaryKey(String id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TmgrTask record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TmgrTask record);
}