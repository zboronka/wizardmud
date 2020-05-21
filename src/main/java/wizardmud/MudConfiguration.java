package wizardmud;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.server.ServerFactory;
import io.dropwizard.server.DefaultServerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.Valid;

public class MudConfiguration extends Configuration {
	@Valid
	@NotNull
	private ServerFactory server = new DefaultServerFactory();

	@Valid
	@NotNull
	private DataSourceFactory database = new DataSourceFactory();

	@Valid
	@NotNull
	@JsonProperty("firebase")
	private FirebaseOptionsFactory firebaseOptions;

	public FirebaseOptionsFactory getFirebaseOptionsFactory() {
		return firebaseOptions;
	}

	public void setFirebaseOptionsFactory(FirebaseOptionsFactory firebaseOptions) {
		this.firebaseOptions = firebaseOptions;
	}

	@JsonProperty("server")
	public ServerFactory getServerFactory() {
		return server;
	}

	@JsonProperty("server")
	public void setServerFactory(ServerFactory factory) {
		this.server = factory;
	}

	@JsonProperty("database")
	public DataSourceFactory getDataSourceFactory() {
		return database;
	}

	@JsonProperty("database")
	public void setDataSourceFactory(DataSourceFactory factory) {
		this.database = factory;
	}
}
