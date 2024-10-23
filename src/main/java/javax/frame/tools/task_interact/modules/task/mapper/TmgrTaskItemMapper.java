package javax.frame.tools.task_interact.modules.task.mapper;

import javax.frame.tools.task_interact.modules.task.model.TmgrTaskItem;

public interface TmgrTaskItemMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     *
     * @mbg.generated
     */
    int insert(TmgrTaskItem record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(TmgrTaskItem record);

    /**
     *
     * @mbg.generated
     */
    TmgrTaskItem selectByPrimaryKey(String id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TmgrTaskItem record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TmgrTaskItem record);
}