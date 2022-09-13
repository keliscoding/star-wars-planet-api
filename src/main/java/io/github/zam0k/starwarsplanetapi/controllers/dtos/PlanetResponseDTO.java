package io.github.zam0k.starwarsplanetapi.controllers.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PlanetResponseDTO {
    private String name;
    private List<String> films;
}
