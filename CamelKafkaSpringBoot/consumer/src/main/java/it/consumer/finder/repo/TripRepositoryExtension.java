package it.consumer.finder.repo;

import it.consumer.finder.repo.entities.TripEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TripRepositoryExtension {
    @Query("select t FROM TripEntity t WHERE lastdata >= :treminutifa AND processed = 0")
    List<TripEntity> findAllWithLastDataAfter(@Param("treminutifa") Date treminutifa);
    TripEntity findByIdDevice(String IdDevice);

}
