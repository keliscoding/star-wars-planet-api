package io.github.zam0k.starwarsplanetapi.services.impl;

import io.github.zam0k.starwarsplanetapi.dtos.CreatePlanetDTO;
import io.github.zam0k.starwarsplanetapi.dtos.PlanetDTO;
import io.github.zam0k.starwarsplanetapi.dtos.PlanetResponseDTO;
import io.github.zam0k.starwarsplanetapi.dtos.SwapiResponseDTO;
import io.github.zam0k.starwarsplanetapi.exceptions.BadRequestException;
import io.github.zam0k.starwarsplanetapi.exceptions.InternalServerException;
import io.github.zam0k.starwarsplanetapi.exceptions.NotFoundException;
import io.github.zam0k.starwarsplanetapi.models.Planet;
import io.github.zam0k.starwarsplanetapi.repositories.PlanetRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.EMPTY_LIST;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class PlanetServiceImplTest {

  private CreatePlanetDTO planetDTO;
  private Planet planet;
  private PlanetResponseDTO planetResponseDTO;
  private static final Long ID = 1L;
  private static final String NAME = "Planet Name";
  private static final String CLIMATE = "Planet Name";
  private static final String TERRAIN = "Planet Name";
  private static final Integer APPARITIONS = 5;

  @InjectMocks private PlanetServiceImpl service;

  @Mock private PlanetRepository repository;

  @Mock private RestTemplate restTemplate;

  @BeforeEach
  void setUp() {
    planetDTO = new CreatePlanetDTO(NAME, CLIMATE, TERRAIN);
    planet = new Planet(ID, NAME, CLIMATE, TERRAIN, APPARITIONS);
    planetResponseDTO = new PlanetResponseDTO(NAME, List.of("1", "2", "3", "4", "5"));
  }

  @AfterEach
  void tearDown() {}

  @Test
  void whenCreateThenReturnSuccess() {
    when(repository.findByNameIgnoreCase(anyString())).thenReturn(Optional.empty());
    when(restTemplate.getForObject(anyString(), any()))
        .thenReturn(new SwapiResponseDTO(List.of(planetResponseDTO)));
    when(repository.save(any(Planet.class))).thenReturn(planet);

    PlanetDTO response = service.create(planetDTO);

    assertAll(
        () -> assertEquals(ID, response.getId()),
        () -> assertEquals(NAME, response.getName()),
        () -> assertEquals(TERRAIN, response.getTerrain()),
        () -> assertEquals(CLIMATE, response.getClimate()),
        () -> assertEquals(APPARITIONS, response.getNumberOfApparitions()));
  }

  @Test
  void whenCreateThenReturnPlanetAlreadyRegisteredException() {
    when(repository.findByNameIgnoreCase(anyString())).thenReturn(Optional.of(planet));
    try {
      service.create(planetDTO);
    } catch (Exception ex) {
      assertAll(
              () -> assertEquals(BadRequestException.class, ex.getClass()),
              () -> assertEquals("planet already registered", ex.getMessage()));
    }
  }

  @Test
  void whenCreateThenReturnInternalServerErrorException() {
    when(repository.findByNameIgnoreCase(anyString())).thenReturn(Optional.empty());
    when(restTemplate.getForObject(anyString(), any()))
            .thenReturn(new RuntimeException());

    try {
      service.create(planetDTO);
    } catch (Exception ex) {
      assertAll(
              () -> assertEquals(InternalServerException.class, ex.getClass()),
              () -> assertEquals("star wars api is unavailable, try again later", ex.getMessage()));
    }
  }

  @Test
  void whenCreateThenReturnPlanetDoesNotExistInLoreException() {
    when(repository.findByNameIgnoreCase(anyString())).thenReturn(Optional.empty());
    when(restTemplate.getForObject(anyString(), any()))
        .thenReturn(new SwapiResponseDTO(EMPTY_LIST));

    try {
      service.create(planetDTO);
    } catch (Exception ex) {
      assertAll(
              () -> assertEquals(BadRequestException.class, ex.getClass()),
              () -> assertEquals("planet must exist in Star Wars", ex.getMessage()));
    }
  }

  @Test
  void whenCreateThenReturnPlanetWithWrongNameException() {
    when(repository.findByNameIgnoreCase(anyString())).thenReturn(Optional.empty());

    planetResponseDTO.setName("NAME COMPLETE");

    when(restTemplate.getForObject(anyString(), any()))
            .thenReturn(new SwapiResponseDTO(List.of(planetResponseDTO)));

    try {
      service.create(planetDTO);
    } catch (Exception ex) {
      assertAll(
              () -> assertEquals(BadRequestException.class, ex.getClass()),
              () -> assertEquals("planet name does not exist in Star Wars", ex.getMessage()));
    }
  }

  @Test
  void whenFindAllThenReturnSuccess() {
    when(repository.findAll()).thenReturn(List.of(planet));

    List<PlanetDTO> response = service.findAll();

    assertAll(
        () -> assertNotNull(response),
        () -> assertEquals(1, response.size()),
        () -> assertEquals(ID, response.get(0).getId()),
        () -> assertEquals(NAME, response.get(0).getName()),
        () -> assertEquals(TERRAIN, response.get(0).getTerrain()),
        () -> assertEquals(CLIMATE, response.get(0).getClimate()),
        () -> assertEquals(APPARITIONS, response.get(0).getNumberOfApparitions()));
  }

  @Test
  void whenFindPlanetsByNameThenReturnSuccess() {
    when(repository.findByNameContainingIgnoreCase(anyString())).thenReturn(List.of(planet));

    List<PlanetDTO> response = service.findPlanetsByName(NAME);

    assertAll(
        () -> assertNotNull(response),
        () -> assertEquals(1, response.size()),
        () -> assertEquals(ID, response.get(0).getId()),
        () -> assertEquals(NAME, response.get(0).getName()),
        () -> assertEquals(TERRAIN, response.get(0).getTerrain()),
        () -> assertEquals(CLIMATE, response.get(0).getClimate()),
        () -> assertEquals(APPARITIONS, response.get(0).getNumberOfApparitions()));
  }

  @Test
  void whenFindByIdThenReturnSuccess() {
    when(repository.findById(anyLong())).thenReturn(Optional.of(planet));

    PlanetDTO response = service.findById(ID);

    assertAll(
        () -> assertEquals(ID, response.getId()),
        () -> assertEquals(NAME, response.getName()),
        () -> assertEquals(TERRAIN, response.getTerrain()),
        () -> assertEquals(CLIMATE, response.getClimate()),
        () -> assertEquals(APPARITIONS, response.getNumberOfApparitions()));
  }

  @Test
  void whenFindByIdThenReturnNotFoundException() {
    try {
      service.findById(ID);
    } catch (Exception ex) {
      assertAll(
          () -> assertEquals(NotFoundException.class, ex.getClass()),
          () -> assertEquals("planet cannot be found", ex.getMessage()));
    }
  }

  @Test
  void whenDeleteReturnSuccess() {
    when(repository.findById(anyLong())).thenReturn(Optional.of(planet));

    service.delete(ID);
    verify(repository, times(1)).delete(any());
  }

  @Test
  void whenDeleteReturnNotFoundException() {
    try {
      service.delete(ID);
    } catch (Exception ex) {
      assertAll(
          () -> assertEquals(NotFoundException.class, ex.getClass()),
          () -> assertEquals("planet cannot be found", ex.getMessage()));
    }
  }
}
