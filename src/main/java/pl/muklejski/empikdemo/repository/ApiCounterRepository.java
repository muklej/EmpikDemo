package pl.muklejski.empikdemo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.muklejski.empikdemo.model.ApiCounterEntity;

@Repository
public interface ApiCounterRepository extends CrudRepository<ApiCounterEntity, String> {

	default void incrementApiCounterOrCreate(String login) {
		synchronized (this) {
			ApiCounterEntity apiCounterEntity = this.findById(login)
				.orElse(new ApiCounterEntity(login));
			apiCounterEntity.getRequestCount().getAndIncrement();
			this.save(apiCounterEntity);
		}
	}

}
