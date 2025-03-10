package pl.filiphagno.spring6reactive.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.filiphagno.spring6reactive.mapper.BeerMapper;
import pl.filiphagno.spring6reactive.model.BeerDTO;
import pl.filiphagno.spring6reactive.repository.BeerRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public Flux<BeerDTO> listBeers() {
        return beerRepository.findAll()
                .map(beerMapper::beerToBeerDTO);
    }

    @Override
    public Mono<BeerDTO> findBeerById(Integer beerId) {
        return beerRepository.findById(beerId).map(beerMapper::beerToBeerDTO);
    }

    @Override
    public Mono<BeerDTO> saveBeer(BeerDTO newBeer) {
       return beerRepository.save(beerMapper.beerDTOToBeer(newBeer)).map(beerMapper::beerToBeerDTO);
    }

    @Override
    public Mono<BeerDTO> updateBeer(Integer beerId, BeerDTO newBeer) {
        return beerRepository.findById(beerId)
                .map(foundBeer -> {
                    foundBeer.setBeerName(newBeer.getBeerName());
                    foundBeer.setBeerStyle(newBeer.getBeerStyle());
                    foundBeer.setPrice(newBeer.getPrice());
                    foundBeer.setUpc(newBeer.getUpc());
                    foundBeer.setQuantityOnHand(newBeer.getQuantityOnHand());
                    return foundBeer;
                })
                .flatMap(beerRepository::save)
                .map(beerMapper::beerToBeerDTO);
    }

    @Override
    public Mono<Void> deleteBeerById(Integer beerId) {
        return beerRepository.deleteById(beerId);
    }
}
