package javax.frame.tools.task_interact.modules.commons.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import javax.frame.tools.task_interact.modules.task.utils.LocalDateTimeUtil;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class LocalDateTimeTypeHandler extends BaseTypeHandler<LocalDateTime> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, LocalDateTime parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, new BigDecimal(LocalDateTimeUtil.getTimestamp(parameter)));
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
        BigDecimal bigDecimal = rs.getObject(columnName, BigDecimal.class);
        return (bigDecimal == null) ? null : LocalDateTimeUtil.getLocalDateTime(bigDecimal.toPlainString());
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        BigDecimal bigDecimal = rs.getObject(columnIndex, BigDecimal.class);
        return (bigDecimal == null) ? null : LocalDateTimeUtil.getLocalDateTime(bigDecimal.toPlainString());
    }

    @Override
    public LocalDateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        BigDecimal bigDecimal = cs.getObject(columnIndex, BigDecimal.class);
        return (bigDecimal == null) ? null : LocalDateTimeUtil.getLocalDateTime(bigDecimal.toPlainString());
    }
}
