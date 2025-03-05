package pl.filiphagno.spring6reactive.service;

import org.springframework.http.ResponseEntity;
import pl.filiphagno.spring6reactive.model.BeerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BeerService {

    Flux<BeerDTO> listBeers();

    Mono<BeerDTO> findBeerById(Integer beerId);

    Mono<BeerDTO> saveBeer(BeerDTO newBeer);

    Mono<BeerDTO> updateBeer(Integer beerId, BeerDTO newBeer);

    Mono<Void> deleteBeerById(Integer beerId);
}
