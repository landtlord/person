package be.hogent.person.service;

import be.hogent.person.business.PersonEntity;
import be.hogent.person.business.PersonRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@AllArgsConstructor
public class personService {
    @Autowired
    private PersonMapper mapper;

    @Autowired
    private PersonRepo repo;

    @GetMapping("/person/{id}")
    public Person getById(String id) {
        Long longId = Long.parseLong(id);
        Optional<PersonEntity> personEntity = repo.findById(longId);
        return personEntity.map(entity -> mapper.toDTO(entity)).orElse(null);
    }

    @PostMapping("/task")
    public Person save(@RequestBody Person task) {
        PersonEntity personEntity = mapper.toEntity(task);
        PersonEntity savedEntity = repo.save(personEntity);
        return mapper.toDTO(savedEntity);
    }

    @GetMapping("/task")
    public List<Person> getAll() {
        Iterable<PersonEntity> all = repo.findAll();
        List<PersonEntity> collect = StreamSupport.stream(all.spliterator(), false)
                .collect(Collectors.toList());
        return mapper.toDTO(collect);
    }
}
