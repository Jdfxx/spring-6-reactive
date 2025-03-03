package pl.filiphagno.spring6reactive.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pl.filiphagno.spring6reactive.entity.Beer;

public interface BeerRepository extends ReactiveCrudRepository<Beer, Integer> {
}
