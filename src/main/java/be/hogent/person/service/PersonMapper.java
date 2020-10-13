package be.hogent.person.service;

import be.hogent.person.business.PersonEntity;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    Person toDTO(PersonEntity personEntity);

    PersonEntity toEntity(Person person);

    default List<Person> toDTO(List<PersonEntity> personEntities){
        return personEntities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
