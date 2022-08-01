package co.udea.hero.api.tourofhero.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder(toBuilder = true)
@Generated
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "HEROES")
public class Hero {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String name;
}
