package pl.muklejski.empikdemo.service.constants;

public class GitHubConstants {

	private GitHubConstants() {
		throw new IllegalStateException("Utility class");
	}


	public static final String USERS_ENDPOINT = "https://api.github.com/users/{login}";
}
