package wizardmud.auth;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;

import com.google.firebase.auth.FirebaseAuth;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

public class FirebaseOAuthAuthenticator implements Authenticator<String, MudUser> {
	MudUser authUser;

	@Override
	public Optional<MudUser> authenticate(String credentials) throws AuthenticationException {
		authUser = null;
		final CountDownLatch done = new CountDownLatch(1);

		FirebaseAuth.getInstance().verifyIdToken(credentials)
		    .addOnSuccessListener(firebaseToken -> {
		    	authUser = new MudUser(firebaseToken.getEmail());
		    	done.countDown();
			})
		    .addOnFailureListener(e -> {
		    	done.countDown();
			});
		try {
			done.await();
		} catch (InterruptedException e) {
			throw new AuthenticationException(e.getMessage(), e);
		}

		return Optional.ofNullable(authUser);
	}
}
