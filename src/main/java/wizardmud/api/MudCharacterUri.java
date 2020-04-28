package wizardmud.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class MudCharacterUri {
	private String name;
	private String uri;

	public MudCharacterUri(String name, String uri) {
		this.name = name;
		this.uri = uri;
	}

	@JsonProperty
	public String name() {
		return name;
	}

	@JsonProperty
	public String uri() {
		return uri;
	}

	public void uri(String uri) {
		this.uri = uri;
	}
}
