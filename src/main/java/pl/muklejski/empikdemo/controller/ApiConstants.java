package pl.muklejski.empikdemo.controller;

public class ApiConstants {

	private ApiConstants() {
		throw new IllegalStateException("Utility class");
	}

	public static final String USER_CONTROLLER_GET_ENDPOINT = "/users/{login}";
}
