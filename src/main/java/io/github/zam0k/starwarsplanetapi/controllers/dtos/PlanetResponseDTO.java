package io.github.zam0k.starwarsplanetapi.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanetResponseDTO {
    private String name;
    private List<String> films;
}
