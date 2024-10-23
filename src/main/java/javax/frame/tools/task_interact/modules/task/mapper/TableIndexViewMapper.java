package javax.frame.tools.task_interact.modules.task.mapper;

import javax.frame.tools.task_interact.modules.task.model.TableIndexView;

public interface TableIndexViewMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(TableIndexView record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(TableIndexView record);

    /**
     *
     * @mbg.generated
     */
    TableIndexView selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TableIndexView record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TableIndexView record);
}