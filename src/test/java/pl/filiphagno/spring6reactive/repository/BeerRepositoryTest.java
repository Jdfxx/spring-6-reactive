package pl.filiphagno.spring6reactive.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import pl.filiphagno.spring6reactive.config.DatabaseConfig;
import pl.filiphagno.spring6reactive.entity.Beer;

import java.math.BigDecimal;


@DataR2dbcTest
@Import(DatabaseConfig.class)
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveNewBeer() {
        beerRepository.save(getNewBeer()).subscribe(System.out::println);
    }

    Beer getNewBeer() {
        return Beer.builder()
                .beerName("Beer")
                .beerStyle("Ale")
                .upc("12345")
                .price(BigDecimal.TEN)
                .quantityOnHand(20)
                .build();
    }

}