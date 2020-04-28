package wizardmud.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class MudCharacter {
	private String name;
	private int strength;
	private int dexterity;
	private int constitution;
	private int wisdom;
	private int intelligence;
	private int charisma;

	public MudCharacter() {
		super();
	}

	public MudCharacter(String name, int strength, int dexterity, int constitution, int wisdom, int intelligence, int charisma) {
		this.name = name;
		this.strength = strength;
		this.dexterity = dexterity;
		this.constitution = constitution;
		this.wisdom = wisdom;
		this.intelligence = intelligence;
		this.charisma = charisma;
	}

	@JsonProperty
	public String name() {
		return name;
	}

	@JsonProperty
	public int strength() {
		return strength;
	}

	@JsonProperty
	public int dexterity() {
		return dexterity;
	}

	@JsonProperty
	public int constitution() {
		return constitution;
	}

	@JsonProperty
	public int wisdom() {
		return wisdom;
	}

	@JsonProperty
	public int intelligence() {
		return intelligence;
	}

	@JsonProperty
	public int charisma() {
		return charisma;
	}
}
