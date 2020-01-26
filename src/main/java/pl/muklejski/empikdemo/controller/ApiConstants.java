package pl.muklejski.empikdemo.controller;

public class ApiConstants {

	public static final String USER_CONTROLLER_GET_ENDPOINT = "/users/{login}";

	private ApiConstants() {
		throw new IllegalStateException("Utility class");
	}
}
