package pl.muklejski.empikdemo;

import java.time.ZonedDateTime;
import pl.muklejski.empikdemo.model.User;
import pl.muklejski.empikdemo.model.UserDto;

public class TestData {

	private static final String LOGIN = "muklej";
	private static final String NAME = "Michal";
	private static final String USER = "User";
	private static final String DATE = "2020-01-25T06:07:08Z[UTC]";


	protected UserDto getValidUserDto() {
		return getValidUserDtoBuilderPartiallyFilled()
			.publicRepos(11L)
			.followers(5L)
			.build();
	}

	protected UserDto getValidUserDtoWithZeroFollowers() {
		return getValidUserDtoBuilderPartiallyFilled()
			.publicRepos(10L)
			.followers(0L)
			.build();
	}

	private UserDto.UserDtoBuilder getValidUserDtoBuilderPartiallyFilled() {
		return UserDto.builder()
			.id(1523456L)
			.login(LOGIN)
			.name(NAME)
			.type(USER)
			.createdAt(ZonedDateTime.parse(DATE));
	}

	protected User getValidUser() {
		return getValidUserBuilderFilledPartially()
			.calculations(13L)
			.build();
	}

	protected User getValidUserZeroCalculations() {
		return getValidUserBuilderFilledPartially()
			.calculations(0L)
			.build();
	}

	private User.UserBuilder getValidUserBuilderFilledPartially() {
		return User.builder()
			.id(1523456L)
			.login(LOGIN)
			.name(NAME)
			.type(USER)
			.createdAt(ZonedDateTime.parse(DATE));
	}
}