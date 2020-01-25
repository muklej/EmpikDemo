package pl.muklejski.empikdemo;

import java.time.ZonedDateTime;
import pl.muklejski.empikdemo.model.User;
import pl.muklejski.empikdemo.model.UserDto;

public class TestData {

	protected UserDto getValidUserDto() {
		return 	UserDto.builder()
			.id(1523456L)
			.login("muklej")
			.name("Michal")
			.type("user")
			.publicRepos(10L)
			.followers(5L)
			.createdAt(ZonedDateTime.parse("2020-01-25T06:07:08Z[UTC]"))
			.build();
	}

	protected UserDto getValidUserDtoWithZeroFollowers() {
		return 	UserDto.builder()
			.id(1523456L)
			.login("muklej")
			.name("Michal")
			.type("user")
			.publicRepos(10L)
			.followers(0L)
			.createdAt(ZonedDateTime.parse("2020-01-25T06:07:08Z[UTC]"))
			.build();
	}

	protected User getValidUser() {
		return 	User.builder()
			.id(1523456L)
			.login("muklej")
			.name("Michal")
			.type("user")
			.createdAt(ZonedDateTime.parse("2020-01-25T06:07:08Z[UTC]"))
			.calculations(12L)
			.build();
	}

	protected User getValidUserZeroCallculations() {
		return 	User.builder()
			.id(1523456L)
			.login("muklej")
			.name("Michal")
			.type("user")
			.createdAt(ZonedDateTime.parse("2020-01-25T06:07:08Z[UTC]"))
			.calculations(0L)
			.build();
	}

}
