package fr.cailliaud.rsl.restapi.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

@Data
public class Skill {

    private String name;
    private String description;
    @Field("damage_stat")
    private String damageStat;
    private Set<String> upgrades;
    private int cooldown;

}
