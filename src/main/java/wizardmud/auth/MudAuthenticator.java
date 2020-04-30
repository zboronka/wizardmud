package wizardmud.auth;

import java.util.Optional;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

public class MudAuthenticator implements Authenticator<BasicCredentials, MudUser> {
	@Override
	public Optional<MudUser> authenticate(BasicCredentials credentials) throws AuthenticationException {
		if("password".equals(credentials.getPassword()))
			return Optional.of(new MudUser(credentials.getUsername()));
		return Optional.empty();
	}
}
