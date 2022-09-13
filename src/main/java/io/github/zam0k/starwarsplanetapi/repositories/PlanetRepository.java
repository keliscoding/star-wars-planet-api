package io.github.zam0k.starwarsplanetapi.repositories;

import io.github.zam0k.starwarsplanetapi.models.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long> {
    List<Planet> findByNameContainingIgnoreCase(String name);
    Optional<Planet> findByNameIgnoreCase(String name);
}
