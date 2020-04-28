package wizardmud.dao;

import wizardmud.api.MudCharacter;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class MudCharacterMapper implements RowMapper<MudCharacter> {
	public MudCharacter map(ResultSet rs, StatementContext sc) throws SQLException {
		return new MudCharacter(rs.getString("Name"),
		                        rs.getInt("Strength"),
		                        rs.getInt("Dexterity"),
		                        rs.getInt("Constitution"),
		                        rs.getInt("Wisdom"),
		                        rs.getInt("Intelligence"),
		                        rs.getInt("Charisma"));
	}
}
