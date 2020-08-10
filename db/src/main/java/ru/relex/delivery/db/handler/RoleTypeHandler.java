package ru.relex.delivery.db.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.stereotype.Component;
import ru.relex.delivery.commons.model.UserRole;

import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@MappedTypes({UserRole.class})
@MappedJdbcTypes({JdbcType.INTEGER, JdbcType.BIGINT})
public class RoleTypeHandler implements TypeHandler<UserRole> {

    @Override
    public void setParameter(PreparedStatement ps, int i, UserRole parameter, JdbcType jdbcType) throws SQLException {
        if (parameter ==null) {
            ps.setNull(i, jdbcType.TYPE_CODE);
        } else {
            ps.setInt(i, parameter.getId());
        }
    }

    @Override
    public UserRole getResult(ResultSet rs, String columnName) throws SQLException {
        return UserRole.fromId(rs.getInt(columnName));
    }

    @Override
    public UserRole getResult(ResultSet rs, int columnIndex) throws SQLException {
        return UserRole.fromId(rs.getInt(columnIndex));
    }

    @Override
    public UserRole getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return UserRole.fromId(cs.getInt(columnIndex));
    }
}
