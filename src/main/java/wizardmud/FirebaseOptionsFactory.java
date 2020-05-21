package wizardmud;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;

public class FirebaseOptionsFactory {
	@NotBlank
	@JsonProperty
	private String databaseUrl;

	@NotBlank
	@JsonProperty
	private String credentialPath;

	public String databaseUrl() {
		return databaseUrl;
	}

	public String credentialPath() {
		return credentialPath;
	}

	public void credentialPath(String credentialPath) {
		this.credentialPath = credentialPath;
	}

	public FirebaseOptions build() throws FileNotFoundException {
		FileInputStream serviceAccount = new FileInputStream(credentialPath);
		return new FirebaseOptions.Builder()
		    .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
		    .setDatabaseUrl(databaseUrl)
		    .build();
	}
}
