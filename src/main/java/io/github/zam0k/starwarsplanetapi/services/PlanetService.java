package io.github.zam0k.starwarsplanetapi.services;

import io.github.zam0k.starwarsplanetapi.controllers.dtos.CreatePlanetDTO;
import io.github.zam0k.starwarsplanetapi.controllers.dtos.PlanetDTO;

import java.util.List;

public interface PlanetService {
  PlanetDTO create(CreatePlanetDTO planet);

  List<PlanetDTO> findAll();

  List<PlanetDTO> findPlanetsByName(String name);

  PlanetDTO findById(Long id);

  void delete(Long id);
}
