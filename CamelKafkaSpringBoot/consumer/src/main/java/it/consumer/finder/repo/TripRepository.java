package it.consumer.finder.repo;

import it.consumer.finder.repo.entities.TripEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface TripRepository extends CrudRepository<TripEntity, Integer>, TripRepositoryExtension {

}
