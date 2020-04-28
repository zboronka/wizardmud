package wizardmud.dao;

import wizardmud.api.MudCharacterUri;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class MudCharacterUriMapper implements RowMapper<MudCharacterUri> {
	public MudCharacterUri map(ResultSet rs, StatementContext sc) throws SQLException {
		return new MudCharacterUri(rs.getString("Name"), String.valueOf(rs.getInt("ID")));
	}
}
