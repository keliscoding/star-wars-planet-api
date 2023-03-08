package io.github.zam0k.starwarsplanetapi.services.impl;

import io.github.zam0k.starwarsplanetapi.dtos.CreatePlanetDTO;
import io.github.zam0k.starwarsplanetapi.dtos.PlanetDTO;
import io.github.zam0k.starwarsplanetapi.dtos.SwapiResponseDTO;
import io.github.zam0k.starwarsplanetapi.exceptions.BadRequestException;
import io.github.zam0k.starwarsplanetapi.exceptions.InternalServerException;
import io.github.zam0k.starwarsplanetapi.exceptions.NotFoundException;
import io.github.zam0k.starwarsplanetapi.models.Planet;
import io.github.zam0k.starwarsplanetapi.repositories.PlanetRepository;
import io.github.zam0k.starwarsplanetapi.services.PlanetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static io.github.zam0k.starwarsplanetapi.utils.mappers.CreatePlanetMapper.CREATE_PLANET_MAPPER;
import static io.github.zam0k.starwarsplanetapi.utils.mappers.PlanetMapper.PLANET_MAPPER;

@Service
@RequiredArgsConstructor
public class PlanetServiceImpl implements PlanetService {

  private final PlanetRepository repository;
  private final RestTemplate restTemplate;
  private static final String SWAPI_URL = "https://swapi.dev/api/planets";

  @Override
  @Transactional
  public PlanetDTO create(CreatePlanetDTO planetDto) {

    boolean isPlanetAlreadyInDatabase = findByName(planetDto.getName()).isPresent();
    if (isPlanetAlreadyInDatabase) throw new BadRequestException("planet already registered");

    String url = SWAPI_URL + "?search=" + planetDto.getName();
    SwapiResponseDTO response;

    try {
      response = restTemplate.getForObject(url, SwapiResponseDTO.class);
      if (response == null) throw new InternalServerException("response is null");

    } catch (Exception ex) {
      throw new InternalServerException("star wars api is unavailable, try again later");
    }

    boolean isResponseEmpty = response.getResults() == null || response.getResults().isEmpty();

    if (isResponseEmpty) throw new BadRequestException("planet must exist in Star Wars");

    String planetNameResponse = response.getResults().get(0).getName();
    boolean doesPlanetNameMatchPlanetNameResponse =
        planetNameResponse.equalsIgnoreCase(planetDto.getName());

    if (!doesPlanetNameMatchPlanetNameResponse)
      throw new BadRequestException("planet name does not exist in Star Wars");

    List<String> films = response.getResults().get(0).getFilms();

    Planet planet = CREATE_PLANET_MAPPER.createPlanetDtoToPlanet(planetDto);

    planet.setNumberOfApparitions(films.size());

    return PLANET_MAPPER.planetToPlanetDto(repository.save(planet));
  }

  @Override
  public List<PlanetDTO> findAll() {

    return PLANET_MAPPER.planetListToPlanetDtoList(repository.findAll());
  }

  @Override
  public List<PlanetDTO> findPlanetsByName(String name) {

    return PLANET_MAPPER.planetListToPlanetDtoList(repository.findByNameContainingIgnoreCase(name));
  }

  @Override
  public PlanetDTO findById(Long id) {

    return PLANET_MAPPER.planetToPlanetDto(findPlanetById(id));
  }

  @Override
  public void delete(Long id) {
    repository.delete(findPlanetById(id));
  }

  private Planet findPlanetById(Long id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("planet cannot be found"));
  }

  private Optional<Planet> findByName(String name) {
    return repository.findByNameIgnoreCase(name);
  }
}
