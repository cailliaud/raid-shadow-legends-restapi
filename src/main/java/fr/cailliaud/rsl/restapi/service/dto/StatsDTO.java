package fr.cailliaud.rsl.restapi.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class StatsDTO {

    @NotNull
    @Min(value = 0)
    @JsonProperty("attaque")
    private int attack;

    @NotNull
    @Min(value = 0)
    @JsonProperty("defense")
    private int defense;

    @NotNull
    @Min(value = 0)
    @JsonProperty("vitesse")
    private int speed;

    @NotNull
    @Min(value = 0)
    @Max(value = 100)
    @JsonProperty("taux_critique")
    private int criticalRate;

    @NotNull
    @Min(value = 0)
    @JsonProperty("degat_critique")
    @Field("critical_damage")
    private int criticalDamage;

    @NotNull
    @Min(value = 0)
    @JsonProperty("resistance")
    private int resistance;

    @NotNull
    @Min(value = 0)
    @JsonProperty("precision")
    private int accuracy;

    @NotNull
    @Min(value = 0)
    @JsonProperty("points_de_vie")
    private int healthPoints;

}
