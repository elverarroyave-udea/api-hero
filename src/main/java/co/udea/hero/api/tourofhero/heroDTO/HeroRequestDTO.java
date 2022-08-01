package co.udea.hero.api.tourofhero.heroDTO;

import co.udea.hero.api.tourofhero.model.Hero;
import lombok.*;

@Data
@Builder(toBuilder = true)
@Generated
@AllArgsConstructor
@NoArgsConstructor
public class HeroRequestDTO {
    private String name;

    public static Hero toHeroModel(HeroRequestDTO heroRequestDTO){
        return Hero.builder()
                .name(heroRequestDTO.name)
                .build();
    }
}
