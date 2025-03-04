package pl.filiphagno.spring6reactive.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.filiphagno.spring6reactive.model.BeerDTO;
import pl.filiphagno.spring6reactive.service.BeerService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class BeerController {

    public static final String BEER_PATH = "api/v2/beer";
    public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";

    private final BeerService beerService;

    @GetMapping(BEER_PATH)
    Flux<BeerDTO> listBeers() {
        return beerService.listBeers();
    }

    @GetMapping(BEER_PATH_ID)
    Mono<BeerDTO> getBeerById(@PathVariable("beerId") Integer beerId) {
        return beerService.findBeerById(beerId);
    }

    @PostMapping(BEER_PATH)
    Mono<ResponseEntity<String>> getBeerById(@RequestBody BeerDTO newBeer) {

        return beerService.saveBeer(newBeer).map(
                beerDTO ->
                        ResponseEntity.created(URI.create("http://localhost:8080/" +
                                BEER_PATH + "/" + beerDTO.getId())).build()
        );

    }

}
