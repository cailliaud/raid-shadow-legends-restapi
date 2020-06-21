package fr.cailliaud.rsl.restapi.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class ChampionStats {

    @NotNull
    @Min(value = 0)
    private int attack;

    @NotNull
    @Min(value = 0)
    private int defense;

    @NotNull
    @Min(value = 0)
    private int speed;

    @NotNull
    @Min(value = 0)
    @Field("critical_rate")
    private int criticalRate;

    @NotNull
    @Min(value = 0)
    @Field("critical_damage")
    private int criticalDamage;

    @NotNull
    @Min(value = 0)
    private int resistance;

    @NotNull
    @Min(value = 0)
    private int accuracy;
}
