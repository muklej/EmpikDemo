package pl.muklejski.empikdemo.service.constants;

public class GitHubConstants {

	public static final String USERS_ENDPOINT = "https://api.github.com/users/{login}";


	private GitHubConstants() {
		throw new IllegalStateException("Utility class");
	}
}
