package pl.muklejski.empikdemo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class UserDto {

	private long id;
	private String login;
	private String name;
	private String type;

	@JsonAlias("avatar_url")
	private String avatarUrl;

	@JsonAlias("public_repos")
	private long publicRepos;
	private long followers;

	@JsonAlias("created_at")
	private ZonedDateTime createdAt;
}
