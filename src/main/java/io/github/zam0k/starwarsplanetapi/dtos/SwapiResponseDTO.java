package io.github.zam0k.starwarsplanetapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SwapiResponseDTO {
    private List<PlanetResponseDTO> results;
}
