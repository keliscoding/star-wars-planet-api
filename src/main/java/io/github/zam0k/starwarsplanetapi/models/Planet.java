package io.github.zam0k.starwarsplanetapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Planet {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 80, nullable = false, unique = true)
  private String name;

  @Column(length = 30, nullable = false)
  private String climate;

  @Column(length = 30, nullable = false)
  private String terrain;

  @Column(name = "number_of_apparitions", nullable = false)
  private Integer numberOfApparitions;
}
