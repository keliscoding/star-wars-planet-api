package io.github.zam0k.starwarsplanetapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlanetDTO {
  @NotBlank(message = "name cannot be null")
  @Size(min = 5, max = 80, message = "name must be between 5 and 80 characters")
  private String name;

  @NotBlank(message = "climate cannot be null")
  @Size(min = 3, max = 30, message = "climate must be between 3 and 30 characters")
  private String climate;

  @NotBlank(message = "terrain cannot be null")
  @Size(min = 3, max = 30, message = "terrain must be between 3 and 30 characters")
  private String terrain;
}
