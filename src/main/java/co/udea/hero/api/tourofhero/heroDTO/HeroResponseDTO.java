package co.udea.hero.api.tourofhero.heroDTO;

import co.udea.hero.api.tourofhero.model.Hero;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder(toBuilder = true)
@Generated
@AllArgsConstructor
@NoArgsConstructor
public class HeroResponseDTO {

    private Integer id;

    private String name;

    public static HeroResponseDTO toHeroDTO(Hero hero){
        return HeroResponseDTO.builder()
                .id(hero.getId())
                .name(hero.getName())
                .build();
    }

    public static List<HeroResponseDTO> toHeroesDTO(List<Hero> heroes){
        List<HeroResponseDTO>  heroesDTO = new ArrayList<>();
        heroes.forEach(hero -> heroesDTO.add(toHeroDTO(hero)));
        return heroesDTO;
    }
}
