package io.github.zam0k.starwarsplanetapi.controllers;

import io.github.zam0k.starwarsplanetapi.dtos.CreatePlanetDTO;
import io.github.zam0k.starwarsplanetapi.dtos.PlanetDTO;
import io.github.zam0k.starwarsplanetapi.services.PlanetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/planets/v1")
@RequiredArgsConstructor
public class PlanetController {

  private final PlanetService service;

  @PostMapping
  public ResponseEntity<PlanetDTO> create(@Valid @RequestBody CreatePlanetDTO planet) {
    PlanetDTO planetDTO = service.create(planet);
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(planetDTO.getId())
            .toUri();
    return ResponseEntity.created(uri).build();
  }

  @GetMapping
  public ResponseEntity<List<PlanetDTO>> findPlanets(
      @RequestParam(value = "name", required = false, defaultValue = "") String name) {
    if (name.isBlank()) {
      List<PlanetDTO> planets = service.findAll();
      if (planets.isEmpty()) return ResponseEntity.noContent().build();
      return ResponseEntity.ok(planets);
    }

    return ResponseEntity.ok(service.findPlanetsByName(name));
  }

  @GetMapping("/{id}")
  public ResponseEntity<PlanetDTO> findById(@PathVariable("id") Long id) {
    PlanetDTO planet = service.findById(id);
    return ResponseEntity.ok(planet);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<PlanetDTO> delete(@PathVariable("id") Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
