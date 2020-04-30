package wizardmud;

import wizardmud.auth.MudAuthenticator;
import wizardmud.auth.MudUser;
import wizardmud.resources.CharactersResource;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import io.dropwizard.jdbi3.JdbiFactory;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class MudApplication extends Application<MudConfiguration> {
	public static void main(String[] args) throws Exception {
		new MudApplication().run(args);
	}

	@Override
	public String getName() {
		return "mud";
	}

	@Override
	public void initialize(Bootstrap<MudConfiguration> bootstrap) {}

	@Override
	public void run(MudConfiguration configuration, Environment environment) {
		final JdbiFactory factory = new JdbiFactory();
		final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mud");
		jdbi.installPlugin(new SqlObjectPlugin());
		final CharactersResource resource = new CharactersResource(jdbi);
		environment.jersey().register(resource);
		environment.jersey().register(new AuthDynamicFeature(
		            new BasicCredentialAuthFilter.Builder<MudUser>()
		            .setAuthenticator(new MudAuthenticator())
		            .setRealm("Character Creation")
		            .buildAuthFilter()));
		environment.jersey().register(new AuthValueFactoryProvider.Binder<>(MudUser.class));
	}
}
