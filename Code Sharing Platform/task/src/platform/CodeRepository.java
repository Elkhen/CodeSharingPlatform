package platform;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CodeRepository extends CrudRepository<Code, Long> {
    Optional<Code> findByUuid(String UUID);
}
