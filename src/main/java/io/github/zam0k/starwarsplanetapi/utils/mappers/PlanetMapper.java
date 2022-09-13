package io.github.zam0k.starwarsplanetapi.utils.mappers;

import io.github.zam0k.starwarsplanetapi.controllers.dtos.PlanetDTO;
import io.github.zam0k.starwarsplanetapi.models.Planet;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PlanetMapper {
  PlanetMapper PLANET_MAPPER = Mappers.getMapper(PlanetMapper.class);

  PlanetDTO planetToPlanetDto(Planet planet);

  Planet planetDtoToPlanet(PlanetDTO planet);

  List<PlanetDTO> planetListToPlanetDtoList(List<Planet> planets);
}
