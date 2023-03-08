package io.github.zam0k.starwarsplanetapi.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlanetDTO {
    private Long id;
    private String name;
    private String climate;
    private String terrain;
    private Integer numberOfApparitions;
}
