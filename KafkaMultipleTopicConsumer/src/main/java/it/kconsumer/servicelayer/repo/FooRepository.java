package it.kconsumer.servicelayer.repo;

import it.kconsumer.servicelayer.repo.entities.FooEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface FooRepository extends CrudRepository<FooEntity, String>, FooRepositoryExtension {
}
