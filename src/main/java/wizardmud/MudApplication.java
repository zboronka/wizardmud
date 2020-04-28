package wizardmud;

import wizardmud.resources.CharactersResource;
import wizardmud.health.TemplateHealthCheck;

import io.dropwizard.Application;
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
	}
}
