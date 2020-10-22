package it.kconsumer.servicelayer.repo;

import it.kconsumer.servicelayer.repo.entities.FooEntity;

public interface FooRepositoryExtension {
    FooEntity findByFooId(String FooId);

}
