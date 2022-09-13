package io.github.zam0k.starwarsplanetapi.utils.mappers;

import io.github.zam0k.starwarsplanetapi.controllers.dtos.CreatePlanetDTO;
import io.github.zam0k.starwarsplanetapi.models.Planet;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreatePlanetMapper {

  CreatePlanetMapper CREATE_PLANET_MAPPER = Mappers.getMapper(CreatePlanetMapper.class);

  Planet createPlanetDtoToPlanet(CreatePlanetDTO planet);
}
