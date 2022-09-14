package io.github.zam0k.starwarsplanetapi.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlanetDTO {
    @NotBlank(message = "name cannot be null")
    private String name;
    @NotBlank(message = "climate cannot be null")
    private String climate;
    @NotBlank(message = "terrain cannot be null")
    private String terrain;
}
