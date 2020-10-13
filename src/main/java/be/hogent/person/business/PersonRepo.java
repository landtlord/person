package be.hogent.person.business;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepo extends CrudRepository<PersonEntity, Long> {
}
