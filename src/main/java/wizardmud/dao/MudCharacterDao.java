package wizardmud.dao;

import wizardmud.api.MudCharacter;
import wizardmud.api.MudCharacterUri;

import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;

import java.util.ArrayList;

public interface MudCharacterDao {
	@SqlUpdate("INSERT INTO Characters (ID, Name, Strength, Dexterity, Constitution, Wisdom, Intelligence, Charisma) VALUES (:id, :name, :strength, :dexterity, :constitution, :wisdom, :intelligence, :charisma)")
	void insert(@Bind("id") int id, @BindMethods MudCharacter character);

	@SqlQuery("SELECT MAX(ID) FROM Characters")
	int maxId();

	@SqlQuery("SELECT ID, Name FROM Characters")
	@RegisterRowMapper(MudCharacterUriMapper.class)
	ArrayList<MudCharacterUri> getCharacters();

	@SqlQuery("SELECT * FROM Characters WHERE ID = ?")
	@RegisterRowMapper(MudCharacterMapper.class)
	MudCharacter getCharacter(int ID);
}
