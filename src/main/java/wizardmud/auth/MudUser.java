package wizardmud.auth;

import java.security.Principal;

public class MudUser implements Principal {
	private String username;

	public MudUser(String username) {
		this.username = username;
	}

	public String getName() {
		return username;
	}
}
