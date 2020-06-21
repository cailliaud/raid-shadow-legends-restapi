package fr.cailliaud.rsl.restapi.domain;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * A Champion.
 */
@Getter
@Setter
@Document
public class Champion  {

    @Id
    private ObjectId id;

    @NotNull
    @Size(min = 1, max = 50)
    @Indexed
    private String name;

    @NotNull
    @Size(min = 1, max = 50)
    private String faction;

    @NotNull
    @Size(min = 1, max = 50)
    private String rarity;

    @NotNull
    @Size(min = 1, max = 50)
    private String type;

    @NotNull
    @Field("stats")
    private ChampionStats stats;

    private Set<Skill> skills;

    @NotNull
    private String aura;



}
