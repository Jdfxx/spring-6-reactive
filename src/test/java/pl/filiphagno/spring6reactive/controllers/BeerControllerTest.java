package pl.filiphagno.spring6reactive.controllers;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import pl.filiphagno.spring6reactive.entity.Beer;
import pl.filiphagno.spring6reactive.model.BeerDTO;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureWebTestClient
class BeerControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @Order(1)
    void testListBeers() {
        webTestClient.get().uri(BeerController.BEER_PATH)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type", "application/json")
                .expectBody().jsonPath("$.size()").isEqualTo(3);
    }

    @Test
    @Order(2)
    void testGetBeerById() {
        webTestClient.get().uri(BeerController.BEER_PATH_ID, 1)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type", "application/json")
                .expectBody(Beer.class);
    }

    @Test
    @Order(3)
    void testCreateBeer() {

        BeerDTO beerDTO = getBeerDTO();

        webTestClient.post().uri(BeerController.BEER_PATH)
                .body(Mono.just(beerDTO), BeerDTO.class)
                .header("content-type", "application/json")
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().location("http://localhost:8080//api/v2/beer/4");
    }



    @Test
    @Order(4)
    void testUpdateBeer() {
        webTestClient.put().uri(BeerController.BEER_PATH_ID, 1)
                .body(Mono.just(getBeerDTO()), BeerDTO.class)
                .header("content-type", "application/json")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @Order(5)
    void testDeleteBeerById() {
        webTestClient.delete().uri(BeerController.BEER_PATH_ID, 1)
                .exchange()
                .expectStatus().isNoContent();
    }

    private static BeerDTO getBeerDTO() {
        BeerDTO beerDTO = BeerDTO.builder()
                .beerName("Test")
                .beerStyle("Test")
                .upc("12356")
                .price(BigDecimal.TEN)
                .quantityOnHand(6)
                .build();
        return beerDTO;
    }
}
