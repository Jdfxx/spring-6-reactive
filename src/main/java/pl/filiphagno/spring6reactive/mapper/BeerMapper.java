package pl.filiphagno.spring6reactive.mapper;

import org.mapstruct.Mapper;
import pl.filiphagno.spring6reactive.entity.Beer;
import pl.filiphagno.spring6reactive.model.BeerDTO;

@Mapper(componentModel = "spring")
public interface BeerMapper {
    public Beer beerDTOToBeer(BeerDTO beerDTO);
    public BeerDTO beerToBeerDTO(Beer beer);
}
