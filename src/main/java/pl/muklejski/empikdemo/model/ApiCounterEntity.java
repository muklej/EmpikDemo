package pl.muklejski.empikdemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "API_COUNTER")
@Data
@NoArgsConstructor
public class ApiCounterEntity {

	@Id
	@Column(name = "LOGIN")
	private String login;

	@Column(name = "REQUEST_COUNT")
	private long requestCount;

	public ApiCounterEntity(String login) {
		this.login = login;
		this.requestCount = 0L;
	}

	public void incrementRequestCount() {
		this.requestCount++;
	}
}
